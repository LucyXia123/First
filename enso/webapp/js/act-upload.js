/**
 * 作文评选活动上传内容 内容列表
 * depends on common.js
 * @returns
 */
window.g_activity = {};
window.g_student = {};
window.g_participant = {};

$(function() {
    // 当前登录用户的ID
    var userId = null;

    if (DEBUG) {
        userId = "STU201709301537110001";
    } else {
        userId = $("#js-userid").val();
        if (null === userId || "null" === userId) {
            location.href = "login.jsp?referer=act-enroll.jsp";
        }
    }

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

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("[" + textStatus + "]: " + errorThrown);
    });

    // 查看是否已经报名, 取得报名信息
    $.ajax({
        type: "GET",
        url: "./queryActivityParticipantsByConditional",
        data: {usernumber: userId, activityId: DEFAULT_ACTIVITIY_ID},
    	dataType:"json"
    }).done(function(data) {
    	/*
        if (data.type !== "学生") {
        	console.debug(data);
            alert("只有学生用户才能参加活动：["+data.type+"]");
            location.href = "index.jsp";
        }
        */
        ['mobile', 'username', 'userNumber', 'realname', 'school', 'area', 'grade', 'classname'].forEach(function(prop) {
            if (data.hasOwnProperty(prop)) {
                g_student[prop] = data[prop];
            }
        });
        var school = g_student.school;
        var a = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
        var g = g_student.grade;
        var grade = (g > 9) ? g : a[g];

        var nm = printf("{0}{1}年级{2}班{3}", school, grade, data.classname, data.realname);
        console.log(nm);

        if (data.hasOwnProperty("slogan")) {
            Object.keys(data).forEach(function(key, idx) {
                window.g_participant[key] = data[key];
            });
        }
        console.log(window.g_participant);

        var $upload = $("#upload").empty();
        var $avatar = $("<img>").addClass("avatar").attr("src", g_participant.avatarUrl).attr("title", "用户头像");
        $upload.append($avatar);

        var $metas = $("<div class='enroll-metas'>");
        var $userinfo = $("<div>").addClass("user-info");
        $("<p>").html(nm).appendTo($userinfo);
        var $a = $("<a>").addClass("btn").addClass("btn-default").attr("href", "act-enroll.jsp?edit=1")
            .html("<span class=\"glyphicon glyphicon-pencil\"></span>编辑</a>");
        $userinfo.append($a);
        $metas.append($userinfo);

        var $buttons = $("<div>").addClass("user-buttons");
        $metas.append($buttons);

        var $btn1 = $("<a class=\"btn btn-default\" href=\"javascript:;\"><span class=\"glyphicon glyphicon-eye-open\"></span>查看作品</a>");
        $btn1.attr("data-id", data.id);
        $btn1.on("click", function(e) {
            e.preventDefault();
            // 查看作品
            check_work_handler(this.getAttribute("data-id"));
        });
        var $btn2 = $("<a class=\"btn btn-default\" href=\"javascript:;\"><span class=\"glyphicon glyphicon-cloud-upload\"></span>上传作品</a>");
        $btn2.on("click", function(e) {
            // 上传作品
            e.preventDefault();
            $("#workModal").modal("show");
        });
        // 本校报名情况 
        var $btn3 = $("<a class=\"btn btn-default\" href=\"model-school.jsp\"><span class=\"glyphicon glyphicon-list-alt\"></span>本校报名情况</a>");
        $btn3.attr("href", (function(stu) {
            var d = {
                "school": stu.school,
                "pageNumber": 1,
                "pageSize": 8
            };
            var get_param = function(d) {
                var a = [];
                for (var prop in d) {
                    if (d.hasOwnProperty(prop)) {
                        a.push(prop + "=" + d[prop]);
                    }
                }
                return a.join('&');
            };
            return "act-list-by-school.jsp?" + get_param(d);
        })(g_student));

        $buttons.append($btn1).append($btn2).append($btn3);

        $upload.append($metas);

        // 列表
        $.ajax({
            type: "GET",
            url: "./queryStudentActivityWorks",
            data: {"count":12, "area":g_student.area}
        }).done(function(data) {
            var na = ['0', '一', '二', '三', '四', '五', '六', '七', '八', '九'];

            if (data instanceof Array) {
                var $works = $("#works").empty();

                if (data.length===0) {
                    $works.html("<p>当前作品列表为空</p>");
                }

                // 取得评论/投票用户(当前登录用户)编号
                var getCommentatorUsernumber = function() {
                    var n = $("#js-userid").val();
                    if (null === n || "null" === n) {
                        window.location.href = "login.jsp?referer=" + escape(window.location.href);
                        return "";
                    }
                    return n;
                };

                Array.prototype.forEach.call(data, function(item) {
                    var $card = $("<div>").addClass("card").attr("data-id", item.id);
                    $works.append($card);

                    $("<div>").addClass("imgwrap").html("<img src=\""+item.avatarUrl+"\" />").appendTo($card);
                    var $figure = $("<div>").addClass("figure").appendTo($card);

                    var g = item.grade;
                    var grade = (g > 9) ? g : na[g];
                    var nm = printf("{0}{1}年级{2}班{3}", item.school, item.grade, item.className, item.realName);
                    $figure.append($("<p>"+nm+"</p>"));

                    // item.vote
                    var $tutor = $("<div>").addClass("tutor");
                    $tutor.html("指导老师: " + item.instructor);
                    // var $r = $("<div>").addClass("pull-right");
                    // $tutor.append($r);
                    var $vote = $("<a href=\"javascript:;\" title='投票'><span class=\"glyphicon glyphicon-tree-conifer\"></span></a>");
                    // $r.append($vote);
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
                                $num.html(data.count + "票");
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
                    $vote.appendTo($sns);
                    $vote.parent().append($("<span class=\"num\">"+ item.votes +"票</span>"));
                    // var $likes = $("<span>").addClass("likes").text(item.likes).appendTo($sns);
                    var url = "act-view.jsp?id=" + item.id;
                    var $check = $("<a class=\"check\" href='"+url+"'>查看作品</a>").appendTo($sns);
                    $check.on("click", function() {
                        // queryStudentActivityWorksById
                    });

                    $figure.append($sns);
                });
            }
        });

        // load bootstrap modal
        var script = new Script(function() {
            var $userUpload = $("#J_user_upload");
            // 隐藏的上传文件表单
            var $fileUpload = $("#J_file_upload");

            // 上传作品
            var $workModal = $("#workModal");
            $workModal.on("show.bs.modal", function() {
                $userUpload.empty();
                var $file = $fileUpload.find("input[type='file']");
                if ($file.length===0) {
                    $file = $("<input type=\"file\" name=\"files\" multiple=\"multiple\">");
                    $fileUpload.append($file);
                }
            });
            $workModal.on("hidden.bs.modal", function() {
                $fileUpload.empty();
            });

            $workModal.on("shown.bs.modal", function() {
                var ss = printf("{0}&nbsp;{1}年级{2}班", g_student.school, grade2kanji(g_student.grade), g_student.classname);

                var $user = $("<div>").addClass("user").html(
                    ["<div class=\"user\">",
                        "<img class=\"avatar\" src=\""+g_participant.avatarUrl+"\">",
                        "<div class=\"metas\">",
                        "<h4>" +g_student.realname+ "</h4>",
                        "<div class=\"school\">" +ss+ "</div>",
                        "<div class=\"slogan\">作文题目: <input id=\"J_title\" type=\"text\" value=\""+g_participant.slogan+"\" /></div>",
                        "<div class=\"director\">指导老师: "+g_participant.instructor+"</div>",
                        "</div>",
                        "</div>"].join('') );
                $userUpload.append($user);

                var $upAlt = $("<div>").addClass("up_alt");
                var $up1 = $("<a class=\"alt\" href=\"javascript:;\"><span>传图片</span></a>").on("click", function(e) {
                    e.preventDefault();
                    // 传图片 通过文件上传表单
                    var $file = $fileUpload.find("input[type='file']");
                    $file.trigger("click");
                });
                var $up2 = $("<a class=\"alt\" href=\"javascript:;\"><span>传文件</span></a>").on("click", function(e) {
                    e.preventDefault();
                    // 传文件 通过文件上传表单
                    var $file = $fileUpload.find("input[type='file']");
                    $file.trigger("click");
                });
                var $up3 = $("<a class=\"alt last\" href=\"javascript:;\"><span>自己打</span></a>").on("click", function(e) {
                    e.preventDefault();
                    $upAlt.find(".alt").hide();
                    var $textarea = null;
                    if ($upAlt.find("textarea").length === 0) {
                        $textarea = $("<textarea>").css({"height":"130px", "width":"100%"}).attr("id", "editor1");
                        $upAlt.append( $textarea );

                        var up3 = this;

                        if (typeof CKEDITOR === "undefined") {
                            var s = new Script(function() {
                                CKEDITOR.replace('editor1');
                                up3.parentNode.style.height = "260px";
                            });
                            s.set("./ckeditor/ckeditor.js");
                        } else {
                            CKEDITOR.replace('editor1');
                            up3.parentNode.style.height = "260px";
                        }

                    } else {
                        $textarea = $upAlt.find("textarea");
                    }
                    $textarea.show();

                    var $wrap = $userUpload.children(".up_wrap");
                    $wrap.children(".btn-default").hide();
                    var $btnPrimary = null;
                    if ($wrap.children(".btn-primary").length===0) {
                        $btnPrimary = $("<button>").addClass("btn").addClass("btn-primary");
                        $wrap.append($btnPrimary);
                    } else {
                        $btnPrimary = $wrap.children(".btn-primary");
                    }
                    // 自己打 立即上传
                    $btnPrimary.text("立即上传").show().off("click").on("click", function(e) {
                        e.preventDefault();

                        var $inputTitle = $("#J_title");
                        var title = $inputTitle.val();
                        if (null === title || "" === title) {
                            alert("输入文章标题");
                            $inputTitle.focus();
                            return false;
                        }

                        var textData = {
                            "participantId": g_participant.id,
                            "title": title,
                            "content": CKEDITOR.instances.editor1.getData(),
                            "usernumber": g_student.userNumber
                        };
                        $.ajax({
                            type: "POST",
                            url: "./uploadActivityMaterialsOfContent",
                            dataType:"json",
                            contentType:"application/json",
                            data: JSON.stringify(textData)
                        }).done(function(data) {
                            if (200 === data.status) {
                                // FIXME: data.id 上传文件之后的自增ID
                                $("#fbModal").modal("show").find(".btn").attr("href", "act-view.jsp?id=" + g_participant.id);
                                $("#workModal").hide();
                            } else {
                                alert(printf("上传失败[{0}]:{1}", data.status, data.msg));
                            }
                        }).fail(function(jqXHR, textStatus, errorThrown) {
                            alert("uploadActivityMaterialsOfContent failed[{0}]: {1}", textStatus, errorThrown);
                        });
                    });
                });
                $upAlt.append($up1).append($up2).append($up3);
                $userUpload.append($upAlt);

                var $upWrap = $("<div>").addClass("up_wrap");

                // 传文件
                $("<button class=\"btn btn-default\">立即上传</button>").on("click", function() {
                	$("#J_loading").show();
                	
                    var form = $fileUpload.get(0);
                    var formData = new FormData(form);

                    var flag = null;
                    if (form.files.value.length === 0) {
                        // 未上传文件
                        flag = 0;
                    } else {
                        // 上传了文件
                        flag = 1;
                    }
                    console.log("flag=" + flag);
                    formData.append("flag", flag);

                    formData.append("participantId", g_participant.id);

                    var $inputTitle = $("#J_title");
                    var title = $inputTitle.val();
                    if (null === title || "" === title) {
                        alert("输入文章标题");
                        $inputTitle.focus();
                        return false;
                    }
                    formData.append("title", title);
                    formData.append("usernumber", g_student.userNumber);

                    $.ajax({
                        url  : "./uploadActivityMaterialsOfFile",
                        type : "POST",
                        data : formData,
                        cache       : false,
                        contentType : false,
                        processData : false,
                        dataType    : "html"
                    }).done(function(resp) {
                        if (DEBUG) {
                            alert(resp);
                        }
                        var data = JSON.parse(resp);
                        if (200 === data.status) {
                            $("#fbModal").modal("show").find(".btn").attr("href", "act-view.jsp?id=" + g_participant.id);
                            $("#workModal").hide();
                        } else {
                            alert(printf("上传失败[{0}]:{1}", data.status, data.msg));
                        }
                        // data.submissionId
                        $("#J_loading").hide();
                        console.log(data);
                    }).fail(function(jqXHR, textStatus, errorThrown) {
                        alert("uploadActivityMaterialsOfFile fail["+textStatus+"]: " + errorThrown);
                    });

                }).appendTo($upWrap);

                $userUpload.append($upWrap);
            });
        });
        script.set("./bootstrap/js/bootstrap.min.js");

        // 上传成功提示modal弹出时, 上传窗口隐藏
        $("#fbModal").on("show.bs.modal", function() {
            $("#workModal").hide();
        }).on("hide.bs.modal", function() {
            window.location.href = "act-view.jsp";
        });

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert(printf("取得报名信息ERROR[{0}]: {1}", textStatus, errorThrown));
    });


    /**
     * 查看作品
     * id:　作品ID
     */
    function check_work_handler(id) {
        window.location.href = "act-view.jsp?id=" + id;
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