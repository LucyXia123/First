/**
 * Created by Administrator on 2017-08-18.
 */
/**
 * 院校采风 动态加载 depends on ./bootstrap/js/jquery.isotope.js
 */
(function() {
	$("#js-yxcf").load("./include/yxcf.html", function() {
		var isotope = new Script(function() {			
			;(function(element) {

			    var $container = $('.respl-items').isotope({
					itemSelector: '.respl-item',
					filter: '.kfyxcf',
			        layoutMode: 'fitRows'
			    });
			    var filterFns = {
			        numberGreaterThan50: function () {
			            var number = $(this).find('.number').text();
			            return parseInt(number, 10) > 50;
			        },
			        ium: function () {
			            var name = $(this).find('.name').text();
			            return name.match(/ium$/);
			        }
			    };
			    var $filters = $("#filters");
			    $filters.on('click', 'a', function (e) {
			    	e.preventDefault();
			    	var $this = $(this);
					$('.opt').children().each(function(){
					    $(this).removeClass('select');
					});
					$this.addClass('select');			    	
			    	
			        var filterValue = $this.attr('data-filter');
			        filterValue = filterFns[filterValue] || filterValue;
			        $container.isotope({ filter: filterValue });			        		       
			    });
				})('#yxcf_responsive');
		
			$(".XTCFList li").hover(function(){
				
				$(this).find(".hoverline").fadeIn().end().find(".images").addClass("mousehover").stop().animate({
					"height":"185px"															  
				},500).find("img").stop().animate({
					"margin-top":"-30px"	
				},500);	
				$(this).find(".view").stop().animate({
					"bottom":"0"									 
				},500);
				
			},function(){
				$(this).find(".hoverline").fadeOut().end().find(".images").removeClass("mousehover").stop().animate({
					"height":"185px"															  
				},500).find("img").stop().animate({
					"margin-top":"0"	
				},500);	
				$(this).find(".view").stop().animate({
					"bottom":"-60px"									 
				},500);
				
			});
		});
		isotope.set("./bootstrap/js/isotope.pkgd.js");
	});	
})();

/**
 * 师生风情 教师字幕显示
 */
(function() {
	try {
		// load hiSlider css
		CSS("./bootstrap/css/jquery.hiSlider.min.css");
		
		var script = new Script(function() {
			var $tabs = $("#js-area-st");
			var $al = $tabs.children("li").children("a");
			// 0,1,2,3 开封市 濮阳市 商丘市 许昌市
			var areaList = ["开封市", "濮阳市", "商丘市", "许昌市"];
			
			for (var i = 0; i < $al.length; i++) {
				var $a = $al.eq(i);
				(function($a, i) {
					$a.on("click", function(e) {
						e.preventDefault();
						$("#js-shisheng").load("./include/shisheng/" + i + ".html", function() {
							$.ajax({	
								type: "GET",
								url: "./news/listTeacherByArea",
								data: {area: areaList[i], limit: 6, type: "学生"}
							}).done(function(resp) {
								var data = resp.teacherList;
								
								// 商丘市不从数据库取 用"商丘市第一高级中学"这个学校的学生
								if (areaList[i] === "商丘市") {
									data = [
										{
									        "name": "王炳瑞 ",									       									        
									        "img": "publish/shangqiuyigao/students/1.jpg",
									        "url": "shangqiu1gaoteacher.jsp?id=11"
									    },
									    {
									        "name": "王晓彤",									        									        
									        "img": "publish/shangqiuyigao/students/2.jpg",
									        "url": "shangqiu1gaoteacher.jsp?id=12"
									    },
									    {									        
									        "name": "黑若琳",									        									       
									        "img": "publish/shangqiuyigao/students/3.jpg",
									        "url": "shangqiu1gaoteacher.jsp?id=13"
									    },
									    {									        
									        "name": "马梦阳",									        
									        "img": "publish/shangqiuyigao/students/4.jpg",
									        "url": "shangqiu1gaoteacher.jsp?id=14"
									    }
									];
								}
								
								if (data.length > 0) {
									var $target = $("#js-hiSlider").empty();								
									data.forEach(function(item) {
										var $li = $("<li>").attr("data-title", item.name).addClass("hiSlider-item");
										$target.append($li);
										var $a = $("<a>").attr("target", "_blank");
										if (item.id) {
											$a.attr("href", "teacher.jsp?id=" + item.id);
										} else {
											// 商丘市第一高级中学
											item.url && $a.attr("href", item.url);
										}										
										$li.append($a);
										var $img = $("<img src=\""+item.img+"\" alt=\""+item.name+"\">");
										$a.append($img);
									});
								}
								
						        $('#js-hiSlider').hiSlider({
						            isFlexible: true,
						            mode: 'fade',
						            isSupportTouch: true,
						            isShowTitle: true,
						            isShowPage: true,
						            titleAttr: function(curIdx) {
						              return $('img', this).attr('alt');
						            }
						        });
							});
												        
					        /*
							$.ajax({
								type: "GET",
								url: "./news/listTeacherByArea",
								data: {area: areaList[i], limit: 6}
							}).done(function(resp) {
								var data = resp.teacherList;
								var $target = $("#student").empty();						
								var $ul = $("<ul>").addClass("student-list").appendTo($target);
								data.forEach(function(item) {
									var $card = $("<li>").addClass("card").appendTo($ul);
									var url = "teacher.jsp?id=" + item.id;
									var html = "<a href=\""+url+"\" target=\"_blank\"><img src=\""+item.img+"\" /></a>" + 
												"<div class=\"card-metas\">" + 
												"<p>" + item.intro + "</p><span>"+item.school+"</span>" + 
												"</div>";
									$card.html(html);
								});
							});
							*/
						});
						$tabs.children("li").removeClass("active");
						$a.parent().addClass("active");
					});
				})($a, i);
			}
			$al.first().trigger("click");
		});
		script.set("./bootstrap/js/jquery.hiSlider.js");
	} catch (e) {
		console.error(e);
	}
}).call();

