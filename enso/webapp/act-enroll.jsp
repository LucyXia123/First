<%@ page contentType="text/html;charset=utf-8"%>
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
<link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="./css/base.css" />
<link type="text/css" rel="stylesheet" href="./css/my.css" />
<link type="text/css" rel="stylesheet" href="./css/act.css" />
<style>
body {
  background: url(./images/user/enroll-middle.jpg) center center repeat rgb(245, 245, 245);
}
.act-wrapper {
    padding: 40px 0;
    font-family: "Microsoft Yahei", sans-serif;
    min-width: 400px;
    margin: 0 auto;
    width: 70%;
    min-width: 700px;
}
.act-wrapper .article {
    max-width: 700px;
    width: auto;
    margin: 0 auto;
}
.bg_enroll {
    width: 100%;
    outline: none;
    border: none;
    margin-top: -2px;
}
</style>
<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
  <div id="top"></div>
  <nav class="navbar">
    <div class="container-fluid">
      <a class="navbar-brand" href="index.jsp"> <img class="logo"
        src="./images/logo.jpg" title="同方教育在线" />
      </a>
      <div id="navbar" class="navbar-collapse collapse">
        <jsp:include page="./include/nav.jsp" flush="true" />
        <jsp:include page="./include/nav-right.jsp" flush="true" />
        <!-- 登录控制 -->
      </div>      
    </div>
  </nav>
  <img class="bg_enroll" src="./images/user/enroll-top.jpg" />
  <div class="container">   
    <div class="act-wrapper">         
      <div id="J_act" style="display: none;">
      <!-- 
        <h1 class="act-title">"未来小作家"作文评选活动</h1>
        <div class="article">
          <div class="act-label">活动介绍、活动安排、活动详情、参与人、颁奖等介绍</div>
          <article class="act-info">
           </article>
        </div>
       -->
      </div>

      <!-- /.article -->
      <div class="enroll">
        <h2>报名</h2>
        <form method="POST" action="./insertActivityParticipants" enctype="multipart/form-data" id="J_enroll">
          <div class="img-wrapper">
            <div id="J_preview">
              <img src="./images/user/default-image.jpg" />
            </div>
            <label class="upload-trigger" for="J_upload" title="点击上传头像">上传头像</label>
            <input id="J_upload" type="file" class="hide" name="files">
          </div>
          <!-- <input type="hidden" name="activityId" value="1"> -->          
          <input type="hidden" name="userNumber" value="">
          <div class="props">
             <div class="form-group">
              <label>姓名: </label><span id="J_realname"></span>
            </div>
            <div class="form-group">
              <label for="J_slogan">参赛题目: </label><input id="J_slogan" type="text" name="slogan" class="form-control" maxlength="16" value="" />
            </div>
            <div class="form-group">
              <label for="J_instructor">指导老师: </label><input id="J_instructor" type="text" name="instructor"  class="form-control" value="" />
            </div>
            <div class="form-group">
              <label>学校班级: </label><span id="J_school"></span>
            </div>
          </div>
          <button class="btn btn-primary">点击预览名片</button>
        </form>
      </div>
    </div>
  </div>

  <img class="bg_enroll" src="./images/user/enroll-bottom.jpg" />
  <div id="footer"></div>
  <script> $("#footer").load("./include/footer.html"); </script>
  
<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">我的参赛名片</h4>
      </div>
      <div class="modal-body" id="J_card">
      <!-- 
        <div class="card-canvas">
                     照片二维码
        </div>
        <div class="card-metas">
          <p>刘莹莹正在参加开封电视台举办的<br />"未来小作家"作文评选</p>
          <div class="title">
            <div>参赛作品</div><div class="slogan">雪花飘飘</div>
          </div>          
          <span>扫描二维码围观</span>          
        </div>     
       -->
      </div>
      <div class="modal-footer" id="J_buttons">
        <button type="button" class="btn btn-default" data-dismiss="modal">重新生成</button>
        <a type="button" class="btn btn-primary" href="javascript:;">确认提交</a>       
      </div>
    </div>
  </div>
</div>

  <script type="text/javascript" src="./js/common.js"></script>
  <!-- qrcode lib -->
  <script type="text/javascript" src="./bootstrap/js/qrcode.js"></script>
  <script type="text/javascript" src="./bootstrap/js/jquery.qrcode.js"></script>
  <!-- modal -->
  <script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/filter/modal-alert.js"></script>
  <!-- 我的活动 -->
  <script type="text/javascript" src="./js/act-enroll.js"></script>
</body>
</html>