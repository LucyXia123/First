var URL_USERINFO_GET = './student/getuser';

(function() {
	// login control
	var userid = $("#js-userid").val();
	if (null == userid || "null" == userid || "" === userid) {
		location.href = "login.jsp?referer=" + escape(location.href);
	}

	// load tab
	$("#js_navcont").load("./include/mynav.html", function() {
		var $li = $(this).find("li.select");
		var a = null, href = "";
		for (var i = 0; i < $li.length; i++) {
			a = $li[i].children[0];
			if (a.href === window.location.href) {
				a.parentNode.classList.add("active");
			}
		}
		
		var $this = $(this);
		
		// 取得用户名
        $.ajax({
            type: "GET",
            url: URL_USERINFO_GET,
            data: {"userid":userid},
            dataType: "json"
        }).done(function(data) {
        	$this.find("li.person").children("span").html(data.username);
        });
	});
	
    // 学习中心高亮
    $("#navbar").children(".nav").find("li").eq(3).addClass("active");
})();
