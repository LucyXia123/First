var g_participant = {};

$(function() {
    // 当前登录用户的ID
    var commentatorUsernumber = null;
    var participantId = null;

    if (DEBUG) {
    	loginUserNumber = "STU201709301537110001";
    }

    var qs = enso_query_string();
    if (qs.hasOwnProperty("id")) {
        participantId = qs.id;
    } else {
        document.write("<h2>participantId is not defined.</h2>");
        return false;
    }   

    // 取得已经上传的作品详情
    $.ajax({
        type: "GET",
        url: "./queryStudentActivityWorksById",
        data: {"id": participantId},
        dataType:"json"
    }).done(function(data) {
        console.log(data);
        
        if (!data.hasOwnProperty("submissionTitle")) {
        	document.write("<h2>该报名者未上传作品, 2s后返回...</h2>");
        	setTimeout(function() {
        		// history.go(-1);  // "act4guest.jsp";        		
        		window.location.href = "act-upload.jsp";
        		return true;
        	}, 2000);
        	return false;
        }
        
        // 报名ID
        var participantId = data.id;

        var ss = printf("{0}&nbsp;{1}年级{2}班", data.school, data.grade, data.className);
        
        var $user = $("#J_user").empty();
        
        $user.append($("<img class=\"avatar\" src=\""+data.avatarUrl+"\" />"));
        var $metas = $("<div class='metas'>");
        $user.append($metas);
        
        $metas.html(            
            "<h4>" +data.realName+ "</h4>\r\n" +
            "<div class=\"school\">" + ss + "</div>\r\n" +
            "<div class=\"slogan\">作文题目: " +data.submissionTitle + " </div>\r\n" +
            "<div class=\"director\">指导老师: "+data.instructor+"</div>\r\n");
        
        var $sns = $("<div class='sns'>").appendTo($metas);
        
        var $thumbUp = $("<a id=\"J_thumbup\" href=\"javascript:;\" title=\"点赞\">");
        $thumbUp.html("<span class=\"glyphicon glyphicon-thumbs-up\"></span>");
        $sns.append($thumbUp);
        // 点赞数 <span class=\"num\">"+data.likes+"</span>
        $("<span>").addClass("num").text(data.likes).appendTo($sns);
        
        // 被点赞人
        var participantUsernumber = data.userNumber;
        
        // 取得评论用户(当前登录用户)编号
        var getCommentatorUsernumber = function() {
            commentatorUsernumber = $("#js-userid").val();
            if (null === commentatorUsernumber || "null" === commentatorUsernumber) {
                window.location.href = "login.jsp?referer=" + escape(window.location.href);
                return "";
            }
            return commentatorUsernumber;
        };
        
        $thumbUp.on("click", function(e) {
        	e.preventDefault();
        	// 点赞
        	var commentatorUsernumber = getCommentatorUsernumber();
        	// 评论用户　未登录
        	if (commentatorUsernumber.length===0) {return false;}        
        	
        	var $this = $(this);
        	$.ajax({
        		type: 'GET',
        		url: './activity/iLikes',
        		data: {
        			commentatorUsernumber: commentatorUsernumber,
        			participantUsernumber: participantUsernumber,
        			participantId: participantId,
        			activityId: DEFAULT_ACTIVITIY_ID
        		}
        	}).done(function(data) {
        		if (data.status === 200) {
        			var $num = $this.next();  // 点赞数 <span class="num">0</span>
        			$num.text(data.count);
        		} else {
        			alert(JSON.stringify(data));
        		}
        		
        		if (data.chance < 1) {
        			var $alert = $("#J_alert_danger");
        			$alert.html("本日5次点赞数已经用完").show();
        			setTimeout(function() {
        				$alert.hide();
        			}, 3000);
        		}
        		        		
        	}).fail(function(jqXHR, textStatus, errorThrown) {
        		alert("点赞["+textStatus+"]: " + errorThrown);
        	});
        });        
        
        var $vote = $("<a id=\"J_vote\" href=\"javascript:;\" title=\"投票\">");
        $sns.append($vote);
        $vote.html("<span class=\"glyphicon glyphicon-tree-conifer\"></span>");
        // 投票数
        $("<span>").addClass("num").text(data.votes).appendTo($sns);
        
        $vote.on("click", function(e) {
        	e.preventDefault();
        	var $this = $(this);
        	// 投票
        	var commentatorUsernumber = getCommentatorUsernumber();
        	if (commentatorUsernumber.length===0) {return false;}
        	$.ajax({
        		type: 'GET',
        		url: './activity/iVotes',
        		data: {
        			commentatorUsernumber: commentatorUsernumber,
        			participantUsernumber: participantUsernumber,
        			participantId: participantId,
        			activityId: DEFAULT_ACTIVITIY_ID
        		}
        	}).done(function(data) {
        		if (data.status === 200) {
        			var $num = $this.next();  // 投票数 <span class="num">0</span>
        			$num.text(data.count);
        		} else {
        			alert(JSON.stringify(data));
        		}
        		if (data.chance < 1) {
        			var $alert = $("#J_alert_danger");
        			$alert.html("每日只能投票1次!").show();
        			setTimeout(function() {
        				$alert.hide();
        			}, 3000);
        		}
        	}).fail(function(jqXHR, textStatus, errorThrown) {
        		alert("投票["+textStatus+"]: " + errorThrown);
        	});
        });       

        var $wrap = $("#J_article").empty();

        var s = data.submissionWorks;
        var a = $.parseJSON(s);

        if (a instanceof Array) {
            var $div, $a;
            // 上传了单个文件
            if (a.length < 2) {
                var url = a[0].image_url;
                var suffix = url.substring(url.lastIndexOf("."));
                if (DEBUG) {
                    url = url.replace(/www.tfjyzx.com/, "localhost");
                } 
                // else {/* 上线用www.tfjyzx.com */url = url.replace(/localhost/, "www.tfjyzx.com");}
                // 自己打
                if (suffix === ".html") {
                    var $a = $("<a>").attr("href", url).attr("target", "_blank")
                        .html("<span class=\"glyphicon glyphicon-eye-open\"></span>点击查看文章正文");
                    $a.css({
                        "margin": "0 auto",
                        "display": "block",
                        "line-height": "25px",
                        "padding": "10px 0",
                        "text-align": "center"
                    });
                    $wrap.append($a);
                    var $iframe = $("<iframe>").attr("src", url);
                    $wrap.append($iframe);

                    $iframe.on("load", function() {
                        // iframe xdomain issue
                    });
                    $(".act-wrapper").css({"width":"100%"});
                }
                // 上传文档
                else if (suffix === ".doc" || suffix === ".docx") {
                    $div = $("<div>").css({"height":"30px", "line-height":"30px", "padding-top": "20px"});
                    $("<span>").text("点击链接下载文档: ").appendTo($div);
                    // 写出doc文档的链接供下载
                    $a = $("<a href='"+url+"' target='_blank'></a>").appendTo($div)
                        .css({"color": "#317EF3"});
                    $a.html(url.substring(url.lastIndexOf('/')+1));
                    $wrap.append($div);
                } else if (suffix === ".png" || suffix === ".jpg"|| suffix === ".jpeg" || suffix === ".gif") {
                    // image
                    $div = $("<div>");
                    // 预览图片
                    $a = $("<a href='"+url+"' target='_blank'></a>").appendTo($div);
                    $a.append($("<img src='"+url+"'>"));
                    $(".act-wrapper").css({"width":"100%"});
                    $wrap.append($div);
                } else {
                    $div = $("<div>").css({"height":"30px", "line-height":"30px", "padding-top": "20px"});
                    $("<span>").text("点击链接下载文件: ").appendTo($div);
                    $a = $("<a href='"+url+"' target='_blank'></a>").appendTo($div)
                        .css({"color": "#317EF3"});
                    $a.html(url.substring(url.lastIndexOf('/')+1));
                    $wrap.append($div);
                }
            }
            // 上传了多个文件 (图片)
            else {
                var $ul = $("<ul>");
                Array.prototype.forEach.call(a, function(item) {
                    var $li = $("<li>");

                    var url = item.image_url;
                    var pos = url.lastIndexOf(".");
                    var suffix = url.substring(pos);

                    if (suffix === ".doc" || suffix === ".docx") {
                        $li.html("<a href=\""+url+"\">"+url.substring(0, pos)+"</a>");
                    } else {
                        $li.html("<a href=\""+url+"\" target=\'_blank\'><img src='"+url+"'></a>");
                    }
                    $ul.append($li);
                });
                $wrap.append($ul);
                $(".act-wrapper").css({"width":"100%"});
            }

        } else {
            alert("Unexpected data: queryStudentActivityWorksById response is not Array.");
        }
        g_participant = data;

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert(textStatus + ":" + errorThrown);
    });

    // load qrcode
    var s = new Script(function() {
        var a = new Script(function() {
            var $qrcode = $("#qrcode");
            var url = BASEURL + "act-view.jsp?id="  + qs.id;
            console.log(url);
            
            $qrcode.qrcode({
                render: "canvas",
                text: url,
                height: 200,
                width: 200                
            });
        });
        a.set("./bootstrap/js/jquery.qrcode.js");
    });
    s.set("./bootstrap/js/qrcode.js");
    
    // 返回作品列表
    (function() {
    	var type = $("#J_type").val();
    	var $back = $(".back");
    	if (type==="学生") {
    		$back.attr("href", "act-upload.jsp");
    	} else {
    		$back.attr("href", "act4guest.jsp");
    	}
    })();

    /**
     * 1 => 一, 2=>二  (年级)
     */
    function grade2kanji(g) {
        var a = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
        return (g > 9) ? g : a[g];
    }
    
    // 正文活动高亮
    $("#navbar").children(".nav").find("li").eq(4).addClass("active");
})