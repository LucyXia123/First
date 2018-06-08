/**
 * Created by Administrator on 2017-10-29.
 */
$(function() {
	var form = document.getElementById("form-query");	
	const URL_QUERY = "./listUsers";
	var $myModal = $("#myModal");
	var usernumber;
	
	// extra: grade":"3","classname":"1", version: 1
	var student = {
		"usernumber": "用户编号",
		"username": "用户名",
		"mobile": "手机号",
		"email": "邮箱",
		"school": "学校",
		"area": "地区",
		"realname": "真实姓名",
		"idnum": "身份证号",
		"createTime": "创建日期"
	};
	
	$myModal.on("show.bs.modal", function() {
		$.ajax({
			type: 'GET',
			url: "./getUserByNum",
			data: {"usernumber":usernumber}
		}).done(function(data) {
			var $target = $("#user-detail").empty();
			Object.keys(student).forEach(function(key, idx) {				
				// student[key] data[key]
				if (data.hasOwnProperty(key)) {
					var $dt = $("<dt>").text(student[key]);
					var $dd = $("<dd>").text(data[key]);
					$target.append($dt).append($dd);
				}			
			});
			var g = ['0','一','二','三','四','五','六','七','八','九'];
			var $dt = $("<dt>").text("班级");
			var $dd = $("<dd>").text(g[data.grade]+"年级"+ data.classname +"班");
			$target.append($dt).append($dd);
			// 教材版本
			var versions = ['未知版本','人教版','川教版','北师大版','苏教版','广教版'];
			$dt = $("<dt>").text("教材版本");
			$dd = $("<dd>").text(versions[data.version]);
			$target.append($dt).append($dd);
		});
	});
	
	$(form).on("submit", function(e) {
		e.preventDefault();
		var form = this;
		
		var param = {};
		['username', 'mobile'].forEach(function(field) {
			var value = form[field].value;
			if ($.trim(value) !== "") {
				param[field] = value;
			}
		});
		
		$.ajax({
			type: "GET",
			url: URL_QUERY,
			data: param
		}).done(function(data) {
			var $list = $("#js-list").empty();
			var c = $list.prev().first().children(0).children().length;   // 列数
			var g = ['0','一','二','三','四','五','六','七','八','九'];
			
			if (data instanceof Array) {
				if (data.length > 0) {
					Array.prototype.forEach.call(data, function(elem) {
						var $tr = $("<tr>").attr("data-usernumber", elem.usernumber);						
						$list.append($tr);
						var $a = $("<a>").attr("href", "javascript:;").text(elem.username);
						$a.on("click", function(e) {
							e.preventDefault();
							usernumber = elem.usernumber;
							$myModal.modal("show");
						});
						$("<td>").append($a).appendTo($tr);			
						$("<td>").text(elem.mobile).appendTo($tr);
						$("<td>").text(elem.email).appendTo($tr);
						$("<td>").text(elem.school).appendTo($tr);
						$("<td>").text( g[elem.grade] +'年级'+ elem.classname+'班' ).appendTo($tr);
						$("<td>").text(elem.realname).appendTo($tr);
					});	
				} else {
					var $tr = $("<tr>").addClass("dumb");
					$list.append($tr);
					var $td = $("<td>").attr("colspan", c).text("没有找到满足条件的结果").css({"text-align":"center"});
					$tr.append($td);
				}
			} else {
				alert("返回结果错误!");
			}			
		})
	})
});