// 右上导航栏 a链接位置修改 (登录/账号设置/退出)  
(function() {
  var $navbar = $("#navbar");
 
  var $mid = $navbar.find(".nav-mid");
  $mid.find("a").each(function(i) {
	    var url = this.href;
	    if (url !== "javascript:;") {
	      this.href = "../.." + url.substring(url.lastIndexOf("/"));
	    }
  });
  var $right = $navbar.children(".navbar-right");
  $right.find("a").each(function(i) {  
    var url = this.href;
    if (url !== "javascript:;") {
      this.href = "../.." + url.substring(url.lastIndexOf("/"));
      this.href += "?referer=" + escape(window.location.href);
    }
  });
  var form = $right.children(".nav-main-search").children("form").get(0);
  form.action = "../../search.jsp";
  
	setTimeout(function() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "../../js/filter/activity.js";
		document.body.appendChild(script);
	}, 500);
	
	// 平台首页高亮
	$("#navbar").find("li").first().addClass("active");
})();