$(function() {
	var userId = $("#js-userid").val();
		
	// 学生可以进入活动, 家长和游客不能进入活动
	$(".J_toActivity").on("click", function(e) {
		e.preventDefault();
		
		var that = this;
		
		if (null===userId || "null"===userId) {
			location.href = "login.jsp?referer=" + escape(location.href);
			return false;
		}
		$.ajax({
			type: "GET",
			url: "./student/getuser",
			data: {"userid": userId}
		}).done(function(data) {
			if (data.type !== "学生") {
				that.href = "act4guest.jsp";
				location.href = "act4guest.jsp";
				return false;
			}
			
		    // 查看是否已经报名, 取得报名信息
		    $.ajax({
		    	type: "GET",
		    	url: "./queryActivityParticipantsByConditional",
		    	data: {usernumber: userId, activityId: DEFAULT_ACTIVITIY_ID}
		    }).done(function(data) {
		    	
		    	if (data.hasOwnProperty("avatarUrl")) {
		    		// 已经报名, 跳转到上传作品页面.
		    		that.href = "act4guest.jsp";
		    		location.href = "act-upload.jsp";			    		
		    	} else {
		    		// 未报名, 跳转到报名页面.
		    		that.href = "act-enroll.jsp";
		    		location.href = "act-enroll.jsp";
		    	}
		    	
		    }).fail(function(jqXHR, textStatus, errorThrown) {
		    	alert(printf("取得报名信息ERROR[{0}]: {1}", textStatus, errorThrown));
		    });
		    
		});
	});
	
});
