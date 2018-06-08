<%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
            <title>同方教育在线-记者站报道详情预览中状态</title>
            <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />

            <link rel="stylesheet" href="./css/base.css" />
            <link rel="stylesheet" href="./css/index.css" />
            <link rel="stylesheet"  href="./css/schoolReport/yulanState.css" />
            <link rel="stylesheet"  href="./css/schoolReport/tuwen.css" />
           
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
         <!--音频那张top-->
   <div class="rd_ly_top">
  
            <span>校园记者站 ></span>
            <span>主题名称 ></span>
            <span class="now">报道标题 </span>
       
    </div>
     <diV class="tw_mid_content">
            <!--报道标题-->
            <div style="width: 100%;height: 120px">
                <dl>
                    <dt>报道标题报道标题报道标题报道标题报道标题报道标题报道标题报道标题</dt>
                    <!--作者-->
                    <dd>作者:刘德华</dd>
                    <!--地区学校名称-->
                    <dd><span>北京</span>---<span>实验小学</span></dd>
                    <!--时间-->
                    <dd>2018:01:02:03</dd>
                    <!--浏览量-->
                    <dd>12222次浏览</dd>
                </dl>
            </div>
    <!--报道图片-->
	  <div class="tw_image"></div>

    <!--报道摘要-->
	  <span class="tw_digest">报道摘要报道摘要报道摘要报道摘要报道摘要</span>
	  <!--报道框-->
	  <p class="tw_box"></p>
    

		 <span id="yl_continue">继续编辑</span>
		 <span id="yl_confirm">确认发布</span>
	 <script src="./bootstrap/js/jquery.js"></script>
	 <script src="./bootstrap/js/bootstrap.js"></script>

</body>
</html>