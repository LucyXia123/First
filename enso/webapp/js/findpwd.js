$(function() {
	// trigger modal
	var $modal = $("#myModal");
	
    load_js("./bootstrap/js/bootstrap.min.js", function() {
    	$("#js-findpwd").on("click", function() {
    		$modal.modal("show");
    	});
    });
	
	// modal错误提示
	var $prompt = $("#modal-prompt");

	// 表单校验 + 发送短信验证码
    load_js("./js/libsso.js", function() {
    	// check mobile number
    	var $mob = $("#m-mobile");
    	$mob.on("blur", function() {
    		var mobile = this.value;
    		if (!TLibSSO.isPhone(mobile)) {
    			$mob.parent().next().html("<span class='text-danger'>&times;手机号不合法</span>");
			} else {
				$.ajax({
					type: "GET",
					url: "./student/checkmobile",
					data: {"mobile":mobile}
				}).done(function(data) {
					if (data.result===1 || data.result==="1") {
						$mob.parent().next().html("<span class='text-danger'>&times;该手机号未注册</span>");
					} else {
						$mob.parent().next().html("<span class='text-success'>&radic;</span>");
					}
				});				
			}
    	});
    	
    	var $pwd = $("#m-pwd"), $repwd = $("#m-confirmPwd");
    	$repwd.on("blur", function() {
    		var $this = $(this);
        	if ($pwd.val() !== $this.val()) {
        		$this.parent().next().html("<span class='text-danger'>&times;2次密码输入不一致</span>");
        		$pwd.parent().next().empty();
        	} else {
        		if ($pwd.val() !== "") {
            		$this.parent().next().html("<span class='text-success'>&radic;</span>");
            		$pwd.parent().next().html("<span class='text-success'>&radic;</span>");
        		} else {
        			$pwd.parent().next().html("<span class='text-danger'>&times;密码不能为空</span>");
        		}
        	}
    	});
    	
		// $("#m-code").on("blur", function() {});
    
    	// 点击取得验证码
		var btnGetCode = document.getElementById("js-getCode");
		btnGetCode.onclick = function(e) {
			e.preventDefault();
			// send sms
			var mobile = $mob.val();
			$.ajax({
				type: "GET",
				url: "./student/sessionSMS",
				data: {"mobile":mobile}
			}).done(function(data) {
				if (0==data.result) {
					TLibSSO.timeCountDown(btnGetCode);
				} else {
					$prompt.html(data.cause);
					console.error(data);
				}				
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$prompt.text("发送短信验证码请求通信ERROR["+textStatus+"]: " + errorThrown);
			})            
		};
		
		// 提交表单
		$("#js-post").on("click", function() {
			var $smsCode = $("#m-code");
			$("modal-body").find(".Prompt").hide();
			
			var pwd = $pwd.val();
			var repwd = $repwd.val();
			if (pwd===""||repwd==="") {
				$prompt.html("密码不能为空"); $("modal-body").find(".Prompt").show();
				return false;
			}
			if (pwd!==repwd) {
				$prompt.html("2次输入密码不一致"); $("modal-body").find(".Prompt").show();
				return false;
			}
			if (0===$smsCode.val().length) {
				$prompt.html("短信验证码不能为空"); $("modal-body").find(".Prompt").show();
				return false;
			}
			
			var formData = {
				"mobile": $mob.val(),
				"password": $pwd.val(),
				"code": $smsCode.val()
			}
			
			$.ajax({
				type: "POST",
				url: "./student/findPwd",
				data: formData
			}).done(function(data) {
				// 重置成功 自动用新手机号 和密码登录
				if (0==data.result) {
					$("modal-body").find(".Prompt").hide();
					$("#input-username").val(formData.mobile);
					$("#input-pwd").val(formData.password);
					$("#js-loginForm").submit();
				} else {
					$prompt.html(data.cause); $(".Prompt").show();			
				}				
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$prompt.text("找回密码通信ERROR["+textStatus+"]: " + errorThrown);
			});
			
			setDisableAfter2Second(this);
		});
		
	});  // END load_js("./js/libsso.js", ...
    
   // 1s内不能重复点击登录按钮 <button></button>
    function setDisableAfter2Second(btn) {
        var $submit = $(btn);
        $submit.attr("disabled", true);
        setTimeout(function() {
        	$submit.removeAttr("disabled");
        }, 2000);
    };
    
}); // END $(function() {... 

// import js
function load_js(src, callback) {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = src;
    document.body.appendChild(script);

    if (typeof callback !== "undefined" && callback) {
        if (navigator.appName.toLowerCase().indexOf('netscape') === -1) {
            script.onreadystatechange = function() {
                script.readyState === "complete" && callback(this);
            }
        } else {
            script.onload = function() {
                callback(this);
            }
        }
    }
}
