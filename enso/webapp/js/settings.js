// var URL_USERINFO_GET = window.CONTEXT_PATH + '/data/userinfo.json';
var URL_USERINFO_GET = window.APP_ROOT + '/student/getuser';
var URL_USERINFO_SET = window.APP_ROOT + '/student/update';
var URL_RESET_PASSWORD = window.APP_ROOT + '/resetPassword'; 
var role = "";

$(function() {
    var userid = $("#js-userid").val();
    if (null == userid || "null" == userid || "" === userid) {
    	location.href = "login.jsp?referer=mysettings.jsp";
    }
    var mobileOrigin = null;
    
    // 班级dropdown初期化
    (function(domid) {
    	var select = document.getElementById(domid);
    	select.style.width = "9em";
    	select.innerHTML = "";    	
    	var option = null;
    	for (var i = 1; i < 51; i++) {
    		option = document.createElement("option");
    		option.value = i;
    		option.innerHTML = i;
    		select.appendChild(option); 
    	}
    	select.firstElementChild.setAttribute("selected", "selected");
    })("f-classname");
    
    // 提交用户信息变更表单
    var $form = $("#basicForm");
    
// 取得用户信息
    function user_info_get() {
        $.ajax({
            type: "GET",
            url: URL_USERINFO_GET,
            data: {"userid":userid},
            dataType: "json"
        }).done(function(data) {

            Object.keys(data).forEach(function(key, idx) {
                $("#f-" + key).val(data[key]);
                if (key === "mobile") {
                    mobileOrigin = data[key];
                    console.log("origin: " + mobileOrigin);
                }
                /* 学校2级联动 */
                if (key === "school") {
                	var script = new Script(function() {
                    	var s = data["school"];                	
                    	
                    	var $city = $("#js-city");
                    	$.getJSON("./data/schools.json", function(schools) {
                        	$city.citySelect({
                        		url: schools,
                        		prov: data.area,                   // "开封市"
                        		city: s.substr(data.area.length),  // "回民中学"
                        		dist: "",
                        		nodata: "none"
                        	});
                        	var change_handler = function() {
                        		var area = $city.children(".prov").val();
                        		var school = area + $city.children(".city").val();
                        		$("#f-school").val(school);
                        		$("#f-area").val(area);
                        	};
                        	$city.children(".city").on("change", change_handler);
                        	$city.children(".prov").on("change", change_handler);
                    	});
                	});
                	script.set("./bootstrap/js/jquery.cityselect.js");
                }
            });
            $("#pwd-userid").val(data.userid);
            
        	// 学生, 家长, 游客
        	role = data.type;
        	if (role !== "学生") {
        		$form.find(".students_related").hide();
        		$(".main-wrapper").css({"height":"700px"});
        	}
        });
    }
    user_info_get();

    var $sms = $("#js-sms");
    $("#f-mobile").on("change", function() {        
        var nm = this.value;
        
        var $modal = $("#myModal");
        if (nm.length == 0) {
        	$("#js-error").text("手机不能为空");
        	$modal.modal("show");
        	return false;
        }

        if (this.value === mobileOrigin) {
        	$sms.hide();
        } else {
        	if (window.TLibSSO.isPhone(nm)) {
        		$sms.show();
        	} else {
        		$sms.hide();        		    		
        		$("#js-error").text("新手机号不合法");        		        	
	        	$modal.modal("show");	
        	}        	
        }         
    });

    // 保存用户信息
    $form.on("submit", function(e) {
        e.preventDefault();
        var $btn = $(this).find(".btn-primary").attr('disabled', 'disabled').html("保存中...");
        
        var mobile = $("#f-mobile").val();
        var $fuser = $("#f-username");
        var username = $.trim( $fuser.val() );
        
        if (!TLibSSO.checkUsername(username, $fuser.next())) {
        	return false;
        }            

        var $femail = $("#f-email");
        var email = $.trim( $femail.val() );
        if (email.length > 0) {
            if (!TLibSSO.isEmail(email)) {
                $femail.next().html("邮箱不合法");
                return false;
            }
        }

        var $fschool = $("#f-school");
        var school = $.trim($fschool.val());
        if (0 === school.length) {
            $fschool.next().html("学校名不能为空");
            return false;
        }

        var $frealname = $("#f-realname");
        var realname = $.trim($frealname.val());
        if (realname.length > 0) {
            if (!TLibSSO.isRealName(realname)) {
                $frealname.next().html("真实姓名必须是2-4个汉字");
                return false;
            }	
        } else {
        	if (role === "学生") {
        		 $frealname.next().html("学生必须要填真实姓名");
        		 return false;
        	}
        }

        var $fidnum = $("#f-idnum");
        var idnum = $fidnum.val();
        if (idnum) {
            if (!TLibSSO.isIDCardNo(idnum)) {
                $fidnum.next().html("身份证号不合法");
                return false;
            }
        }

        var data = {
            "usernumber": userid,
            "username":username,
            "mobile": mobile,
            "email": email,
            "school": school,
            "grade": $("#f-grade").val(),
            "classname": $("#f-classname").val(),
            "realname": realname,
            "idnum": idnum,
            "version": $("#f-version").val(),
            "area": $("#f-area").val()
        };
        // 变更了手机号需要短信验证
        if ("" === mobile) {
        	$("#js-error").text("手机不能为空");
        	$("#myModal").modal("show");
        	return false;
        } 
        if (mobile !== mobileOrigin) {
            $sms.show();

                var code = $.cookie('sms_code');
                if (null === code || "" === code) {
                    $sms.find(".ex-tips").html('短信验证码已过期,请重新获取');
                    return false;
                } else {
                    if ($("#f-sms").val() === code) {
                        $sms.find(".ex-tips").html('短信验证成功');
                    } else {
                        $sms.find(".ex-tips").html('短信验证码错误, 保存失败!');
                        return false;
                    }
                }

        }
        $.ajax({
            type: "POST",
            url: URL_USERINFO_SET,
            data: data,
            dataType: "json"
        }).done(function(data) {
            var $fb = $("#js-fbBasic");
            if (data.hasOwnProperty("result")) {
                if (data.result === 1) {
                    user_info_get();
                    $fb.html("<div class=\"alert alert-success\" role=\"alert\">保存用户信息成功</div>");
                } else {
                    $fb.html("<div class=\"alert alert-danger\" role=\"alert\">"+"保存用户信息失败: "+data.cause+"</div>");
                }
            } else {
                console.error("parsing xhr data error");
            }
            $btn.attr("disabled", false).html("保存");
            $fb.show();
            setTimeout(function() {
                $fb.hide();
            }, 5000);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('保存用户信息通信失败: ' + textStatus);
            console.debug(errorThrown);
        });
        $(".ex-tips").empty();
        $("#js-fbBasic").hide();
    });

    // 更换绑定手机 发送短信验证码
    $("#js-sendsms").on("click", function(e) {
        e.preventDefault();
        var data, code;
        
        if (!$.cookie) {
            var script = new Script(function() {
                // 向客户发送手机验证码的ajax
                $.cookie('sms_code', null);
                // 短信验证码 写入cookie, 5min内有效
                date = new Date(Date.now() + 1000 * 60 * 5);
                code = TLibSSO.getSMSCode();
                $.cookie('sms_code', code, {expires: date});
            });
            script.set("./bootstrap/js/jquery.cookie.js");
        }

        var data = {
            'mobile': $("#f-mobile").val(),
            'code': code
        };
        $.get(URL_SMS, data, function(resp) {
            var data = {};

            if (data.result == 1) {
                // 启动再次发送短信验证码 countdown
                setTimeout(function () {
                    $sms.find("button").css({"border": "1px solid #ccc"});
                    TLibSSO.timeCountDown("#js-getCode");
                }, 100);
            } else if (data.result == 0) {
                $sms.find(".ex-tips").html('发送短信验证码失败');
            } else {
                alert("Unknown result.");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            $sms.find(".ex-tips").html('发送短信验证码通信错误: ' + textStatus);
        });
    });

    // blur显示验证码正确?
    $("#f-sms").on("blur", function() {
        var code = $.cookie("sms_code");
        var $tips = $("#js-sms").find(".ex-tips");
        if (this.value === code) {
            $tips.html('短信验证成功');
        } else {
            $tips.html('短信验证失败');
        }
    });

    // 修改密码
    $("#pwdForm").on("submit", function(e) {
        e.preventDefault();

        var $oldpwd = $("#pwd-oldpassword"), $pwd = $("#pwd-password"), $repwd = $("#pwd-repassword");
        var oldpwd = $oldpwd.val(), pwd = $pwd.val(), repwd = $repwd.val();

        if (0 === oldpwd.length) {
            $oldpwd.next().html("原始密码不能为空");
            return false;
        }
        if (0 === pwd.length) {
            $pwd.next().html("新密码不能为空");
            return false;
        }
        if (pwd === oldpwd) {
            $pwd.next().html("新密码不能与原始密码相同");
            return false;
        }

        if (pwd !== repwd) {
            $(this).find(".ex-tips").last().html("2次密码不一致");
            return false;
        }
        var data = {
            "userid": userid,
            "oldPassword": oldpwd,
            "password": pwd
        };
        $.ajax({
            type: "POST",
            url: URL_RESET_PASSWORD,
            data: data,
            dataType: "json"
        }).done(function(data) {
            var $fb = $("#js-fbPwd");
            if (1 === data.result) {
                $fb.html("<div class=\"alert alert-success\" role=\"alert\">修改密码成功</div>");
            } else {
                $fb.html("<div class=\"alert alert-danger\" role=\"alert\">修改密码失败: "+data.cause+"</div>");
            }
            $fb.show();
            setTimeout(function() {
                $fb.hide();
            }, 5000);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            $("#js-fbPwd").html("<div class=\"alert alert-danger\" role=\"alert\">重置密码通信错误: "+textStatus+"</div>");
        });

        $(".ex-tips").empty();
    });   
    
});