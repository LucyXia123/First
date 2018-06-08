<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;活动采风</title>
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
        <script src="./bootstrap/js/jquery-3.2.1.min.js"></script>
    </head>
    <body id="news">

        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="../index.jsp">
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
                <li><a href="../index.jsp">首页</a></li>
                <li><a href="model-school.jsp#校园采风">活动采风</a></li>        
                <li class="active"><a href="javascript:;">正文</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title" style="text-align:center"> 标题 </h1>
                    <div class="article-metas" style="text-align:center">
                        <div>学校: <span id="js-school"><% out.print(session.getAttribute("school"));%></span></div>
                        <span>|</span><div>日期: <span id="js-date">2017年09月29日 09:22</span>
                        </div>                           
                    </div>
                </div>
                <div class="article-body">
                    <!--<div><img id="js-img"  height="240" width="350" class="avatar" src="" /></div>-->
                    <article id="js-content">
                    </article>
                </div>
            </div>
            <!-- 相关采风 -->
<!--            <div class="related mar-t40">
                <div class="separate-line"><div class="highlight"></div></div>
                <div class="related-header">
                    <h2>相关活动</h2>
                    <a class="more" href="../404tbd.jsp" target="_blank">更多</a>
                </div>
                <div class="related-body" id="js-related">
                    <ul class="related-titles mar-t10">
                        <li><a href="kaifeng27theme.jsp?id=1">开封市第二十七中学科普进校园活动</a></li>
                        <li><a href="kaifeng27theme.jsp?id=2">开封市第二十七中学未成年人思想道德建设系列活动“向国旗敬礼”</a></li>
                        <li><a href="kaifeng27theme.jsp?id=3">开封市第二十七中学烈士纪念日活动</a></li>
                        <li><a href="kaifeng27theme.jsp?id=4">开封市第二十七中学生举办跳大绳活动</a></li>
                    </ul>
                </div>
            </div>-->
        </div>

        <div id="footer"></div>
        <script>$("#footer").load("./include/footer.html");</script>
    </body>
    <script type="text/javascript" src="../js/common.js"></script>
    <script>
            (function() {
                var qs = enso_query_string();
                var g_id = qs.id;
                $.getJSON("./publish/kaifeng27/json/theme.json", function(data) {
                    $.each(data, function(i, obj) {
                        if (g_id == obj.id) {
                            $("#js-title").text(obj.title);
                            $("#js-date").text(obj.date);
                            var $school = $("#js-school");
                            var s = $school.text();
                            if (null === s || "null" === s) {
                                $school.text("开封市第二十七中学");
                            }
                            $("#js-content").html(obj.content);
//                            $("#js-img").attr("src", obj.img);

                        }
                    });

                });

                $.ajax({
                    type: 'GET',
                    url: './json/theme.json',
                }).done(function(data) {


                    if (null == data || {} === data) {
                        document.write("找不到这个记者站正文: id=" + g_id);
                        return false;
                    }
                    try {
                        $("#js-title").text(data.title);
                        $("#js-date").text(data.date);
                        var $school = $("#js-school");
                        var s = $school.text();
                        if (null === s || "null" === s) {
                            $school.text(data.school);
                        }
                        $("#js-content").html(data.content);
                        $("#js-img").attr("src", data.img);
                    } catch (e) {
                        console.error(e);
                    }

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
                })
            }).call();

    </script>
</html>
