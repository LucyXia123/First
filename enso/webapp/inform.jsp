<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;通知公告</title>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/content.css" />
    <style>
    .article-body > article > div {
        margin-top: 10px;
        line-height: 1.8em;
        zoom: 1;
        font-size: 16px;
    }
    .article-metas {
      min-width: 180px;
      width: 180px;
    }
    </style>
    <script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
  </head>
  <body id="news">
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="index.jsp"><span class="logo">同方教育在线</span></a>
        </div>
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
        <li><a href="model-school.jsp#通知公告">通知公告</a></li>
        <li class="active"><a href="javascript:;">普及电梯安全知识 增强学生防身意识</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title">普及电梯安全知识 增强学生防身意识</h1>
          <div class="article-metas">            
            <div id="js-date">2017年09月29日 09:22</div><span>|</span>
            <!-- <div>作者: <span id="js-author">刘洋</span></div><span class="last">|</span> -->
            <div><a id="js-url" href="">原文链接</a></div>      
          </div>
        </div>
        <div class="article-body">
          <article id="js-content"></article>
         </div>
      </div>
      <div class="related mar-t40">
          <div class="separate-line"><div class="highlight"></div></div>
          <div class="related-header">
            <h2>相关公告</h2>
            <a class="more" href="404tbd.jsp" target="_blank">更多</a>
          </div>
          <div class="related-body" id="js-related">
            <ul class="related-titles mar-t10">
              <li><a href="inform.jsp?id=1">一篇文章既有新闻，又有图片该如何存储到数据库</a></li>
              <li><a href="inform.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="inform.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="inform.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="inform.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
              <li><a href="inform.jsp?id=1">90后毕业写小说：月收入高达50万</a></li>
            </ul>
          </div>
      </div>
    </div>
    
   <div id="footer"></div>
   <script>$("#footer").load("./include/footer.html");</script>
  </body>
  <script type="text/javascript" src="./js/common.js"></script>
  <script>
  $(function() {
		var qs = enso_query_string();
		if (!qs.hasOwnProperty("id")) {
			document.write("<h5>参数错误: ?id=x</h5>");
			return false;
		}
		var id = qs.id;
		
		// 根据文章id加载文章的所有属性和正文
		(function() {
			var $content = $("#js-content");
			$.ajax({
				type: "GET",
				url: "./news/getInform",
				data: {"id": id}
			}).done(function(resp) {
				var data = resp.informList[0];
				if (null === data || {} === data) {
					document.write("<h5>没有这条通知: ?id="+id+"</h5>");
					return false;
				}
				Object.keys(data).forEach(function(key, idx) {
					if (key === "url") {
						$("#js-url").attr("url", data.url);
					} else {
						if (key !== "id") {
							var $target = $("#js-" + key);
							$target.length>0 && $target.html(data[key]);
						}
						// 文章标题
						if (key === "title") {
							$(".breadcrumb").find("li.active").html("<a href='javascript:;'>"+data[key]+"</a>")
						}	
					}				
				});
			}).fail(function(jqXHR, textStatus, errorThrown) {
				$content.html("<p>文章加载失败: "+ textStatus +"</p>");
			});
			$content.html("<p>文章加载中...</p>");
		})();
		
		// 加载相关文章id和标题
		(function() {
			var $target = $("#js-related");
			$.ajax({
				type: "GET",
				url: "./news/listInform",
				data: {"limit":6}
			}).done(function(resp) {
				var data = resp.informList;
				var $ul = $("<ul>").addClass("related-titles").addClass("mar-t10");			
				Array.prototype.forEach.call(data, function(elem) {
					var $a = $("<a>").attr("href", "inform.jsp?id="+elem.id).text(elem.title);
					var $li = $("<li>").append($a);				
					$ul.append($li);
				});
				$target.empty().append($ul);
			});
			$target.html("<p>相关文章列表加载中...</p>");
		})();
		
		var $ul = $(".header-tc-content").children(".header-user-menu-list").children(".header-user-menu-item");
		// login referer
		$ul.first().children("a").attr("href", "login.jsp?referer=" + escape(location.href));
		// logout referer
		$ul.last().children("a").attr("href", "login.jsp?referer=" + escape(location.href));
  });

  </script>
</html>
