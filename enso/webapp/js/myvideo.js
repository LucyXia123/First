var URL_VIDEO = window.APP_ROOT + '/video/selectById';
//var URL_VIDEO = window.CONTEXT_PATH + '/data/video.json';
var URL_COMMENT_POST = window.APP_ROOT + '/filter/videocomment';   // '/application/register.php';
var URL_COMMENT_DEL = window.APP_ROOT + '/filter/deleteComment';  // '/data/comment-del.json';
var URL_COMMENT_LIST = window.APP_ROOT + '/video/listCommentByVideoId';
var qs = enso_query_string();
var videoId = null, pageSize = null, totalPage = null;

$(function() {
    if (!qs.hasOwnProperty("videoId")) {
        document.write("<h2>参数错误, 找不到GET参数videoId</h2>");
        return false;
    }
    videoId = qs.videoId;

    var $user = $("#js-userid");
    var sess_userid = $user.val(),
        sess_username = $user.attr("data-username");

    var currentPage = qs.hasOwnProperty("currentPage") ? (qs.currentPage?qs.currentPage:1):1,
           pageSize = qs.hasOwnProperty("pageSize") ? (qs.pageSize?qs.pageSize:20):20;
    	   // pageSize = 5;

    $.ajax({
        type: 'GET',
        url: URL_VIDEO,
        data: {'videoId':videoId, "currentPage":currentPage, "pageSize":pageSize},
        cache: true
    }).done(function(data) {
        $("#js-videoname").html(data.videoname);
        
        // 去除string中space, double quote
        var trim = String.prototype.trim;
        String.prototype.trim = function() {
            return this.replace(/^[\s\"]+/, "").replace(/[\s\"]+$/, "");
        }
        // Step#1.设置media路径src        
        var url = data.videoURL ? data.videoURL.trim() : "";
        if ("" === url) {
        	document.write("<h2>找不到这个视频videoId="+videoId+"</h2>");
        	return false;
        }
        
        // media format, video/mp4, ...
        var suffix = url.substr(url.lastIndexOf(".")).substring(1);
        if ("mpg" === suffix) {
        	suffix = "webm"; // "mpeg"; 
        }
        var mf = "video/" + suffix;
        mf = unescape(mf).trim();
        String.prototype.trim = trim;

        $("#js-videoURL").attr("src", url).attr("type", mf);
        // 暂时没有视频海报
        if (data.hasOwnProperty('poster')) {
            $("#my-video").attr("poster", data.poster);
        }
        // Step#2.加载Aliplayer
        var script = new Script(function() {
            var player = Aliplayer({
            	id: "my-video",
            	autoplay: true,
            	width: "750px",
            	height: "430px",
            	source: data.videoURL, // "http://vedio.tfjyzx.com/0daef5e96a34442aacbdfa3a1380008e/a952209fcf834535b5cd68d910f78a4d-5287d2089db37e62345123a1be272f8b.mp4",
            	language: 'en-us'
            });
        });
        // script.set("./videojs/js/video.min.js");
        // CSS("./videojs/css/video-js.css");
        script.set("https://g.alicdn.com/de/prismplayer/2.4.0/aliplayer-min.js");
        CSS("https://g.alicdn.com/de/prismplayer/2.3.5/skins/default/aliplayer-min.css");

        $("#js-videointroduce").html(data.videointroduce);
        render_comment(data);
        totalPage = data.totalPage;

    }).fail(function(jqXHR, textStatus, errorThrown) {
        document.write("<h2>获取视频信息失败</h2>");
        // setTimeout(function() {window.location.href = window.CONTEXT_PATH + '/model-school.jsp';}, 2000);
    });
    /**
     * 发布评论
     */
    $("#js-submit").on("click", function (e) {
        e.preventDefault();

        var $text = $("#js-text");
        var textval = $text.val().trim();
        var $notice = $("#js-notice");
        if (textval.length < 6) {
            $notice.html("<div class=\"alert alert-danger\" role=\"alert\">评论内容至少6个字符</div>");
            return false;
        }
        $notice.empty();

        var that = this;
        that.innerText = '评论发表中...';
        that.setAttribute("disabled", "disabled");

        $.ajax({
            type: 'POST',
            url: URL_COMMENT_POST,
            data: {
                'userid':sess_userid,
                'content':textval,
                "videoid": qs.videoId,
                "username": sess_username
            }
        }).done(function(data) {
            /**
             * 1min内只能发表1个评论
             * @param timeCtrlId, 评论提交按钮 dom或者ID
             * @param timeout,     timeout秒后可以再提交
             */
            var timeCountDown = function(timeCtrlId, timeout) {
                timeout || (timeout = 60);
                var disabled = {
                    "background-color": "#e5e9ef !important",
                    "border-color": "#e5e9ef !important",
                    "color": "#b8c0cc !important",
                    "cursor": "not-allowed"
                };
                var origin = {
                    "background-color": "#00a1d6",
                    "border-color": "#00a1d6",
                    "color": "#fff",
                    "cursor": "pointer"
                };
                $(timeCtrlId).text(timeout + "s后评论").attr("disabled",true).css(disabled);
                var countdown = timeout-1;
                var timer = setInterval(function() {
                    if (0 === countdown) {
                        $(timeCtrlId).text("发表评论").removeAttr("disabled").css(origin);
                        countdown = timeout-1;
                        window.clearInterval(timer);
                    } else {
                        $(timeCtrlId).text(countdown+"s后评论").attr("disabled",true).css(disabled);
                        countdown--;
                    }
                }, 1000);
            };
            timeCountDown(that, 3);

            // reload comment list
            $.ajax({
            	type: 'GET',
            	url: URL_COMMENT_LIST,
            	data: {'videoId':videoId, "currentPage":currentPage, "pageSize":pageSize}
            }).done(function(data) {
            	data.currentPage = currentPage;
            	data.totalPage = totalPage;
            	render_comment(data);   // {currentPage:xxx, totalPage, list: [0:{xxx},1:{xxx},...]}
            }).fail(function(jqXHR, textStatus, errorThrown) {
            	alert("刷新评论列表失败: " + textStatus);
            });

            that.innerText = '发表评论';
            that.removeAttribute("disabled");
            // 清空评论输入框textarea
            $text.val('');

        }).fail(function(jqXHR, textStatus, errorThrown) {
            $notice.html("<div class=\"alert alert-danger\" role=\"alert\">发表评论通信错误"+ textStatus +"</div>");
            console.error(errorThrown);
        });
    });
    
    // 请先登录后发表评论 (・ω・) 登录后跳转到这个页面/tf_online/video.jsp?videoId=1
    (function() {
    	var $a = $("#js-loginBaffle");
    	if ($a.length > 0) {
    		var id = qs.videoId;
    		$a.attr("href", "login.jsp?referer=video.jsp%3FvideoId%3D" + id);
    	}
    })();

    /**
     * load评论列表 + 评论分页
     * @param data
     */
    function render_comment(data) {
        var a = data.list;
        // 评论内容
        var $target = $("#js-comment").empty();
        if (a instanceof Array) {
            var get_history_text = function(ts) {
                var now = Date.now();
                var interval = Math.round( (now - ts)/1000 );

                if (0 <= interval) {
                    if (interval < 10) {
                        return "刚刚";
                    } else if (interval < 60) {
                    	return interval + "秒前";
                    } else if (interval < 3600) {
                        return Math.floor(interval/60) + '分钟前';
                    } else if (interval < 86400) {
                        return Math.floor(interval/3600) + '小时前';
                    }
                }
                var date = new Date();
                date.setTime(ts);
                return date.Format("yyyy-MM-dd hh:mm:ss");
            };

            Array.prototype.forEach.call(a, function(elem, i) {
                // .list-item
                var $item = $("<div>").addClass("list-item").attr("data-index", i).attr("data-id", elem.id);
                // .user-face
                var $face = $("<div>").addClass("user-face").attr("data-usercard-mid", elem.userid);
                $item.append($face);
                var $a = $("<a>").attr("href", "javascript:;").append($("<img>").attr("src", "./publish/user/avatar/avatar.png"));
                $face.append($a);
                // .con
                var $con = $("<div>").addClass("con");
                $con.append($("<div>").addClass("user").html("<a class=\"name\" href=\"javascript:;\">"+elem.username+"</a>"));
                $("<p>").addClass("text").text(elem.content).appendTo($con);

                // 毫秒时间 => 15小时前
                var historyText = get_history_text(elem.createtime);

                var $time = $("<span>").addClass("time").text(historyText);
                var $info = $("<div>").addClass("info").append($time).appendTo($con);
                // 可以删除自己的评论                
                if (String(elem.userid) === String(sess_userid)) {
                    var $aa = $("<a>").addClass("delete").attr("title", "删除评论").html("<span class=\"glyphicon glyphicon-trash\"></span>");
                    $aa.on("click", function(e) {
                        e.preventDefault();
                        delete_my_comment($item);
                    });
                    $info.append($aa);
                }
                $item.append($con);

                $target.append($item);
            });
            if (0 === a.length) {
                var $h5 = $("<h5>").html("暂时没有评论, 快来抢个沙发~").css({"margin-left":"86px"});
                $target.append($h5);
            }
        } else {
            console.log('get comment array failed');
        }

        // 评论分页
        var $page = $("#js-pagination");
        $page.empty();

        if (data.totalPage < 2) {
            return;
        }
        // currentPage?
        if (data.currentPage > data.totalPage) {
            data.currentPage = data.totalPage;
        }
        if (data.currentPage < 1) {
            data.currentPage = 1;
        }

        // first page
        if (1===data.currentPage) {
            // 隐藏上一页按钮
            $("<span>").addClass("disabled").html("上一页").appendTo($page);
            $("<span>").addClass("current").html(1).appendTo($page);
        } else {
            // 显示上一页按钮
            $("<a>").attr("href", "javascript:;").addClass("prev").html("上一页").appendTo($page);
            $("<a href='javascript:;'>").addClass("tcd-number").html(1).appendTo($page);
        }

        // middle pages
        if (data.totalPage < 8) {
            for (var i = 2; i < data.totalPage; i++) {
                if (i === data.currentPage) {
                    $("<span>").addClass("current").html(i).appendTo($page);
                } else {
                    $("<a href='javascript:;' class='tcd-number'>").html(i).appendTo($page);
                }
            }
        } else {
            if (data.currentPage === 1) {
                $("<a href='javascript:;' class='tcd-number'>").html(2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(3).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(4).appendTo($page);
                $("<span>").addClass("dot").html("...").appendTo($page);
            } else if (data.currentPage === 2) {
                $("<span>").addClass("current").html(2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(3).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(4).appendTo($page);
                $("<span>").addClass("dot").html("...").appendTo($page);
            } else if (data.currentPage === 3) {
                $("<a href='javascript:;' class='tcd-number'>").html(2).appendTo($page);
                $("<span>").addClass("current").html(3).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(4).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(5).appendTo($page);
                $("<span>").addClass("dot").html("...").appendTo($page);
            } else if (data.currentPage === 4) {
                $("<a href='javascript:;' class='tcd-number'>").html(2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(3).appendTo($page);
                $("<span>").addClass("current").html(4).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(5).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(6).appendTo($page);
                $("<span>").addClass("dot").html("...").appendTo($page);
            } else if (data.currentPage === data.totalPage-3) {
                $("<span>").addClass("dot").html("...").appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-1).appendTo($page);
                $("<span class='current'>").html(data.currentPage).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.totalPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.totalPage-1).appendTo($page);
            } else if (data.currentPage === data.totalPage-2) {
                $("<span>").addClass("dot").html("...").appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-1).appendTo($page);
                $("<span class='current'>").html(data.currentPage).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.totalPage-1).appendTo($page);
            } else if (data.currentPage === data.totalPage-1) {
                $("<span>").addClass("dot").html("...").appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-1).appendTo($page);
                $("<span class='current'>").html(data.currentPage).appendTo($page);
            } else if (data.currentPage === data.totalPage) {
                $("<span>").addClass("dot").html("...").appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-3).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-1).appendTo($page);
            } else {
                $("<span>").addClass("dot").html("...").appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-2).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage-1).appendTo($page);
                $("<span class='current'>").html(data.currentPage).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage+1).appendTo($page);
                $("<a href='javascript:;' class='tcd-number'>").html(data.currentPage+2).appendTo($page);
                $("<span>").addClass("dot").html("...").appendTo($page);
            }
        }

        // last page
        if (data.totalPage === data.currentPage) {
            $("<span>").addClass("current").html(data.totalPage).appendTo($page);
            $("<span>").addClass("disabled").html(data.totalPage).appendTo($page);
        } else {
            $("<a href='javascript:;'>").addClass("tcd-number").html(data.totalPage).appendTo($page);
            $("<a href='javascript:;'>").addClass("next").html("下一页").appendTo($page);
        }
        var $j = $("<div class=\"page-jump\">共<span>"+data.totalPage+"</span>页, 跳至<input type=\"text\" />页</div>");
        $page.append($j);

        // 跳转页
        (function() {
            var input = $page.find("input").get(0);

            input.onkeyup = function(e) {
                if (e.keyCode === 13) {
                    var p = parseInt(this.value);
                    p = isNaN(p) ? 1:p;
                    jump_comment_page(p);
                }
            };
        })();

        $page.find("a").on("click", function(e) {
            e.preventDefault();
            var a = this, className = a.className;
            var currentPage = null;

            if (className === "tcd-number") {
                currentPage = a.innerText;
            } else if (className === "prev") {
                currentPage--;
                currentPage = currentPage < 1 ? 1:currentPage;
            } else if (className === "next") {
                currentPage++;
                currentPage = (currentPage > data.totalPage) ? data.totalPage : (currentPage+1);
            }
            jump_comment_page(currentPage);
        });

        function jump_comment_page(currentPage) {
            $.ajax({
                type: 'GET',
                url: URL_VIDEO,
                data: {'videoId':videoId, "currentPage":currentPage, "pageSize":pageSize},
                cache: false
            }).done(function(data) {
                render_comment(data);
            });
        }
    }

    /**
     * 删除自己的评论
     * @param $item
     */
    function delete_my_comment($item) {
        var commentid = $item.attr("data-id");
        $.ajax({
            type: 'GET',
            url: URL_COMMENT_DEL,
            data: {"userid": sess_userid, "commentid":commentid}
        }).done(function(data) {
            $item.remove();
            console.log('删除自己的评论成功');
        }).fail(function(jqXHR, textStatus, errorThrown) {
            $("#js-notice").html("<div class=\"alert alert-danger\" role=\"alert\">删除评论后台通信错误: "+textStatus+"</div>");
        });
    }
    
    // 加载相关视频列表
    $.ajax({
    	type: "GET",
    	url: "./video/listRelatedVideos",
    	data: {id: videoId}
    }).done(function(data) {
    	var $target = $("#aboutVideo").children(".about-video-content");
    	if (data.length>0) {
    		$target.empty();
    	}
    	$target.empty();
    	var $a = null;
    	
    	for (var i = 0, n=data.length; i < n; i++) {
    		var item = data[i];
    		if (item.id == videoId) {
    			continue;
    		}
    		var url = "video.jsp?videoId=" + item.id;
    		var title = item.name;
    		$a = $("<a>").addClass("item").attr("href", url).attr("title", escape(title));
    		$target.append($a);
    		
    		var html = 
    			    "<div class=\"item-img\">\r\n" + 
    				"	<img class=\"item-img-content\" src=\""+item.poster+"\">\r\n" + 
    				"	<div class=\"item-play-icon\"></div>\r\n" + 
    				"</div>\r\n" + 
    				"<div class=\"item-info\">\r\n" + 
    				"	<div class=\"item-title\">"+title+"</div>\r\n" + 
    				"	<div class=\"item-tip\">\r\n" + 
    				"		<span>"+Math.ceil(Math.random()*1000)+"人查看</span>\r\n" + 
    				"	</div>\r\n" + 
    				"</div>";
    		$a.html(html);
    	}
    });
});

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
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};