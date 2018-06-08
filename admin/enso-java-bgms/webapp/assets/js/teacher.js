$(function() {
	const URL_QUERY = "getTeachers",
	      URL_GET_ONE = "getTeacherById";

	var school_option = function(domid) {
		var select = document.getElementById(domid);
		$.ajax({
			type: "GET",
			url: "data/online-list.json",
			cache: true
		}).done(function(data) {
			data.list.forEach(function(elem) {
				var option = document.createElement("option");
				option.value = elem;
				option.innerHTML = elem;
				select.appendChild(option);
			});
		});
	};	
	school_option("i-school");
	school_option("new-school");
	school_option("edit-school");
	
	var typelist = ["教师","学生"];
	var type_option = function(domid) {
		var select = document.getElementById(domid);
		typelist.forEach(function(elem) {
			var option = document.createElement("option");
			option.value = elem;
			option.innerHTML = elem;
			select.appendChild(option);
		});
	};
	type_option("i-type");
	type_option("edit-type");
	type_option("new-type");
	
	var Teacher = {
		type: "学生/老师",
		name: "姓名",
		img: "照片",
		school: "所在学校",
		intro: "简介",
		content: "详细内容"
	};
	
	// bootstrap modal
	var g_id = null;               // 数据库自增id
	var $tbody = $("#js-list");    // 结果集列表
	var $alert = $("#js-alert");   // 查询出错feedback
	var $myModal = $("#myModal");  // 详情modal
	
	var loader = new Script(function() {
		// 教师/学生列表
	    $("#js-query").on("click", function() {
	    	var $this = $(this);
	    	$this.text("查询中...");	    	
	    	
	    	$.ajax({
	    		type: "GET",
	    		url: URL_QUERY,
	    		data: $("#form-query").serialize()
	    	}).done(function(data, textStatus, jqXHR) {
	    		$this.text("查询");
	    		// render table
	    		$tbody.empty();
	    		
	    		if (data instanceof Array) {
					if (data.length===0) {
						var $td =  $("<td>").attr("colspan",7).text("查询结果为空").css({"text-align":"center"});
						$("<tr>").append( $td ).appendTo($tbody);
						return false;
					}
					data.map(function(elem) {
						var $tr = $("<tr>").attr("data-id", elem.id);
						
						// 教师或学生ID
						$("<td>").text(elem.id).appendTo($tr);					
						// 姓名
						var $a = $("<a>").attr("href", "javascript:;").text(elem.name);
						$a.on("click", function(e) {
							e.preventDefault();
							g_id = $(this).parent().parent().attr("data-id");
							$myModal.modal("show");
						});
						$("<td>").append($a).appendTo($tr);						
						// 所在学校									
						$("<td>").text(elem.school).appendTo($tr);
						// 身份
						$("<td>").text(elem.type).appendTo($tr);
						// 头像图片url
						// $("<td>").text(elem.img).appendTo($tr);
						$a = $("<a>").addClass("img").attr("target", "_blank").attr("href", elem.img);
						$a.append($("<img>").attr("src", elem.img));
						$("<td>").append($a).appendTo($tr);
						// 学校简介
						var $div = $("<div>").addClass("text").html(elem.intro);
						$("<td>").append($div).appendTo($tr);									

						var $button = $("<button>").addClass("btn btn-primary btn-xs").html("<i class=\"fa fa-pencil\"></i>");
						$button.on("click", function(e) {
							e.preventDefault();
							g_id = $(this).parent().parent().attr("data-id");
							$editModal.modal("show");
						});
						$("<td>").append($button).appendTo($tr);
						
						$tbody.append($tr);
						return $tr;
					});
					$alert.hide();
				} else {
					$alert.text("查询结果格式不正确").show();
				}
	    	}).fail(function(jqXHR, textStatus, errorThrown) {
	    		$this.text("查询");
	    		var $alert = $("#js-alert");
	    		$alert.html("查询服务器通信出错["+textStatus+"]: " + errorThrown).show();
	    		setTimeout(function() {
	    			$alert.fadeOut(1000);
	    		}, 3000);
	    	});
	    }).trigger("click");
	    
	    // 查看学校详情modal
		var $myModal = $("#myModal");	
		$myModal.on("show.bs.modal", function() {
    		$.ajax({
    			type: 'GET',
    			url: URL_GET_ONE,
    			data: {"id":g_id}
    		}).done(function(data) {
    			var $target = $("#detail-show").empty();
    			Object.keys(Teacher).forEach(function(key, idx) {
    				if (data.hasOwnProperty(key)) {
    					var $dt = $("<dt>").text(Teacher[key]);
    					var $dd = $("<dd>").html(data[key]);
    					$target.append($dt).append($dd);
    				}
    			});
    		});
		});
		
    	// 编辑内容 取出内容
		var $editModal = $("#editModal");
    	$editModal.on("show.bs.modal", function() {
    		$.ajax({
    			type: 'GET',
    			url: URL_GET_ONE,
    			data: {"id":g_id}
    		}).done(function(data) {
    			var $t = null;
    			Object.keys(Teacher).forEach(function(key, idx) {
    				$t = $("#edit-" + key);
    				if (data.hasOwnProperty(key)) {
    					if ($t.length>0 && $t.get(0).type !== "file") {
    						$t.val(data[key]);	
    					}		
    				}
    			}); // END Object.keys(Video)
    			$("#edit-id").val(g_id);
    		});
    	}).on("hidden.bs.modal", function() {
    		$("#editAlertDanger").hide();
    		$("#editAlertSuccess").hide();
    	});
    	
     	// 修改内容
    	$("#submit-edit").on("click", function(e) {
    		e.preventDefault();
    		var form = document.getElementById("form-edit");
    		var formData = new FormData(form);
    		$("#editAlertDanger").hide();
    		
    		$.ajax({
    			type: "POST",
    			url: "updateTeacher",
    			data: formData,
    			cache: false,
    			contentType: false,
    			processData: false,
    			dataType: "html"
    		}).done(function(resp) {
    			var data = null;
    			try {
    				data = JSON.parse(resp);				
    			} catch (e) {
    				$("#editAlertDanger").html("parse JSON failed: " + resp).show();
    				console.error(e);
    				return false;
    			}
    			if (0===data.result) {
    				$("#editAlertSuccess").html("保存成功!").show();
    				// update table
    				$("#js-query").trigger("click");
    				// hide modal
    				setTimeout(function() {
    					$editModal.modal("hide");
    				}, 1000);
    			} else {
    				$("#editAlertDanger").html(data.cause).show();
    			}
    		}).fail(function(jqXHR, textStatus, errorThrown) {
    			$("#editAlertDanger").html("修改内容通信错误["+textStatus+"]: " + errorThrown);
    		});
    	});
    	
     	// 新增教师学生 modal show
		var $newModal = $("#newModal");
		$("#js-new").on("click", function() {
			$newModal.modal("show");
		});
		// 新增记录 设置默认学校和身份
		$newModal.on("show.bs.modal", function() {
			$("#new-school").val( $("#i-school").val() );
			$("#new-type").val( $("#i-type").val() );
		});
		// 新增教师学生 handler
    	$("#js-add").on("click", function() {
    		var form = document.getElementById("form-new");
    		var formData = new FormData(form);    		
    		$.ajax({
    			type: 'POST',
    			url: "newTeacher",
    			data: formData,
    			cache: false,
    			contentType: false,
    			processData: false,
    			dataType: "html"
    		}).done(function(resp) {
    			try {
    				var data = JSON.parse(resp);				
    			} catch (e) {
    				$("#newAlertDanger").html("parse json failed: " + resp).show();
    				console.error(e);
    				return false;
    			}
    			if (0===data.result) {
    				// update table
    				$("#js-query").trigger("click");
    				
    				$("#newAlertSuccess").html("新增成功: " + data.cause).show();
    				// hide modal
    				setTimeout(function() {
    					$newModal.modal("hide");
    				}, 1000);
    			} else {
    				$("#newAlertDanger").html(data.cause).show();
    			}
    			return true;
    		});
    		$("#newAlertDanger").hide();
    		$("#newAlertSuccess").hide();
    	}).on("hidden.bs.modal", function() {
    		$("#newAlertDanger").hide();
    		$("#newAlertSuccess").hide();
    	});    	
    	    	
	});
	loader.set("assets/js/bootstrap.min.js");
	
    // 初始状态隐藏错误alert
    $(".alert").hide();
    
    $("#i-type").on("change", function() {
    	$("#js-query").trigger("click");
    });
    $("#i-school").on("change", function() {
    	$("#js-query").trigger("click");
    });
});