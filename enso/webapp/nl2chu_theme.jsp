<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;活动采风</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/base.css" />
    <link rel="stylesheet" href="./css/content.css" />
    <style>
    .article-metas {
      margin-left: -12em;
    }
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
      <ol class="breadcrumb mar-t40">
        <li><a href="index.jsp">首页</a></li>
        <li><a href="model-school.jsp#校园采风">活动采风</a></li>        
        <li class="active"><a href="javascript:;">正文</a></li>
      </ol>
      <div class="article">
        <div class="article-header">
          <h1 class="t-c" id="js-title"><!-- 标题 --></h1>
          <div class="article-metas">
            <div>学校: <span id="js-school"><% out.print(session.getAttribute("school"));%></span></div><span>|</span>           
            <div>日期: <span id="js-date">2017年09月29日 09:22</span></div>                           
          </div>
        </div>
        <div class="article-body">
          <!--<div><img id="js-img" class="avatar" src="" /></div>-->
          <article id="js-content">
          </article>
         </div>
      </div>
      <!-- 相关采风 -->
<!--      <div class="related mar-t40">
          <div class="separate-line"><div class="highlight"></div></div>
          <div class="related-header">
            <h2>相关活动</h2>
            <a class="more" href="404tbd.jsp" target="_blank">更多</a>
          </div>
          <div class="related-body" id="js-related">
<ul class="related-titles mar-t10"><li><a href="journal.jsp?id=13">县长赵丽玲一行莅临我校慰问并视察工作2017.9.9</a></li><li><a href="journal.jsp?id=12">元旦联欢暨艺术节文艺汇演</a></li><li><a href="journal.jsp?id=11">范县第一初中师生书画艺术展览</a></li><li><a href="journal.jsp?id=10">2017年中考冲刺暨表彰大会</a></li><li><a href="journal.jsp?id=9">沐浴阳光，享受挑战——“阳光”体育运动会范县第一初中运动员赛场英姿</a></li></ul>
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
     $.getJSON("./include/json/nlxdecjzx/"+qs.id+".json", function(data)
        {
             $("#js-title").text(data.title);
              $("#js-date").text(data.date);
              $("#js-school").text(data.school);  
              $("#js-content").html(data.content);
              $("#js-img").attr("src", data.img); 
        });
//    $.ajax({
//      type: 'GET',
//      url: 'news/getTheme',
//      data: {"id":g_id}
//    }).done(function(data) {
//      if (null == data || {}===data) {
//        document.write("找不到这个记者站正文: id="+g_id);
//        return false;
//      }
//      try {       
//        $("#js-title").text(data.title);
//        $("#js-date").text(data.date);
//        var $school = $("#js-school");
//        var s = $school.text();
//        if (null===s || "null"===s) {
//          $school.text(data.school);  
//        }       
//        $("#js-content").html(data.content);
//        $("#js-img").attr("src", data.img); 
//      } catch(e) {
//        console.error(e);
//      }     
 
      /*
      // BEGIN 加载相关文章id和标题
      (function() {
        var $target = $("#js-related");
        // var school = $("#js-school").text();
        // 开封市五中暂时没有配数据
        var school = "濮阳市范县第一初级中学";
        $.ajax({
          type: "GET",
          url: "news/listTheme",
          data: {"school":school, "limit":6}
        }).done(function(data) {
          var $ul = $("<ul>").addClass("related-titles").addClass("mar-t10");
          Array.prototype.forEach.call(data, function(elem) {
            var $a = $("<a>").attr("href", "journal.jsp?id="+elem.id).text(elem.title);
            var $li = $("<li>").append($a);       
            $ul.append($li);
          });
          $target.empty().append($ul);
        });
        $target.html("<p>相关文章列表加载中...</p>");
      }).call();
      // END 加载相关文章id和标题
      */
//    }) 
  }).call();
  
  </script>
</html>
