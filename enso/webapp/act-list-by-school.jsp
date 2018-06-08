<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线 &middot; 活动-&gt;本校报名情况</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="./css/base.css"/>
    <link type="text/css" rel="stylesheet" href="./css/my.css"/>
    <link type="text/css" rel="stylesheet" href="./css/act.css"/>
    <script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="top"></div>
<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><img class="logo" src="./images/logo.jpg" title="同方教育在线"/></a>
        <div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true"/>
            <jsp:include page="./include/nav-right.jsp" flush="true"/>
            <!-- 登录控制 -->
        </div>
    </div>
</nav>

<div class="container">
    <div class="act-wrapper">

        <div id="J_act">
            <h1 class="act-title">?活动</h1>
            <div class="article">
                <div class="act-label">活动介绍、活动安排、活动详情、参与人、颁奖等介绍</div>
                <article class="act-info">xxxxxxxx通过此次活动可以提高学生的文笔水平，展现个人魅力。可以相互学习。相互促进。</article>
            </div>
        </div>
        <div id="J_alert_warning" class="alert alert-warning" role="alert" style="display:none;">...</div>
        <div id="J_alert_danger" class="alert alert-danger" role="alert" style="display:none;">...</div>
        <!-- 作品列表 -->
        <div id="works" class="row">
            <!-- .card -->
            <div class="card" data-id="1">
                <div class="imgwrap"><img src="./images/user/default-image.jpg"/></div>
                <div class="figure">
                    <p>xx市第十三中九年级十班王某某</p>
                    <div class="tutor">指导老师: 刘某
                      <div class="pull-right">
                        <a href="javascript:;"><span class="glyphicon glyphicon-tree-conifer"></span></a>
                        <span class="num">0票</span>
                      </div>
                    </div>
                    <div class="sns">
                        <!-- 点赞 -->
                        <a href="javascript:;"><span class="glyphicon glyphicon-thumbs-up"></span></a>
                        <span class="num">2</span>
                        <span class="credit">100积分</span>
                        <a class="check" href="javascript:;">查看作品</a>
                    </div>
                </div>
            </div>
            <!-- /.card -->
        </div> <!--/ #works -->
        <nav id="J_pagination" aria-label="...">
          <ul class="pager">
            <li><a href="javascript:;">上一页</a></li>
            <li><a href="javascript:;">下一页</a></li>
          </ul>
        </nav>
        <a href="act-upload.jsp"><span class="glyphicon glyphicon-arrow-left">返回上传作品</span></a>  
    </div>
</div>

<div id="footer"></div>
<script> $("#footer").load("./include/footer.html"); </script>

<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/filter/modal-alert.js"></script>
<!-- 我的活动 -->
<script type="text/javascript" src="./js/act-list-by-school.js"></script>
</body>
</html>