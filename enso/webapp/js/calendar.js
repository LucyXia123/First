/**
 * Created by mzh on 2017-08-30.
 */
var URL_EVENT_GET = window.APP_ROOT + "/filter/getevent"; // "./data/thuimg.json";   

var URL_GET_DATE = "./data/getdate.jsp";

var CURRENTSERVERDATE = "2017-09-22";  // 服务器端的时间, 浏览器上的时间可能不准确
var LIMITDATE = "2017-08-13";          // 可以后退的最早日期
var PRELOADIMGNUM = 5;
var jdata = {};

$(function() {
    $.ajax({
        url: URL_GET_DATE,
        async: false,
        success: function(data) {
            CURRENTSERVERDATE = $.trim(data);
            console.log(CURRENTSERVERDATE);
        }
    });

    // client test
    // var date = new Date();
    // CURRENTSERVERDATE = date.getFullYear() + '-' + dulnum(date.getMonth()+1) + '-' + dulnum(date.getDate());
    var cal = null;

    $.ajax({
        type: 'GET',
        url: URL_EVENT_GET,
        dataType:"json",
        cache:false,
        data: {"userid":$("#js-userid").val()}
    }).done(function(data) {
        // isEmpty @file: common.js or: $.isEmptyObject()
        if (typeof data === "undefined" || isEmpty(data)) {
            $.getJSON("./data/thuimg.json", null, function(data) {
                cal = get_event_handler(data);
            });
        } else {
            cal = get_event_handler(data);
        }
    });

    /**
     * xhr load events handler
     * @param data
     */
    function get_event_handler(data) {
        jdata = data;
        var cal = new Calendar(jdata);
        cal.cal();
        // poster  prev/next 上一个有海报的日期
        $('#thuimgPre').click(function () {
        	var $calendarDay = $("#calendarDay");
            var $p = $calendarDay.find("a.current").parent();
            var c = 0;   // 递归次数

            // 找到之前日中有数据的列
            var getp = function () {
            	var $calendarDay = $("#calendarDay");
                // 如果是第一列则要向后退一个月
                if ($p.get(0) === $calendarDay.find("li").first().get(0)) {
                    cal.subMonth();
                    $p = $calendarDay.find("li").last();
                } else {
                    $p = $p.prev();
                }
                if (c > 30) {
                	cal.addMonth();
                    return $p;
                }
                // 每个月第一天，新图还未发布时问题
                if ($p.length === 0) {
                    cal.subMonth();
                    $p = $calendarDay.find("a:not(.none):last").parent();
                    $p = $p.prev();
                }
                // 跳过没有事件的日期
                if ($p.children("a.none").length > 0) {
                    c++;
                    getp();
                }
                return $p;
            };
            $p = getp();
            $p.find("a").trigger("click");
            getImg($("#calendarYear").text(), $("#calendarMonth").text(), $p.text());
        });

        // 下一个有海报的日期
        $('#thuimgNext').click(function () {
            var $calendarDay = $("#calendarDay");
            var $n = $calendarDay.find("a.current").parent();

            var getn = function () {
                // if ($(n)[0] === $("#calendarDay li:last")[0]) {
                var $list = $calendarDay.find("li");
                if ($n.get(0) === $list.last().get(0)) {
                    cal.addMonth();
                    $list = $calendarDay.find("li");
                    $n = $list.first();
                } else {
                    $n = $n.next();
                }
                if ($n.children("a.none").length > 0) {
                    getn();
                }
                return $n;
            };
            $n = getn();
            $n.find("a").trigger("click");
            getImg($("#calendarYear").text(), $("#calendarMonth").text(), $n.text())
        });

        // calendar month prev/next
        $('#calendarMonthpre').click(function () {
            cal.subMonth();
        });
        $('#calendarMonthnext').click(function () {
            cal.addMonth();
        });

        /**
         * click date axis to get the poster
         * @type {*|jQuery|HTMLElement}
         */
        var $calendarDay = $("#calendarDay");
        $calendarDay.on("click", "a", function () {
            if ($(this).hasClass("none")) {
                $("#thuImgTitle").empty();
                $("#thuimgWrap").css({"background": "#fff"}).find("img").attr("src", "");

                $("#track-theme").val('');
                $("#track-text").val('');
            } else {
                $("#thuimgWrap").css({"background": "url(../images/loading.gif) no-repeat center center;"});
                getImg($("#calendarYear").text(), $("#calendarMonth").text(), this.innerText);
            }
        });

        var tmpdate = new Date(CURRENTSERVERDATE);
        tmpdate = getClosedDate(tmpdate);
        getImg(tmpdate.Format("yyyy"), tmpdate.Format("MM"), tmpdate.Format("d"));

        return cal;
    }

    /**
     * 取得日期为y-m-d的海报图
     * @param y  string
     * @param m  string
     * @param d  string
     */
    function getImg(y, m, d) {
        var date = takefulldate(y, m, d);   // 2017-09-24

        var $calendarDay = $("#calendarDay");
        if (jdata[date]) {
            $calendarDay.find("a").each(function() {
                var $this = $(this);
                if ($("#calendarYear").text() == y && dulnum($("#calendarMonth").text()) == dulnum(m) && $this.text() == d) {
                    $this.addClass("current");
                } else {
                    $this.removeClass("current");
                }
            });

            var $calendarToday = $("#calendarToday");
            // if (date == takefulldate(CURRENTSERVERDATE.Format("yyyy"), CURRENTSERVERDATE.Format("MM"), CURRENTSERVERDATE.Format("dd"))) {
            if (date === CURRENTSERVERDATE) {
                $calendarToday.html("Today").addClass("today");
                $('#thuimgNext').hide();
            } else {
                $calendarToday.html(parseISO8601(date).Format('yyyy.M.d')).removeClass("today");

                if (parseInt(jdata[date].id, 10) !== 1) {
                    $('#thuimgNext').show();
                } else {
                    $('#thuimgNext').hide();
                }
                var limitdate = new Date(LIMITDATE);
                if (parseInt(y, 10) === limitdate.getFullYear() && parseInt(m, 10) === limitdate.getMonth() + 1) {
                    if ($calendarDay.find("a").not(".none").first().text() === d) {
                        $('#thuimgPre').hide();
                    } else {
                        $('#thuimgPre').show();
                    }
                } else {
                    $('#thuimgPre').show();
                }
            }

            ImgAction(date);
            preload(date);

            var o = jdata[date], title = "", content = "";
            // 加载标题
            if (o.hasOwnProperty("title")) {
                title = o.title;
            }
            $("#track-theme").val(title);
            // 加载正文
            if (o.hasOwnProperty("content")) {
                content = o.content;
            }
            $("#track-text").val(content);
        }
    }

    /**
     * 加载大图片或swf
     * @param date
     */
    function ImgAction(date) {
        var $wrap = $("#thuimgWrap");
        var $bigimg = $wrap.find(".mainImg");

        var $imgTitle = $("#thuImgTitle");

        var bimg = new Image();
        bimg.onload = function() {
            $bigimg.attr({ "src": this.src, "data-date": date });
            $bigimg.animate({ opacity: 'show', left: 0 }, 500);

            $imgTitle.text( jdata[date].title );
            $wrap.find('.thuimga').attr("href", jdata[date].url);
            /*
             if (jdata[date].hasOwnProperty("js")) {
             jdata[date].js && $.getScript(jdata[date].js);
             }
             */
        };
        $bigimg.css("left", "20px").hide();

        if (jdata[date].img) {
            bimg.src = jdata[date].img;
        }
    }

    function preload(date) {
        var preimg = [];
        var tmpdate = parseISO8601(date);
        tmpdate = getClosedDate(tmpdate);
        for (var ii = 0; ii < PRELOADIMGNUM; ii++) {
            preimg[ii] = new Image();
            if (jdata[tmpdate.Format("yyyy-MM-dd")]) {
                preimg[ii].src = jdata[tmpdate.Format("yyyy-MM-dd")].img;
            }
            tmpdate.setDate(tmpdate.getDate() - 1);
            tmpdate = getClosedDate(tmpdate);
        }
    }

    function getClosedDate(tmpdate) {

        if (jdata[takefulldate(tmpdate.Format("yyyy"), tmpdate.Format("MM"), tmpdate.Format("dd"))]) {} else {
            tmpdate.setDate(tmpdate.getDate() - 1);
            var limitdate = new Date(LIMITDATE);
            if (tmpdate <= limitdate) {
                console.log("没有更早日期了:" + limitdate);
                return tmpdate;
            }
            if (!isNaN(tmpdate)) {
                getClosedDate(tmpdate);
            }
        }

        return tmpdate;
    }
});  // end $(function() {});

