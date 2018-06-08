$(function() {
	var qs = enso_query_string();
	if (!qs.hasOwnProperty("id")) {
		document.write("<h5>参数错误: ?id=x</h5>");
		return false;
	}
	var newsId = qs.id;
	
	// 根据文章id加载文章的所有属性和正文
	(function() {
		var $content = $("#js-content");
		$.ajax({
			type: "GET",
			url: window.APP_ROOT + "/news/get",
			data: {"id": newsId}
		}).done(function(data) {
			if (null === data || {} === data) {
				document.write("<h5>没有这篇文章: ?id="+newsId+"</h5>");
				return false;
			}
			Object.keys(data).forEach(function(key, idx) {
				if (key !== "id") {
					var $target = $("#js-" + key);
					$target.length>0 && $target.html(data[key]);
				}
				// 文章标题
				if (key === "title") {
					$(".breadcrumb").find("li.active").html("<a href='javascript:;'>"+data[key]+"</a>")
				}
			});
		}).fail(function(jqXHR, textStatus, errorThrown) {
			$content.html("<p>文章加载失败: "+ textStatus +"</p>");
		});
		$content.html("<p>文章加载中...</p>");
	})();

	
	// 加载相关文章id和标题
	(function() {
		var $target = $("#js-related");
		$.ajax({
			type: "GET",
			url: window.APP_ROOT + "/news/related",
			data: {"thisId":newsId, "limit":6}
		}).done(function(data) {
			var $ul = $("<ul>").addClass("related-titles").addClass("mar-t10");			
			Array.prototype.forEach.call(data, function(elem) {
				var $a = $("<a>").attr("href", "news.jsp?id="+elem.id).text(elem.title);
				var $li = $("<li>").append($a);				
				$ul.append($li);
			});
			$target.empty().append($ul);
		});
		$("#js-related").html("<p>相关文章列表加载中...</p>");
	})();
});