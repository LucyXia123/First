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
<link rel="stylesheet" href="./css/unsupport.css" />
<title>你的浏览器不支持</title>
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
          <h1>为了获得更好的用户体验，请点击下面的图标下载同方教育在线APP。</h1>
          <div class="lead">
            <a href="http://appdown.tfjyzx.com:8080/eduOnline/jyzx.apk" title="点击下载app" target="_blank"><img src="/images/browsers/app.png" /></a>            
          </div>
		</div>
	</div>
    <script src="./bootstrap/js/jquery-1.7.2.min.js"></script>
    <div id="footer"></div>
    <script>$("#footer").load("./include/footer.html");</script>
</body>
</html>