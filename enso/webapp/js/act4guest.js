window.g_activity = {};

$(function() {
    var qs = enso_query_string();
    var pageSize = 8;

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

    var p = qs.hasOwnProperty("p") ? qs.p : 1;
    if (p < 1) {p = 1;}
    list_participant(p);
    
    // 按条件查询
    $("#J_search_submit").on("click", function(e) {
    	e.preventDefault();
    	var form = this.parentNode;
    	var condition = {
    		school: form.school.value,
    		grade: form.grade.value,
    		classname: form.classname.value,
    		realname: form.realname.value
    	};
    	list_participant(p, condition);
    });

    function list_participant(pageNumber, condition) {
    	var url = "";
    	var data = {
          "activityId": DEFAULT_ACTIVITIY_ID,
          "flag": 0,
          "pageNumber": pageNumber,
          "pageSize": pageSize
        };
    	if (typeof condition === "undefined" || condition === null) {
    		url = "./activity/queryActivityParticipant";
    	} else {
    		url = "./activity/listActivityParticipants";
    		Object.keys(condition).forEach(function(key, idx) {
    			data[key] = condition[key];
    		});
    	}
        // 取得作品列表
        $.ajax({
            type: "GET",
            url: url,
            data: data
        }).done(function(data) {
            var na = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];

            if (data instanceof Array) {
                var $works = $("#works").empty();
                if (data.length === 0) {
                    $works.html("<p>结果条目为空</p>");
                    return false;
                }
                Array.prototype.forEach.call(data, function(element, index) {
                	(function(item) {
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

                          // 点赞 + 投票 wrapper
                          var $sns = $("<div>").addClass("sns");                                                  
                                
                          // 有作品
                          if (item.submissionId) {
                              // 点赞
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
                              // 点赞数
                              $("<span>").addClass("num").text(item.likes).appendTo($sns);
                              
                              // 投票
                              var $vote = $("<a href=\"javascript:;\" title='投票'><span class=\"glyphicon glyphicon-tree-conifer\"></span></a>");
                              $sns.append($vote);
                              
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
                              $vote.parent().append($("<span class=\"num\">"+ item.votes +"票</span>"));  
                              
                              var participantId = item.id;
                              var url = "act-view.jsp?id=" + participantId;
                              var $check = $("<a class=\"check\" href='"+url+"'>查看作品</a>");
                              
                              $sns.append($check);
                          } else {
                        	  // submissionId equals 0 => 无投票 无点赞 不能查看作品
                           	  $sns.append($("<span>").html("暂无作品"));
                          }

                          $figure.append($sns);
                	})(element);
              
                });
            }

            var $pagination = $("#J_pagination").empty().css({"position":"relative"});
            var $pager = $("<ul>").addClass("pager");
            $pagination.append($pager);
            var $prev = $("<a href=\"javascript:;\">上一页</a>").css({"margin-right":"30px"}); 
            var $next = $("<a href=\"javascript:;\">下一页</a>").css({"margin-left":"30px"});
            $pager.append($("<li>").append($prev));
            $pager.append($("<li>").append($next));

            $prev.on("click", function(e) {
                if (pageNumber < 2) {
                    var li = this.parentNode;
                    li.classList.contains("disabled") || li.classList.add("disabled");
                    return false;
                }
                pageNumber = parseInt(pageNumber)-1;
                list_participant(pageNumber, condition);
                console.log("page=" + pageNumber);
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
                pageNumber = parseInt(pageNumber)+1;

                list_participant(pageNumber, condition);
                console.log("page=" + pageNumber);
            });
            
            $pagination.append( $("<div>").addClass("page_num").html("第"+pageNumber+"页") );
        });
        
    }  // end function list_participant
    
});

// 作品查询
(function() {
	// 学校 init
	var $select_school = $("#s_school").empty().append( $("<option>").val("").html("请选择") );
	
	$.getJSON("./data/schools.json", function(data) {
		$.each(data.citylist, function(key, value) {
			var s = "";
			Array.prototype.forEach.call(value.c, function(item) {
				s = value.p + item.n;
				$("<option>").val(s).html(s).appendTo($select_school);
			});
		});
	});
	
	// 年级 init 1->12
	var $select_grade = $("#s_grade").empty();
	(function(n) {
		if (n===0) {
			return $("<option>").val("").html("请选择").appendTo($select_grade);			 
		}
		arguments.callee(n-1);
		return	$("<option>").val(n).html(n).appendTo($select_grade);		
	})(12);
	
	// 班级 init 1->50
	var $select_classname = $("#s_classname").empty();
	(function(n) {
		if (n===0) {
			return $("<option>").val("").html("请选择").appendTo($select_classname); 
		}
		arguments.callee(n-1);
		$("<option>").val(n).html(n).appendTo($select_classname);
	})(50);

    // 正文活动高亮
    $("#navbar").children(".nav").find("li").eq(4).addClass("active");
})();