function dulnum(num) {
    num = parseInt(num, 10);
    if (0 < num && num < 10) {
        return "0" + num;
    }
    return num;
}

function takefulldate(y, m, d) {
    m = parseInt(m, 10);
    d = parseInt(d, 10);
    return y + "-" + dulnum(m) + "-" + dulnum(d);
}

Date.prototype.Format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

function parseISO8601(dateStringInRange) {
    var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,
        date = new Date(NaN),
        month,
        parts = isoExp.exec(dateStringInRange);

    if (parts) {
        month = +parts[2];
        date.setFullYear(parts[1], month - 1, parts[3]);
        if (month !== date.getMonth() + 1) {
            date.setTime(NaN);
        }
    }
    return date;
}

/**
 * 构筑日期轴
 * @param data
 * @constructor
 */
function Calendar(data) {
    var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    var today = new (function() {
        var date = new Date(CURRENTSERVERDATE);
        this.year = date.getFullYear();
        this.month = date.getMonth() + 1;
        this.day = date.getDate();
    })();
    var year = today.year;
    var month = today.month;

    this.cal = function() {
        var endDay = this.getDays(month, year);
        var $li = null, curDate = CURRENTSERVERDATE;
        var $calendar = $("#calendarDay").empty();
        for (var intLoop = 1; intLoop <= endDay; intLoop++) {
            var date = year + "-" + dulnum(month) + "-" + dulnum(intLoop);
            $li = $("<li>");
            if (data[date]) {
                var $a = null;
                if (date === $("#thuimgWrap").find(".mainImg").attr("data-date")) {
                    $a = $("<a>").addClass("current").html(intLoop).appendTo($li);
                } else {
                    $a = $("<a>").html(intLoop).appendTo($li);
                }
                curDate = date;
                $a.trigger("click");
            } else {
                $("<a>").addClass("none").html(intLoop).appendTo($li);
            }
            $calendar.append($li);
        }
        $("#calendarMonth").html(month);
        $("#calendarYear").html(year);

        $calendar.find(".current").addClass("focus");
        $("#track-date").val(curDate);
    };

    this.getDays = function(month, year) {
        if (2 == month) {
            return (0 == year % 4 && 0 != year % 100) || (0 == year % 400) ? 29 : 28;
        }
        return daysInMonth[month - 1];
    };

    this.subMonth = function() {
        var limitdate = new Date(LIMITDATE);
        if (year == limitdate.getFullYear() && month == limitdate.getMonth() + 1) return;
        if (month < 2) {
            month = 12;
            year = year - 1;
        } else {
            month = month - 1;
        }
        this.cal();
    };

    this.addMonth = function() {
        if (year == today.year && month == today.month)
            return;
        if (month > 11) {
            month = 1;
            year = year + 1;
        } else {
            month = month + 1;
        }
        this.cal();
    };
}