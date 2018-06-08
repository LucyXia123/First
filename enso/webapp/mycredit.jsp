<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线 - 我的学习轨迹</title>
    <link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="./css/base.css" />
    <link type="text/css" rel="stylesheet" href="./css/my.css" />
    <link type="text/css" rel="stylesheet" href="./css/point.css" />
    <script type="text/javascript" src="./bootstrap/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<div id="top"></div>

<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
            <img class="logo" src="./images/logo.jpg" title="同方教育在线" />
        </a>
        <div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
            <jsp:include page="./include/nav-right.jsp" flush="true" />
            <%
                String userid = null;
                String username = null;

                if (null == session) {
                    out.print("<script>location.href='login.jsp?referer=mycredit.jsp'</script>");
                } else {
                    userid = (String) session.getAttribute("userid");
                    username = (String) session.getAttribute("username");
                    if (null == userid) {
                        out.print("<script>location.href='login.jsp?referer=mycredit.jsp'</script>");
                    }
                }
            %>
        </div>
        <!--/.navbar-collapse -->
    </div>
</nav>

<div class="mywrapper">
    <div class="home-crumb">
        <span><a href="index.jsp">首页</a></span> <span class="crumb-arrow"><a
            href="model-school.jsp">我的示范校</a></span> <span class="crumb-arrow">学习中心</span>
    </div>
    <div class="main-wrapper">
        <div class="nav-cont" id="js_navcont">

        </div>
        <div class="main-cont" id="pointContent">
            <div class="panel">
                <div class="panel-heading">
                    <h3 class="panel-title">我的积分</h3>
                </div>
                <div class="panel-body">
                    <div class="notice" style="display: none;"><img src="./images/user/notice.png">
                        2017年6月1日起积分抵现功能下线，升级后的积分可以兑换更多超值权益及购物券。
                    </div>
                    <div class="content">
                        <div id="J_pointSummary">
                            <div class="summary clearfix">
                                <div class="item valid">
                                    <span class="desc">可用的积分</span><span class="point" id="J_AvailablePoint">0</span>
                                </div>
                                <div class="item expired-soon">
                                    <span class="desc">去年过期的积分</span><span class="point">0</span>
                                    <span class="date">(已于2017.12.31过期)</span>
                                </div>
                                <!-- 
                                <div class="item exchange">
                                    <a class="disabled" style="display: none;" href="404tbd.jsp" target="_blank">兑换超值商品与优惠劵</a>
                                </div>
                                 -->
                            </div>
                        </div>
                        <div class="detail">
                            <div class="masthead clearfix">
                                <span class="why">来源/用途</span>
                                <span class="what">积分变化</span>
                                <span class="when">当前积分</span>
                                <span class="notes right">时间</span>
                            </div>
                            <div id="J_pointDetail">
                                <ul class="item-list" id="J_item-list">
                                    <!-- <li class="no-have-detail">您当前没有积分明细</li> -->
                                    <!--
                                        <li class="has-detail clearfix">
                                      <div class="why">
                                        <img src="publish/user/point/diqiuyi.jpg"/>
                                        <div class="metas">
                                          <span class="source">星屿2017新版15cm高清国际地球仪摆件高约20cm开学礼物</span>
                                          <span class="number">编号: 88655307539254730</span>
                                        </div>
                                      </div>
                                      <div class="what"><span class="plus">+1</span></div>
                                      <div class="when"><span class="date">2017年12月1日 13:05:55</span></div>
                                      <div class="notes"><span>交易获得</span></div>
                                    </li>
                                    <li class="has-detail clearfix">
                                      <div class="why">
                                        <img src="publish/user/point/act.jpg"/>
                                        <div class="metas">
                                          <span class="source">天猫购物券刮刮卡-扣积分</span>
                                          <span class="number">编号: 100208000043188488</span>
                                        </div>
                                      </div>
                                      <div class="what"><span class="minus">-200</span></div>
                                      <div class="when"><span class="date">2017年11月16日 12:50:29</span></div>
                                      <div class="notes"><span>天猫购物卡</span></div>
                                    </li>
                                    <li class="has-detail clearfix">
                                      <div class="why">
                                        <img src="publish/user/point/weiyi.jpg"/>
                                        <div class="metas">
                                          <span class="source">安踏棒球运动服休闲夹克时尚立领外套棒球衫卫衣15538707</span>
                                          <span class="number">编号: 799115898033254730</span>
                                        </div>
                                      </div>
                                      <div class="what"><span class="plus">+62</span></div>
                                      <div class="when"><span class="date">2017年11月15日 13:23:29</span></div>
                                      <div class="notes"><span>交易获得</span></div>
                                    </li>
                                     -->

                                </ul>
                            </div>
                            <div id="J_pointError" class="hidden error"></div>
                            <div id="J_pointPager" class="pager"></div>
                            <nav id="J_page" aria-label="pagination">
                                <ul class="pager">
                                    <li><a href="javascript:;">上一页</a></li>
                                    <li><a href="javascript:;">下一页</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer"></div>
<script>$("#footer").load("./include/footer.html");</script>
<script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/my.js"></script>
<script type="text/javascript" src="./js/mypoint.js"></script>
</body>
</html>