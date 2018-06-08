<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="keywords" content="同方,在线教育,同方凌讯">
<title>同方在线教育平台</title>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/base.css" />
<title>你要访问的页面正在建设中...</title>
<style>
body {
	color: #333;
}

.block404 {
	padding: 40px 15px;
	text-align: center;
    min-height: 500px;
    height: auto;
}

.block404 h1 {
	text-align: center;
	font-size: 24px;
	margin-top: 50px;
	color: #333;
}
#footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
}
.navbar {
  background-color: #f5f5f5;
}
</style>
<script src="./js/common.js"></script>
<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<nav class="navbar">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">
			<img class="logo" src="./images/logo.jpg" title="同方教育在线" />
		</a>
		<div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
			<jsp:include page="./include/nav-right.jsp" flush="true" />
		</div>
	</div>
</nav>
  
	<div class="container">
		<div class="block404">
			<img src="./images/404tbd.jpg" />
			<!--
			<h1>很抱歉, 你要访问的页面正在建设中...</h1>
			<p class="lead">
				<a href="javascript:;" onclick="history.go(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a>&nbsp;
				回到<a href="index.jsp" title="Home Page"><span class="glyphicon glyphicon-home"></span>首页</a><br />
			</p>
			-->
		</div>
	</div>
    <script src="./bootstrap/js/jquery-1.7.2.min.js"></script>
    <div id="footer"></div>
    <script>$("#footer").load("./include/footer.html");</script>
</body>
</html>