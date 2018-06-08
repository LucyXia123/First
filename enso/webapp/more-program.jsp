<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;示范校更多&gt;&gt;</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />    
    <link type="text/css" rel="stylesheet" href="./css/base.css" />
    <link type="text/css" rel="stylesheet" href="./css/main.css" />
    <link type="text/css" rel="stylesheet" href="./css/more.css" />
    <script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="./js/common.js"></script>
  </head>
<body id="more">

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

  <div class="header">
    <div class="header-wrapper">
      <div class="crumb">
        <a class="has-next" href="index.jsp">首页</a>
        <a class="has-next" href="model-school.jsp#教育栏目">教育栏目</a>
        <a href="javascript:;" id="js-type"></a>
      </div>
    </div>
  </div>
    <div class="more-wrapper">
      <div class="module-body" id="js-list">
        <!-- 示范校 item -->
        <div class="school-item">
          <div class="item-preview">
            <div class="poster" style="background-image:url('./publish/school/1.png')"></div>          
            <a class="play" href="video.jsp?videoId=16"><span class="play-arrow"></span></a>
          </div>
          <div class="item-text">            
            <h3 class="item-title"><a href="video.jsp?videoId=16" target="_blank">【开封市第七中学】历经沧桑看今朝，七中旧貌换新颜！</a></h3>
            <div class="item-content">看七中，现已拥有42个教学班，170多名教职工，在校学生2200多人的较大规模的学校，并以良好的校风和教学质量，一流的师资队伍和办学条件在全市享有盛名。2003年底，经市教育局考核，学校被授予首批市级示范性高中。在全校师生的共同努力下，七中各项工作取得了丰硕成果。尤其近两年，学校先后被评为教育工作先进单位、先进基层党组织、“十五”重点课题先进实验学校、河南省先进家长学校等殊荣.</div>
            <div class="item-date">2017/10/21</div>
          </div>
        </div>
        <!-- /示范校 item -->
      </div>      
  </div>
   <!-- footer -->
  <div id="footer"></div>
  <script>$("#footer").load("./include/footer.html");</script>
  
  <script>
  var qs = enso_query_string();
	/**
	 * 示范校列表中 加载师范学校简介列表
	 */
	(function(qs) {
		var $target = $("#js-list");	
		var type = null;
		var area = qs.area ? qs.area : null;
		
		$.ajax({
			type: 'GET',
			url: "./news/listTVProgram",
			data: {"area":area}
		}).done(function(data) {
			$target.empty();
			var a = data.videoList;
			a.map(function(elem) {
				if (type===null) {
					type = elem.type;
				}
				var videoURL = "video.jsp?videoId="+elem.id;
				
				var $item = $("<div class=\"school-item\">");
				$target.append($item);
				
                var $preview = $("<div>").addClass("item-preview").appendTo($item);
                var $poster = $("<img>").addClass("poster").attr("src", elem.poster);
                $preview.append( $poster );
				var $play = $("<a>").addClass("play").attr("href", videoURL)
					.append( $("<span>").addClass("play-arrow") );
				$preview.append($play);
				
				var $text = $("<div>").addClass("item-text").appendTo($item);
				var $title = $("<h3>").addClass("item-title").append( 
						$("<a>").attr("href",videoURL).attr("target", "blank").html(elem.name) ).appendTo($text);
				$("<div>").addClass("item-content").html(elem.content).appendTo($text);
				
				return $item;
			});
			
			$("#js-type").text(type);
		});
	})(qs);
  </script>
</body>
</html>