<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="keywords" content="同方,在线教育,同方凌讯">
	<title>同方教育在线 - 我的学习轨迹</title>
	<link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
	<link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="./css/base.css" />
	<link type="text/css" rel="stylesheet" href="./css/my.css" />
	<style>#footer {margin-top: 0;} .main-wrapper {height: 950px;}</style>
	<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="top"></div>
<nav class="navbar">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">
			<img class="logo" src="./images/logo.jpg" title="同方教育在线" />
		</a>
		<div id="navbar" class="navbar-collapse collapse">
			<jsp:include page="./include/nav.jsp" flush="true" />
			<jsp:include page="./include/nav-right.jsp" flush="true" />
			<%
				String userid = null;
				String username = null;

				if (null == session) {
					out.print("<script>location.href='login.jsp?referer=mysettings.jsp'</script>");
				} else {
					userid = (String) session.getAttribute("userid");
					username = (String) session.getAttribute("username");
					if (null == userid) {
						out.print("<script>location.href='login.jsp?referer=mysettings.jsp'</script>");
					}
				}
			%>
		</div>
	</div>
</nav>

<div class="mywrapper">
	<div class="home-crumb">
		<span><a href="index.jsp">首页</a></span> <span class="crumb-arrow"><a
			href="model-school.jsp">我的示范校</a></span> <span class="crumb-arrow">学习中心</span>
	</div>
	<div class="main-wrapper">
		<div class="nav-cont" id="js_navcont">

		</div>
		<div class="main-cont">
			<div class="panel">
				<div class="panel-heading">
					<h2 class="panel-title">个人资料</h2>
				</div>
				<div class="panel-body main">
					<section class="basic">
						<h3>基本信息</h3>
						<form id="basicForm" action="/ENSO/student/setUserInfo">
							<input id="f-userid" type="hidden" name="userid"
								   value="<%=session.getAttribute("userid") %>">
							<input type="hidden" name="token" id="f-token" value="76e3740e3dcb104a89c829aa48f94ab3" />
							<ul>
								<li><label for="f-username"><b class="require-red">*</b>用户名</label>
									<div class="inputbox">
										<input type="text" id="f-username" name="username" value="" title="用户名不可修改"
											   maxlength="30" disabled="disabled" readonly> <span class="ex-tips"></span>
									</div></li>
								<li><label for="f-mobile"><b class="require-red">*</b>手机号</label>
									<div class="inputbox">
										<input type="text" id="f-mobile" name="mobile" value=""
											   maxlength="30"> <span class="ex-tips"></span>
									</div></li>
								<li id="js-sms" style="display: none;"><label for="f-sms"><b
										class="require-red">*</b>短信验证码</label>
									<div class="inputbox">
										<input type="text" id="f-sms" name="sms" value=""
											   maxlength="6">
										<button id="js-sendsms" class="btn btn-primary">获取验证码</button>
										<span class="ex-tips"></span>
									</div></li>
								<li><label for="f-email">邮箱</label>
									<div class="inputbox">
										<input type="text" id="f-email" name="email" value=""
											   title="邮箱" /> <span class="ex-tips"></span>
									</div></li>
								<li class="students_related"><label for="f-school"><b class="require-red">*</b>学校</label>
									<div class="inputbox">
										<input type="hidden" id="f-area" name="area" value="" />
										<input type="hidden" id="f-school" name="school" value="" />
										<div id="js-city">
											<select class="prov"></select>
											<select class="city"></select>
											<select class="dist"></select>
										</div>
										<span class="ex-tips"></span>
									</div></li>
								<li class="students_related"><label for="f-grade"><b class="require-red">*</b>年级</label>
									<div class="inputbox">
										<span class="select-box">
                         <select id="f-grade" name="grade">
                            <option value="1">一年级</option>
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
										</span>
									</div></li>
								<li class="students_related"><label for="f-classname"><b class="require-red">*</b>班级</label>
									<div class="inputbox">
                   <select id="f-classname" name="classname" title="真实姓名"></select>
										<!-- <input type="text" id="f-classname" name="classname" value="" />  -->
                    
										<span class="ex-tips"></span>
									</div></li>
								<li class="students_related"><label for="f-version"><b class="require-red">*</b>教材版本</label>
									<div class="inputbox">
										<span class="select-box"> <select id="f-version"  name="version">
												<option value="1">人教版</option>
												<option value="2">川教版</option>
												<option value="3">北师大版</option>
												<option value="4">苏教版</option>
												<option value="5">广教版</option>
										</select>
										</span>
									</div></li>
								<li><label for="f-realname">真实姓名</label>
									<div class="inputbox">                  
     <input type="text" id="f-realname" name="realname" value="" title="真实姓名" />                                     
                          <span class="ex-tips"></span>
									</div></li>
								<li><label for="f-idnum">身份证号</label>
									<div class="inputbox">
										<input type="text" id="f-idnum" name="idnum" value=""
											   title="身份证号" /> <span class="ex-tips"></span>
									</div></li>
							</ul>
							<button type="submit" class="btn btn-primary">保存</button>
						</form>
						<div id="js-fbBasic"></div>
					</section>
					<section class="pwd">
						<h3>修改密码</h3>
						<form id="pwdForm" action="/ENSO/resetPassword">
							<input id="pwd-userid" type="hidden" name="usernumber"
								   value="<%=session.getAttribute("userid") %>">
							<ul>
								<li><label for="pwd-oldpassword"><b
										class="require-red">*</b>原始密码</label>
									<div class="inputbox">
										<input type="password" id="pwd-oldpassword" name="oldpassword"
											   value="" maxlength="16"> <span class="ex-tips"></span>
									</div></li>
								<li><label for="pwd-password"><b
										class="require-red">*</b>新密码</label>
									<div class="inputbox">
										<input type="password" id="pwd-password" name="password"
											   value="" minlength="6" maxlength="16"> <span
											class="ex-tips"></span>
									</div></li>
								<li><label for="pwd-repassword"><b
										class="require-red">*</b>确认新密码</label>
									<div class="inputbox">
										<input type="password" id="pwd-repassword" name="repassword"
											   value="" maxlength="16"> <span class="ex-tips"></span>
									</div></li>
							</ul>
							<button type="submit" class="btn btn-primary">保存</button>
						</form>
						<div id="js-fbPwd"></div>
					</section>
				</div>
			</div>  <!-- /.panel -->
		</div>  <!-- /.main-cont -->
	</div>
</div>
<div id="footer"></div>
<script>$("#footer").load("./include/footer.html");</script>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">输入不合法</h4>
			</div>
			<div class="modal-body">
				<p id="js-error"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/my.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/libsso.js"></script>
<script type="text/javascript" src="./js/settings.js"></script>
</body>
</html>