<%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
            <title>同方教育在线-记者站我的收藏</title>
            <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />

            <link rel="stylesheet" href="./css/base.css" />
            <link rel="stylesheet" href="./css/index.css" />
            <link rel="stylesheet"  href="./css/schoolReport/sr_myPost.css" />
            <link rel="stylesheet"  href="./css/schoolReport/mySave.css" />
            <link rel="stylesheet"  href="./css/schoolReport/myTask.css" />
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
        <div class="container-fluid">
		 <!--路径导航-->
		 <ol class="yl_nav">
			 <li><a href="#">首页</a></li>
			 <li>> 我的示范校</li>
			 <li class="now">> 学习中心</li>
		 </ol>
	 </div>
		 <!--侧边导航栏-->
		 <!--侧边栏-->
	  <div class="container" >
	       <div class="row">
	       <aside class="yl_aside  col-lg-3">
			 <!--用户-->
			 <div class="user">
				 <img src="./images/sr_station/u643.png" alt=""/>
				 <!--头像名字-->
				 <span>hooby</span>
			 </div>
			 <!--菜单-->
			 <div class="yl_menu ">
				 <ul>
					 <li><a href="#"><span class="glyphicon glyphicon-user"></span> 我的学习轨迹</a></li>
					 <li>
						 <a href="javascript:;"><span class="glyphicon glyphicon-list"></span> 我的活动</a>
					 </li>
					 <li ><a href="#" ><span class="glyphicon glyphicon-tags "></span> 我的投稿</a></li>
					 <li><a href="#" class="active"><span class="glyphicon glyphicon-tags"></span> 我的任务</a></li>
					 <li><a href="#" ><span class="glyphicon glyphicon-tags"></span> 我的收藏</a></li>
					 <li><a href="#"><span class="glyphicon glyphicon-tags"></span> 账号设置</a></li>
				 </ul>
			 </div>
		 </aside>
		 <!--中间区域-->
		 <div class="yl_content  col-lg-9">
		  <span>进行中的任务</span>
		  <div class="row">
            <div class="col-lg-4">
             <span>任务标题</span>
             <p>主题任务</p>
             <p>答题任务</p>
              <p></p>
            </div>
            <div class="col-lg-4">
             <span>任务周期</span>
             <p>2018.01.01~2018.01.02</p>
             <p>2018.01.01~2018.01.02</p>
            </div>
            <div class="col-lg-4">
                <div class="myTask_compelet">
                   <a href="schoolReport_themeAsk.html">去完成</a>
                   <a href="schoolReport-askAnswerTask.html">去完成</a>
                </div>
               
            </div>
            </div>
	        <div class="page">
		        <ul class="pagination pagination-sm">
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
          
		   <!-- 已经完成的任务 -->
		   <span>已经完成的任务</span>
		   <div class="row">
             <div class="col-lg-4">
             <span>任务标题</span>
             <p>主题任务</p>
             <p>答题任务</p>
              <p></p>
            </div>
            <div class="col-lg-4">
             <span>任务周期</span>
             <p>2018.01.01~2018.01.02</p>
             <p>2018.01.01~2018.01.02</p>
            </div>
            <div class="col-lg-4">
                <span>完成日期</span>
                <p>2018.01.01~2018.01.02</p>
                <p>2018.01.01~2018.01.02</p>
            </div>
               
            </div>
            </div>
	        <div class="second_page">
		        <ul class="pagination pagination-sm">
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
          
		 
	     </div>
	</div>

</body>
</html>