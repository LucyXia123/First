<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav navbar-nav nav-mid">
  <li><a href="${pageContext.request.contextPath}/index.jsp">平台首页</a></li>
  <li><a href="${pageContext.request.contextPath}/model-school.jsp">我的学校</a></li>
  <li><a href="${pageContext.request.contextPath}/video.jsp?videoId=61">本地课程</a></li>
  <li><a href="${pageContext.request.contextPath}/mytrack.jsp">学习中心</a></li>
  <li><a href="${pageContext.request.contextPath}/act-enroll.jsp" class="J_toActivity">征文活动</a></li>
  <li><a href="${pageContext.request.contextPath}/school-report-station.jsp">校园记者站</a></li>
</ul>

<script>
	setTimeout(function() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		// script.src = "./js/filter/activity.js";
		script.src = window.BASEURL ? 
				window.BASEURL + "js/filter/activity.js": 
				"http://www.tfjyzx.com/js/filter/activity.js";
		document.body.appendChild(script);
	}, 500);	

	(function() {
		var $nav = $("#navbar").children(".nav");
		
		var href = window.location.href;
		var getBaseURI = function(uri) {
			var lastIndex = uri.lastIndexOf('?');
			if (lastIndex >-1) {
				return uri.substring(0, lastIndex);
			}
			return uri;
		}
		$nav.find("a").each(function(i, a) {			
			var thisHref = getBaseURI(this.href);
			// var baseURI = getBaseURI(this.baseURI);
			var $this = $(this);
			if (thisHref === href) {
				$this.parent().addClass("active");
				$nav.find("a").not($this).parent().removeClass("active");
			}
		});
		
	})();
</script>