/**
 *  活动采风
 */
(function() {
	$.ajax({
		type: "GET",
		url: "./news/allTheme",
		data: {limit:6}		
	}).done(function(data) {
		if (!(data instanceof Array)) {
			console.error("Unexpected theme data: " + data);
			return false;
		}
		var $act = $("#js-act-list");
		try {
			if (data.length > 0) {
				$act.empty();
			}
			Array.prototype.forEach.call(data, function(elem) {
				var url = null;
				if (elem.url===null || elem.url===""||elem.url==="javascript:;") {
					url = "theme.jsp?id=" + elem.id;
				} else {
					url = elem.url;
				}
				var $item = $("<li>").addClass("act-item");
				// 标题
				var title = elem.title;
				if (title.length > 32) {
					title = title.substr(0,32) + "...";
				}
				// 图片				
				var img = elem.img;
				var imga = img.split(",");
				if (1<imga.length) {
					img = imga[0];
				}
				// 正文内容 strip_tags
				var content = elem.content;
				if (content.length > 40) {
					content = content.substr(0,40) + "...";
				}
				content = strip_tags(content);
				// content = content.replace(/<\/?[^>]+(>|$)/g, "");
				$item.html("<a class='pull-left' href='"+url+"' target=\'_blank\'><img src='"+img+"'></a>" + 
						   "<div class='pull-right'>" + 
						   "  <a class='act-title' href='"+url+"' target='_blank'><h4>"+title+"</h4></a>" + 
						   "  <div class=\"act-content\">"+content+"</div>" + 
						   "  <div class=\"act-metas\">"+elem.hot+"人观看</div>" + 
						   "</div>");
				$act.append($item);
			});
		} catch (e) {
			console.error(e);
		}

		return true;
	});
})();

/**
 * 最新 最热 推荐 排序切换
 */
(function() {
	var $wraps = $(".order-by");
	Array.prototype.forEach.call($wraps, function(wrap, i) {
		var $items = $(wrap).children("a");
		$items.on("click", function() {
			for (var i = 0; i < $items.length; i++) {
				$items.get(i) !== this ? $items.eq(i).removeClass("active")
						: $(this).addClass("active");
			}
		});
	});
})();

/**
 * 未登录状态 点击a都跳转到登录页面  #js-userid @ref: ~/webapp/include/nav-right.jsp
 */
window.onload = function() {
	var DEBUG = true;  // 先不要求首页登录
	if (!DEBUG) {
		var user = $("#js-userid").val();
		if (user === "undefined" || user === undefined || user === "null") {
			$("a").off("click").on("click", function(e) {
				e.preventDefault(); 
				location.href = "login.jsp?referer=model-school.jsp";
			});
		}
	}
};

