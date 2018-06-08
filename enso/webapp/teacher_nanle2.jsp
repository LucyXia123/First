<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;教师详情</title>
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
    .related-body ul.related-titles > li {
      width: 25%;
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
        <li><a href="model-school.jsp#教师风采">教师/学生风采</a></li>        
        <li class="active"><a href="javascript:;">姓名</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title"></h1>
          <div class="article-metas">
            <div>学校: <span id="js-school"><% out.print(session.getAttribute("school"));%></span></div><span>|</span>           
            <div>日期: <span id="js-datetime">2017年09月29日 09:22</span></div>                           
          </div>
        </div>
        <div class="article-body">
          <div><img id="js-img" class="avatar" src="" /></div>
          <article id="js-content"></article>
         </div>
      </div>
      <!-- 相关教师 -->
      <div class="related mar-t40">
          <div class="separate-line"><div class="highlight"></div></div>
          <div class="related-header">
            <h2>相关教师/学生</h2>
            <a class="more" href="404tbd.jsp" target="_blank">更多</a>
          </div>
          <div class="related-body" id="js-related">
            <ul class="related-titles mar-t10">
              <!-- <li><a href="news.jsp?id=1">一篇文章既有新闻，又有图片该如何存储到数据库</a></li> -->
            </ul>
          </div>
      </div>
    </div>    
    <div id="footer"><jsp:include page="./include/footer.html" flush="true" /></div>
    
  </body>
  <script type="text/javascript" src="./js/common.js"></script>
  <script>
  (function() {
	  var qs = enso_query_string();
	  var g_id = qs.id;
	  $.getJSON("./include/json/nlxdecjzx/"+qs.id+".json", function(data)
          {
               $(".breadcrumb").children(".active").html("<a href=\"javascript:;\">"+data.name+"</a>");
                $("#js-title").text(data.name + "简介");
                $("#js-datetime").text(data.datetime);
                $("#js-school").text(data.school); 
                $("#js-content").html(data.content);
                $("#js-img").attr("src", data.img); 
          });
//	  $.ajax({
//		  type: 'GET',
//		  url: "./include/"+qs.id+"json",
//		  data: {"id":g_id}
//	  }).done(function(data) {
//		  if (null == data || {}===data) {
//			  document.write("找不到这个老师: id="+g_id);
//			  return false;
//		  }
//		  var teacher = data.teacher;
//		  try {
//			  $(".breadcrumb").children(".active").html("<a href=\"javascript:;\">"+teacher.name+"</a>");
//			  $("#js-title").text(teacher.name + "简介");
//			  $("#js-datetime").text(teacher.datetime);
//			  var $school = $("#js-school");
//			  var s = $school.text();
//			  if (null===s || "null"===s) {
//				  $school.text(teacher.school);  
//			  }			  
//			  $("#js-content").html(teacher.content);
//			  $("#js-img").attr("src", teacher.img); 
//		  } catch(e) {
//			  console.error(e);
//		  }		  
//			// BEGIN 加载相关文章id和标题
//			(function() {
//				var $target = $("#js-related");
//				var school = $("#js-school").text();
//				$.ajax({
//					type: "GET",
//					url: "./news/listTeacher",
//					data: {"school":school, "limit":4}
//				}).done(function(res) {
//					var data = res.teacherList;
//					var $ul = $("<ul>").addClass("related-titles").addClass("mar-t10");			
//					Array.prototype.forEach.call(data, function(elem) {
//						var $a = $("<a>").attr("href", "teacher.jsp?id="+elem.id).text(elem.name);
//						var $li = $("<li>").append($a);				
//						$ul.append($li);
//					});
//					$target.empty().append($ul);
//				});
//				$("#js-related").html("<p>相关文章列表加载中...</p>");
//			}).call();
//			// END 加载相关文章id和标题
//	  }) 
  }).call();
  

  </script>
</html>
