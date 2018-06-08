<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;活动详情</title>
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
                height: 360px;
                width:360px;
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
                    <img class="logo" src="../images/logo.jpg" title="同方教育在线" />
                </a>
		<div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
			<jsp:include page="./include/nav-right.jsp" flush="true" />
		</div>
            </div>
        </nav>
        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="index.jsp">首页</a></li>
                <li><a href="model-school.jsp#教师风采">活动采风</a></li>        
                <li class="active"><a href="javascript:;">正文</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title"></h1>
                    <div class="article-metas">
                        <div>
                            <span id="js-school"></span> </div><span>|</span>                
                        <div>日期: <span id="js-datetime">2017年09月</span></div>
                    </div>                       
                </div>
            </div>
            <div class="article-body">
                <div><img id="js-img"  height="240" width="350" class="avatar" src="" /></div>
                <article id="js-content"></article>
            </div>
        </div>
        <!-- 相关教师 -->
<!--        <div class="related mar-t40">
            <div class="separate-line"><div class="highlight"></div></div>
            <div class="related-header">
                <h2>相关教师/学生</h2>
                <a class="more" href="../404tbd.jsp" target="_blank">更多</a>
            </div>
            <div class="related-body" id="js-related">
                <ul class="related-titles mar-t10">
                     <li><a href="news.jsp?id=1">一篇文章既有新闻，又有图片该如何存储到数据库</a></li> 
                    <li><a href="kaifeng27teacher.jsp?id=3">郭冬玲</a></li>
                    <li><a href="kaifeng27teacher.jsp?id=4">韩若冰</a></li>
                    <li><a href="kaifeng27teacher.jsp?id=5">李冰</a></li>
                    <li><a href="kaifeng27teacher.jsp?id=6">骆蕤</a></li>
                </ul>
            </div>
        </div>-->
    </div>    
    <div id="footer"><jsp:include page="./include/footer.html" flush="true" /></div>

</body>
<script type="text/javascript" src="./js/common.js"></script>
<script>

    var qs = enso_query_string();
    var g_id = qs.id;
    $.getJSON("./publish/kaifeng27/json/teacher.json", function (data) {
        $.each(data, function (i, obj) {
            if (g_id == obj.id) {
//                        $(".breadcrumb").children(".active").html("<a href=\"javascript:;\">" + obj.name + "</a>");
                $("#js-title").text(obj.name + "简介");
                $("#js-datetime").text(obj.date);
                $("#js-school").text("开封市第二十七中学");
                $("#js-content").html(obj.content);
                $("#js-img").attr("src", obj.img);

            }
        });



    });
</script>
</html>
