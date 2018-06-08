<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;我的示范校</title>
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
        <%  if (null == session) {out.print("<script>location.href=\"login.jsp\";</script>");} %>
        <!--/.navbar-collapse -->
    </div>
</nav>
<div class="header">
  <div class="header-wrapper">
    <div class="crumb">
      <a class="has-next" href="index.jsp">首页</a>
      <a class="has-next" href="model-school.jsp#活动采风">活动采风</a>
      <a href="javascript:;">全部</a>
    </div>
  </div>
</div>

<div class="more-wrapper">
  <div class="module-body" id="js-list">
  <!-- 
   <div class="school-item">
      <div class="item-preview">
        <a href="theme.jsp?id=13" target="blank"><img class="poster" src="publish/theme/puyang/5.jpg"></a>        
      </div>
      <div class="item-text">
        <h3 class="item-title"><a href="theme.jsp?id=13" target="blank">县长赵丽玲一行莅临我校慰问并视察工作2017.9.9</a></h3>
        <div class="item-content">9月7日上午，县长赵丽玲、副县长杜晓玲、县教育局局长黄守月等莅临范县第一初级中学，表达了对广大教师的慰问和教师节的美好祝福，并对初一新生的入学安排工作展开视察。</p><p>大家走进教室，观看初一教室内的布置。县教育局局长黄守月情不自禁地讲起一体机的妙用。</p><p>目前，一体机技术已被广泛地引进课堂。这种多媒体教学作为一种新兴的、先进的教学模式正在向传统的教学模式提出挑战。多媒体教学具有明显的优势，它丰富着教学内容，易引起学生的学习兴趣；增加课堂的信息量，提高教学效率，已成为解决学时矛盾的重要途径；改善教学环境，免除教师上课时板书的劳累，可以更多地注意课堂教学内容的组织和讲授。</p><p>县长赵丽玲重点询问了我校初一新生的各项入学安排进展情况，并提出了一些宝贵的建设性意见。</p><p>9月8日下午，我校举办教师节庆祝大会。会议由副校长张改玲主持。校长邢玉川高度赞扬了全体教师们的无私奉献精神，并号召大家再接再厉，坚定信心，为教育事业继续奋斗。</div>
      </div>
    </div>
   -->
  </div>
</div>

<!-- footer -->
<div id="footer"></div>

<script>
	(function() {
		$.ajax({
			type: "GET",
			url: "./news/allTheme",
			data: {"limit":1000}
		}).done(function(data) {
			var $body = $("#js-list").empty();
			var url = "";
			
			if (data instanceof Array) {
				data.forEach(function(item, i) {
					var $div = $("<div>").addClass("school-item");
					url = item.url;				
					if (url==="javascript:;"|| url==="") {
						url = "theme.jsp?id=" + item.id;
					}
					var $preview = $("<div>").addClass("item-preview");
					var imga = item.img.split(",");
					var img = "";
					if (imga.length === 1) {
						img = item.img;
					} else {
						img = imga[0];
					}
					$preview.html("<a href=\""+url+"\" target=\"blank\"><img class=\"poster\" src=\""+img+"\"></a>");
					$div.append($preview);
										
					var content = strip_tags(item.content);
					if (content.length > 200) {
						content = content.substring(0,200) + "...";	
					}
					var $text = $("<div>").addClass("item-text");
					var html = "<h3 class=\"item-title\"><a href=\""+url+"\" target=\"blank\">"+item.title+"</a></h3>"+
					        "<div class=\"item-content\">"+content+"</div>";
					$text.html(html);
					$div.append($text);
					
					$body.append($div);
				});
			} else {
				document.write("<h2>取得主题活动失败: </h2>" + data);
			}
		});
	}).call();
	
	$("#footer").load("./include/footer.html");
</script>
</body>
</html>
