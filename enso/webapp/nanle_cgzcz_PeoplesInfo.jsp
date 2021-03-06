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
            .article {min-height: 600px; height: auto;}  
            .article-body .avatar {
                margin: 0 auto;
                display: block;
                box-sizing: border-box;
                vertical-align: baseline;
                background: transparent;
                max-width: 100%;
            }
            .related-body ul.related-titles > li {
                width: 25%;
            }
            .article-metas {
              margin-left: -12em;
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
            </div>
        </nav>
        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="index.jsp">首页</a></li>
                <li><a href="model-school.jsp?school=濮阳市南乐县城关镇初级中学">教师/学生风采</a></li>
                <li class="active"><a href="javascript:;">姓名</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title"></h1>
                    <div class="article-metas">
                        <div>学校：南乐县城关镇初级中学</div>
                        <span>|</span>
                        <div>日期：<span id="js-datetime"></span></div>
                    </div>
                </div>
                <div class="article-body">
                    <div><img id="js-img" class="avatar" src="" /></div>
                    <article id="js-content"></article>
                </div>
            </div>
            <!-- 相关教师 -->
<!--            <div class="related mar-t40">
                <div class="separate-line"><div class="highlight"></div></div>
                <div class="related-header">
                    <h2>相关教师/学生</h2>
                    <a class="more" href="404tbd.jsp" target="_blank">更多</a>
                </div>
                <div class="related-body" id="js-related">
                  <ul class="related-titles mar-t10">
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=students&info=11&name=马芳婕&date=2017-12-24">马芳婕</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=students&info=12&name=王雨晴&date=2017-12-24">王雨晴</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=students&info=13&name=葛管彤&date=2017-12-24">葛管彤</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=students&info=14&name=任晓曼&date=2017-12-24">任晓曼</a></li>
                    
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=11&name=冯钦&date=2017-12-24">冯钦</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=12&name=张晓莉&date=2017-12-24">张晓莉</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=13&name=李晓辉&date=2017-12-24">李晓辉</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=14&name=胡香云&date=2017-12-24">胡香云</a></li>
                    
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=15&name=苏志敏&date=2017-12-24">苏志敏</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=16&name=谷军岭&date=2017-12-24">谷军岭</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=17&name=韩志兵&date=2017-12-24">韩志兵</a></li>
                    <li><a href="nanle_cgzcz_PeoplesInfo.jsp?model=teachers&info=18&name=马红霞&date=2017-12-24">马红霞</a></li>
                  </ul>
                </div>
            </div>-->
        </div>
        <div id="footer"><jsp:include page="./include/footer.html" flush="true" /></div>

    </body>
    <script type="text/javascript" src="./js/common.js"></script>
    <script>

        $(function () {
            urlinfo = decodeURI(window.location.href); //获取当前页面的url
            console.info(urlinfo);
            $.ajax({
                type: "GET",
                url: "./publish/nanle/cgzcz/" + GetArgsFromHref(urlinfo, "model") + "/" + GetArgsFromHref(urlinfo, "info") + ".json",
                dataType: "json",
                success: function (data) {
                    console.info(data);
                    var content = "";
                    for (var i = 0; i < data.contexts.length; i++) {
                        console.info(data.contexts[i]);
                        content += data.contexts[i] + "\n";
                    }
                    $("#js-content").html(content);
                }
            });

            $("#js-title").text(GetArgsFromHref(urlinfo, "name") + "简介");
            $("#js-datetime").text(GetArgsFromHref(urlinfo, "date"));
            $("#js-img").attr("src", "./publish/nanle/cgzcz/" +
                    GetArgsFromHref(urlinfo, "model") + "/" + GetArgsFromHref(urlinfo, "name") + ".jpg");
        });

        function GetArgsFromHref(sHref, sArgName)
        {
            var args = sHref.split("?");
            var retval = "";

            if (args[0] == sHref) /*参数为空*/
            {
                return retval; /*无需做任何处理*/
            }
            var str = args[1];
            args = str.split("&");
            for (var i = 0; i < args.length; i++)
            {
                str = args[i];
                var arg = str.split("=");
                if (arg.length <= 1)
                    continue;
                if (arg[0] == sArgName)
                    retval = arg[1];
            }
            return retval;
        }
    </script>
</html>
