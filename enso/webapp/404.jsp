<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;页面建设中...</title>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <title>找不到该页面...</title>    
    <style>
        body {
            color: #333;
        }
        .block404 {
            padding: 40px 15px;
            text-align: center;
        }
        .block404 h1 {
            text-align: center;
            font-size: 24px;
            margin-top: 50px;
            color: #333;
        }
        #footer {
            position: absolute;
            left:0;
            bottom:0;
        }
    </style>
    <script src="./js/common.js"></script>
    <script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp"><span class="logo">同方教育在线</span></a>
        </div>
		<div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
			<jsp:include page="./include/nav-right.jsp" flush="true" />
		</div>
    </div>
</nav>
<div class="container">
    <div class="block404">
        <h1>抱歉！找不到该页面......</h1>
        <p class="lead">回到<a href="index.jsp" title="/">首页</a><br /></p>
    </div>
</div>
<div id="footer"></div>
<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
<script>
$("#footer").load("./include/footer.html");
</script>
</body>
</html>