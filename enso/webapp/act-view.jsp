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
    <div class="act-wrapper" id="act-view">
        <div class="user" id="J_user">
            <!--
             <img class="avatar" src="./images/user/default-image.jpg" />
             <div class="metas">
                <h4>刘莹莹</h4>
                 <div class="school">xx市第十三中学&nbsp;五年二班</div>
                 <div class="slogan">作文题目: 雪花飘飘</div>
                 <div class="director">指导老师: 李文宣</div>
                 <div class="sns">
                   <a id="J_thumbup" href="javascript:;" title="点赞"><span class="glyphicon glyphicon-thumbs-up"></span><span class="num">3</span></a>
                   <a id="J_vote" href="javascript:;" title="投票"><span class="glyphicon glyphicon-tree-conifer"></span><span class="num">3</span></a>
                 </div>
                -->
        </div>
        <div id="J_alert_warning" class="alert alert-warning" role="alert" style="display:none;">...</div>
        <div id="J_alert_danger" class="alert alert-danger" role="alert" style="display:none;">...</div>
        <article id="J_article">
            <!--
                <p>一个人来到这个社会，就要接受这个社会的熏陶，成为这个社会的一名成员，并且参与这个社会的各项活动。在这样的环境中<a href="http://www.sanwen.net/suibi/shenghuo/" target="_blank">生活</a>，每个人都有<a href="http://xiangxinziji.sanwen8.cn/" target="_blank">自己</a>的理想和追求，并且沿着这个方向前进，完成自己的<a href="http://rensheng.sanwen.net/" target="_blank">人生</a><a href="http://gongzuojihua.sanwen.net/" target="_blank">计划</a>蓝图，成就自己的辉煌人生。</p>
                <p>人生一词在每个人的心中绝对不会陌生的。但是，每个人的人生道路又各不相同，每个人的人生都充满了诉说不尽的风采。一个人就是一本丰富多彩的史记，记录着社会的发展与时代的背景。因为环境造就人，塑造人生观。因为我们是社会中的一个成员，生活在社会的空间，接受社会的影响，同时也影响周边的人，由这些人组成一个完整的社会。人是社会的高级动物，能通过大脑的思维来改变社会，改变自己的人生轨迹，完成人生理想蓝图的实施。这就是人的高明之处，有别于其他动物仅仅维持吃喝而已。<a href="http://ganwu.sanwen8.cn/" target="_blank">感悟</a>人生就是对人生的反省，<a href="http://gongzuozongjie.sanwen.net/" target="_blank">总结</a>自己的生活经历过程。总结<a href="http://cengjing.sanwen8.cn/" target="_blank">过去</a>，通过回味反省，找出生活的完美之处。人类从远古的生活方式逐渐过渡到今天的科学<a href="http://www.sanwen.net/shige/xiandai/" target="_blank">现代</a>化社会，是一个漫长的总结经验，汲取先进的理念，摈弃糟粕的过程。没有反省，怎么能知道自己过去的作法与<a href="http://baogao.sanwen.net/shehuishijian/" target="_blank">实践</a>的正确性，通过对比才能推动我们社会的前进，提高人的思维，人的行为准则的标准化。这个标准化的要求就是社会公德的认可性。</p>
                <p>我们作为社会的一个公民，以社会的法规为行动准则，不论你生活在西方世界，还是东方世界，但有一个大众公认的准则存在其中，约束着你的言行，超出就会受到社会的制约和舆论的谴责。所以，我们的人生观在社会的约束下形成，社会环境塑造人生。一个人生活在社会的空间内，长期接受社会的影响，社会道德规范教育，促使你成为这个社会的一个合法的公民。在社会活动中你的人生观，人生道路由社会的约束定型。所以，每个人的<a href="http://chengzhang.sanwen8.cn/" target="_blank">成长</a>经历不同，但身上存在着这个社会的烙印，许许多多的社会理念积淀在自己的思想观念里，无形中你就是这个社会的缩影。一个人就是一部社会的史诗，负载着这个时代的背景。看人就向看一本书。</p>
                <p>新中国成立了六十多年，如今的社会人员组成由两个部分组成，一是建国前的老年人，一部分是建国后与共和国同龄人，还有共和国成立后出生的人，所有的这些人组合成这个国家的整体。不管生活经历如何，但每个人身上都有社会发展的烙印。现在有一个新名词非常的醒目：80后，90后。这些代名词指定的是一代人的生成。不管生活在哪一个层次中，你是中国的公民，是社会的一分子，是社会的成员。你的言行要遵循社会的公德去行动，没有什么特权，必须遵守“宪法”，要以宪法为行动准则。这就是社会造就人的意义所在。我们的人生观也是在这样的环境中生成，为人民服务是我们的行动纲领，是我们奋斗的目标。</p>
                <p>人生观是我们言行的指南，理想是前进的路线。我们为生活制定了切实可行的<a href="http://gongzuojihua.sanwen.net/cehuashu/" target="_blank">方案</a>，期盼着明天的<a href="http://www.sanwen8.cn/" target="_blank">美好</a>，并且向着这个方向努力拼搏。在人生道路上展示着自己的才华，展示着个人的风采。人生几十年的光景，瞬间即逝，在历史长河上显得异常短暂。在这几十年中，我们一路艰辛跋涉，成功的荣耀，失败的惨痛教训，喜怒哀乐，悲欢离合，七情六欲，酸甜苦辣融汇于一身。我们的人生包含着绚丽的光环，也蕴含着坎坷的艰难。回眸顾盼，无愧于社会，无愧于<a href="http://xiaojing.sanwen8.cn/" target="_blank">父母</a>，无愧于父老乡亲，心中坦然无憾，这就是人生的壮丽丰碑。</p><p>首发散文网：<a href="https://www.sanwen.net/subject/3956421/">https://www.sanwen.net/subject/3956421/</a></p>
             -->
        </article>
        <div class="thumb-up">
            <div id="qrcode"></div>
            <h4>扫描为我点赞</h4>
            <a class="back" href="act-upload.jsp"><span class="glyphicon glyphicon-arrow-left"></span>返回作品列表</a>
        </div>
    </div>
</div>
<input id="J_type" type="hidden" value="<% out.print(session.getAttribute("type")); %>" />

<div id="footer"></div>
<script> $("#footer").load("./include/footer.html"); </script>
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/filter/modal-alert.js"></script>
<script type="text/javascript" src="./js/act-view.js"></script>
</body>
</html>