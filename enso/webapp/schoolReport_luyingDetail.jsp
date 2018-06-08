<%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
             <title>同方教育在线-录音详情</title>
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./css/base.css" />
        <link rel="stylesheet" href="./css/index.css" />
         <link rel="stylesheet"  href="./css/schoolReport/schoolreport-ly.css" />
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
    <div class="w" >
        <div class="ly_title">
            <span>校园记者站 ></span>
            <span>主题名称 ></span>
            <span>报道标题 </span>
        </div>
        <diV class="mid_content">
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

            <!--封面图-->

            <div class="fm-des"">
                <div class="fm-image">
                    <img src="./images/sr_station/ly_fm.jpg" alt="图片加载失败">
                </div>
                <!--作品摘要区-->
                <div class="digest">哈哈嘻嘻嘻嘻嘻嘻嘻嘻哈哈嘻嘻嘻嘻嘻嘻嘻嘻哈哈嘻嘻嘻嘻嘻嘻嘻嘻哈哈嘻嘻嘻嘻嘻嘻嘻嘻</div>
            </div>
            <div class="player clearfix">
                <audio src="./images/sr_station/Kalimba.mp3"></audio>
                <div class="controls">
                    <!--播放/暂停-->
                    <a href="javascript:;" class=" switch glyphicon glyphicon-pause"></a>
                    <!--播放进度-->
                    <div class="progress">
                        <div class="line"></div>
                        <div class="bar"></div>
                    </div>
                    <!--当前播放时间/播放总时长-->
                    <div class="timer">
                        <span class="current">00:00:00</span> / <span class="total">00:00:00</span>
                    </div>
                    <!--音量-->
                    <a href="javascript:;" class="expand glyphicon glyphicon-volume-up"></a>
                    <div class="yl_progress">
                       <div class="yl_bar"></div>
                    </div>
                </div>
            </div>
            <!-- 点赞功能收藏区 -->
            <span class="ly_function">
				<!--左点赞-->
				<span class="ly_dz">
					<!--点赞数量统计-->
				   <img src="./images/sr_station/u645.png" alt=""> <span id="dzs">100</span>
				</span>
				<div class="ly_sc">
					<img  id ="ly_image" src="./images/sr_station/u646.png" alt="">  
					<span>收藏</span>
		        </div>
				<!--微信图标-->
	            <img  class="wx_image" src="./images/sr_station/u643.png" alt="">
				<!--评论数-->
				<div id="ly_pls" class="text ">
	                 <p><span>共123条评论</span></p>
				</div>
			</span>
				<!--输入评论框-->
			<div class="input-group ">
					  <input type="text" class="form-control" placeholder="请写下您的评论">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button">提交评论</button>
					      </span>
	            </div>
		         <!--评论区-->
				 <div id="tw_plq" >
					 <!--评论框第一条评论-->
					 <div class="tw_plq_inner">
							 <div class="tw_plq_record">
								 <img id="u653_img" class="img " src="./images/sr_station/u653.png"/>
								 <span>用户名</span>
								 <p><span>今天 12:12:38</span></p>
								 <p id="tw_write">评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容</p>
								 <span id="more_answer"><a href="#">回复</a></span>
			
							 </div>
							 <div class="tw_plq_record">
								 <img id="u653_img" class="img " src="./images/sr_station/u653.png"/>
								 <span>用户名</span>
								 <p><span>今天 12:12:38</span></p>
								 <p id="tw_write">评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容</p>
			                     <span id="more_answer"><a href="#">回复</a></span>
							 </div>
							 <div class="tw_plq_record">
								 <img id="u653_img" class="img " src="./images/sr_station/u653.png"/>
								 <span>用户名</span>
								 <p><span>今天 12:12:38</span></p>
								 <p id="tw_write">评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容</p>
			                     <span id="more_answer"><a href="#">回复</a></span>
							 </div>
							 <div class="tw_plq_record">
								 <img id="u653_img" class="img " src="./images/sr_station/u653.png"/>
								 <span>用户名</span>
								 <p><span>今天 12:12:38</span></p>
								 <p id="tw_write">评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容</p>
			                      <span id="more_answer"><a href="#">回复</a></span>
							 </div>
						   <span class="tw_dot">........</span>
						   <a href="#" id="tw_loadMore">加载更多回复</a>
					 </div>

           

            

           

           
        </diV>
    </div>

    <script src="./js/school-report/jquery.min.js"></script>
</body>
</html>