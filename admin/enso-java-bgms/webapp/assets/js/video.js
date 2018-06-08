$(function() {
	const URL_GET_ONE = "getVideoById",
		URL_SET_ONE = "updateVideo",
		URL_NEW_VIDEO = "newVideo";

	var $tbody = $("#js-list");
	var $alert = $("#js-alert");
	var g_id = null;
	var $myModal = $("#myModal");
	var $editModal = $("#editModal");
	
	function select_type(domid, callback) {
		var types = ['学校介绍', '宋城教育', '龙都教育', '课程', '新闻', '其他'];
		var select = document.getElementById(domid);
		select.innerHTML = "";
		types.map(function(type) {
			var option = document.createElement("option");
			option.value = type;
			option.innerText = type;
			select.appendChild(option);
			return option;
		});
		callback && callback(select);
	}
	select_type("i-type", function(select) {
		var $form = $("#form-query");
		select.onchange = function() {
			$form.submit();
		};
	});
	select_type("edit-type");
	select_type("new-type");
	
	var Video = {
		"id": "ID",
		"type": "视频类型",
		"name": "视频标题",
		"location": "视频链接",
		"poster": "海报链接",
		"content": "简介内容"
	};
	
	// 查询视频
	$("#form-query").on("submit", function(e) {		
		e.preventDefault();
		var form = this;
		var $btn = $(this).find("button");
		$btn.text("查询中...");
		
		$.ajax({
			type: "GET",
			url: "getvideos",
			data: {"type":$("#i-type").val()}
		}).done(function(data) {
			$btn.text("查询");
			$tbody.empty();
			if (data instanceof Array) {
				if (data.length===0) {
					var $td =  $("<td>").attr("colspan",7).text("查询结果为空").css({"text-align":"center"});
					$("<tr>").append( $td ).appendTo($tbody);
					return false;
				}
				data.map(function(elem) {
					var $tr = $("<tr>").attr("data-id", elem.id);
					
					$("<td>").text(elem.id).appendTo($tr);
					$("<td>").text(elem.type).appendTo($tr);
					
					var $a = $("<a>").addClass("info").attr("href", "javascript:;").text(elem.name);
					$a.on("click", function(e) {
						e.preventDefault();
						g_id = $(this).parent().parent().attr("data-id");
						$myModal.modal("show");
					});
					$("<td>").append($a).appendTo($tr);
					
					var url = elem.location;
					$a = $("<a>").addClass("url").attr("target", "_blank").attr("href", url).html(url);
					$("<td>").append($a).appendTo($tr);					
					var poster = elem.poster;
					$a = $("<a>").addClass("url").attr("target", "_blank").attr("href", poster).html(poster);
					$("<td>").append($a).appendTo($tr);
					
					var content = elem.content;
					$("<td>").append( $("<span>").html(content).attr("title", content) ).appendTo($tr);
					
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
			$alert.text("查询视频通信错误["+textStatus+"]: " + errorThrown).show();
		});
	}).submit(); // 加载页面时候自动查询
	
	// 显示详细信息
	$myModal.on("show.bs.modal", function() {
		$.ajax({
			type: 'GET',
			url: URL_GET_ONE,
			data: {"id":g_id}
		}).done(function(data) {
			var $target = $("#detail-show").empty();
			Object.keys(Video).forEach(function(key, idx) {
				if (data.hasOwnProperty(key)) {
					var $dt = $("<dt>").text(Video[key]);
					var $dd = null;
					if (idx > 2) {
						var $div = $("<div>").css({
							"width": "560px",
							"word-wrap": "break-word"
						}).html(data[key]);
						$dd = $("<dd>").append($div);
					} else {
						$dd = $("<dd>").html(data[key]);
					}												
					$target.append($dt).append($dd);
				}			
			});
		});
	});
	
	// 视频编辑 load data
	$editModal.on("show.bs.modal", function() {
		$.ajax({
			type: 'GET',
			url: URL_GET_ONE,
			data: {"id":g_id}
		}).done(function(data) {
			Object.keys(Video).forEach(function(key, idx) {
				if (data.hasOwnProperty(key)) {
					$("#edit-" + key).val(data[key]);
				}
			}); // END Object.keys(Video)
			$("#edit-id").val(g_id);
		});  // END $.ajax({}).done()...
	});  // END $editModal.on("show.bs.modal", ...
	// 关闭modal场合 隐藏反馈提示信息
	$editModal.on("hidden.bs.modal", function() {
		$("#editAlertDanger").hide();
		$("#editAlertSuccess").hide();
	});
	// 视频编辑 set data
	$("#submit-edit").on("click", function() {
		var $form = $("#form-edit");
		$.ajax({
			type: 'POST',
			url: URL_SET_ONE,
			data: $form.serialize()
		}).done(function(data) {
			// {result: 0, cause: "success"}
			if (0===data.result) {
				$("#editAlertSuccess").html("保存成功!").show();
				// update table
				$("#form-query").submit();
				// hide modal
				setTimeout(function() {
					$editModal.modal("hide");
				}, 1500);
			} else {
				$("#editAlertDanger").html(data.cause).show();
			}			
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$("#editAlertDanger").html("修改内容通信错误["+textStatus+"]: " + errorThrown);
		});
	});

	// 新增视频
	var $newModal = $("#newModal");
	$newModal.on("show.bs.modal", function() {
		$("#new-type").val( $("#i-type").val() );
	});
	$("#js-new").on("click", function(e) {
		e.preventDefault();
		$newModal.modal("show");
	});
	$("#js-add").on("click", function() {
		var $form = $("#form-new");
		$.ajax({
			type: 'POST',
			url: URL_NEW_VIDEO,
			data: $form.serialize()
		}).done(function(data) {
			if (0===data.result) {
				$("#newAlertSuccess").html("新增成功: " + data.cause).show();
				// update table
				$("#form-query").submit();
				// hide modal
				setTimeout(function() {
					$newModal.modal("hide");
				}, 1500);
			} else {
				$("#newAlertDanger").html(data.cause).show();
			}
		});
	});
	$newModal.on("hidden.bs.modal", function() {
		$("#newAlertDanger").hide();
		$("#newAlertSuccess").hide();
	});
});