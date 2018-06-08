/**
 * Created by mzh on 2017-09-06.
 * depends on calendar.js
 */
var URL_EVENT_POST = window.APP_ROOT + "/postevent"; // "/util/oneUpload";

$(function() {
    "use strict";

    // 图片上传预览
    $("#track-upload").on("change", function() {
        var file = this;
        var prevDiv = document.getElementById('js-preview');
        prevDiv.innerHTML = "";
        if (file.files && file.files[0])  {
            var reader = new FileReader();
            reader.onload = function(e) {
                var img = new Image();
                img.width = 100;
                img.height = 100;
                img.src = e.target.result;
                prevDiv.appendChild(img);
            };
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'; width=160px; height=160px;"></div>';
        }
    });

    /**
     * 上传大事件
     */
    var $calendarDay = $("#calendarDay");
    $calendarDay.on("click", "a", function() {
        // select a date to upload
        var $this = $(this);
//          ,$theme = $("#track-theme"),
//           $text = $("#track-text");
        $calendarDay.find(".focus").removeClass("focus");
        $this.addClass("focus");

        var day = $this.text(),
            month = $("#calendarMonth").text(),
            year = null;
        var dotDate = $("#calendarToday").text();       
        if ("Today" === dotDate) {
        	year = CURRENTSERVERDATE.substr(0, 4);
        } else {
        	year = dotDate.substr(0, 4);
        }
        day = day < 10 ? "0"+day : day;
        month = month < 10 ? "0"+month : month;
        var dateFocus = year + '-' + month + '-' + day;
        $("#track-date").val(dateFocus);
    });

    /**
     * 学习轨迹大事件 图文上传
     */
    $("#user-track-form").attr("action", URL_EVENT_POST).on("submit", function(e) {
        e.preventDefault();
        if (!window.hasOwnProperty('FormData')) {
            alert("上传功能不支持IE10以下浏览器"); // 请使用IE10以上浏览器或其他浏览器!
            return false;
        }
        var form = this;
        var formdata = new FormData(form);
        // formdata.append("userid", form.userid);
        $.ajax({
            url: form.action,
            type: "POST",
            data: formdata,
            cache: false,
            contentType: false,
            processData: false,
            dataType: "html"
        }).done(function(data) {
            var res = null;
            try {
                res = JSON.parse(data);
            } catch(e) {
                alert("parse json error!");
                return false;
            }
            if (res.hasOwnProperty("result")) {
                if (1 === res.result) {
                    location.reload();
                } else {
                    alert("上传失败: " + res.cause);
                }
            } else {
                alert("get res.result error");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("上传大事件图文xhr通信失败");
        });
    });
});

