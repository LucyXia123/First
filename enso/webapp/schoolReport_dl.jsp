  <%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
            <title>同方教育在线-记者站点亮校园主页</title>
            <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
            <link href="./fonts/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
            <link rel="stylesheet" href="./css/base.css" />
            <link rel="stylesheet" href="./css/index.css" /> 
            <link rel="stylesheet"  href="./css/schoolReport/sr_dl.css" />
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
        <!--页签导航  -->
        <div class="ly_title">
            <span>校园记者站 ></span>
            <span class="now">点亮校园活动主页</span>
       
        </div>
         <div class="container  w">
		  <!--顶部学校介绍 一行两列-->
			<div class="container">
				<div class="row">
					<div class="col-lg-5 ">
						<div class="schoolName">
							<img width="40px" height="40px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							<span class="f">北京市第一中学</span>
							
						</div>
						<div class="schoolDesc">活动文字介绍：本次讲堂本次讲堂本次讲堂本次讲堂本次讲堂本次讲堂本次讲堂本次讲堂</div>
					</div>
					<div class="col-lg-2">
					    <div class="dl_first" >
					      <img width="30px" height="30px" src="./images/sr_station/qh.jpg" alt="美图">
					      <span>已点亮</span>
					    
					    </div>
					    <div class="dl_last" >
						<span class=" " ><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="美图"></span>
						<span  >当前第二名</span>
						</div>
					</div>
					<div class="col-lg-5 ">
						  <div class="row">
								 <div class="col-lg-6" style="text-align: center">
									 <ul class="s">
										 <li >
										 <div>
										       <img width="30px" height="30px" src="./images/sr_station/huahua.png" alt="">
										       <span class="l">当前能量:10000</span>
										  </div>
										  
										  <div class="progress l" id="dl_value ">
								              <div class="progress-bar " role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
									          10000点
								              </div>
						                  </div>	
										 </li>
									 </ul>
									 <ul class="s">
										 <li >
											 <img width="30px" height="30px" src="./images/sr_station/huahua.png" alt="">
											 <span>当前能量:10000</span>
										 </li>
									 </ul>
									 <ul class="s">
										 <li >
											<img width="30px" height="30px" src="./images/sr_station/huahua.png" alt="">
											 <span>当前能量:10000</span>
										 </li>
									 </ul>
									 <ul class="s">
										 <li >
											 <img width="30px" height="30px" src="./images/sr_station/huahua.png" alt="">
											 <span>当前能量:10000</span>
										 </li>
									 </ul>
								 </div>
							 </div>
					</div>
				   
				    </div>
				
				</div>
			</div>
		
			<!--任务栏 两行一列-->
			<div class="container dl_task">
			  <div class="row">
					<div class="col-lg-12 ">
						<span>未来小作家：上传个人作品 能量+1<a href="#" class="now">去完成</a></span>
				    </div>
					<div class="row">
						<div class="col-lg-12 ">
							<span>未来小作家：上传个人作品 能量+1<a href="#" class="now">去完成</a></span>
		
						</div>
				   </div>
			</div>
		  </div>
		   <!--名师风采 一行五列-->
		  <div class="container dl_teacher">
						<div class="row teacherTips">
							<div class="col-lg-6 ">名师风采</div>
							<div class="col-lg-6 " style="text-align: right;font-size: 1.5rem;">由穿杨模考提供师资支持</div>
						</div>
						<div class="row">
							<div class="col-lg-2  ">
								<img   src="./images/u182.png"" alt="" >
		                        <div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							<div class="col-lg-2 ">
								<img src="./images/u182.png" alt="" >
		                        <div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							<div class="col-lg-2 ">
								<img src="./images/u182.png"" alt="" >
		
								<div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							<div class="col-lg-2 ">
								<img src="./images/u182.png"" alt="" >
		
								<div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							<div class="col-lg-2 ">
								<img src="./images/u182.png"" alt="" >
		
								<div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							<div class="col-lg-2 ">
								<img src="./images/u182.png"" alt="" >
		
								<div class="teacherInfo">
									<span class="f myfont">姬问</span>
									<span class="r">开封市第五中学</span>
								</div>
								<p>开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学开封市第五中学</p>
							</div>
							</div>
						</div>	
		   <!--学校能量风云榜 一行四列-->
		     <div class="container dl_energyBang">
		       <div class="row teacherTips ">
				  <div class="col-lg-12 ">学校能量风云榜</div>
							
			   </div>
			   <div class="l_line"></div>
			   <div class="r_line"></div>
			   <div class="myschool">我的学校</div>
			   <div class="myschool-top"></div>
			  <div class="row  dl_energyrow">
			      <div>
					<div class="col-lg-1 h_juz">
						<div class="schoolPaimimg">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							
						</div>
				    </div>
					
					<div class="col-lg-2 ">
						<div class="schoolIntro">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							<div class="schoolIntroText f">
							    <span >开封市</span>
							    <span >开封市第一小学</span>
							</div>
							
						</div>
		
					</div>
					<div class="col-lg-3 bangValue" >
						<div class="progress" id="dl_value ">
							<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
								10000点
							</div>
						</div>	
		
					</div>
					<div class="col-lg-6 " id="energy_pic">
								<ul>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
								</ul>
								<a  class="r" href="#"><span>更多</span></a>
					</div>
			   </div>
		   </div>
	       <div class="row  dl_energyrow">
			      <div>
					<div class="col-lg-1 h_juz">
						<div class="schoolPaimimg">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							
						</div>
				    </div>
					
					<div class="col-lg-2 ">
						<div class="schoolIntro">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							<div class="schoolIntroText f">
							    <span >开封市</span>
							    <span >开封市第一小学</span>
							</div>
							
						</div>
		
					</div>
					<div class="col-lg-3 bangValue" >
						<div class="progress" id="dl_value ">
							<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
								10000点
							</div>
						</div>	
		
					</div>
					<div class="col-lg-6 " id="energy_pic">
								<ul>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
								</ul>
								<a  class="r" href="#"><span>更多</span></a>
					</div>
			   </div>
		   </div>
	       <div class="row  dl_energyrow">
			      <div>
					<div class="col-lg-1 h_juz">
						<div class="schoolPaimimg">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							
						</div>
				    </div>
					
					<div class="col-lg-2 ">
						<div class="schoolIntro">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							<div class="schoolIntroText f">
							    <span >开封市</span>
							    <span >开封市第一小学</span>
							</div>
							
						</div>
		
					</div>
					<div class="col-lg-3 bangValue" >
						<div class="progress" id="dl_value ">
							<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
								10000点
							</div>
						</div>	
		
					</div>
					<div class="col-lg-6 " id="energy_pic">
								<ul>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
								</ul>
								<a  class="r" href="#"><span>更多</span></a>
					</div>
			   </div>
		   </div>
	       <div class="row  dl_energyrow">
			      <div>
					<div class="col-lg-1 h_juz">
						<div class="schoolPaimimg">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							
						</div>
				    </div>
					
					<div class="col-lg-2 ">
						<div class="schoolIntro">
							<img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f">
							<div class="schoolIntroText f">
							    <span >开封市</span>
							    <span >开封市第一小学</span>
							</div>
							
						</div>
		
					</div>
					<div class="col-lg-3 bangValue" >
						<div class="progress" id="dl_value ">
							<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:100%;">
								10000点
							</div>
						</div>	
		
					</div>
					<div class="col-lg-6 " id="energy_pic">
								<ul>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
									<li><img width="50px" height="50px" src="./images/sr_station/qh.jpg" alt="暂时没有学校信息"  class="f"></li>
								</ul>
								<a  class="r" href="#"><span>更多</span></a>
					</div>
			   </div>
		   </div>
	 
			<!--底部-->


</div>
<script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="./bootstrap/js/bootstrap.js"></script>
        
        </body>
    </html>