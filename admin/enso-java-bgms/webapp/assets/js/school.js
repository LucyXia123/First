$(function() {
	const URL_GET_ONE = "getSchoolById",
		  URL_QUERY = "listSchoolByArea";
	
	var School = {
		"id": "ID",
		"area": "地区",
		"title": "学校名称",
		"pinyin": "校名拼音",
		"logo": "学校校徽url",
//		"img": "图片url(暂未展示)",   // 学校介绍下面的图片 
		"content": "学校简介",
		"avatar": "校长头像url",
		"words": "校长寄语"		
	};
	
	var area_option = function(domid, callback) {
		var select = document.getElementById(domid);
		$.ajax({
			type: "GET",
			url: "data/area.json",
			cache: true
		}).done(function(data) {
			data.area.forEach(function(elem) {
				var option = document.createElement("option");
				option.value = elem;
				option.innerHTML = elem;
				select.appendChild(option);
			});
			callback && callback(select);
		});
	};	
	area_option("i-area", function(select) {
		select.onchange = function() {
			$("#js-query").trigger("click");
		};
	});
	area_option("new-area");
	area_option("edit-area");
	
	// bootstrap modal
	var g_id = null;
	var $tbody = $("#js-list");
	var $alert = $("#js-alert");
	
	var loader = new Script(function() {
		// 学校列表
	    $("#js-query").on("click", function() {
	    	var $this = $(this);	
	    	$this.text("查询中...");

	    	var d = {"area":$("#i-area").val()};
	    	
	    	$.ajax({
	    		type: "GET",
	    		url: URL_QUERY,
	    		data: d
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
						
						// 学校ID
						$("<td>").text(elem.id).appendTo($tr);					
						// 学校名称
						var $a = $("<a>").addClass("title").attr("href", "javascript:;").text(elem.title);
						$a.on("click", function(e) {
							e.preventDefault();
							g_id = $(this).parent().parent().attr("data-id");
							$myModal.modal("show");
						});
						$("<td>").append($a).appendTo($tr);							
						// 学校所在地区						
						$("<td>").text(elem.area).appendTo($tr);
						// 校徽
						var $td = $("<td>");
						$a = $("<a>").addClass("img").attr("target", "_blank").attr("href", elem.logo);
						$a.append($("<img>").attr("src", elem.logo));
						$("<td>").append($a).appendTo($tr);	
						// 学校简介
						var $div = $("<div>").addClass("text").html(elem.content);
						$("<td>").append($div).appendTo($tr);						
						// 校长头像url
						$td = $("<td>");
						$a = $("<a>").addClass("img").attr("target", "_blank").attr("href", elem.avatar);
						$a.append($("<img>").attr("src", elem.avatar));
						$("<td>").append($a).appendTo($tr);						
						// 校长寄语
						$div = $("<div>").addClass("text").html(elem.words);
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
	    	})
	    }).trigger("click");     // end $("#js-query").on("click", ...
		
	    // 查看学校详情modal
		var $myModal = $("#myModal");	
		$myModal.on("show.bs.modal", function() {
    		$.ajax({
    			type: 'GET',
    			url: URL_GET_ONE,
    			data: {"id":g_id}
    		}).done(function(data) {
    			var $target = $("#detail-show").empty();
    			Object.keys(School).forEach(function(key, idx) {
    				if (data.hasOwnProperty(key)) {
    					var $dt = $("<dt>").text(School[key]);
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
    			Object.keys(School).forEach(function(key, idx) {
    				if (data.hasOwnProperty(key)) {
    					$t = $("#edit-" + key);
    					if ($t.length > 0 && $t.get(0).type !== "file") {
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
    		$.ajax({
    			type: "POST",
    			url: "updateSchool",
    			data: formData,
    	        cache: false,
    	        contentType: false,
    	        processData: false,
    	        dataType: "html"
    		}).done(function(data) {
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
    	
    	// 新增学校
		var $newModal = $("#newModal");
		$newModal.on("show.bs.modal", function() {
			$("#new-area").val( $("#i-area").val() );
		});
		$("#js-new").on("click", function() {
			$newModal.modal("show");
		});
    	$("#js-add").on("click", function() {    		    		
    		var form = document.getElementById("form-new");
    		var formData = new FormData(form);
    		$.ajax({
    			type: 'POST',
    			url: "newSchool",
    			data: formData,
    			cache: false,
    			contentType: false,
    			processData: false,
    			dataType: "html"
    		}).done(function(resp) {
    			try {
    				var data = JSON.parse(resp);				
    			} catch (e) {
    				$("#newAlertDanger").html(data.cause).show();
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
});