    <%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
            <title>同方教育在线-记者站首页</title>
            <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />

            <link rel="stylesheet" href="./css/base.css" />
            <link rel="stylesheet" href="./css/index.css" />
            <link rel="stylesheet"  href="./css/schoolReport/school-report.css" />
            <script type="text/javascript" src="./js/school-report/jquery.min.js"></script>
          
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
        <%
    String userid = null;
    String username = null;
    
    if (null == session) {
      out.print("<script>location.href='login.jsp?referer=mytime.jsp'</script>");
    } else {
      userid = (String) session.getAttribute("userid");
      username = (String) session.getAttribute("username");
      if (null == userid) {
        out.print("<script>location.href='login.jsp?referer=school-report-station.jsp'</script>");
      }
    } 
  %>
      <!--/.navbar-collapse -->
    </div>
  </nav>
        <div class="sr_container ">
            <div class="sr_top">
                <%--轮播图--%>
                <div class="all" id='box'>
                     <img src="./images/sr_station/banner.jpg" width="789px" height="182px">
                     <a href="schoolReport_dl.jsp"><button type="button" class="btn btn-default" id="detail">了解详情</button></a>
                </div>
               <%--右侧点亮--%>
                <div class="sr_lighter">
	               <!-- TODO -->
	                <span class="schoolName"> </span><br>
	                <span class="energyValue"></span>
	      
	         
                <span class="lightTips">恭喜！您的校区已被点亮！<br>
	                不要骄傲，多多完成任务、积极投稿，继续集赞能量，获得更高奖励！</span>
         
                </div>
            </div>

        <%--当前任务栏--%>
            <div class="sr_currentask">
          
               <div class="rec-l">
                    
                <!--   <span > 主题任务    <a href="schoolReport_themeAsk.html">去完成</a>     +1能量<br> </span>
                    <span style="padding-left:84px" >问答任务</span>    <a href="schoolReport-askAnswerTask.html">去完成</a>    +1能量 <br></span>
                </div>  -->
            </div>
        <%--主题报道--%>
            <div class="themeActive">
                <ul id="myTab" class="nav nav-tabs">
                <!-- <li>
                      <a href='javasccript:;' data-toggle='tab' >公共主题</a></li>
                <li>
                      <a href='javasccript:;' data-toggle='tab' >任务主题</a></li>
 -->                     <a href="javascript:;">
                     <span id="detail" >参与主题</span></a>
                      <a href="schoolReport_lookAllTheme.jsp">
                      <span id="detail_more" >查看全部主题</span></a>
                </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="home">
                    <!-- <div class="pro-bd">
                        <ul>
                            <li>
                                <div class="pic">
                                  <img src="./images/pic1.png" >
                                </div>
                                <div class="pro-title">
                                报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                                </div>
                                <p>
                                  <span>by:用户名</span><br>
                                  <span> <img src="./images/u182.png"> 100</span>&nbsp&nbsp&nbsp&nbsp&nbsp
                                  <span> <img src="./images/u185.png"> 100</span>

                                </p>
                                <span id="reportStyle">图文</span>
                            </li>
                        </ul>
                    </div> -->
               </div>
                <div class="tab-pane fade" id="ios" style="display:none">
            <%--内容--%>
            </div>
            </div>
            </div>
        <%--全部报道--%>
        <div id="allReport">
            <span class="t">全部报道</span>
            <a href="schoolReport_themeAsk.jsp"><button type="button" class="btn btn-default" id="detail">我要投稿</button></a>
            <!--nav排序-->
            <nav class="navbar navbar-default ">
                <div class="container-fluid">
                  <a href="javascript:;"><span>最热 <i class="glyphicon glyphicon-arrow-down"></i></span></a>  
                  <a href="javascript:;"><span>最新<i class="glyphicon glyphicon-arrow-down"></i></span></a>
                  <a href="javascript:;"> <span>最赞<i class="glyphicon glyphicon-arrow-down"></i></span></a>
                  <a href="javascript:;"><span> <span>类型<i class="glyphicon glyphicon-chevron-down"></i> </span></a>
                  <a href="javascript:;">  <span>学校<i class="glyphicon glyphicon-chevron-down"></i></span></a>
                    
                </div>
            </nav>
        <%--报道展示区--%>
            <div class="display">
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span > <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span> <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span> <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span> <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span> <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
                <ul>
                    <li>
                    <div class="pic">
                    <img src="./images/pic1.png">
                    </div>
                    <div class="pro-title">
                    报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题
                    </div>
                    <p>
                    <span>by:用户名</span><br>
                    <span> <img src="./images/u182.png"> 100</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span> <img src="./images/u185.png"> 100</span>

                    </p>
                    </li>
               </ul>
            </div>
         <%--分页区--%>
        <div class="page">
        <ul class="pagination pagination-lg">
        <li>
        <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        </a>
        </li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
        <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        </a>
        </li>
        </ul>
        </div>
       
       
        <div class="content-wrapper" id="js-navbottom-wrapper"></div>
		<script>$("#js-navbottom-wrapper").load("./include/nav-bottom.html");</script>
		
		<div id="footer"></div>
		<script>$("#footer").load("./include/footer.html");</script>
		
		<script type="text/javascript" src="./js/common.js"></script>
		<script type="text/javascript" src="./js/index.js"></script>
		<script type="text/javascript" src="./js/footer.js"></script>
		
        <script type="text/javascript" src="./js/school-report/schoolReport_shouye.js"></script>
       
    </body>
    </html>