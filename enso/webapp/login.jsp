<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">    
    <title>同方教育在线-个人用户登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/login.css" />
    <style>
        .login {
            position: relative;
            top: 40px;
            min-height: 600px;
            height: auto;
        }
        .modal-dialog {
            top: 15%;
        }

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
<div class="login">
    <div class="panel">
        <div class="panel-heading">
            <h1>登录个人中心</h1>
            <p>还没有帐号?<a href="register.jsp">返回注册</a></p>
        </div>
        <div class="panel-body">
            <div class="Prompt"><img src="./images/prompt.png"/><span id="js-prompt"></span></div>
            <!-- action="http://127.0.0.1/ENSO/student/login" -->
            <form id="js-loginForm" class="form-horizontal" name="login" method="POST">
                <div class="form-group mbl">
                    <label for="input-username" class="col-sm-4 control-label">用户名/手机号:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="input-username" name="username" placeholder="用户名或手机号" maxlength="40" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-username"></div>
                </div>
                <div class="form-group mbl">
                    <label for="input-pwd" class="col-sm-4 control-label">密码:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="input-pwd" name="pwd" placeholder="密码" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-pwd"></div>
                </div>
                <div id="js-captcha" class="form-group mbl">
                    <label for="input-captcha" class="col-sm-4 control-label">验证码:</label>
                    <div class="col-sm-2 mar-b10">
                        <input type="text" class="form-control" id="input-captcha" name="captcha" />
                    </div>
                    <div class="col-sm-2">
                        <img class="captcha" src="" id="getcode_num" title="看不清,点击换一张" />
                    </div>
                    <div class="col-sm-4 help-block" id="help-captcha"></div>
                </div>
                <div class="form-group mbl">
                    <div class="col-sm-offset-4 col-sm-4 controls">
                        <input id="js-checkbox" type="checkbox" name="remember" title="打钩记住密码"><span class="remember">记住密码</span>
                        <div class="help-block" style="display:none;"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-4">
                        <button id="js-submit" type="submit" class="btn btn-primary btn-block">登录</button>
                    </div>
                </div>
                <div class="form-group mbl">
                    <div class="col-sm-offset-4 col-sm-4">
                        <a id="js-findpwd" href="javascript:;" style="display: none;">找回密码</a>
                        <span class="text-muted mhs" style="display: none;">|</span>
                        <span class="text-muted">还没有注册帐号？</span>
                        <a href="register.jsp">立即注册</a>
                    </div>
                </div>
                <div class="social-login"><span></span>
                    <div class="line"></div>
                </div>
            </form>
        </div>
    </div>

</div>
<script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
<div id="footer"></div>
<script>$("#footer").load("./include/footer.html");</script>
<script src="./js/common.js"></script>
<script src="./js/login.js"></script>
<script src="./js/findpwd.js"></script>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">通过注册手机号找回密码</h4>
            </div>
            <div class="modal-body">
                <div class="Prompt"><img src="./images/prompt.png"><span id="modal-prompt"></span></div>
                <form class="form-horizontal" method="POST">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="m-mobile">手机号: </label>
                        <div class="col-sm-6">
                            <input id="m-mobile" type="text" name="mobile" class="form-control" placeholder="输入注册时用的手机号" />
                        </div>
                        <div class="col-sm-3 help-block"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="m-pwd">新密码: </label>
                        <div class="col-sm-6">
                            <input id="m-pwd" type="password" name="password" class="form-control" placeholder="输入新密码" />
                        </div>
                        <div class="col-sm-3 help-block"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="m-confirmPwd">确认密码: </label>
                        <div class="col-sm-6">
                            <input id="m-confirmPwd" type="password" name="confirmPwd" class="form-control" placeholder="输入确认密码" />
                        </div>
                        <div class="col-sm-3 help-block"></div>
                    </div>
                    <div class="form-group">
                        <label for="m-code" class="col-sm-3 control-label">短信验证码:</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="m-code" name="code" placeholder="短信验证码" />
                        </div>
                        <div class="col-sm-3"><input id="js-getCode" type="button" class="btn btn-primary btn-block" value="点击获得验证码"></div>
                        <div class="col-sm-3 help-block" id="help-sms"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="js-post" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<!-- end Modal 找回密码 -->

</body>
</html>