<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;教师详情</title>
        <link rel="shortcut icon" type="image/x-icon" href="../images/favicon.png">
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
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
        <script src="../bootstrap/js/jquery-3.2.1.min.js"></script>
    </head>
    <body id="news">
        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">
                    <img class="logo" src="../images/logo.jpg" title="同方教育在线" />
                </a>
                <div id="navbar" class="navbar-collapse collapse">
                    <jsp:include page="../include/nav.jsp" flush="true" />
                    <jsp:include page="../include/nav-right.jsp" flush="true" />
                </div>
                <!--/.navbar-collapse -->
            </div>
        </nav>
        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="../index.jsp">首页</a></li>
                <li><a href="../model-school.jsp#教师风采">校长寄语</a></li>        
                <li class="active"><a href="javascript:;">姓名</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <!--<h1 class="t-c" id="js-title"></h1>-->
                    <h1>校长寄语</h1>
                    <div class="article-metas">
                        <div>学校: <span id="js-school"><% out.print(session.getAttribute("school"));%></span></div><span>|</span>           
                        <div>日期: <span id="js-datetime">2017年12月12日</span></div>                           
                    </div>
                </div>
                <div class="article-body">
                    <div><img id="js-img"  height="240" width="350" class="avatar" src="" /></div>
                    <article id="js-content"></article>
                </div>
            </div>
            <!-- 相关教师 -->
        </div>    
        <div id="footer"></div>
        <script>$("#footer").load("../include/footer.html");</script>

    </body>
    <script type="text/javascript" src="../js/common.js"></script>
    <script>

            var qs = enso_query_string();
            var g_id = qs.id;
            $.getJSON("./json/teacher.json", function(data) {
                $.each(data, function(i, obj) {
                    if (g_id == obj.id) {
                        $(".breadcrumb").children(".active").html("<a href=\"javascript:;\">" + obj.name + "</a>");
                        $("#js-title").text(obj.name + "简介");
                        $("#js-datetime").text(obj.date);
                        var $school = $("#js-school");
                        var s = $school.text();
                        if (null === s || "null" === s) {
                            $school.text("商丘市第一中学");
                        }
                        $("#js-content").html(obj.content);
                        $("#js-img").attr("src", obj.img);

                    }
                });



            });


    </script>
</html>
