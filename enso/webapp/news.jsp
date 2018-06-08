<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线-文章详情</title>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/content.css" />
    <script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
  </head>
  <body id="news">
  
<nav class="navbar">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">
			<img class="logo" src="./images/logo.jpg" title="同方教育在线" />
		</a>
		<div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
			<jsp:include page="./include/nav-right.jsp" flush="true" />
		</div>
		<!--/.navbar-collapse -->
	</div>
</nav>
    
    <div class="container">
      <ol class="breadcrumb mar-t40">
        <li><a href="index.jsp">首页</a></li>
        <li><a href="model-school.jsp">示范校</a></li>
        <li><a href="news.jsp?id=1">教育信息</a></li>
        <li class="active"><a href="javascript:;">国内教育经费支出占GDP比例连续5年超过4%</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title">国内教育经费支出占GDP比例连续5年超过4%</h1>
          <div class="article-metas">            
            <div id="js-time">2017年09月29日 09:22</div><span>|</span>
            <div>作者: <span id="js-author">刘洋</span></div><span class="last">|</span>
            <div>来源: <span>工人日报</span></div>     
          </div>
        </div>
        <div class="article-body"><article id="js-content"></article></div>
      </div>
<!--      <div class="related mar-t40">
          <div class="separate-line"><div class="highlight"></div></div>
          <div class="related-header">
            <h2>相关新闻</h2>
            <a class="more" href="404tbd.jsp" target="_blank">更多</a>
          </div>
          <div class="related-body" id="js-related">
            <ul class="related-titles mar-t10">
              <li><a href="news.jsp?id=1">一篇文章既有新闻，又有图片该如何存储到数据库</a></li>
              <li><a href="news.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="news.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="news.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="news.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="news.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
            </ul>
          </div>
      </div>-->
    </div>
    
    <jsp:include page="./include/footer.html" flush="true" />
  </body>
  <script type="text/javascript" src="./js/common.js"></script>
  <script type="text/javascript" src="./js/news.js"></script>
</html>
