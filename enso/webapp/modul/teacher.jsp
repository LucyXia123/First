<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
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
        <script type="text/javascript" src="../js/common.js"></script>
        <script>
            $(document).ready(function(){
                var qs = enso_query_string();
                var g_id = qs.id;
                $.getJSON("./json/teacher.json", function(data) {
                    $.each(data, function(i, obj) {
                        if (g_id == obj.id) {
                            $(".breadcrumb").children(".active").html("<a href=\"javascript:;\">" + obj.name + "</a>");
                            $("#js-title").text(obj.name + "简介");
                            $("#js-date").text(obj.date);
                            $("#js-school").text(obj.school);
                            $("#js-img").attr("src", obj.img);
                            var jcontent = obj.content, jcontentstr = '';
                            $.each(jcontent, function(j, cc) {
                                jcontentstr += "<p>";
                                jcontentstr += cc;
                                jcontentstr += "</p>"
                            });
                            $("#js-content").html(jcontentstr);    
                        }
                    });
                });
            });
        </script>
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
                <li><a href="../model-school.jsp#教师风采">教师/学生风采</a></li>        
                <li class="active"><a href="javascript:;">姓名</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title"></h1>
                    <h5>&nbsp;</h5>
                    <h4 style="text-align:center">
                        <span>学校 - </span>
                        <span id="js-school"></span>
                    </h4>
                    <h5>&nbsp;</h5>
                    <h5 style="text-align:center">
                        <span>日期 - </span>
                        <span id="js-date"></span>
                    </h5>
                </div>
                <div class="article-body">
                    <div><img id="js-img"  height="240" width="350" class="avatar" src="./teacherView/ls1.jpg" /></div>
                    <article id="js-content"></article>
                </div>
            </div>
            <!-- 相关教师 -->
        </div>            
        <div id="footer"></div>
        <script>$("#footer").load("../include/footer.html");</script>

    </body>
</html>
