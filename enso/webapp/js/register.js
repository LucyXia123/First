/**
 * Created by mzh on 2017-08-31.
 * depends on libsso.js, common.js
 */
var URL_REGISTER = window.APP_ROOT + '/student/save';
var URL_USER = window.APP_ROOT + "/student/checkusername";
var URL_MOBILE = window.APP_ROOT + '/student/checkmobile';
var IS_SMS_ENABLED = false;

$(function() {
    "use strict";

    var form = document.forms['register'];

    // ==============blur验证=====================
    $("#input-username").on("blur", function() {
        checkUsername(this);
    }).on("focus", function() {
        if (this.value === '用户名') {
            this.value = '';
        }
    });

    var $inputpwd = $("#input-pwd");
    $inputpwd.on("blur", function() {
        checkPwd(this);
    });
    // 密码显示与隐藏
    $inputpwd.next().click(function (e) {
        e.preventDefault();
        var a = $inputpwd.val();
        if (a === "") {
            return false;
        }
        var c = $(this).attr("class");

        if (c === "m1") {
            // 显示密码
            $(this).removeClass("m1");
            if ($inputpwd.val().length > 1) {
                $inputpwd.hide().attr("type", "text").val(a).show();
                return true;
            }
            $(this).parent().find("input").attr("type", "text").attr("disabled", true);
        } else {
            // 隐藏密码
            $(this).addClass("m1");
            if ($inputpwd.val().length > 1) {
                $inputpwd.hide().attr("type", "password").val(a).show();
                return true;
            }
            $(this).parent().find("input").attr("type", "password").removeAttr("disabled");
        }
    });
    // 显示密码强度
    $inputpwd.keyup(function (e) {
        if (e.keyCode == 9)  // tab
            return;
        // 判断是否包含汉字
        if (TLibSSO.isChina($(this).val())) {
            $(this).val("");
            $(".psd-Strength span").removeClass();
        }
        // 实时知道安全等级
        var length = $(this).val().length;
        // 判断判断是否有数字
        var strongRegex = /[0-9]/;
        // 判断是否包含字母
        var Regex = /[a-zA-Z]/;
        // 判断是否包含特殊符号
        var por = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
        // 判断如果包含汉字，将清空输入的内容,并提示有非法汉字输入
        var val_leng1 = $(this).val().charAt($(this).val().length - 1);
        if (!Regex.test(val_leng1) && !por.test(val_leng1) && !strongRegex.test(val_leng1) && length != 0) {
            $(this).val($(this).val().substr(0, $(this).val().length - 1));
            $(this).val("");
            length = 0;
            $("#help-pwd").html("包含非法汉字，请重新输入");
        }

        var hasDigits = false;
        var hasAlpha = false;
        // 根据所包含的内容多少来判断密码强度,以下判断包含多少种内容。
        var i = 0;
        if (por.test($(this).val())) {
            i++;
        }
        if (strongRegex.test($(this).val())) {
            hasDigits = true;
            i++;
        }
        if (Regex.test($(this).val())) {
            hasAlpha = true;
            i++;
        }
        // 以下根据输入的个数及密码种类来判断输入的密码强度
        var $spans = $(".psd-Strength").find("span");
        // 必须同时有字母和数字
        if (!hasDigits || !hasAlpha) {
            $spans.removeClass().eq(0).addClass("danger");
            this.focus();
            return false;
        }
        if (length === 0) {
            $spans.removeClass().eq(0).addClass("danger");
            this.focus();
        }
        else if (length < 6) {
            $spans.removeClass().eq(0).addClass("danger");
        } else if (length >= 6 && length < 8) {
            if (i === 1) {
                $spans.removeClass().eq(0).addClass("danger");
            } else {
                $spans.removeClass().addClass("commonly").eq(2).removeClass();
            }
        } else if (length >= 8 && length < 12) {
            if (i === 1) {
                $spans.removeClass().eq(0).addClass("danger");
            } else if (i === 2) {
                $spans.removeClass().addClass("commonly").eq(2).removeClass();
            } else {
                $spans.removeClass().addClass("security");
            }
        } else if (length >= 12 && length <= 16) {
            if (i < 2) {
                $spans.removeClass().addClass("commonly").eq(2).removeClass();
            } else {
                $spans.removeClass().addClass("security");
            }
        }
    });

    $("#input-mobile").on("blur", function() {
        checkMobile(this);
        this.placeholder = '输入11位大陆手机号';
    }).on("focus", function() {
        this.placeholder = '';
    });

    $("#input-email").on("blur", function() {
        checkEmail(this);
        this.placeholder = 'xxx@xxx.com';
    }).on("focus", function() {
        this.placeholder = '';
    });

    $("#input-idnum").on("blur", function () {
        checkIdnum(this);
        this.placeholder = '输入18位或15位身份证号码';
    }).on("focus", function() {
        this.placeholder = '';
    });

    $("#input-school").on("blur", function() {
        checkSchool(this);
        this.placeholder = '输入学校名称';
    }).on("focus", function() {
        this.placeholder = '';
    });
    
    // 班级号限制只能输入数字
    $("#input-classname").on("keyup", function() {
        this.value = this.value.replace(/[^\d]/g,'');
    }).on("afterpaste", function() {
        this.value = this.value.replace(/[^\d]/g, '');
    }).on("change", function() {
        var a = parseInt(this.value);
    	if (isNaN(a) || a < 1) {
    		this.value = 1;
    	}
    });

    // 输入短信验证码
    $('#input-sms').on("keyup", function(e) {
        var code = $.cookie('sms_code');
        var $helpsms = $("#help-sms");
        if (typeof code === "undefined" || code === null || code === '') {
            $helpsms.html(strdanger('请点击获取短信验证码'));
            return false;
        } else {
            if (this.value !== code) {
                $helpsms.html(strdanger('短信验证码错误'));
                return false;
            }
        }
        $("#js-submit").removeAttr('disabled');
        return true;
    }).on("blur", function() {
        var code = $.cookie('sms_code');
        console.info('cookie sms_code: '+code);
        if (this.value === code) {
            $("#help-sms").html(strsuccess('短信验证成功'));
            $("#js-submit").removeAttr('disabled');
        }
    });
    if (IS_SMS_ENABLED) {
    	$("#js-fg-sms").show();
    }

    $("#input-realname").on("blur", function() {
    	checkRealname(this);
        this.placeholder = '输入真实姓名(汉字)';
    }).on("focus", function() {
        this.placeholder = '';
    });

    /**
     * xhr提交注册表单 #js-submit
     */
    $(form).on("submit", function(e) {
        e.preventDefault();
        var form = this;

        // 提交表单验证最后去掉注释 comment
        if (!validateDataRegForm(form)) {
            return false;
        }
        // 验证短信验证码 (2017-09-29 17:17:00 comment off)
        if (IS_SMS_ENABLED) {
            var code = $.cookie('sms_code');
            var $helpsms = $("#help-sms");
            if (typeof code === "undefined" || code === null || code === '') {
                $helpsms.html(strdanger('短信验证码已经过期, 请重新获取。'));
                return false;
            } else {
                if ($("#input-sms").val() !== code) {
                    $helpsms.html(strdanger('短信验证码错误'));
                    return false;
                }
            }
        }
        
        if (form.type.value === "学生") {
            // 校名
            if (form.school.value.length < 3) {
            	$("#help-school").html(strdanger("校名至少3个汉字"));
            	return false;
            }
        } else {
        	form.school.value = "暂无";        	
        }         
        // 提交按钮
        var $btn = $("#js-submit").addClass("disabled").text("注册中...").css({"cursor":"not-allowed"});
       
        var datastr = $(form).serialize();
        
        $.ajax({
            type: 'POST',
            url: "customerRegisterInterface",
            data: datastr,
            dataType: 'json'
        }).done(function(data) {
        	switch (data.result) {
        	case 200:
            	// 注册成功 跳转到登录页面
            	var $modal = $("#modal-2");
            	$modal.on("shown.bs.modal", function() {                	
                	$.cookie("user", form.mobile.value);
                	$.cookie("pwd", form.pwd.value);
            	});
            	$modal.modal('show');
            	
            	setTimeout(function() {
            		location.href = 'login.jsp';
            	}, 1500);
            	break;
        	case 406:
        	case 407:
        		// 用户名和手机号已存在！ 用户名已存在
        		$("#help-username").html(strdanger(data.cause));
        		$("#input-username").focus();
        		break;
        	case 408:
        		$("#help-mobile").html(strdanger(data.cause));
        		$("#input-mobile").focus();
        		break;
        	case 500:
            	// 注册失败 show modal
            	$("#js-cause").html("注册失败: " + data.cause);
            	$("#modal-1").modal('show');
            	break;
        	default:
        		alert("Unexpected result");
        	}
        	$btn.removeClass("disabled").html("免费注册").css({"cursor":"pointer"});

        }).fail(function( jqXHR, textStatus, errorThrown ) {
        	$("#js-prompt").html('注册xhr通信失败: ' + textStatus);
        	$(".Prompt").show();
        });
        // 清空错误提示
        $(".help-block").empty();
        
    });

    /**
     * 通过短信平台获取短信验证码
     */
    (function() {
        $("#js-getCode").on("click", function() {
            var regForm = document.getElementById("js-regForm");
            var btn = this;

            $.cookie('sms_code', null);
            // 短信验证码 写入cookie, 5min内有效
            var date = new Date(Date.now() + 1000 * 60 * 5);
            var code = TLibSSO.getSMSCode();
            $.cookie('sms_code', code, {expires: date});

            // 发送验证码之前验证用户名,密码,手机号
            var mobile = document.getElementById("input-mobile");
            var username = document.getElementById("input-username");
            var pwd = document.getElementById("input-pwd");
            if (checkUsername(username) && checkPwd(pwd) && checkMobile(mobile)) {
                var data = {'mobile': regForm.mobile.value, 'code': code};
                // 向客户发送手机验证码的ajax
                $.get(URL_SMS, data, function(data) {                	
                	if (data.result === 1) {
                        // 启动再次发送短信验证码 countdown
                        setTimeout(function () {
                            $(btn).css({"border": "1px solid #ccc"});
                            TLibSSO.timeCountDown("#js-getCode");
                        }, 100);
                	} else if (data.result === 0) {
                		$("#help-sms").html(strdanger('发送短信验证码失败: ' + data.cause));
                	} else {
                		alert("Unknown result.");
                	}                	
                }).fail(function(jqXHR, textStatus, errorThrown) {
                	$("#help-sms").html(strdanger('发送短信验证码通信错误: ' + textStatus));
                });
            } else {
                $(mobile).focus();
                $("#help-sms").html(strdanger('用户名/密码/手机号不合法'));
                console.log('validate error, send sms not permit');
                return false;
            }
        });
    })();
    
    /* 学校2级联动 */
    (function() {
    	var $city = $("#js-city");
    	$.getJSON("./data/schools.json", function(schools) {
        	
        	var $levelOne = $city.children(".prov");
        	var $levelTwo = $city.children(".city");
        	var $inputSchool = $("#input-school");
        	
        	// 初期化
        	$.map(schools['citylist'], function(item, index) {
        		var $option = $("<option>").val(item.p).html(item.p);
        		$levelOne.append($option);
        	});
        	var defaultValue = $levelOne.val();        	
        	$.map(schools['citylist'], function(item, index) {
        		if (item.p === defaultValue) {
        			$.map(item.c, function(child, index) {
        				var $option = $("<option>").val(child.n).html(child.n);
        				$levelTwo.append($option);
        			});
        		}
        	});
        	$inputSchool.val($levelOne.val() + $levelTwo.val());
        	// on change
        	$levelOne.on("change", function() {
        		var change_name = this.value;        		
        		$levelTwo.empty();
        		$.map(schools['citylist'], function(item, index) {
        			if (item.p == change_name) {
        				$.map(item.c, function(child, ii) {
        					var $option = $("<option>").val(child.n).html(child.n);
        					$levelTwo.append($option);      					
        					$inputSchool.val($levelOne.val() + $levelTwo.val());
        				});
        			}
        		});
        	});
			$levelTwo.on("change", function() {
				$inputSchool.val($levelOne.val() + $levelTwo.val());
			});
			
			// 设置默认地区为开封
			$levelOne.val( schools.citylist[0].p );
    	});
    })();
    
    // 班级dropdown
    (function(domid) {
    	var select = document.getElementById(domid);
    	select.innerHTML = "";    	
    	var option = null;
    	for (var i = 1; i < 51; i++) {
    		option = document.createElement("option");
    		option.value = i;
    		option.innerHTML = i;
    		select.appendChild(option); 
    	}
    	select.firstElementChild.setAttribute("selected", "selected");
    })("input-classname");

    // 验证表单内的input标签的内容是否符合规定格式
    function validateDataRegForm(form) {
        if (!checkUsername(form.username)) {
            $(form.username).focus();
            return false;
        }
        if (!checkPwd(form.pwd)) {
            $(form.pwd).focus();
            return false;
        }
        // if (!checkRepwd(form.repwd)) { $(form.repwd).focus(); return false; }
        if (!checkMobile(form.mobile)) {
            $(form.mobile).focus();
            return false;
        }
        
        if (form.type.value === "学生") {
            // 验证真实姓名
            if (!checkRealname(form.realname)) {           
            	var $span = $("<span>").addClass('text-danger').html("&times;真实姓名必填");
            	$("#help-realname").empty().append($span);
            	$(form.realname).focus();
            	return false;
            } else {
            	var $span = $("<span>").addClass('text-success').html("&radic;"); // 真实姓名合法
            	$("#help-realname").empty().append($span);
            }
            
            if (!checkEmail(form.email)) {
                $(form.email).focus();
                return false;
            }
            
            // 验证学校
            if (form.school.value.length < 3) {
            	$("#help-school").html(strdanger("学校名称至少3个字符"));
                $(form.school).focus();
                return false;
            }
            
            // 验证身份证号
            if (!checkIdnum(form.idnum)) {
                $(form.idnum).focus();
                return false;
            }
        } 
  
        var ca = document.getElementById("input-agree");
        if (!ca.checked) {
            $("#help-agree").html(strdanger('你还没有同意协议'));
            return false;
        }

        if (!ca.onchange) {
            ca.onchange = function (e) {
                e.preventDefault();
                if (ca.checked) {
                    $("#help-agree").empty();
                } else {
                    $("#help-agree").html(strdanger('你还没有同意协议'));
                }
            };
        }
        return true;
    }

    function checkUsername(obj) {
        var username = obj.value;
        var $help = $("#help-username");
        
        var pat = /^[a-zA-Z][a-zA-Z0-9_]{2,29}$/;
        if (!pat.test(username)) {
        	$help.html(strdanger('用户名必须是3-30位字母数字或下划线,字母开头'));
        	return false;
        }
                
        // 过滤特殊字符
        var pat = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        if (pat.test(username)) {
            $help.html(strdanger('用户名中不能有特殊字符'));
            return false;
        }
        $help.html("&radic;");
        
        return true;
    }

    function checkPwd(obj) {
        var pwd = obj.value;
        var $help = $("#help-pwd");
        if (0 === pwd.length) {
            $help.html(strdanger('密码不能为空'));
            return false;
        }
        // 密码(6-16位，数字/字母/符号组合)
        if (pwd.length < 6) {
            $help.html(strdanger('密码长度至少6位'));
            return false;
        } else if (pwd.length > 16) {
            $help.html(strdanger('密码长度不能超过16位'));
            return false;
        }
        /*
        if (/^[0-9]{6,16}$/.test(pwd)) {
            $help.html(strdanger('密码不能是纯数字，至少包含一个字母'));
            return false;
        }
        var pat = /^(?![^a-zA-Z]+$)(?!\D+$).{6,16}$/;
        if (!pat.test(pwd)) {
            $help.html(strdanger('密码必须包含数字和字母'));
            return false;
        }
        */
        $help.html('&radic;');
        return true;
    }

    function checkMobile(obj) {
        var mobile = obj.value;
        var $help = $("#help-mobile");
        if (0 === mobile.length) {
            $help.html(strdanger('手机号不能为空'));
            return false;
        }
        if (!TLibSSO.isPhone(mobile)) {
            $help.html(strdanger('请输入正确的手机号'));
            return false;
        }        
        
        $help.empty();
        return true;
    }

    function checkEmail(obj) {
        var email = obj.value;
        var $help = $("#help-email");

        if ($.trim(email).length) {
            if (!TLibSSO.isEmail(email)) {
                $help.html(strdanger('请输入正确的邮箱'));
                return false;
            }
        }
        $help.empty();
        return true;
    }

    /**
     * 验证真实姓名(必填)
     */
    function checkRealname(obj) {        
        var $help = $("#help-realname");
        // if (obj.value.length > 0) {
            if(TLibSSO.isRealName(obj.value)) {
            	$help.html(strsuccess('真实姓名合法'));
            } else {
                $help.html(strdanger('真实姓名不能有空格和\\/:*?\"\'<>|'));
                return false;            	
            }
        // }
        $help.html("&radic;");
        return true;
    }

    function checkIdnum(obj) {
        // idCardNoUtil
        var $help = $("#help-idnum");
        // 输入了身份证号要验证
        var idno = obj.value;
        if (idno.length > 0) {
            if (TLibSSO.isIDCardNo(idno)) {
                $help.html(strsuccess('身份证号合法'));
                console.log(TLibSSO.getIDCardInfo(idno));
                return true;
            } else {
                $help.html(strdanger('身份证号不合法'));
                return false;
            }
        }
        return true;
    }

    function checkSchool(obj) {
        var $help = $("#help-school");
        if (0 === $.trim(obj.value).length) {
            $help.html(strdanger('学校名称不能为空'));
            return false;
        }
        $help.empty();
        return true;
    }

    // 身份切换
    $("#status-wrapper").find("input[type=radio]").on("change", function() {
    	var status = form.type.value;
    	var $switch = $("#J_switch");
    	if (status === "学生") {
    		$switch.show();
    		form.style.marginTop = 0;
    	} else {
    		$switch.hide();
    		form.style.marginTop = "70px";
    	}    	
    });
});

function strdanger(msg) {
    return '<span class="text-danger">&times;'+msg+'</span>';
}

function strsuccess(msg) {
    // return '<span class="text-success">&radic;'+msg+'</span>';
	return '<span class="text-success">&radic;</span>';
}

