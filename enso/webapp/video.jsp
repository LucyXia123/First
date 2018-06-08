<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>同方教育在线&middot;视频播放</title>
<link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
<link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="./css/base.css" />
<link type="text/css" rel="stylesheet" href="./css/video.css" />
<script type="text/javascript" src="./bootstrap/js/jquery-1.10.2.min.js"></script>
</head>
<body>
	<%
    String userid = null;
    String username = null;
    
    if (null != session) {
        userid = (String)session.getAttribute("userid");
        username = (String)session.getAttribute("username");
    }
    // nologin 未登录也可以访问视频播放页面
%>
<div id="top"></div>
<nav class="navbar">
    <div class="container-fluid">
       <a class="navbar-brand" href="index.jsp">
        <img class="logo" src="./images/logo.jpg" title="同方教育在线" />
       </a>
       <div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
            <jsp:include page="./include/nav-right.jsp" flush="true" />
            <%  if (null == session) {out.print("<script>location.href=\"login.jsp\";</script>");} %>
        </div>
        <!--/.navbar-collapse -->
    </div>
</nav>

	<div class="header">
		<div class="header-wrapper">
			<div class="crumb">
				<a class="has-next" href="index.jsp">首页</a>
                <a class="has-next" href="model-school.jsp">我的课堂</a>
				<a href="javascript:;">视频播放</a>
			</div>
		</div>
	</div>
	<%--视频播放器 + 相关视频--%>
	<div id="videoWrap" class="video">
		<div class="video-title-wrap" id="js-videoname">
			<!--第二讲——例证态度题如何没读懂照样对-->
		</div>
		<div class="video-wrapper clearfix">
			<div id="videoContent" class="video-content-wrap">
              <div class="prism-player" id="my-video"></div>
			</div>
			<div id="aboutVideo" class="about-video">
				<div class="title">相关视频</div>
				<div class="about-video-content">
					<a class="item" href="video.jsp?videoId=5" title="宋城教育&quot;最美教师&quot;">
						<div class="item-img">
							<img class="item-img-content"
								src="http://118.190.209.209/media/png/songcheng.20170910.png">
							<div class="item-play-icon"></div>
						</div>
						<div class="item-info">
							<div class="item-title">宋城教育&quot;最美教师&quot;|开封市庆祝第33个教师节</div>
							<div class="item-tip">
								<span>100人查看</span>
							</div>
						</div>
					</a> <a class="item" href="video.jsp?videoId=6"
						title="宋城教育&middot;最美的你">
						<div class="item-img">
							<img class="item-img-content"
								src="http://118.190.209.209/media/png/songcheng.20170913.png">
							<div class="item-play-icon"></div>
						</div>
						<div class="item-info">
							<div class="item-title">宋城教育由中共开封市委&开封市人民政府主办,
								由开封市教育局承办，"最美的你"活动。</div>
							<div class="item-tip">
								<span>192人查看</span>
							</div>
						</div>
					</a> <a class="item" href="video.jsp?videoId=7"
						title="宋城教育&middot;开封市举行诵读大赛">
						<div class="item-img">
							<img class="item-img-content"
								src="http://118.190.209.209/media/png/songcheng.20170920.png">
							<div class="item-play-icon"></div>
						</div>
						<div class="item-info">
							<div class="item-title">宋城教育&middot;开封市举行诵读大赛</div>
							<div class="item-tip">
								<span>116人查看</span>
							</div>
						</div>
					</a> <a class="item" href="video.jsp?videoId=8"
						title="宋城教育&middot;汉字大赛">
						<div class="item-img">
							<img class="item-img-content"
								src="http://mingzhanghui:8000/media/png/songcheng.20170927.png">
							<div class="item-play-icon"></div>
						</div>
						<div class="item-info">
							<div class="item-title">宋城教育&middot;汉字大赛</div>
							<div class="item-tip">
								<span>16人查看</span>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<%--视频下方信息 + 评论--%>
	<div class="all-info">
		<div class="all-info-left">
			<%--视频简介--%>
			<div class="video-detail-wrapper block">
				<div class="title">视频简介</div>
				<div class="video-detail-content block-content">
					<div>
						<p id="js-videointroduce">
							本期课程内容介绍：<br /> 力哥解惑：文章细节无数， 如何甄别核心细节所在？<br /> 系统班解题技巧之 细节题
							如何精准定位系统班解题<br /> 技巧之 例证题 初谈 如何没读懂照样做对
						</p>
						<%--这里可以添加图片...--%>
					</div>
				</div>
			</div>
			<%--评论--%>
			<div class="hot-comment block">
				<div class="title">热门评论</div>
				<div class="comm" id="bbComment">
					<div class="bb-comment">
						<%--notice & tab header--%>
						<div id="js-notice" class="reply-notice"></div>
						<div class="comment-header clearfix"></div>
						<%--发布评论--%>
						<div
							class="comment-send <%if (null==userid) {out.print("no-login");}%>">
							<div class="user-face">
								<img class="user-head" src="./publish/user/avatar/avatar.png">
							</div>
							<div class="textarea-container">
								<% if(null==userid) {out.print("<div class=\"baffle\">请先<a id=\"js-loginBaffle\" class=\"b-btn btn-open-mini-Login\" href=\"login.jsp\">登录</a>后发表评论 (・ω・)</div>");} %>
								<i class="ipt-arrow"></i>
								<textarea id="js-text" cols="80" name="msg" rows="5"
									placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" class="ipt-txt"></textarea>
								<button id="js-submit" type="button" class="comment-submit"
									<%if (null==userid) {out.print("disabled");}%>>发表评论</button>
							</div>
						</div>
						<%--评论列表--%>
						<div class="comment-list" id="js-comment">
							<div class="list-item" data-id="420424063" data-index="0">
								<%--左边头像--%>
								<div class="user-face" data-usercard-mid="614602">
									<a href="javascript:;"><img
										src="./publish/user/avatar/php.jpg" alt="用户头像" /></a>
								</div>
								<%--右边评论内容--%>
								<div class="con">
									<div class="user">
										<a class="name" href="javascript:;">CodeEmpreso</a>
									</div>
									<p class="text">用心做立绘，用手做角色，用脚做游戏。</p>
									<div class="info">
										<%--<span class="floor">#65</span>--%>
										<span class="time">15小时前</span>
										<%--<a class="delete" title="删除评论"><span class="glyphicon glyphicon-trash"></span></a>--%>
										<%--<span class="like"><i></i><span>201</span></span>--%>
										<%--<span class="hate"><i></i></span>--%>
										<%--<span class="reply btn-hover btn-highlight">回复</span>--%>
									</div>
								</div>
							</div>
							<div class="list-item" data-id="420424064" data-index="1">
								<%--左边头像--%>
								<div class="user-face" data-usercard-mid="614603">
									<a href="javascript:;"><img
										src="./publish/user/avatar/kaerfhgd.jpg" alt="用户头像" /></a>
								</div>
								<%--右边评论内容--%>
								<div class="con">
									<div class="user">
										<a class="name" href="javascript:;">kaerfhgd</a>
									</div>
									<p class="text">我们村这2G网就是快，第一没问题</p>
									<div class="info">
										<%--<span class="floor">#65</span>--%>
										<span class="time">16小时前</span>
										<%--<span class="like"><i></i><span>201</span></span>--%>
										<%--<span class="hate"><i></i></span>--%>
										<%--<span class="reply btn-hover btn-highlight">回复</span>--%>
									</div>
								</div>
							</div>
						</div>
						<%--评论分页--%>
						<div id="js-pagination" class="bottom-page paging-box-big">
							<span class="disabled">上一页</span> <span class="current">1</span>
							<a href="javascript:;" class="tcd-number">2</a> <a
								href="javascript:;" class="tcd-number">3</a> <a
								href="javascript:;" class="tcd-number">4</a> <span class="dot">...</span>
							<a href="javascript:;" class="tcd-number">11</a> <a
								href="javascript:;" class="next">下一页</a>
							<div class="page-jump">
								共<span>11</span>页, 跳至<input type="text" title="跳转到xx页" />页
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%--aside 热门推荐--%>
		<div id="allInfoRight" class="all-info-right">
			<div class="hot-recommend">
				<div class="title">热门推荐</div>
				<div class="hot-recommend-content">
					<div class="item">
						<a class="item-img" href="video.jsp?videoId=17"
							title="ULTIMATE PHOTO GUIDE"> 
                        <img class="item-img-content" src="http://118.190.209.209/media/poster/longdu_edu_1020va0.jpg">
							<div class="item-play-icon"></div>
						</a> <a class="item-title" href="video.jsp?videoId=17" title="龙都教育">龙都教育</a>
						<div class="item-tip"></div>
						<div class="item-bottom"></div>
					</div>
					<div class="item">
						<a class="item-img" href="video.jsp?videoId=18"
							title="Microsoft - The Verge" class="item-img">
                          <img class="item-img-content" src="http://118.190.209.209/media/poster/songcheng-6.jpg">
							<div class="item-play-icon"></div>
						</a> <a href="video.jsp?videoId=18" title="宋城教育6"
							class="item-title">宋城教育6</a>
						<div class="item-tip"><span>19人查看</span></div>
						<div class="item-bottom"></div>
					</div>
					<div class="item">
						<a class="item-img" href="video.jsp?videoId=19"
							title="Microsoft Power BI" class="item-img"> <img
							class="item-img-content"
							src="http://118.190.209.209/media/poster/kaifeng7tesebanxue.jpg">
							<div class="item-play-icon"></div>
						</a> <a href="video.jsp?videoId=19" target="_blank"
							title="开封7中特色办学" class="item-title">开封7中特色办学</a>
						<div class="item-tip">
							<span>20人查看</span>
						</div>
						<div class="item-bottom"></div>
					</div>
				</div>
			</div>
		</div>
	</div>    
    <div id="footer"></div>
    <script>$("#footer").load("./include/footer.html");</script>
	<!--<script src="http://vjs.zencdn.net/5.8.8/video.js"></script>-->	
	<script src="./js/common.js"></script>
	<script src="./js/myvideo.js"></script>
</body>
</html>