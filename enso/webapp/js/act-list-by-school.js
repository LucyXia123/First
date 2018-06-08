window.g_activity = {};

$(function() {
    var qs = enso_query_string();

    // 取得评论用户(当前登录用户)编号
    var getCommentatorUsernumber = function() {
        var n = $("#js-userid").val();
        if (null === n || "null" === n) {
            window.location.href = "login.jsp?referer=" + escape(window.location.href);
            return "";
        }
        return n;
    };

    // 取得活动信息
    $.ajax({
        type: "GET",
        url: "./querySchoolActivity",
        data: {
            "id": DEFAULT_ACTIVITIY_ID
        }
    }).done(function(data) {
        var $act = $("#J_act");
        try {
            // 能正常取得活动信息数据
            if (data.hasOwnProperty("name")) {
                $act.empty();
                $("<h1>").addClass("act-title").text(data.name).appendTo($act);
                var $article = $("<div>").addClass("article");
                $act.append($article);
                $article.append($("<div>").addClass("act-label").html(
                    printf("活动介绍、活动安排、活动详情、参与人、颁奖等介绍 ({0}至{1})", data.startDate, data.finishDate)));
                $article.append($("<div>").addClass("act-info").text(data.description));
            }
        } catch (e) {
            console.error(e);
        }
        Object.keys(data).forEach(function(key) {
            window.g_activity[key] = data[key];
        });
        console.log(g_activity);

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("[" + textStatus + "]: " + errorThrown);
    });

    var school = (function() {
    	if (qs.hasOwnProperty("school")) {
    		return qs.school;
    	} else {
    		document.write("<h2>学校名称不能为空!</h2>");
    		return false;
    	}
    })();
    
    var p = qs.hasOwnProperty("pageNumber") ? qs.pageNumber : 1;
    if (p < 1) {p = 1;}
    var pageSize = qs.hasOwnProperty("pageSize") ? qs.pageSize : 8;
    
    list_participant(p);     

    function list_participant(pageNumber) {

        // 取得作品列表
        $.ajax({
            type: "GET",
            url: "./activity/queryActivityParticipantsBySchool",
            data: {
                "school": school,
                "pageNumber": pageNumber,
                "pageSize": pageSize
            }
        }).done(function(data) {
            var na = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];

            if (data instanceof Array) {
                var $works = $("#works").empty();
                if (data.length === 0) {
                    $works.html("<p>结果条目为空</p>");
                    return false;
                }
                Array.prototype.forEach.call(data, function(item) {
                    var $card = $("<div>").addClass("card").attr("data-id", item.id);
                    $works.append($card);

                    $("<div>").addClass("imgwrap").html("<img src=\""+item.avatarUrl+"\" alt='头像' />").appendTo($card);
                    var $figure = $("<div>").addClass("figure").appendTo($card);

                    var g = item.grade;
                    var grade = (g > 9) ? g : na[g];
                    var nm = printf("{0}{1}年级{2}班{3}", item.school, grade, item.classname, item.realname);
                    $figure.append($("<p>"+nm+"</p>"));

                    // item.vote
                    var $tutor = $("<div>").addClass("tutor");
                    $tutor.html("指导老师: " + item.instructor);        
                    $figure.append($tutor);                    

                    var $sns = $("<div>").addClass("sns");
                    
                    var $a = $("<a href=\"javascript:;\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>");
                    $a.on("click", function(e) {
                        e.preventDefault();
                        // 点赞用户
                        var commentatorUsernumber = getCommentatorUsernumber();
                        // 评论用户　未登录
                        if (commentatorUsernumber.length===0) {return false;}

                        var $this = $(this);

                        $.ajax({
                            type: 'GET',
                            url: './activity/iLikes',
                            data: {
                                commentatorUsernumber: commentatorUsernumber,
                                participantUsernumber: item.userNumber,
                                participantId: item.id,
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

                    }).appendTo($sns);
                    $("<span>").addClass("num").text(item.likes).appendTo($sns);
                                        
                    var $vote = $("<a href=\"javascript:;\" title='投票'><span class=\"glyphicon glyphicon-tree-conifer\"></span></a>");
                    $sns.append($vote);
                    $sns.append($("<span class=\"num\">"+ item.votes +"</span>"));   
                    
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
                                participantUsernumber: item.userNumber,
                                participantId: item.id,
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
                    
                    // var $likes = $("<span>").addClass("likes").text(item.likes).appendTo($sns);
                    var participantId = item.id;
                    var url = "act-view.jsp?id=" + participantId;
                    var $check = $("<a class=\"check\" href='"+url+"'>查看作品</a>").appendTo($sns);
                    
                    if (item.submissionId) {
                        $sns.append($check);
                    } else {
                     	$sns.append($("<span>").html("暂无作品"));
                    }

                    /*
                        // 取得已经上传的作品详情
                        setTimeout(function() {
                            $.ajax({
                                type: "GET",
                                url: "./queryStudentActivityWorksById",
                                data: {"id": participantId},
                                dataType:"json"
                            }).done(function(data) {                           
                                if (data.hasOwnProperty("id")) {
                                    $sns.append($check);
                                } else {
                                 	$sns.append($("<span>").html("暂无作品"));
    							}
                            });
                        }, 500);
                    */

                    $figure.append($sns);
                });
            }

            var $pagination = $("#J_pagination").empty();
            var $pager = $("<ul>").addClass("pager");
            $pagination.append($pager);
            var $prev = $("<a href=\"javascript:;\">上一页</a>"), $next = $("<a href=\"javascript:;\">下一页</a>");
            // $prev.attr("href", "act4guest.jsp?p=" + (parseInt(p)-1));
            // $next.attr("href", "act4guest.jsp?p=" + (parseInt(p)+1));
            $pager.append($("<li>").append($prev));
            $pager.append($("<li>").append($next));

            $prev.on("click", function(e) {                
                if (p < 2) {
                    var li = this.parentNode;
                    li.classList.contains("disabled") || li.classList.add("disabled");
                    return false;
                }
                p = parseInt(p)-1;
                list_participant(p);
            });
            $next.on("click", function(e) {
                if (data.length === 0) {
                    return false;
                }
                if (data.length < pageSize) {
                    alert("已经是最后一页");
                    var li = this.parentNode;
                    li.classList.contains("disabled") || li.classList.add("disabled");
                    return false;
                }
                p = parseInt(p)+1;

                list_participant(p);
            });
        });
    }

    /**
     * 1 => 一, 2=>二  (年级)
     */
    function grade2kanji(g) {
        var a = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
        return (g > 9) ? g : a[g];
    }
    
    // 正文活动高亮
    $("#navbar").children(".nav").find("li").eq(4).addClass("active");
});