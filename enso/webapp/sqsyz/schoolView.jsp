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
        <link rel="stylesheet" href="../css/base.css" />
        <link rel="stylesheet" href="../css/content.css" />
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
                <a class="navbar-brand" href="../index.jsp">
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
                <li><a href="../model-school.jsp#校园采风">校园风采</a></li>        
                <li class="active"><a href="javascript:;">教学楼</a></li>
            </ol>
            <div class="article">
                <div class="article-header">
                    <h1 class="t-c" id="js-title" style="text-align:center">教学楼</h1>
                    <div class="article-metas" style="text-align:center">
                        <div>学校: <span id="js-school">商丘市第一中学</span></div>
                        <span>|</span><div>日期: <span id="js-date">2017年12月12日</span>
                        </div>                           
                    </div>
                </div>
                <div class="article-body">
                    <div class="t-c">
                        <img class="pt" src="./schoolView/jxl-1.jpg" />
                    </div>
                    <div class="t-c">
                        <img class="pt" src="./schoolView/jxl-2.jpg" />
                    </div>
                    <div class="t-c">
                        <img class="pt" src="./schoolView/jxl-3.jpg" />
                    </div>
                    <div class="t-c">
                        <img class="pt" src="./schoolView/jxl-4.jpg" />
                    </div>
                    <article id="js-content">
                        <p>商丘市第一中学创建于1949年。通过六十余年的办学实践和探索，市一中积累了丰富的办学经验，取得了突出的办学成果，为经济发展和社会进步培养输送了数以万计的合格劳动者和数以千计的中、高级建设人才，成为商丘社会各界公认的优质学校。</p>
                        <p>近年来，学校先后被评定为商丘首批示范性中学、河南首批办学管理规范化学校、河南省首批校园网示范校、河南省文明标兵学校、河南省青少年科技教育示范学校和全国优秀家长学校等；先后被确认为河南省中学骨干教师教育实践培训基地、国家级德育实验和全国教育“十五”科研规划教育部重点课题实验学校等；先后荣获河南省文明单位、河南省职业道德建设先进单位、商丘市十佳学校等称号。</p>
                        <p>学校占地20余亩，现有66个教学班、5280名在校生，教职工370人。其中特级教师3人，中学高级教师135人，中学一级教师163人，国家、省级优质课教师136人，省级学科带头人、骨干教师46人，优秀班主任、优秀教师53人，河南省名师12人，商丘市名师37人。</p>
                        <p>学校始终坚持正确的办学方向，秉承“敬业乐群、博习通达”八字校训，坚持“让老师工作着快乐着，辛苦着幸福着；让学生成长着进步着，学习着感悟着”的教育理想，提出了“以人为本、厚德启智、强能育才、和谐发展”的办学理念，确立了“办‘精品+特色’的学校、育‘合格+特长’的学生”的办学目标，认真实施全面健康教育，教育教学质量年年攀升。</p>
                        <p>2009年至2013年，市一中省级重点高中上线率和毕业生优秀率均居全市同类学校之首。尤其是2013年，在全面健康理念的倡导与实施下，学校大胆实行系列教育教学改革，全面打造“和悦课堂”教学模式，中考成绩再上新台阶：白文轩同学以653分获全省第一名，左婉同学以651分获全省第三名，达到商丘市一高统招分数线的人数为423人！</p>
                    </article>
                </div>
            </div>
            <!-- 相关采风 -->
        </div>

        <div id="footer"></div>
        <script>$("#footer").load("../include/footer.html");</script>
    </body>
    <script type="text/javascript" src="../js/common.js"></script>
    <script>
    

    </script>
</html>
