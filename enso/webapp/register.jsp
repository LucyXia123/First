<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线-用户注册</title>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/login.css" />
    <style>
      #footer {margin-top: 50px;}
    </style>
</head>
<body>
<div id="top"></div>
<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">
			<img class="logo" src="./images/logo.jpg" title="同方教育在线" />
		</a>
        </div>
    </div>
</nav>
<div class="register">
    <div class="panel">
        <div class="panel-heading">
            <h1>免费注册</h1>
            <p>已有帐号?<a href="login.jsp">直接登录</a></p>
        </div>
        <div class="panel-body wrap-login-list">
            <div class="Prompt"><img src="./images/prompt.png"/><span id="js-prompt"></span></div>
            <form id="js-regForm" class="form-horizontal" name="register" method="POST">
                <div class="form-group">
                    <label for="input-username" class="col-sm-4 control-label"><b class="require-red">*</b>用户名:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-username" name="username" maxlength="32" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-username"></div>
                </div>

                <div class="form-group">
                    <label for="input-pwd" class="col-sm-4 control-label"><b class="require-red">*</b>密码:</label>
                    <div class="col-sm-4 password">
                        <input type="password" class="form-control" id="input-pwd" name="pwd" maxlength="16" />
                        <b class="m1" title="点击显示/隐藏密码"></b>
                        <div class="psd-Strength">
                            <div>
                                <span class="danger"></span>
                                <span></span>
                                <span></span>
                            </div>
                            <div>
                                <label>危险</label>
                                <label>一般</label>
                                <label>安全</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 help-block" id="help-pwd"></div>
                </div>
                <div class="form-group">
                    <label for="input-mobile" class="col-sm-4 control-label"><b class="require-red">*</b>手机:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-mobile" name="mobile" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-mobile"></div>
                </div>

                <div id="js-fg-sms" class="form-group" style="display: none;">
                    <label for="input-sms" class="col-sm-4 control-label"><b class="require-red">*</b>短信验证码:</label>
                    <div class="col-sm-2 mar-b10">
                        <input type="text" class="form-control" id="input-sms" name="sms" />
                    </div>
                    <div class="col-sm-2"><input id="js-getCode" type="button" class="btn btn-primary btn-block" value="免费获得验证码"></div>
                    <div class="col-sm-4 help-block" id="help-sms"></div>
                </div>
                <div class="form-group">
                   <label for="input-type" class="col-sm-4 control-label">身份:</label>
                   <div class="col-sm-4" id="status-wrapper">
                        <input id="input-type" type="radio" name="type" value="学生" checked="checked" /><span>学生</span>
                        <input type="radio" name="type" value="家长" /><span>家长</span>
                        <input type="radio" name="type" value="游客" /><span>游客</span>                        
                    </div>                    
                </div>
                <!-- 学生身份才显示 -->
                <div id="J_switch">
                      <div class="form-group">
                    <label for="input-realname" class="col-sm-4 control-label"><b class="require-red">*</b>真实姓名:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-realname" name="realname" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-realname"></div>
                </div>
                <div class="form-group">
                    <label for="input-email" class="col-sm-4 control-label">邮箱:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-email" name="email" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-email"></div>
                </div>
                <!-- 学校城市3级联动 -->
                <div class="form-group">
                    <label for="input-school" class="col-sm-4 control-label"><b class="require-red">*</b>学校:</label>
                <div class="col-sm-4">
                <input id="input-school" class="form-control" name="school" type="hidden"/>
                <div id="js-city">
                  <select class="prov" name="area"></select>
                  <select class="city"></select>                  
                </div>
               </div>
                <div class="col-sm-4 help-block" id="help-school"></div>
                </div>
                <div class="form-group">
                    <label for="input-grade" class="col-sm-4 control-label"><b class="require-red">*</b>年级:</label>
                    <div class="col-sm-4">
                        <select class="form-control select" name="grade" id="input-grade">
                            <option value="1" selected>一年级</option>
                            <option value="2">二年级</option>
                            <option value="3">三年级</option>
                            <option value="4">四年级</option>
                            <option value="5">五年级</option>
                            <option value="6">六年级</option>
                            <option value="7">七年级</option>
                            <option value="8">八年级</option>
                            <option value="9">九年级</option>
                            <option value="10">十年级</option>
                            <option value="11">十一年级</option>
                            <option value="12">十二年级</option>
                        </select>
                    </div>
                </div>
                <!-- class name 班级 -->
                <div class="form-group">
                    <label for="input-classname" class="col-sm-4 control-label"><b class="require-red">*</b>班级:</label>
                    <div class="col-sm-4">
                    <!-- <input type="text" class="form-control" id="input-classname" name="classname" title="输入所在班级(数字)" value="1" /> -->
                        <select id="input-classname" class="form-control" name="classname" title="选择所在班级(数字)"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-version" class="col-sm-4 control-label"><b class="require-red">*</b>教材版本:</label>
                    <div class="col-sm-4">
                        <select class="form-control select" name="version" id="input-version">
                            <option value="1" selected>人教版</option>
                            <option value="2">川教版</option>
                            <option value="3">北师大版</option>
                            <option value="4">苏教版</option>
                            <option value="5">广教版</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-idnum" class="col-sm-4 control-label">身份证号:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-idnum" name="idnum" title="输入18位或15位身份证号码" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-idnum"></div>
                </div>
                </div>
               <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-4">
                        <div class="checkbox">
                            <label>
                                <!--http://www.dearedu.com/help/xy/-->
                                <input id="input-agree" type="checkbox" checked="checked"> 同意<a href="contract.jsp" target="_blank">《同方在线教育平台服务协议》</a>
                            </label>
                        </div>
                    </div>
                    <div class="col-sm-4 help-block" id="help-agree"></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-4">
                        <button id="js-submit" type="submit" class="btn btn-primary btn-block">免费注册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<div id="footer"></div>

<div id="modal-1" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">注册</h4>
            </div>
            <div class="modal-body">
                <p><img class="exclaim" src="./images/prompt.png"><span id="js-cause">注册失败!</span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div id="modal-2" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">注册</h4>
            </div>
            <div class="modal-body">
                <p class="done"><span>注册成功, 正在跳转...</span></p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" href="login.jsp">确定</a>
                <a class="btn btn-default" data-dismiss="modal">关闭</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
<!--
<script type="text/javascript" src="./bootstrap/js/jquery.cityselect.js"></script> 
 -->
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./bootstrap/js/jquery.cookie.js"></script>
<script type="text/javascript" src="./js/libsso.js"></script>
<script type="text/javascript" src="./js/register.js"></script>
<script>
(function() {
	var labels = document.getElementsByTagName("label");
	if (Array.from) {
		var a = Array.from(labels);
		a.forEach(function(item) {
			item.title = "*为必填项";
		});
		labels = null;
	} else {
		for (var i = 0; i < labels.length; i++) {
			labels[i].title = "*为必填项";
		}
	}	
	
	$("#footer").load("./include/footer.html");
})();
</script>
</body>
</html>