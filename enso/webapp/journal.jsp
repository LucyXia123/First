<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;院校采风</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/content.css" />
    <style>
    .article-body .avatar {
      margin: 0 auto;
      display: block;
      box-sizing: border-box;      
      padding: 0;
      border: 0;
      outline: 0;
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
      <ol class="breadcrumb mar-t40" id="J_url">
        <li><a href="index.jsp">首页</a></li>
        <li><a href="model-school.jsp#校园采风">校园采风</a></li>        
        <li class="active"><a href="javascript:;">正文</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title"></h1>
          <div class="article-metas">
            <div>学校: <span id="js-school"><% out.print(session.getAttribute("school"));%></span></div><span>|</span>           
            <div>日期: <span id="js-date"></span></div>                           
          </div>
        </div>
        <div class="article-body">
          <div><img id="js-img" class="avatar" src="" /></div>
          <article id="js-content"></article>
         </div>
      </div>
      <!-- 相关采风 -->
<!--      <div class="related mar-t40">
          <div class="separate-line"><div class="highlight"></div></div>
          <div class="related-header">
            <h2>相关活动</h2>
            <a class="more" href="more-act.jsp" target="_blank">更多</a>
          </div>
          <div class="related-body" id="js-related">
<ul class="related-titles mar-t10"><li><a href="journal.jsp?id=16">开封五中举办曹靖华诞辰120周年纪念会 - 暨2017教师节“靖华”奖表彰大会</a></li><li><a href="journal.jsp?id=15">开封五中举办彝山书院文化建设专家研讨会</a></li><li><a href="journal.jsp?id=14">省工艺美术大师吴天放参观开封五中书院文化建设</a></li><li><a href="journal.jsp?id=13">生涯规划教育</a></li></ul>
          </div>
      </div>-->
    </div>
    
   <div id="footer"></div>
   <script>$("#footer").load("./include/footer.html");</script>
  </body>
  <script type="text/javascript" src="./js/common.js"></script>
  <script>
  (function() {
    var qs = enso_query_string();
    var g_id = qs.id;
    
    $.ajax({
      type: 'GET',
      url: './news/getJournal',
      data: {"id":g_id}
    }).done(function(data) {
      if (null == data || {}===data) {
        document.write("找不到这个记者站正文: id="+g_id);
        return false;
      }
      try {       
        $("#js-title").text(data.title).attr("title", data.title);
        $("#js-date").text(data.date);
        var $school = $("#js-school").text(data.school);  
        $("#js-content").html(data.content);
        var $img = $("#js-img");
        
        if (data.img === "") {
        	$img.parent().remove();
        } else {
            var imga = data.img.split(",");
            if (imga.length === 1) {
            	$img.attr("src", data.img);	
            } else {
            	var $wrapper = $img.parent().empty();
            	var $h1 = $("<h1>").addClass("t-c").appendTo($wrapper);
            	imga.forEach(function(item) {
            		var $img = $("<img>").addClass("pt").attr("src", item);
            		$h1.append($img);
            	});
            }
        }
        
      } catch(e) {
        console.error(e);
      }     
      
      var school = data.school;
 
      // BEGIN 加载相关文章id和标题
      (function(sc, id) {    	  
        var $target = $("#js-related");
        $.ajax({
          type: "GET",
          url: "./news/listJournals",
          data: {"school":sc, "limit":12}
        }).done(function(data) {
          var $ul = $("<ul>").addClass("related-titles").addClass("mar-t10");
          Array.prototype.forEach.call(data, function(elem) {        	  
        	  if (elem.id != id) {
            	  var url = "";
            	  if (elem.url !== "javascript:;") {
            		  url = elem.url;
            	  } else {
            		  url = "journal.jsp?id="+elem.id;
            	  }
                  var $a = $("<a>").attr("href", url).text(elem.title);
                  var $li = $("<li>").append($a);       
                  $ul.append($li); 
        	  }
          });
          $target.empty().append($ul);
        });
        $target.html("<p>相关文章列表加载中...</p>");
      })(school, g_id);
      // END 加载相关文章id和标题
      
      var $ol = $("#J_url").empty();
      $("<li>").html("<a href=\"index.jsp\">首页</a>").appendTo($ol);
      $("<li>").html("<a href=\"model-school.jsp?school="+school+"\">示范校</a>").appendTo($ol);
      $("<li>").addClass("active").html("<a href=\"javascript:;\">校园采风</a>").appendTo($ol);
    }); // .done...
  }).call();
  
  </script>
</html>
