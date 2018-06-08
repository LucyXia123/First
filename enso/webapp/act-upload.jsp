<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线 &middot; 我的活动</title>
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
        <!-- 
          <h1 class="act-title">?活动</h1>
            <div class="article">
                <div class="act-label">活动介绍、活动安排、活动详情、参与人、颁奖等介绍</div>
                <article class="act-info"></article>
            </div>
         -->
        </div>

        <div id="upload">
          <!-- 
            <img class="avatar" src="./images/user/default-image.jpg" title="用户头像"/>
            <div class="enroll-metas">
                <div class="user-info">
                    <p>开封市第十三中学五年级二班刘莹莹</p>
                    <a class="btn btn-default" href="act-enroll.jsp?edit=1"><span class="glyphicon glyphicon-pencil"></span>编辑</a>
                </div>
                <div class="user-buttons">
                    <a class="btn btn-default" href="act-enroll.jsp"><span class="glyphicon glyphicon-eye-open"></span>查看作品</a>
                    <a class="btn btn-default" href="javascript:;"><span class="glyphicon glyphicon-cloud-upload"></span>上传作品</a>
                    <a class="btn btn-default" href="model-school.jsp"><span class="glyphicon glyphicon-list-alt"></span>本校报名情况</a>
                </div>
            </div>
           -->
        </div>
        <div id="J_alert_warning" class="alert alert-warning" role="alert" style="display:none;">...</div>
        <div id="J_alert_danger" class="alert alert-danger" role="alert" style="display:none;">...</div>
        <!-- 作品列表 -->
        <div id="works" class="row">
          <!-- 
            <div class="card" data-id="1">
                <div class="imgwrap"><img src="./images/user/default-image.jpg"/></div>
                <div class="figure">
                    <p>xx市第十三中九年级十班王某某</p>
                    <div class="tutor">指导老师: 刘某<span class="pull-right">0票</span></div>
                    <div class="sns">
                        <a href="javascript:;"><span class="glyphicon glyphicon-thumbs-up"></span></a>
                        <span class="credit">100积分</span>
                        <a class="check" href="javascript:;">查看作品</a>
                    </div>
                </div>
            </div>
            -->
            <!-- /.card -->
        </div> <!--/ #works -->
        <div id="J_pagination">
          
        </div>
    </div>
</div>
<img id="J_loading" src="./images/user/loading.gif" />

<div id="footer"></div>
<script> $("#footer").load("./include/footer.html"); </script>

<!-- Modal 作品上传 -->
<div class="modal fade" id="workModal" tabindex="-1" role="dialog" aria-labelledby="workModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="workModalLabel">作品上传</h4>
            </div>
            <div class="modal-body" id="J_user_upload">
                <div class="user">
                    <img class="avatar" src="./images/user/default-image.jpg" />
                    <div class="metas">
                        <h4>刘莹莹</h4>
                        <div class="school">xx市第十三中学&nbsp;五年二班</div>
                        <div class="slogan">作文题目: <input id="J_title" type="text" value="雪花飘飘" /></div>
                        <div class="director">指导老师: 李文宣</div>
                    </div>
                </div>
                <div class="up_alt">
                    <a class="alt" href="javascript:;"><span>传图片</span></a>
                    <a class="alt" href="javascript:;"><span>传文件</span></a>
                    <a class="alt last" href="javascript:;"><span>自己打</span></a>
                </div>
                <div class="up_wrap">
                    <button class="btn btn-default">立即上传</button>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<!-- Modal 上传成功反馈-->
<div class="modal" id="fbModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <h2>恭喜您上传成功</h2>
      </div>
      <div class="modal-footer">
        <a type="button" class="btn btn-primary" href="act-view.jsp">查看作品名片</a>
      </div>            
    </div>
  </div>
</div>

<form id="J_file_upload" method="POST" action="./uploadActivityMaterialsOfFile" enctype="multipart/form-data">
    <input type="file" name="files" multiple="multiple" />
</form>

<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/filter/modal-alert.js"></script>
<!-- 我的活动 -->
<script type="text/javascript" src="./js/act-upload.js"></script>
</body>
</html>