/**
 * Created by mzh on 2017-08-18.
 * model-school.jsp  我的示范校
 */
$(function() {
    var SCHOOL_DEFAULT = "商丘工学院附属兴华学校";
    var g_school = SCHOOL_DEFAULT;
    var LIMIT = 4;

    // nologin => redirect to login.jsp
    (function() {
    	var DEBUG = true;
    	if (!DEBUG) {
            var user = $("#js-userid").val();
            if (user === "undefined" || user === undefined || user === "null") {
                location.href = "login.jsp?referer=model-school.jsp";
            }
    	}
    })();
    
    /*
    (function() {
        $.ajax({
            type: "GET",
            url: "news/listJournals",
            data: {"school": g_school, "limit": 4}
        }).done(function(data) {
        	var $target = $("#js-journal").empty();
        	var $a = null;
        	
        	Array.prototype.forEach.call(data, function(elem, i) {
        		var url = "journal.jsp?id=" + elem.id;        		
        		var imga = elem.img.split(","); 	
        		if (imga.length === 1) {
            		$a = $("<a>").addClass("card-item").attr("href", url).attr("target", "_blank")
					  .html("<img src=\""+ elem.img +"\">");
        		} else {
        			// 多张图片的话 只取第0张
            		$a = $("<a>").addClass("card-item").attr("href", url).attr("target", "_blank")
					  .html("<img src=\""+ imga[0] +"\">");
        		}
        		if ((i+1)%2===1) {
        			$a.addClass("odd");
        		}
        		var $span = $("<span>").addClass("cap").html(elem.title).appendTo($a);       		
        		$target.append($a);
        	});        	
        });
    }).call();
    */
    
    /*
    // 加载学生风采
    (function() {
    	$.ajax({
    		type: "GET",
    		url: "news/listStudent",
    		data: {school: g_school, limit: 4}
    	}).done(function(resp) {
    		var data = resp.teacherList;
    		
    		var $target = $("#js-students").empty();
    		var elem = null, $a = null;
    		for (var i = 0; i < data.length; i++) {
    			elem = data[i];
    			$a = $("<a>").addClass("card-item").attr("href", "teacher.jsp?id="+elem.id).attr("target", "_blank");
    			if ((i+1)%2===1) {
    				$a.addClass("odd");
    			}
    			var imga = elem.img.split(",");
    			var html = "<img src=\""+imga[0]+"\"><span class=\"cap\">"+elem.name+"</span>";
    			$a.html(html);
    			$target.append($a);
    		}
    	});
    }).call();
    */
    
    /*
    // 加载教师风采
    (function() {
    	$.ajax({
    		type: "GET",
    		url: "news/listTeacher",
    		data: {school: "开封市五中", limit: 8}
    	}).done(function(resp) {
    		var data = resp.teacherList;
    		
    		var $target = $("#js-teacher").empty();
    		var elem = null, $a = null;
    		for (var i = 0; i < data.length; i++) {
    			elem = data[i];
    			$a = $("<a>").addClass("card-item").attr("href", "teacher.jsp?id="+elem.id).attr("target", "_blank");
    			if ((i+1)%2===1) {
    				$a.addClass("odd");
    			}
    			var imga = elem.img.split(",");
    			var html = "<img src=\""+imga[0]+"\"><span class=\"cap\">"+elem.name+"</span>";
    			$a.html(html);
    			$target.append($a);
    		}
    	});
    }).call();  */

    /*
    // 活动采风
    (function() {
        $.ajax({
            type: "GET",
            url: "news/listTheme",
            data: {"school": g_school, "limit": 4}
        }).done(function(data) {
        	var $target = $("#js-theme").empty();
        	var $item = null, html = "";
        	Array.prototype.forEach.call(data, function(elem) {
        		var url = "theme.jsp?id=" + elem.id;
        		$item = $("<div>").addClass("article-item");
        		var imga = elem.img.split(",");
        		if (imga.length === 1) {
            		var $a = $("<a>").addClass("article-img").attr("href", url).attr("target", "_blank")
					  .html("<img src=\""+ elem.img +"\">");
        		} else {
        			// 多张图片的话 只取第0张
            		var $a = $("<a>").addClass("article-img").attr("href", url).attr("target", "_blank")
					  .html("<img src=\""+ imga[0] +"\">");
        		}
        		$item.append($a);
        		var $body = $("<div>").addClass("article-body").appendTo($item);
        		var content = strip_tags(elem.content);
				if (content.length > 40) {
					content = content.substr(0,40) + "...";
				}
        		html = " <a class=\"article-title\" href=\""+url+"\" target=\"_blank\">"+elem.title+"</a>" + 
        				" <div class=\"article-content\">"+content+"</div>" + 
        				" <div class=\"article-metas\">"+Math.ceil(Math.random()*1000)+"人围观</div>"
        		$body.html(html);
        		$target.append($item);
        	});        	
        });
    }).call();
    */

    // 名师优课 
    /*
    (function() {
        $.ajax({
            type: "GET",
            url: "news/listCourse",
            data: {"school": "濮阳市南乐县第二实验小学", "limit": LIMIT}
        }).done(function(resp) {
        	var data = resp.courseList;
        	var $target = $("#js-course").empty();
        	var $item = null;
        	var html = "", url="";
        	$.each(data, function(i, elem) {
        		$item = $("<div>").addClass("article-item");
        		url = "video.jsp?videoId=" + elem.videoid;
        		var content = strip_tags(elem.content);
				if (content.length > 128) {
					content = content.substr(0,128) + "...";
				}
            	html = "<a class=\"article-img\" href=\""+url+"\" target=\"_blank\"><img src=\""+elem.img+"\"></a>" + 
    					"<div class=\"article-body\">" + 
    						"<a class=\"article-title\" href=\""+url+"\">"+elem.title+"</a>" + 
    					   "<div class=\"article-content\">"+content+"</div>" + 
    					   "<div class=\"article-metas\">"+Math.ceil(Math.random()*1000)+"人围观</div>" + 
    					"</div>";
            	$item.html(html);
            	$target.append($item);
        	});       
        });
    }).call();
    */
	
    // 加载学校介绍 以及校长寄语 开封市集英小学, 开封市XX中
    /*
    (function() {
        var $target = $("#js-intro");
        g_school = $target.attr("data-school");

        $.ajax({
            type: "GET",
            url: "./news/getPrincipal",
            data: {"school":g_school}
        }).done(function(data) {
            // BEGIN 学校介绍
            $target.empty();
            var $header = $("<div>").addClass("module-header");
            $target.append($header);

            var $div = $("<div>").addClass("school-logo").appendTo($header);
            var $logo = $("<img>").addClass("logo").attr("src", data.logo);
            $div.append($logo);
            var $words = $("<div>").addClass("words").appendTo($div);
            // 濮阳市范县第一初级中学 => 范县第一初级中学
            var schoolname = data.title;
            if (schoolname.substr(0,3)==="濮阳市") {
                schoolname = schoolname.substr(3);
            }
            var $myfont = $("<div>").addClass("myfont").html(schoolname); // 学校名字
            if (schoolname.length>=8) {
                $myfont.css({"letter-spacing":"-.2em"});
            }
            $words.append( $myfont );
            $words.append( $("<div>").addClass("pinyin").html(data.pinyin) );

            var $body = $("<div>").addClass("module-body");
            $target.append($body);
            var $content = $("<div>").addClass("content").html(data.content);
            // 折叠简介dom
            var $wrap = $("<div>").attr("id", "intro-wrap").append($content).appendTo($body);
            var $readMore = $("<div>").attr("id", "read-more");
            $wrap.parent().append($readMore);

            // 折叠简介event
            var slideHeight = 95; // px
            var defHeight = $wrap.children(".content").height();

            if (defHeight >= slideHeight) {
                $wrap.css({"height":slideHeight+'px'});
                var $a = $("<a href=\"javascript:\">").text("点击打开全文").appendTo($readMore);
                $readMore.append($a);
                $a.on("click", function(e) {
                    e.preventDefault();
                    var curHeight = $wrap.height();
                    // 打开
                    if (curHeight <= slideHeight) {
                        $wrap.animate({height: defHeight}, "normal");
                        $a.text("点击隐藏");
                    }
                    // 隐藏
                    else {
                        $wrap.animate({height: slideHeight}, "normal");
                        $a.text("点击打开全文");
                    }
                });
            }
            // 校长头像
            var $avatar = $("#js-principal-avatar");

            var inherit = {"height":"inherit", "width":"inherit"};
            if (data.hasOwnProperty("avatar")) {
                // $avatar.css({"background":"url('"+data.avatar+"') no-repeat"});
                $avatar.append($("<img>").attr('src', data.avatar).css(inherit));
            } else {
                // $avatar.css({"background":"url('"+data.img+"') no-repeat"});
                $avatar.append($("<img>").attr('src', data.img).css(inherit));
            }

            $words = $("#js-principal-words");
            if (data.words) {
                $words.html(data.words);
            } else {
                $words.html("改学校暂无校长寄语");
            }

            // END 学校介绍
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.error("加载学校介绍/校长寄语失败: " + errorThrown);
        });
    }).call();
    */   
    
});
