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
<link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="./css/base.css" />
<link type="text/css" rel="stylesheet" href="./css/my.css" />
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
      out.print("<script>location.href='login.jsp?referer=mytime.jsp'</script>");
    } else {
      userid = (String) session.getAttribute("userid");
      username = (String) session.getAttribute("username");
      if (null == userid) {
        out.print("<script>location.href='login.jsp?referer=mytime.jsp'</script>");
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
          <div class="nav-cont">
      <nav class="nav-block">
        <ul>
          <li class="person"><i></i> <img
            src="./publish/user/avatar/avatar.png" alt="用户头像" /> <span>用户名</span>
          </li>
          <li class="select"><a href="mytrack.jsp"> <i
              class="class-image"><span
                class="glyphicon glyphicon-calendar"></span></i> <span
              class="mar-left">我的学习轨迹</span>
          </a></li>
          <li class="select"><a href="mycourse.jsp"> <i
              class="class-image"><span class="glyphicon glyphicon-th-list"></span></i>
              <span class="mar-left">我的课程</span>
          </a></li>
          <li class="select"><a href="mycredit.jsp"> <i
              class="class-image"><span class="glyphicon glyphicon-bitcoin"></span></i>
              <span class="mar-left">我的积分</span>
          </a></li>
          <li class="select active"><a href="mytime.jsp"> <i
              class="class-image"><span class="glyphicon glyphicon-time"></span></i>
              <span class="mar-left">我的学时</span>
          </a></li>
          <li class="select"><a href="mysettings.jsp"> <i
              class="class-image"><span class="glyphicon glyphicon-cog"></span></i>
              <span class="mar-left">账号设置</span>
          </a></li>
        </ul>
      </nav>
    </div>
    <div class="main-cont">
      <div class="panel user-course">
        <div class="panel-heading">
          <h3 class="panel-title">我的学时</h3>
        </div>
        <div class="panel-body">正在建设中...</div>
      </div>
    </div>
        </div>
	
	</div>
       <div id="footer"></div>
   <script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>        
   <script>$("#footer").load("./include/footer.html");</script>
	<script type="text/javascript" src="./js/common.js"></script>
	<script type="text/javascript" src="./js/my.js"></script>
</body>
</html>