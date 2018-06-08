<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="keywords" content="同方,在线教育,同方凌讯">
<title>同方教育在线 - 我的活动</title>
<link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
<link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="./css/base.css" />
<link type="text/css" rel="stylesheet" href="./css/my.css" />
<link type="text/css" rel="stylesheet" href="./css/act.css" />
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
    </div>
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

      <!--/.navbar-collapse -->
    </div>
  </nav>

	<div class="mywrapper">
		<div class="home-crumb">
			<span><a href="index.jsp">首页</a></span> <span class="crumb-arrow"><a
				href="model-school.jsp">我的示范校</a></span> <span class="crumb-arrow">学习中心</span>
		</div>
        <div class="main-wrapper">
		  <div class="nav-cont" id="js_navcont"></div>  <!-- /.nav-cont -->
		  <div class="main-cont">
			<div class="panel">
				<div class="panel-heading">
					<h2 class="panel-title">我的活动</h2>
				</div>
				<div id="J_act" class="panel-body main">
                  <h4>各种活动...</h4>
        <!-- 
                  <h4>"未来小作家"作文评选活动</h4>
                  <div class="act-title">活动介绍、活动安排、活动详情、参与人、颁奖等介绍</div>
                  <div class="act-info">
                    
                  </div>
         -->
		        </div>
		     </div>  <!-- /.panel -->
		   </div>  <!-- /.main-cont -->
        </div>
	</div>
   <div id="footer"></div>      
   <script>$("#footer").load("./include/footer.html");</script>
    

    <script type="text/javascript" src="./js/common.js"></script>
    <!-- navbar -->
    <script type="text/javascript" src="./js/my.js"></script>

    <!-- 我的活动 -->
	<script type="text/javascript" src="./js/my-act.js"></script>

</body>
</html>