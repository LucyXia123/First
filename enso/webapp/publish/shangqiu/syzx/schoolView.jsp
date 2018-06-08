<%@ page contentType="text/html;charset=utf-8" %>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;活动采风</title>
        <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.png">
        <link rel="stylesheet" href="../../../bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../../../css/base.css" />
        <link rel="stylesheet" href="../../../css/content.css" />
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
            
            .related-body ul.related-titles>li {
                width: 25%;
            }
        </style>
        <script src="../../../bootstrap/js/jquery-3.2.1.min.js"></script>
    </head>

    <body id="news">

        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="/index.jsp">
                    <img class="logo" src="/images/logo.jpg" title="同方教育在线" />
                </a>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav nav-mid">
                        <li><a href="/index.jsp">我的首页</a></li>
                        <li><a href="/model-school.jsp">我的学校</a></li>
                        <li><a href="/video.jsp?videoId=1">课程</a></li>
                        <li><a href="/mytrack.jsp">学习中心</a></li>
                    </ul>
                    <jsp:include page="../../../include/nav-right.jsp" flush="true" />
                </div>
                <!--/.navbar-collapse -->
            </div>
        </nav>

        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="../../../index.jsp">首页</a></li>
                <li><a href="../../../model-school.jsp#校园采风">校园风采</a></li>
                <li class="active"><a href="javascript:;">校门口</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title" style="text-align:center">校门口</h1>
                    <div class="article-metas" style="text-align:center">
                        <div>学校: <span id="js-school">商丘市实验中学</span></div>
                        <span>|</span>
                        <div>日期: <span id="js-date">2017年09月29日</span>
                        </div>
                    </div>
                </div>
                <div class="article-body">
                    <div class="t-c">
                        <img class="pt" src="/publish/shangqiu/syzx/schoolView/1.jpg" />
                        <img class="pt" src="/publish/shangqiu/syzx/schoolView/5.jpg" />
                    </div>
                    <article id="js-content">
                    </article>
                </div>
            </div>
            <!-- 相关采风 -->
            <div class="related mar-t40">
                <div class="separate-line">
                    <div class="highlight"></div>
                </div>
                <div class="related-header">
                    <h2>相关活动</h2>
                    <a class="more" href="/more-act.jsp" target="_blank">更多</a>
                </div>
                <div class="related-body" id="js-related">
                    <ul class="related-titles mar-t10">
                        <li><a href="/publish/shangqiu/syzx/theme.jsp?id=1">多姿多彩的音美课堂</a></li>
                        <li><a href="/publish/shangqiu/syzx/theme.jsp?id=2">商丘市实验中学第一届“意林杯”英语书写大赛</a></li>
                        <li><a href="/publish/shangqiu/syzx/theme.jsp?id=3">实验中学八年级数学组公开课</a></li>
                        <li><a href="/publish/shangqiu/syzx/theme.jsp?id=4">实验中学七年级语文组公开课教学活动</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="footer"></div>
        <script>
            $("#footer").load("/include/footer.html");
        </script>
    </body>
    <script type="text/javascript" src="/js/common.js"></script>

    </html>