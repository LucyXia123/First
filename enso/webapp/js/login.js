/**
 * Created by Administrator on 2017-09-04.
 */
var URL_LOGIN = window.APP_ROOT + "/student/login";  // window.APP_ROOT + "/student/login"
var URL_CAPTCHA = window.APP_ROOT + "/vcode";

$(function() {
    var g_count = 0;   // 尝试登陆次数

    // 1s内不能重复点击登录按钮 <button></button>
    var setDisableAfter2Second = function () {
        var $submit = $("#js-submit");
        $submit.attr("disabled", true);
        setTimeout(getTime, 1000);
        function getTime() {
            $submit.removeAttr("disabled");
        }
    };

    /**
     * 提交登录表单  /student/login
     */
    var $loginForm = $("#js-loginForm").attr("action", URL_LOGIN);
    /**
     * login handler
     */
    $loginForm.on("submit", function(e) {
        e.preventDefault();
        var $prompt = $(".Prompt");

        var captcha = document.getElementById("js-captcha");
        // 尝试登录第3次错误后显示验证码
        if (g_count >= 3) {
            captcha.style.display = 'block';
            document.getElementById("getcode_num").src = URL_CAPTCHA;
        }

        var form = this;
        var username = $.trim(form.username.value);
        var pwd = form.pwd.value;

        if (!username || !pwd) {
            $("#js-prompt").html('用户名或密码为空');
            $prompt.show();
            return false;
        }
        // === 验证通过 ====
        g_count++;

		$.ajax({
			type : 'POST',
			url : URL_LOGIN,
			dataType : 'json',
			data : {
				"username" : username,
				"pwd" : pwd,
				'captcha' : $("#input-captcha").val(),
				"count" : g_count
			}			
		}).done(function(data) {			
			if (data.hasOwnProperty("result")) {
				if (data.result === 1) {
					// 登录成功跳转到示范院校首页
					var qs = enso_query_string();
					if (qs.hasOwnProperty('referer')) {
						location.href = qs.referer;
					} else {
						location.href = 'model-school.jsp';
					}
				} else {
					$("#js-prompt").html(data.cause);
					$prompt.show();
				}
			} else {
				$("#js-prompt").html('parsing data error');
				$prompt.show();
			}
			$("#js-submit").text("登录");
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$("#js-prompt").html('服务器通信错误:' + textStatus);
			$prompt.show();
			$("#js-submit").text("登录");
			console.error(errorThrown);
			setTimeout(function() {
				$prompt.fadeOut(500)
			}, 5000);
		});
        $("#js-submit").text("登录中...");

        $prompt.hide();
        setDisableAfter2Second();
    });
    
    // 初始状态隐藏验证码, 点击切换验证码
    (function() {
    	document.getElementById("js-captcha").style.display = 'none';
    	
        var captcha = document.getElementById("getcode_num");        
        captcha.src = URL_CAPTCHA;
        captcha.onclick = function(e) {
            e.preventDefault();
            if (!this.src) {
            	this.src = URL_CAPTCHA;
            }
            if (-1 === this.src.lastIndexOf('?')) {
                this.src = this.src +'?t=' + Date.now();
            } else {
                this.src = this.src.substring(0, this.src.indexOf('?')) + '?t=' + Date.now();
            }
        };
    })();

    // 记住密码
    (function() {
        var $user = $("#input-username");
        var $pwd = $("#input-pwd");

        var checkbox = document.getElementById("js-checkbox");
        checkbox.onchange = function() {
            docCookies.unset("user");
            docCookies.unset("pwd");

            // var opt = {expires: 7, path: '/', domain: '127.0.0.1', secure: true};
            if (this.checked) {
                if ($.trim($user.val())) {
                    docCookies.set("user", $user.val(), 7*86400);
                    docCookies.set("pwd", $pwd.val(), 7*86400);
                }
            }
        };
        var username = docCookies.get("user");
        var password = docCookies.get("pwd");
        $user.val(unescape(username));
        $pwd.val(unescape(password));

        if (username && password) {
            checkbox.checked = "checked";
        }
    })();

});

/**
 * cookie utility
 * @type {{set: docCookies.set, unset: docCookies.unset, get: docCookies.get}}
 */
var docCookies = {
    set: function(c_name, value, expireseconds) {
        var path = location.pathname;

        var paths = path.split("/");
        if(paths[paths.length-1] !== "") {
            paths[paths.length-1] = "";
            path = paths.join("/");
        }
        var extime = new Date().getTime();
        var cltime = new Date(extime + (1000*expireseconds));
        var exdate = cltime.toUTCString();

        var re = new RegExp("(?:;?" + c_name + "\s*=)\w+[^;];*");
        var sCookie = document.cookie;

        // overwrite
        if(re.test(sCookie)) {
            document.cookie = sCookie.replace(re, value + "; ");
        } else {
            var s = "";
            s += c_name +"="+ escape(value);
            s += "; path="+ path;
            if(expireseconds) {
                s += "; expires=" +exdate+"; ";
            } else {
                s += "; ";
            }
            document.cookie = s;
        }
    },

    unset: function(c_name) {
        return this.set(c_name, null, -1);
    },

    get: function(c_name) {
        if (document.cookie.length > 0) {
            var a = document.cookie.split("; ");
            // for (var i = 0; i < a.length; i++) {
            for (var i = a.length-1; 0 <= i; i--) {
                var pair = a[i].split("=");
                if (c_name === pair[0]) {
                    return pair[1];
                }
            }
        }
        return "";
    }
};