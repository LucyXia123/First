/**
 * 我的积分
 */
$(function() {
	var userid = $("#js-userid").val();
	var $mainWrap = $(".main-wrapper")
	var wrapHeight = $mainWrap.height();
	
	// 取得积分
	(function(usernumber) {
		$.ajax({
			type: "GET",
			url: "./bonus/bonusAmount",
			data: {"usernumber": usernumber}
		}).done(function(data) {
			document.getElementById("J_AvailablePoint").innerHTML = data.balance;
			
			var accountId = data.id;			
			// 取得积分流水
			bonusSettlement(accountId, 1, 5);  // pageSize=5
			
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("["+textStatus+"]: "+errorThrown);
		});
		
	})(userid);
	
	function bonusSettlement(accountId, pageNum, pageSize) {
		$.ajax({
			type: "GET",
			url: "./bonus/bonusSettlement",
			data: {"accountId":accountId, "pageNum":pageNum, "pageSize":pageSize}
		}).done(function(resp) {
			var $ul = $("#J_item-list");
			if (resp instanceof Array) {
				$ul.empty();
				if (resp.length===0) {						
					$("<li>").addClass("no-have-detail").html("您当前没有积分明细").appendTo($ul);
				} else {						
					resp.forEach(function(item) {
						var $li = $("<li>").addClass("has-detail").addClass("clearfix");
						$ul.append($li);
						// 来源/用途
						var $why = $("<div>").addClass("why").appendTo($li);
						$why.append( $("<img>").attr("src", "publish/user/point/act.jpg") );
						var $metas = $("<div>").addClass("metas");
						$metas.html("<span class=\"source\">"+item.title+"</span><span class=\"number\">编号: "+item.id+"</span>");
						$why.append($metas);
						
						// 积分变化
						var $what = $("<div>").addClass("what").appendTo($li);
						var $span = $("<span>");
						var flag = item.accountType;
						if (flag=== "SPENDING") {
							$span.addClass("minus").text("-"+item.accountAmount);
						} else if (flag === "REVENUE") {
							$span.addClass("plus").text("+"+item.accountAmount);
						} else {
							$span.text(item.accountAmount);
						}
						$what.append($span);
						
						// 日期 (当前积分)
						var $when = $("<div>").addClass("when").appendTo($li);
						$when.append( $("<span>").addClass("date").text(item.balance) );
						
						// 备注 (时间)
						var $notes = $("<div>").addClass("notes").appendTo($li);
						// $("<span>").text("["+flag+"] 剩余积分: " + item.balance)
						$notes.append( $("<span>").text(item.createTime) );
					});
				}  // END else resp.length
			}  // END if (resp instanceof Array)
			
			// pagination
			var prevPage = pageNum-1;
			var nextPage = pageNum+1;
			var $nav = $("#J_page").empty();
			var $pager = $("<ul>").addClass("pager");
			$nav.append($pager);
			var $prev = $("<a>").attr("href", "javascript:;").text("上一页");
			$pager.append( $("<li>").append($prev) );
			$prev.on("click", function(e) {
				if (prevPage <= 0) {
					$(this).parent().addClass("disabled");
					return false;
				} else {
					$(this).parent().removeClass("disabled");
				}
				bonusSettlement(accountId, prevPage, pageSize);
			});
						
			var $next = $("<a>").attr("href", "javascript:;").text("下一页");
			$pager.append( $("<li>").append($next) );
			$next.on("click", function(e) {
				if (resp.length===0) {
					$(this).parent().addClass("disabled");
					return false;
				} else {
					$(this).parent().removeClass("disabled");
				}
				bonusSettlement(accountId, nextPage, pageSize);
			});			
					
			// reset .main-wrapper height
			var len = (resp.length===0) ? 1 : resp.length;
			var height = wrapHeight + len * 120;		
			$mainWrap.css({"height": height + "px"});	
			
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("["+textStatus+"]: "+errorThrown);
		});
	}
});