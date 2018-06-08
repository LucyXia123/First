/**
 * Created by Administrator on 2017-11-19.
 */
$(function() {
	const URL_GET_ONE = "getCourseById",
		  URL_QUERY = "getcourses";
	
    // 已经上线学校列表 dropdown
    var dropdown = function(domid, callback) {
    	$.ajax({
    		cache: true,
    		url: "data/online-list.json",
    		async: false
    	}).done(function(data) {
            try {
                var select = document.getElementById(domid);
                $.each(data.list, function (key, val) {
                    var option = document.createElement("option");
                    option.value = val;
                    option.innerText = val;
                    select.appendChild(option);
                });
                callback && callback(select);
            } catch (e) {
                console.error(e);
            }
    	});
    };
    dropdown("i-school", function(select) {
    	select.onchange = function() {
    		$("#js-query").trigger("click");
    	};
    });
    dropdown("new-school");
    dropdown("edit-school");
    
	var Course = {
		"id": "ID",
		"title": "课程标题",
		"img": "课程图片",
		"school": "所属学校",
		"url": "链接到其他网站(可选)",
		"videoid": "对应的视频ID"		
	};

    var $newModal = $("#newModal"), $myModal = $("#myModal"), $editModal = $("#editModal");
    var g_id = null;
    var $tbody = $("#js-list");

    // bootstrap modal
    var loader = new Script(function() {
        
        // 课程查询 error feedback
        var $alert = $("#js-alert");
        
        $("#js-query").on("click", function(e) {
        	e.preventDefault();
        	
        	var $this = $(this);
        	
        	$this.text("查询中...");
        	var t = $("#i-title").val().trim();
        	var d = {"school":$("#i-school").val()}
        	if (""!== t) {
        		d["title"] = t;
        	}
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
    					var $td =  $("<td>").attr("colspan",6).text("查询结果为空").css({"text-align":"center"});
    					$("<tr>").append( $td ).appendTo($tbody);
    					return false;
    				}
    				data.map(function(elem) {
    					var $tr = $("<tr>").attr("data-id", elem.id);
    					
    					// 课程ID
    					$("<td>").text(elem.id).appendTo($tr);
    					
    					// 课程标题
    					var $a = $("<a>").addClass("info").attr("href", "javascript:;").text(elem.title);
    					$a.on("click", function(e) {
    						e.preventDefault();
    						g_id = $(this).parent().parent().attr("data-id");
    						$myModal.modal("show");
    					});
    					$("<td>").append($a).appendTo($tr);
    					
    					// 课程图片
    					var url = elem.img;
    					$a = $("<a>").addClass("img").attr("target", "_blank").attr("href", url);
    					$a.append( $("<img>").attr("src", url) );
    					$("<td>").append($a).appendTo($tr);
    					
    					$("<td>").text(elem.school).appendTo($tr);
    					$("<td>").text(elem.videoid).appendTo($tr);
    					
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
        		$alert.html("查询服务器通信出错["+textStatus+"]: " + errorThrown).show();
        		setTimeout(function() {
        			$alert.fadeOut(1000);
        		}, 3000);
        	})
        }).trigger("click");  // 初始状态显示列表
    	
    	// 根据课程ID 显示课程详情
    	$myModal.on("show.bs.modal", function() {
    		$.ajax({
    			type: 'GET',
    			url: URL_GET_ONE,
    			data: {"id":g_id}
    		}).done(function(data) {
    			var $target = $("#detail-show").empty();
    			Object.keys(Course).forEach(function(key, idx) {
    				if (data.hasOwnProperty(key)) {
    					var $dt = $("<dt>").text(Course[key]);
    					var $dd = $("<dd>").html(data[key]);											
    					$target.append($dt).append($dd);
    				}
    			});
    		});
    	});

    	// 编辑内容
    	$editModal.on("show.bs.modal", function() {
    		$.ajax({
    			type: 'GET',
    			url: URL_GET_ONE,
    			data: {"id":g_id}
    		}).done(function(data) {
    			// 取出内容
    			var $t = null;
    			Object.keys(Course).forEach(function(key, idx) {
    				if (data.hasOwnProperty(key)) {
    					$t = $("#edit-" + key);
    					if ($t.get(0).type !== "file") {
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
    		var form = document.getElementById('form-edit');
    		var formdata = new FormData(form);
    		
    		$.ajax({
    			type: "POST",
    			url: "updateCourse",
    			data: formdata,
    	        cache: false,
    	        contentType: false,
    	        processData: false,
    	        dataType: "html"
    		}).done(function(resp) {
    			var data = null;
    			try {
    				data = JSON.parse(resp);
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
    			} catch (e) {
    				$("#editAlertDanger").html(data.cause).show();
    				console.error(e);
    			}

    		}).fail(function(jqXHR, textStatus, errorThrown) {
    			$("#editAlertDanger").html("修改内容通信错误["+textStatus+"]: " + errorThrown);
    		})
    	});
    	
    	// 新增内容
    	$("#js-new").on("click", function(e) {
    		e.preventDefault();
    		$newModal.modal("show");
    	});
    	$newModal.on("show.bs.modal", function() {
    		$("#new-school").val( $("#i-school").val() );
    	});
    	$("#js-add").on("click", function() {    		
    		var form = document.getElementById("form-new");
    		var formData = new FormData(form);
    		$.ajax({
    			type: 'POST',
    			url: "newCourse",
    			data: formData,
    	        cache: false,
    	        contentType: false,
    	        processData: false,
    	        dataType: "html"
    		}).done(function(resp) {
    			var data = null;
    			try {
    				data = JSON.parse(resp);
				} catch(e) {
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
    	});
    	$newModal.on("hidden.bs.modal", function() {
    		$("#newAlertDanger").hide();
    		$("#newAlertSuccess").hide();
    	});
    	
    });
    loader.set("assets/js/bootstrap.min.js");
	
    // 初始状态隐藏错误alert
    $(".alert").hide();
    
});
