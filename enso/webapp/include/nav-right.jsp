<%@page import="com.tsinghuadtv.www.util.session.MyHttpSessionListener"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
@font-face {
  font-family: "iconfont";
  src: url('./fonts/iconfont.eot?t=1507623604954'); /* IE9*/
  src: url('./fonts/iconfont.eot?t=1507623604954#iefix')
    format('embedded-opentype'), /* IE6-IE8 */     
    url('data:application/x-font-woff;charset=utf-8;base64,d09GRgABAAAAAAgkAAsAAAAAC5QAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABHU1VCAAABCAAAADMAAABCsP6z7U9TLzIAAAE8AAAARAAAAFZXKksUY21hcAAAAYAAAAB5AAAByJ6e2XlnbHlmAAAB/AAABA4AAAUISuGkSWhlYWQAAAYMAAAALwAAADYPJIspaGhlYQAABjwAAAAcAAAAJAfeA4dobXR4AAAGWAAAABMAAAAYF+kAAGxvY2EAAAZsAAAADgAAAA4EsgNCbWF4cAAABnwAAAAfAAAAIAEVAJ9uYW1lAAAGnAAAAUUAAAJtPlT+fXBvc3QAAAfkAAAAQAAAAFV7YGn3eJxjYGRgYOBikGPQYWB0cfMJYeBgYGGAAJAMY05meiJQDMoDyrGAaQ4gZoOIAgCKIwNPAHicY2Bk/sM4gYGVgYOpk+kMAwNDP4RmfM1gxMjBwMDEwMrMgBUEpLmmMDgwVLxUY27438AQw9zAsAcozAiSAwAp0gzVeJzFkdENgzAMRJ+bpEIVc/DVSSrmQXywCutZYgp6TsIHE3DRi3InR45ioABJfEUG2zBCi1KreeJT88xPfmTgpfPq5sX3YzpPpXd3yVR9rXBZN0t0szePyZ5rfddY97m7mMPa0RO9EzPx0tDv4Xsj6o+pQfoD/zkaewAAAHicRVPdb9tUFL/n3tiJHec6juOPpHESO43drG2aNE66bKR52NquH9BUrWjCpGpDIpo62heqFqQBhcKEJsQqpP0BfEj8AeVxE3vijT8CMfEASBWviAZunBWsc+6Vj31/55zf+V3EIfTPz+QpMZGKJlAV3UQdhICfBIdiC2zPL+NJ0GxOM5KUeAXPDhecMmmB4fBJfbbhuwYf5mWgkIWaPdvwytiDuj+Pr8OsbgGkxtKbiWImQU5BNL3sp4MV/DVouUJGnp8eLE+1k7N5NXIkJRKpROLzCM9xEYxDMoU9Qxc4QeQH33JyWnuaK+EcSCkvvdaL5ccSdz/z962iIQAcH4M6lqfftZW0wuxBWlcTqXA8FjHTscJ4Eo5eRE1VstxfEHsI6/UmOScuGkd30JcIqRS8MtTnoQ3V4dKYa2SBD1MYWnXkGhh6Msw7MxCEC47rNaEMXnXkfmNuHoaWBaPJSDCqI08GMKTMDrwETOrBt8uf2JHGXBOCw77rVd0R4l+8xokiL3OuqFGDUlERI4KsCaLCMeNjuHZGaXQ8emY2HXe7MvWGd+WWbfkyTSSoWrHaX91hockl26qosqLI1DZ+shfdVz+uZkpUlGWReqbZdpyFtUX7RiV1RaSSRK0KA4MfxHAINJGa1BVVIRZhs0gqXEgSWDEsIKri4PhMzkcp/d606ct81urk1OvbQcb2EoBCk5VM9pbnbbHY1A0TvyNHr9YZur24tuDYr2SCjCCLLOV/VVgTFKEQm80eeYb/QHGUQTW0jFDRKWOmpNksTlJMGIeMOEZXGVTXcynoBnsfLrUq0x0LcQGX5WB4TJT6MD7XwMjfWfG8lZ17o83f3Md4fzNYf5tfj5lRIbawa6lhrEiyiiO6c1G0dhckMWpKnQeOHsEJOabg8K//Qww3+PEShK0XL1oOJ6RMLru7MF2RZF2+6m/sMlTeTAmc/X7Hb1JdlipMgzgQ4m18zrqcYD0W2NB5x2MlF4LevJGUgm6ZerzLtsntmUi/UzuqdfqRmXB/Y9oHcvr23ikBf3rjrRN82OseYnzY7R3Gew/zqVT+YW/nSanQb91/TMjj+61+ofQEPukeEHLQ7R5gfIBGnH9IviHHaBI1UAtts3o8hd14L+nVy8CEqzHBB+LVlDCfzMJ1Jl5Gao25yy64UXCdYd0qK7RGoWgz/uvK6D7UWNnMFD4HDZ18sZy7vlEWSGOrDiSEK528lVsXVCv+PLb97nbseboIwqAvJYgkmIKAlWjeF55JPrQuFrcwwJvrXE5rag63dhdD6DXA9z4AearXaakQIqVGo0QAx1ulUrc8+PMRzWfi15aWrjlpA5/HohgL9O/3YNwacz865Xim8dUTCwSMRWKdrA5+F/hHmPwLUejKhAAAeJxjYGRgYADieYoS7+P5bb4ycLMwgMA1psVbEPR/FxYG5gYgl4OBCSQKABbGCakAeJxjYGRgYG7438AQw8IAAkCSkQEVsAEARwwCb3icY2FgYGB+ycDAwoCKARKfAQEAAAAAAAB2AUQBuAH+AoQAAHicY2BkYGBgY5jMwMoAAkxAzAWEDAz/wXwGABe6AbUAeJxlj01OwzAQhV/6B6QSqqhgh+QFYgEo/RGrblhUavdddN+mTpsqiSPHrdQDcB6OwAk4AtyAO/BIJ5s2lsffvHljTwDc4Acejt8t95E9XDI7cg0XuBeuU38QbpBfhJto41W4Rf1N2MczpsJtdGF5g9e4YvaEd2EPHXwI13CNT+E69S/hBvlbuIk7/Aq30PHqwj7mXle4jUcv9sdWL5xeqeVBxaHJIpM5v4KZXu+Sha3S6pxrW8QmU4OgX0lTnWlb3VPs10PnIhVZk6oJqzpJjMqt2erQBRvn8lGvF4kehCblWGP+tsYCjnEFhSUOjDFCGGSIyujoO1Vm9K+xQ8Jee1Y9zed0WxTU/3OFAQL0z1xTurLSeTpPgT1fG1J1dCtuy56UNJFezUkSskJe1rZUQuoBNmVXjhF6XNGJPyhnSP8ACVpuyAAAAHicY2BigAAuBuyAjZGJkZmRhZGVkY2RnYGxgjM3MT0vMy0ztYiltDi1iBVEGPHk5ZcAxZITSzLz8xgYAAkDDTQ=')
    format('woff'), url('./fonts/iconfont.ttf?t=1507623604954')
    format('truetype'),
    /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
   url('./fonts/iconfont.svg?t=1507623604954#iconfont') format('svg');
  /* iOS 4.1- */
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-magnifier:before {
  content: "\e605";
}

.icon-user:before {
  content: "\e926";
}

.icon-user2:before {
  content: "\e601";
}

.icon-notification:before {
  content: "\e69d";
}
</style>

<%
	String userid = null, username = null;
	if (null != session) {
		userid = (String) session.getAttribute("userid");
		username = (String) session.getAttribute("username");
		out.println("<input type='hidden' id='js-userid' value='" + userid + "' data-username='" + username
				+ "'/>");
		// out.println("<input type='hidden' id='js-online-count' value='"+MyHttpSessionListener.getCount()+"'/>");
	}
%>
<div id="top"></div>
<ul class="nav navbar-nav navbar-right">
  <li class="nav-main-search">
    <form action="search.jsp" method="get" role="search">
      <div class="search-wrap">
        <label for="main-q" class="offscreen"></label> <input
          type="search" id="main-q" name="q" placeholder="搜索课程,视频" /> <a
          class="search-trigger" href="javascript:;"><i
          class="iconfont icon-magnifier"></i></a>
        <button type="submit" class="offscreen">Search</button>
      </div>
    </form>
  </li>
  <li><a href="javascript:;"><i
      class="iconfont icon-notification"></i></a></li>
  <li class="header-tool-user"><a href="javascript:;"><i class="iconfont icon-user"></i></a>
    <div class="header-tc-content">
      <span class="header-tc-ct-bg"></span>
      <ul class="header-user-menu-list">
        <%
            final String CONTEXT_PATH = request.getContextPath();
        
        	if (null == userid) {
        		out.print("<li class=\"header-user-menu-item\"><a href=\""+CONTEXT_PATH+"/login.jsp?referer=model-school.jsp\">登录</a></li>");
        		out.print("<li class=\"header-user-menu-item\"><a href=\""+CONTEXT_PATH+"/register.jsp\">注册</a></li>");
        		// out.print("<li class=\"header-user-menu-item\"><a href=\"forgot-password.jsp\">找回账号</a></li>");
        	} else {
        		out.print("<li class=\"header-user-menu-item\"><a href=\""+CONTEXT_PATH+"/mytrack.jsp\" title=\"我的个人中心\">" + username
        				+ "</a></li>");
        		out.print("<li class=\"header-user-menu-item\"><a href=\""+CONTEXT_PATH+"/mysettings.jsp\">账号设置</a></li>");
        		out.print("<li class=\"header-user-menu-item\"><a href=\""+CONTEXT_PATH+"/logout\">退出</a></li>");
        	}
        %>
      </ul>
    </div>
  </li>
</ul>
<%
	session.setAttribute("onlineCount", MyHttpSessionListener.getCount());
%>
<script>
/**
 * 站内搜索
 */
	(function() {
		var $nav = $("#navbar");
		var $navItems = $nav.children().eq(0);
		var $mainNavSearch = $nav.find(".nav-main-search");
		var $searchWrap = $nav.find(".search-wrap");
		var $searchTrigger = $searchWrap.find(".search-trigger");
		var $input = $searchWrap.find("input");
		var $form = $mainNavSearch.find("form");

		$form.on("submit", function(e) {
			e.preventDefault();
			if (0 == $.trim($(this).find("input").val()).length) {
				alert("搜索关键词不能为空");
				return false;
			}
			this.submit();
		});

		$searchTrigger.on("click", function(e) {
			if ($navItems.css("display") !== "none") {
				$input.get(0).focus();
			} else {
				// 搜索框打开状态
				$form.submit();
			}
		});
		var timeout = null;
		var createExpander = function(isAdd) {
			return function(e) {
				if (isAdd) {
					$input.select();
				}
				// if ($mainNavSearch.css("display") === "block") {return;}
				if (e)
					e.preventDefault();
				if (timeout)
					clearTimeout(timeout);
				timeout = setTimeout(function() {
					if (isAdd) {
						$navItems.fadeOut(100, function() {
							$navItems.css("display", "none");
							$searchWrap.addClass("expanded");
							$nav.addClass("expand");
						})
					} else {
						$nav.removeClass("expand");
						timeout = setTimeout(function() {
							$searchWrap.removeClass("expanded");
							$navItems.fadeIn(400);
						}, 250)
					}
				});
			}
		};
		$input.on("focus", createExpander(true)).on("blur", createExpander())

		$input.on("keydown", function(e) {
			e = e || window.event;
			if (e.keyCode === 13) {
				$form.submit();
			}
		});
	}).call();
</script>

