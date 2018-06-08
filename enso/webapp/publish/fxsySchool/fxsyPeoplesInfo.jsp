<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;教师详情</title>
        <link rel="shortcut icon" type="image/x-icon" href="../../images/favicon.png">
        <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../../css/base.css" />
        <link rel="stylesheet" href="../../css/content.css" />
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
            
            p{
                text-indent: 2em;
            }
        </style>
        <script src="../../bootstrap/js/jquery-3.2.1.min.js"></script>
    </head>
    <body id="news">
        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">
                    <img class="logo" src="../../images/logo.jpg" title="同方教育在线" />
                </a>
                <div id="navbar" class="navbar-collapse collapse">
                    <jsp:include page="../../include/nav.jsp" flush="true" />
                    <jsp:include page="../../include/nav-right.jsp" flush="true" />
                </div>
            </div>
        </nav>
        <div class="container">
            <ol class="breadcrumb mar-t40">
                <li><a href="../../index.jsp">首页</a></li>
                <li><a href="../../model-school.jsp?school=濮阳市范县思源学校">示范校</a></li>        
                <li class="active"><a href="javascript:;">教师/学生风采</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title"></h1>
                    <div class="article-metas">
                        <div>学校:范县思源中学
            <!--                <span id="js-school"><% out.print(session.getAttribute("school"));%></span>-->
                        </div><span>|</span>           
                        <div>日期: <span id="js-datetime"></span></div>                           
                    </div>
                </div>
                <div class="article-body">
                    <div><img id="js-img" class="avatar" src="" /></div>
                    <article id="js-content"></article>
                </div>
            </div>

        </div>    
        <div id="footer"></div>
        <script>$("#footer").load("../../include/footer.html")</script>
    </body>
    <script type="text/javascript" src="../../js/common.js"></script>
    <script>

        $(function () {

            urlinfo = decodeURI(window.location.href); //获取当前页面的url
           $.ajax({
             type: "GET",
             url: "./"+ GetArgsFromHref(urlinfo,"model")+"/"+GetArgsFromHref(urlinfo,"info")+".json",
             dataType: "json",
             success: function(data){
                 var content = "";
                 for(var i=0;i<data.contexts.length;i++){
                     content += "<p>"+data.contexts[i]+"\n<p>" ;
                 }
                
                 $("#js-content").html(content);
             }
                
            });
            
            $("#js-title").text(GetArgsFromHref(urlinfo,"name") + "");
            $("#js-datetime").text(GetArgsFromHref(urlinfo,"date"));
            $("#js-img").attr("src","./"+
                    GetArgsFromHref(urlinfo,"model")+"/"+GetArgsFromHref(urlinfo,"name")+".jpg");
            
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