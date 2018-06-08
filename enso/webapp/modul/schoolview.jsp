<%@ page contentType="text/html;charset=utf-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;活动采风</title>
        <link rel="shortcut icon" type="image/x-icon" href="../images/favicon.png">
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/base.css" />
        <link rel="stylesheet" href="../css/content.css" />
        <link rel="stylesheet" type="text/css" href="./css/pgwslider.css">
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
            
            /* 轮播图 */
            .pgwslider_m{ 
/*                width: 640px;
                height: 360px;*/
                width: 800px; 
                height: 400px; 
                margin-left: auto; 
                margin-right: auto; 
                margin-top: 40px; 
                margin-bottom: 40px; 
            }

        </style>
        <script type="text/javascript" src="../js/common.js"></script>
        <script type="text/javascript" src="../bootstrap/js/jquery-3.2.1.min.js"></script>
        <script src="./js/pgwslider.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                var qs = enso_query_string();
                var g_id = qs.id;
                $.getJSON("./json/schoolview.json", function(data) {
                    $.each(data, function(i, obj) {
                        if (g_id == obj.id) {
                            $("#js-title").text(obj.title);
                            var jimage = obj.img;
                            var jimageindex = '';
                            $.each(jimage, function(j, imagepath) {
                                jimageindex = 'lb' + (j + 1);
                                document.getElementById(jimageindex).src = imagepath.src;
                            });
                        }
                    });
                    $('.pgwSlider').pgwSlider();
                });
            });
        </script>
    </head>
    <body id="news">
        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="../index.jsp">
                    <img class="logo" src="../images/logo.jpg" title="同方教育在线" />
                </a>
                <div id="navbar" class="navbar-collapse collapse">
                    <jsp:include page="../include/nav.jsp" flush="true" />
                    <jsp:include page="../include/nav-right.jsp" flush="true" />
                </div>
            </div>
        </nav>
        
        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="../index.jsp">首页</a></li>
                <li><a href="../model-school.jsp#校园采风">校园风采</a></li>
                <li class="active"><a href="javascript:;">校园风采</a></li>
            </ol>
            
            <div class="pgwslider_m">
                <ul class="pgwSlider">
                    <li><img id="lb1" src="./schoolView/cf1_c1.jpg"></li>
                    <li><img id="lb2" src="./schoolView/cf1_c1.jpg"></li>
                    <li><img id="lb3" src="./schoolView/cf1_c1.jpg"></li>
                    <li><img id="lb4" src="./schoolView/cf1_c1.jpg"></li>
                </ul>
            </div>
            
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title" style="text-align:center"> 标题 </h1>
                </div>
            </div>
                        
            <!-- 相关采风 -->
        </div>

        <div id="footer"></div>
        <script>$("#footer").load("../include/footer.html");</script>
    </body>
</html>
