<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="keywords" content="同方,在线教育,同方凌讯">
	<link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
	<title>同方教育在线-首页</title>
	<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="./css/base.css" />
	<link rel="stylesheet" href="./css/index.css" />
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
<!-- 推荐轮播 -->
<%@ include file="./include/carousel.html"%>

<div class="content-wrapper">
	<!-- 合作电台 -->
	<div class="module tv">		
		<ul class="card-list">
            <!-- 开封电视台 -->            
			<li><a href="more-program.jsp?area=开封" title="http://www.kftv.net.cn/#1" target="_blank"><div class="img" style="background-image: url('./images/index/tv_01.jpg')"></div></a></li>      
            <!-- 许昌电视台 -->            
			<li><a href="http://www.xctv.cn/" target="_blank"><div class="img" style="background-image: url('./images/index/tv_02.jpg')"></div></a></li>
            <!-- 商丘电视台 -->
			<li><a href="more-program.jsp?area=商丘"  target="_blank"><div class="img" style="background-image: url('./images/index/tv_03.jpg')"></div></a></li>
            <!-- 濮阳电视台 -->
			<li><a href="more-program.jsp?area=濮阳" title="http://www.pytv.ha.cn/" target="_blank"><div class="img" style="background-image: url('./images/index/tv_04.jpg')"></div></a></li>
			<li class="last-item"><a href="more-program.jsp"><div class="img" style="background-image: url('./images/index/tv_05.jpg')"></div></a></li>
		</ul>
	</div> 
    <a class="module J_toActivity" id="activity-banner" href="act-enroll.jsp" title="未来小作家">
      <div></div>
    </a>

	<!-- 院校采风  -->
    <a name="校园采风" href="javascript:;" />
    <div id="yxcf_responsive" class="main">
          <div class="allXYCF respl-header">
        <ul class="logoTab">
          <li><img class="sub-logo" src="images/xycf-logo.png"></li>
          <li><p>校园采风</p></li>
        </ul>
        <ul id="filters" class="logoTabFilter respl-option">
            <li class="opt"><a class="opt select" href="javascript:;" data-filter=".kfyxcf">开封市</a></li>
            <li class="opt"><a class="opt" href="javascript:;" data-filter=".pyyxcf">濮阳市</a></li>
            <li class="opt"><a class="opt" href="javascript:;" data-filter=".sqyxcf">商丘市</a></li>
            <li class="opt"><a class="opt" href="javascript:;" data-filter=".xcyxcf">许昌市</a></li>
        </ul>
        <ul class="logoTabMore">
            <li><a href="more-school.jsp">全部</a></li>
        </ul>
        <ul class="XTCFList respl-items" id="js-yxcf"></ul>
      </div>   
    </div>

    <!-- 活动采风 -->
   <div class="module activity">
      <div class="module-header">
        <div class="badge">玩</div>
        <h2>活动采风</h2>
        <ul class="area-list"><li><a href="more-act.jsp" target="_blank">全部</a></li></ul>
      </div>
      <div class="module-body">
        <ul class="act-list" id="js-act-list">
          <!-- 1st row  2 * 3-->
          <li class="act-item">
            <a class="pull-left" href="#"><img src="publish/school/avatar/kaifeng14.jpg" /></a>
            <div class="pull-right">
              <a class="act-title" href="#"><h4>河南省舞蹈大赛</h4></a>
              <div class="act-content">决赛阶段，十八个市区，五十多所学校学生参与。今天决出胜负...</div>
              <div class="act-metas">1000多人观看</div>
            </div>
          </li>        
        </ul>
      </div>
    </div>
    <!-- 老师和学生介绍 -->
    <div class="module teacher">
      <div class="module-header">
        <div class="badge">人</div>
        <h2>师生风情</h2>
        <ul class="area-list" id="js-area-st">
          <li class="active"><a href="javascript:;">开封市</a></li>
          <li><a href="javascript:;">濮阳市</a></li>
          <li><a href="javascript:;">商丘市</a></li>
          <li><a href="javascript:;">许昌市</a></li>
        </ul>
      </div>
      <div class="row" id="js-shisheng">
          <!-- ./include/shisheng/{0,1,2,3}.html -->
      </div>
    </div>
    <!-- end .module.teacher -->
   <!-- 精品课程 -->
   <div class="module course">
      <div class="module-header">
        <div class="badge">课</div>
        <h2>精品课程</h2>
        <ul class="area-list"><li><a href="http://www.shuxuejia.com/math/parentsClass.html" target="_blank">全部</a></li></ul>
      </div>
      <div class="clearfix">
          <div class="pull-left">
              <!-- BEGIN 数学加课程 -->
              <div class="course-item">
                  <a href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff808081551980e901552e7f8c747713&courseId=ff808081551980e901552e9b52a57768&videoIndex=0" target="_blank">
                    <img src="publish/course/course_1.jpg" />
                  </a>
                  <div class="course-info">
                      <div class="course-title">函数模型的应用实例</div>
                      <div class="course-content">1.行驶函数中的分段函数模型 2.人口自然增长模型 3.建立模型最大化销售利润 4.建立模型分析身高与体重的关系</div>
                      <div class="course-metas">
                          <a class="click2watch" href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff808081551980e901552e7f8c747713&courseId=ff808081551980e901552e9b52a57768&videoIndex=0" target="_blank">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">90</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
              <div class="course-item">
                  <a href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff8080815de66f2f015de8d157f20134&courseId=ff8080815dc64a88015de8da09673cf9&videoIndex=0" target="_blank">
                    <img src="publish/course/course_2.jpg" />
                  </a>
                  <div class="course-info">
                      <div class="course-title">二次函数y=ax^2+bx+c的图象</div>
                      <div class="course-content">用平移观点看函数: 抛物线y=a(x-h)^2可以由抛物线y=ax^2, 向左或向右平移|h|个单位得到</div>
                      <div class="course-metas">
                          <a class="click2watch" href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff8080815de66f2f015de8d157f20134&courseId=ff8080815dc64a88015de8da09673cf9&videoIndex=0">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">188</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
              <div class="course-item">
                  <a href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff8080815689bbe601569c7f814c09af&courseId=ff8080815980dc380159826548a3026e&videoIndex=0" target="_blank">
                    <img src="publish/course/course_3.jpg" />
                  </a>
                  <div class="course-info">
                      <div class="course-title">数学广角之鸽巢问题</div>
                      <div class="course-content">&middot;鸽巢问题 &middot;最不利原则 &middot;构造鸽巢 &nbsp;鸽巢问题又称抽屉原理或鞋盒原理,它是组合数学中最简单也是最基本的原理之一,从这个原理出发,可以得出许多有趣的结果。</div>
                      <div class="course-metas">
                          <a class="click2watch" href="http://www.shuxuejia.com/shop/math_course_video!index.action?goodsId=ff8080815689bbe601569c7f814c09af&courseId=ff8080815980dc380159826548a3026e&videoIndex=0">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">18</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
              
              <!-- END 数学加课程 -->
              <div class="course-item">
                  <a href="video.jsp?videoId=37" target="_blank"><img src="http://118.190.209.209/media/poster/kaifeng5/kaifeng5.04.English.jpg" /></a>
                  <div class="course-info">
                      <div class="course-title">英语崔炳蔚</div>
                      <div class="course-content">What will happend to you if you do not eat a balanced diet?</div>
                      <div class="course-metas">
                          <a class="click2watch" href="video.jsp?videoId=37" target="_blank">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">81</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
              <div class="course-item">
                  <a href="video.jsp?videoId=36" target="_blank"><img src="http://118.190.209.209/media/poster/kaifeng5/kaifeng5.03.bio.jpg" /></a>
                  <div class="course-info">
                      <div class="course-title">生物刘志华-激素调节</div>
                      <div class="course-content">通过激素的调节: 1. 理解血糖调节过程 2.理解甲状腺激素 3. 理解激素调节特点. 人体血糖的来源和去向. 模拟血糖调节的过程.</div>
                      <div class="course-metas">
                          <a class="click2watch" href="video.jsp?videoId=36">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">98</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
              <div class="course-item">
                  <a href="video.jsp?videoId=34" target="_blank"><img src="http://118.190.209.209/media/poster/kaifeng5/kaifeng5.01.huaxue.jpg" /></a>
                  <div class="course-info">
                      <div class="course-title">化学反应的速率和限度</div>
                      <div class="course-content">教学目标：1.了解化学反应速率的概念  2. 能利用三段式计算化学反应的速率 思考：怎样描述一个化学反应的快慢？</div>
                      <div class="course-metas">
                          <a class="click2watch" href="video.jsp?videoId=34">点击观看视频</a>
                          <div class="watch">
                              <span class="glyphicon glyphicon-user"></span>已有<span class="text-success">39</span>人观看
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="pull-right choose_right paper_download">
               <h4 class="index_h4" id="shiti">
                                        历年中考真题精选
                    <a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="down_more" data-toggle="modal">查看更多</a>
                </h4>                
                <ul class="unstyled zhenti">
                       <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_real" title="2017年江苏省泰州市中考数学试卷及答案" target="_blank">2017年江苏省泰州市中考数学试卷及答案</a></li>
                       <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_real" title="2017年江苏省盐城市中考数学试卷及答案" target="_blank">2017年江苏省盐城市中考数学试卷及答案</a></li>
                       <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_real" title="2017年江苏省淮安市中考数学试卷及答案" target="_blank">2017年江苏省淮安市中考数学试卷及答案</a></li>
                       <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_real" title="2017年江苏省连云港市中考数学试卷及答案" target="_blank">2017年江苏省连云港市中考数学试卷及答...</a></li>
                       <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_real" title="2017年江苏省镇江市中考数学试卷及答案" target="_blank">2017年江苏省镇江市中考数学试卷及答案</a></li>
                </ul>
                <h4 class="index_h4"> 
                       中考模拟冲刺
                    <a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="down_more" data-toggle="modal">查看更多</a>
                </h4>
                <ul class="unstyled zhenti">
                     <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_simulate" target="_blank">基础知识梳理之不等式与不等式组 一年...</a></li>
                     <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_simulate" target="_blank" title="基础知识梳理之不等式与不等式组 五年中考荟萃">基础知识梳理之不等式与不等式组 五年...</a></li>
                     <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_simulate" target="_blank" title="基础知识梳理之不等式与不等式组 三年模拟精选">基础知识梳理之不等式与不等式组 三年...</a></li>
                     <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_simulate" target="_blank" title="基础知识梳理之二元一次方程组 一年创新导向">基础知识梳理之二元一次方程组 一年创...</a></li>
                     <li><a href="http://www.shuxuejia.com/shop/select_goods/medium_test/test.htm" class="download_simulate" target="_blank" title="基础知识梳理之二元一次方程组 五年中考荟萃">基础知识梳理之二元一次方程组 五年中...</a></li>
                </ul>
          </div>
      </div>
    </div>
   <!-- END 精品课程 -->
</div>

<!-- nav bottom -->
<div class="content-wrapper" id="js-navbottom-wrapper"></div>
<script>$("#js-navbottom-wrapper").load("./include/nav-bottom.html");</script>

<div id="footer"></div>
<script>$("#footer").load("./include/footer.html");</script>
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/index.js"></script>
</body>
</html>
