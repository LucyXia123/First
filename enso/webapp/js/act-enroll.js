/**
 * 我的活动 "未来小作家" 作文评选活动
 * depends on common.js
 */
window.g_activity = {
    name: "未来小作家(default)"
};
window.g_student = {
    realname: "张三(default)",
    area: "开封(default)"
};
window.g_participant = {
    slogan: "default slogan"
};

$(function() {
    // 当前登录用户的ID
	var userId = null;
	var form = document.getElementById("J_enroll");
	var $myModal = $("#myModal"); // 我的名片
	 
	if (DEBUG) {
		userId = "STU201709301537110001";
	} else {
		userId = $("#js-userid").val();
	    if (null === userId || "null" === userId) {
	        location.href = "login.jsp?referer=" + escape(location.href);
	        return false;
	    }
	}	

    // 取得学生信息  /student/getuser?userid=STU201709301537110001
    $.ajax({
        type: "GET",
        url: "./student/getuser",
        data: {
            "userid": userId
        }
    }).done(function(data) {
        ['mobile', 'username', 'usernumber', 'realname', 'school', 'area', 'grade', 'classname'].forEach(function(prop) {
            if (data.hasOwnProperty(prop)) {
                g_student[prop] = data[prop];
            }
        });

        /*
        if (data.type !== "学生") {
            alert("只有学生用户才能参加活动");
            location.href = "index.jsp";
            return false;
        }
        */
        var school = g_student.school;
        var a = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
        var g = g_student.grade;
        var grade = (g > 9) ? g : a[g];

        $("#J_realname").text(g_student.realname);
        var nm = printf("{0}{1}年级{2}班", school, grade, g_student.classname);
        $("#J_school").text(nm);
        console.log(g_student);
        console.log(nm)
    });

    // 取得活动信息
    $.ajax({
        type: "GET",
        url: "./querySchoolActivity",
        data: {
            "id": DEFAULT_ACTIVITIY_ID
        }
    }).done(function(data) {
        var $act = $("#J_act");
        try {
            // 能正常取得活动信息数据
            if (data.hasOwnProperty("name")) {
                $act.empty();
                $("<h1>").addClass("act-title").text(data.name).appendTo($act);
                var $article = $("<div>").addClass("article");
                $act.append($article);
                $article.append($("<div>").addClass("act-label").html(printf("活动介绍、活动安排、活动详情、参与人、颁奖等介绍 ({0}至{1})", data.startDate, data.finishDate)));
                $article.append($("<div>").addClass("act-info").text(data.description));
            }
        } catch (e) {
            console.error(e);
        }
        Object.keys(data).forEach(function(key, idx) {
            window.g_activity[key] = data[key];
        });
        console.log(g_activity);

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("[" + textStatus + "]: " + errorThrown);
    });

    // 查看是否已经报名, 取得报名信息
    $.ajax({
    	type: "GET",
    	url: "./queryActivityParticipantsByConditional",
    	data: {usernumber: userId, activityId: DEFAULT_ACTIVITIY_ID}
    }).done(function(data) {
    	if (data.hasOwnProperty("slogan")) {
            $("#J_preview").html("<img src=\""+data.avatarUrl+"\" title=\"用户头像\" />");
            $("#J_slogan").val(data.slogan);
            $("#J_instructor").val(data.instructor);
            Object.keys(data).forEach(function(key, idx) {
                window.g_participant[key] = data[key];
            });
    	}
    	// 已经报名, 跳转到上传作品页面; 返回编辑报名, 不跳转 
    	if (data.hasOwnProperty("avatarUrl")) {
    		var qs = enso_query_string();
    		var edit = qs.hasOwnProperty("edit") ? qs.edit : 0;
    		if ( !edit ) {
    			location.href = "act-upload.jsp";
    		}    		
    		return true;
    	}
    	console.log(window.g_participant);
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	alert(printf("取得报名信息ERROR[{0}]: {1}", textStatus, errorThrown));
    });   
    
    // 活动报名预览 handler
    $myModal.on("show.bs.modal", function(e) {
        var $card = $("#J_card").empty();
        var $canvas = $("<div>").addClass("card-canvas").appendTo($card);
                      
        $canvas.qrcode({
            render: "canvas",
            text: BASEURL + "queryActivityParticipantsByConditional?userNumber="+userId + "&activityId=" + DEFAULT_ACTIVITIY_ID,
            height: 280,
            width: 280,
            src: g_participant.avatarUrl
        });
        var $metas = $("<div>").addClass("card-metas").appendTo($card);
        var $p = $("<p>").html(printf("{0}正在参加{1}电视台举办的<br />{2}", g_student.realname, g_student.area, g_activity.name));
        $metas.append($p);
       
        var $title = $("<div>").addClass("title").html("<div>参赛作品</div><div class=\"slogan\">" + form.slogan.value + "</div>");
        $metas.append($title);
        $("<span>").text("扫描二维码围观").appendTo($metas);    	
    });
    
    // 活动报名预览 trigger
    (function($myModal, userId, form) {        
        form.userNumber.value = userId;               
        
        $(form).on("submit", function(e) {
            e.preventDefault();
            $myModal.modal("show");           
        });
        
        // modal buttons
        var $buttons = $("#J_buttons").empty();
        var $btn = $("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">重新生成</button>");
        $btn.on("click", function() {
        	$myModal.modal("hide");
        });
        // 报名提交表单
        var $a = $("<a type=\"button\" class=\"btn btn-primary\" href=\"javascript:;\">确认提交</a> ");
        $a.on("click", function() {
        	var $this = $(this);
        	        	
        	// 是否已经提交过
        	var qs = qs || enso_query_string();
        	var edit = qs.hasOwnProperty("edit") ? qs.edit : 0;
        	
        	var $imga = $("#J_preview").find("img");
        	var imgUrl = "";
        	// 没有上传过头像 
        	if ($imga.length===0) {
    			alert("新报名必须上传头像");
    			return false;
        	} else {        		
        		imgUrl = $imga.attr("src");
            	if (imgUrl.substring(imgUrl.lastIndexOf('/')) === "/default-image.jpg" || imgUrl === "null") {
            		if ($("#J_upload").val() === "") {
            			alert("新报名必须上传头像");
            			return false;
            		}
            	}
        	}
        	/*
        	if (1===edit) {
        		location.href = "act-upload.jsp";
        		return true;
        	}
        	*/ 	
        	
            var formdata = new FormData(form);
            if (!form.hasOwnProperty("activityId")) {
            	formdata.append("activityId", DEFAULT_ACTIVITIY_ID);
            }
            
            $.ajax({
                url: "insertActivityParticipants",
                type: "POST",
                data: formdata,
                cache: false,
                contentType: false,
                processData: false,
                dataType: "html"
            }).done(function(resp, textStatus, jqXHR) {
            	if (DEBUG) {
                	alert(resp);
            	}
            	var data = JSON.parse(resp);
            	console.log(data);
            	setTimeout(function() {
            		$this.html("确认提交").removeClass("disabled").css({"cursor":"pointer"});
            		location.href = "./act-upload.jsp";
            	}, 1200);
            });
            $this.html("提交中...").addClass("disabled").css({"cursor":"not-allowed"});
        });
        $buttons.append($btn).append($a);
    })($myModal, userId, form);

    // 图片上传预览
    (function(p) {
    	$("#J_upload").on("change", function() {
            var file = this;
            var prevDiv = document.getElementById('J_preview');
            prevDiv.innerHTML = "";
            if (file.files && file.files[0]) {
                var reader = new FileReader();
                var img = null;
                reader.onload = function(e) {
                	img = new Image();
                    img.width = 94;
                    img.height = 78;
                    p.avatarUrl = img.src = e.target.result;
                    prevDiv.appendChild(img);
                };                
                reader.readAsDataURL(file.files[0]);
            } else {
                prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'; width=160px; height=160px;"></div>';
            }
    	});
    })(g_participant);
    
    // 正文活动高亮
    $("#navbar").children(".nav").find("li").eq(4).addClass("active");

});
