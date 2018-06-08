<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="keywords" content="同方,在线教育,同方凌讯">
<title>同方教育在线&middot;活动详情</title>
<link rel="shortcut icon" type="image/x-icon"
  href="./images/favicon.png">
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/base.css" />
<link rel="stylesheet" href="./css/content.css" />
<style>
.article-metas {
  min-width: 370px;
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
  height: 360px;
  width: 360px;
}

.related-body ul.related-titles>li {
  width: 50%;
}
</style>
<script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body id="news">
  <nav class="navbar">
    <div class="container-fluid">
      <a class="navbar-brand" href="index.jsp"> <img class="logo" src="./images/logo.jpg" title="同方教育在线" /></a>
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
      <li><a href="model-school.jsp">活动采风</a></li>
      <li class="active"><a href="javascript:;">正文</a></li>
    </ol>
    <div class="article">
      <div class="article-header">
        <h1 class="t-c" id="js-title"></h1>
        <div class="article-metas">
          <div>学校:开封十四中东校区(21中)</div>
          <span>|</span>
          <div>日期: <span id="js-datetime">2017年09月29日 09:22</span></div>
        </div>
      </div>
      <div class="article-body">
        <div>
          <img id="js-img" class="avatar" src="" />
        </div>
        <article id="js-content"></article>
      </div>
    </div>
        
    <div class="related mar-t40">
      <div class="separate-line">
        <div class="highlight"></div>
      </div>
      <div class="related-header">
        <h2>相关活动</h2>
        <a class="more" href="more-act.jsp" target="_blank">更多</a>
      </div>
      <div class="related-body" id="js-related">
        <ul class="related-titles mar-t10">
          <li><a class="article-title" href="21activity.jsp?model=21activity&amp;info=100&amp;name=101我校志愿者参与骑行宣传活动.jpg&amp;title=开封市第十四中学东校区（21中）志愿者积极参加2017年12.5国际志愿者日&amp;date=2017年12月5日">开封市第十四中学东校区（21中）志愿者积极参加2017年12.5国际志愿者日</a></li>
          <li><a class="article-title" href="21activity.jsp?model=21activity&amp;info=200&amp;name=203左校长在做题.jpg&amp;title=开封市第二十一中学组织开展“学习党的十九大精神”知识答题活动&amp;date=2017年11月30日">开封市第二十一中学组织开展“学习党的十九大精神”知识答题活动</a></li>
          <li><a class="article-title" href="21activity.jsp?model=21activity&amp;info=300&amp;name=301主持人风采赛活动1.jpg&amp;title=开封市第十四中学东校区（21中）-举行校园主持人风采赛date=2017年12月5日">开封市第十四中学东校区（21中）-举行校园主持人风采赛</a></li>
          <li><a class="article-title" href="21activity.jsp?model=21activity&amp;info=400&amp;name=404寇书记讲话.jpg&amp;title=开封市第十四中学东校区（21中）团校开班仪式暨培训课程第一讲&amp;date=2017年11月28日">开封市第十四中学东校区（21中）团校开班仪式暨培训课程第一讲</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="footer"></div>
<script type="text/javascript" src="./js/common.js"></script>
<script>
$(function () {
	$("#footer").load("./include/footer.html");
	
    urlinfo = decodeURI(window.location.href); //获取当前页面的url
           
  $.ajax({
    type: "GET",
    url: "./publish/21school/"+ GetArgsFromHref(urlinfo,"model")+"/"+GetArgsFromHref(urlinfo,"info")+".json",
    dataType: "json",                
  }).done(function(data) {
    var content = "<p>";
    for(var i=0;i<data.contexts.length;i++) {
      content += data.contexts[i]+"\n";
    }
    content += "</p>";
    $("#js-content").html(content);
  });
  $("#js-title").text(GetArgsFromHref(urlinfo,"title"));
  $("#js-datetime").text(GetArgsFromHref(urlinfo,"date"));
            
  $("#js-img").attr("src","./publish/21school/"+ GetArgsFromHref(urlinfo,"model")+"/"+GetArgsFromHref(urlinfo,"name"));
});

 function GetArgsFromHref(sHref, sArgName)
            {
                var args = sHref.split("?");
                var retval = "";

                /* 参数为空 */
                if (args[0] == sHref) {
                	/* 无需做任何处理 */
                    return retval;
                }
                var str = args[1];
                args = str.split("&");
                for (var i = 0; i < args.length; i++) {
                    str = args[i];
                    var arg = str.split("=");
                    if (arg.length <= 1)
                        continue;
                    if (arg[0] == sArgName)
                        retval = arg[1];
                }
                return retval;
            }
    </script>
</body>

</html>
