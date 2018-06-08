<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;校园采风</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/content.css" />
    <style>
    .article-metas {
      min-width: 360px;
      width: 360px;
    }
    .article-body .avatar {
      margin: 0 auto;
      display: block;
      box-sizing: border-box;      
      padding: 0px;
      border: 0px;
      outline: 0px;
      vertical-align: baseline;
      background: transparent;
      max-width: 100%;
      height: auto !important;
    }
    .article-body article p {           
       text-indent: 2em;
    }   
    </style>
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
        <li><a href="model-school.jsp#校园记者站">校园采风</a></li>        
        <li class="active"><a href="javascript:;">正文</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title">不忘初心跟党走</h1>
          <h2 class="t-c">感恩母亲，让青春更美绽放</h2>
          <h3 class="t-c">——开封十四中2017年十四岁集体生日暨离队入团仪式活动</h3>
          <div class="article-metas">
            <div>学校: <span id="js-school">开封第十四中学</span></div><span>|</span>           
            <div>日期: <span id="js-date">2017年09月29日 09:22</span></div>                           
          </div>
        </div>
        <div class="article-body">
          <div>
          	<img id="js-img" class="avatar" src="images/upload/kfyxcf-01/1.jpg" /></div>
          	<article id="js-content">
              <p>5月14日上午开封市第十四中学在校多功能教室隆重举行了校团委精心策划组织的“不忘初心跟党走，感恩母亲，让青春更美绽放?2018届十四岁集体生日暨离队入团仪式活动”。校长吕中伟、书记杜强、德育副校长李瑞全、教学副校长杨凯、办公室主任周贵勤，政教处主任万建平，八年级主任向虹出席本次活动。</p>
            </article>        
          </div>
    </div>
  </div>
  <!-- /.container -->
   <div id="footer"></div>
   <script>$("#footer").load("./include/footer.html");</script>
  </body>
  <script type="text/javascript" src="./js/common.js"></script>
 
</html>
