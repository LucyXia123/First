<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="同方,在线教育,同方凌讯">
    <title>同方教育在线&middot;我的示范校</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
    <link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="./css/base.css" />
    <link type="text/css" rel="stylesheet" href="./css/main.css" />
    <link type="text/css" rel="stylesheet" href="./css/mathplus.css" />
    <script type="text/javascript" src="./bootstrap/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="./js/common.js"></script>
</head>

<body id="self">
<nav class="navbar">
    <div class="container-fluid">
       <a class="navbar-brand" href="index.jsp">
        <img class="logo" src="./images/logo.jpg" title="同方教育在线" />
       </a>
       <div id="navbar" class="navbar-collapse collapse">
            <jsp:include page="./include/nav.jsp" flush="true" />
            <jsp:include page="./include/nav-right.jsp" flush="true" />
            <%  if (null == session) {out.print("<script>location.href=\"login.jsp\";</script>");} %>
        </div>
        <!--/.navbar-collapse -->
    </div>
</nav>
<!-- 推荐轮播 -->
<%@ include file="./include/carousel.html" %>
<a name="教育栏目"></a>
<input id="js-area" type="hidden" value="<% out.print(session.getAttribute("area"));%>" />

<!-- 二级页面 -->
<input id="js-school" type="hidden" value="<% out.print(session.getAttribute("school"));%>" />
<div class="content-wrapper" id="js-ms-content"></div>
<script>
// 根据不同学校加载不同学校的内容
(function() {
	var school = $("#js-school").val();	

	// 切换到其他学校
	var qs = enso_query_string();
	var sc = qs.school;
	if (typeof sc !== "undefined") {
		school = sc;
	}
	
	if (school=="null") {window.location.href = "login.jsp?referer=model-school.jsp";}	
	var snippet = "";
	
	switch (school) {
	case "开封市五中": snippet = "./include/ms/kaifeng5.html"; break;
	case "开封市第七中学": snippet = "./include/ms/kaifeng7.html"; break;
	case "开封市八中": snippet = "./include/ms/kaifeng8.html"; break;
	case "开封市第十三中学": snippet = "./include/ms/kaifeng13.html"; break;
	case "开封市十四中东校区(21中)": snippet = "./include/ms/kaifeng21.html"; break;
	case "开封市第二十七中学": snippet = "./include/ms/kaifeng27.html"; break;
	case "开封市第三十三中学": snippet = "./include/ms/kaifeng33.html"; break;
	case "开封市集英小学": snippet = "./include/ms/jiying.html"; break;
	case "开封市马市街小学": snippet = "./include/ms/modul.html"; break;	
	case "濮阳市范县第一初级中学": snippet = "./include/ms/py_fanxian1.html"; break;
	case "濮阳市南乐县城关镇初级中学": snippet = "./include/ms/nanle_cgzcz.html"; break;
	case "濮阳市南乐县第二实验小学": snippet = "./include/ms/puyang_nanleshiyan2.html"; break;
	case "濮阳市南乐县第二初级中学": snippet = "./include/ms/nanlexianchujizhongxue.html"; break;
	case "濮阳市范县思源学校": snippet = "./include/ms/fxsySchool.html"; break;
	case "濮阳市第三中学": snippet = "./include/ms/puyang3.html"; break;
	case "濮阳市第八中学": snippet = "./include/ms/puyang8.html"; break;
	case "濮阳市油田第十二中学": snippet = "./include/ms/youtian12.html"; break;
	case "商丘市实验小学": snippet = "./include/ms/shangqiushiyan.html"; break;
	case "商丘市前进小学": snippet = "./include/ms/qianjinschool.html"; break;
	case "商丘市第一实验小学": snippet = "./include/ms/sqsdysyxx.html"; break;
	case "商丘市第一高级中学": snippet = "./include/ms/shangqiu1gao.html"; break;
	case "商丘市实验中学": snippet = "./include/ms/shangqiushiyanzhongxue.html"; break;
	case "商丘市第二高级中学": snippet = "./include/ms/shangqiu_high2.html"; break;
	case "商丘市行知学校": snippet = "./include/ms/shangqiu_xingzhi.html"; break;
	case "商丘市工学院附属兴华学校": snippet = "./include/ms/sq_gxyfsxhxy.html"; break;
	case "商丘市外国语实验小学": snippet = "./include/ms/shangqiuForeign.html"; break;
	case "商丘市第一中学": snippet = "./include/ms/sqsyz.html"; break;
	case "濮阳市南乐县育才小学": snippet = "./include/ms/nanleyucai.html"; break;
	default: snippet = "./include/ms/kaifeng5.html";
	}
	// var snippet = "./include/ms/sq_gxyfsxhxy.html";
	$("#js-ms-content").load(snippet);
}).call();
</script>

</div>  <!-- end .content-wrapper -->
<!-- /.content-wrapper END不同学校内容 -->
<!-- 数学加 学习广场 -->
<div class="row-fluid index_title">
    <h5>学习广场</h5>
    <hr />
    <div class="sep-green"></div>
    <div class="sub-title">数学加资深教育专家帮您支招，解决家长的难题</div>
</div>
<div class="row-fluid weekend_zixun">
    <div class="index-left">

        <%--1st column--%>
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-xytd">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">学员天地</span>
                </div>
                <a href="http://www.shuxuejia.com/article_list/parent_class.htm" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <div class="class_div">
                <!-- 发布置顶1篇文章 -->
                <div class="pull-left class_img">
                    <a href="http://www.shuxuejia.com/math/parent_class/7048.htmll" target="_blank">
                        <img class="index1_4" src="./images/mathplus/6033f2062a7c49dea0f3d968a4fb7816.png">
                    </a>
                </div>
                <div class="pull-right">
                    <p class="class_title"><a href="http://www.shuxuejia.com/math/parent_class/7048.html" target="_blank" title="8个孩子不想学习的理由 分析原因才能进步">8个孩子不想学习的理由 分析原因才能进步</a></p>
                    <p class="class_summary">孩子们五花八门的不想学习的理由，到底有哪些需要家长的帮助？如何分析了孩子们诸多理由背后真正的隐含词？...</p>
                    <div>
                        <p class="how_many">
                            <span class="glyphicon glyphicon-user"></span>已有
                            <font style="color: #7cb90e;">48</font>人观看
                        </p>
                    </div>
                </div>
            </div>
            <!-- end .class_div -->
            <ul class="zixun time201605" id="accordion0">
                <li>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6930.html" target="_blank" title="9个坏习惯拉低成绩 80%学生苦不堪言">9个坏习惯拉低成绩 80%学生苦不堪言</a>
                    <font>2017-11-10</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/parent_class/6927.html" target="_blank" title="除了智商和努力 还有一个事情决定孩子的成绩">除了智商和努力 还有一个事情决定孩子的成绩</a>
                    <font>2017-11-09</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/parent_class/6919.html" target="_blank" title="孩子的智商和情商 根源在你和他说话的语气里">孩子的智商和情商 根源在你和他说话的语气里</a>
                    <font>2017-11-09</font>
                </li>
            </ul>
        </div>
        <!-- end .column -->

        <!-- 2nd column -->
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-jjxsc">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">聚焦小升初</span>
                </div>
                <a href="http://www.shuxuejia.com/article_list/focus_rise.htm" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <div class="class_div">
                <div class="pull-left class_img">
                    <a href="#"></a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6931.html" target="_blank">
                        <img class="index1_4" src="./images/mathplus/d89c10dd651a42989c25378bcfc9c8d3.png">
                    </a>
                </div>
                <div class="pull-right">
                    <p class="class_title">
                        <a href="http://www.shuxuejia.com/math/focus_rise/6931.html" target="_blank" title="快速扩大单词量的简单办法  别说我没说！">快速扩大单词量的简单办法  别说我没说！</a>
                    </p>
                    <p class="class_summary">单词是学习英语的基石，英语程度的好坏，单词很重要。自然拼读里有个玩法，就是用筛子记单词，其实和我们的...</p>
                    <div>
                        <p class="how_many">
                            <span class="glyphicon glyphicon-user"></span>已有
                            <font style="color: #7cb90e;">24</font>人观看
                        </p>
                    </div>
                </div>
            </div>
            <!-- end .class_div -->
            <ul class="zixun time201605" id="accordion1">
                <li>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6934.html" target="_blank" title="133个英语单词反义词 小学生家长快收藏">
                        133个英语单词反义词 小学生家长快收藏
                    </a>
                    <font>2017-11-10</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6933.html" target="_blank" title="文言文10个翻译技巧 超级实用的小升初知识">
                        文言文10个翻译技巧 超级实用的小升初...
                    </a>
                    <font>2017-11-10</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6923.html" target="_blank" title="国学文化常识大全 小升初最实用的法宝">
                        国学文化常识大全 小升初最实用的法宝
                    </a>
                    <font>2017-11-09</font>
                </li>
            </ul>
        </div>
        <!-- / 2nd .column -->

        <%--3rd column--%>
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-qwsx">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">趣味数学</span>
                </div>
                <a href="http://www.shuxuejia.com/article_list/intrest_math.htm" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <div class="class_div">
                <div class="pull-left class_img">
                    <a href="#"></a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/6873.html" target="_blank">
                        <img class="index1_4" src="./images/mathplus/4f8bfaf3fd53462e95c8c0f980dc83b6.png">
                    </a>
                </div>
                <div class="pull-right">
                    <p class="class_title">
                        <a href="http://www.shuxuejia.com/math/intrest_math/6873.html" target="_blank" title="数学加迷恋上美猴王 所以有了猴子摘果的趣味数学题">数学加迷恋上美猴王 所以有了猴子摘果的趣味...</a>
                    </p>
                    <p class="class_summary">最近数学加编辑家的娃迷恋上了《西游记》，趁热打铁，数学加编辑给小朋友们带来一个有趣的数学故事。</p>
                    <div>
                        <p class="how_many">
                            <span class="glyphicon glyphicon-user"></span>已有
                            <font style="color: #7cb90e;">78</font>人观看
                        </p>
                    </div>
                </div>
            </div>
            <!-- end .class_div -->
            <ul class="zixun time201605" id="accordion2">
                <li>
                    <a href="http://www.shuxuejia.com/math/intrest_math/6824.html" target="_blank" title="有趣的十位数 你能作对吗？">有趣的十位数 你能作对吗？</a>
                    <font>2017-10-20</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/intrest_math/6782.html" target="_blank" title="你喜欢米鼠与唐鸭吗？来看看这个题目">你喜欢米鼠与唐鸭吗？来看看这个题目</a>
                    <font>2017-10-12</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/intrest_math/6767.html" target="_blank" title="趣味数学：有趣的猜数游戏">趣味数学：有趣的猜数游戏</a>
                    <font>2017-10-10</font>
                </li>
            </ul>
        </div>

        <%--4th column--%>
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-ztzk">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">直通中考</span>
                </div>
                <a href="http://www.shuxuejia.com/article_list/senior_entrance.htm" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <div class="class_div">
                <div class="pull-left class_img">
                    <a href="#"></a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6931.html" target="_blank">
                        <img class="index1_4" src="./images/mathplus/1ea593a14578430d8df6cdf5c19975d9.png">
                    </a>
                </div>
                <div class="pull-right">
                    <p class="class_title">
                        <a href="http://www.shuxuejia.com/math/senior_entrance/6929.html" target="_blank" title="因为作业太多就减少看书时间 老师家长本末倒置了吧">因为作业太多就减少看书时间 老师家长本末倒...</a>
                    </p>
                    <p class="class_summary">确实有些孩子上学以后就时间少了特别多。但是如果低龄孩子因为作业多，就不去读书的话，只能说老师和家长颠...</p>
                    <div>
                        <p class="how_many">
                            <span class="glyphicon glyphicon-user"></span>已有
                            <font style="color: #7cb90e;">19</font>人观看
                        </p>
                    </div>
                </div>
            </div>
            <!-- end .class_div -->
            <ul class="zixun time201605" id="accordion3">
                <li>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6932.html" target="_blank" title="把数学加编辑吓懵的33个会说不会写的字">
                        把数学加编辑吓懵的33个会说不会写的字
                    </a>
                    <font>2017-11-10</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6925.html" target="_blank" title="作文结尾如果这么写 不得高分不可能">
                        作文结尾如果这么写 不得高分不可能
                    </a>
                    <font>2017-11-09</font>
                </li>
                <li>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6926.html" target="_blank" title="别小看了初三上学期 这是决定孩子中考成败的关键期！">
                        别小看了初三上学期 这是决定孩子中考...
                    </a>
                    <font>2017-11-09</font>
                </li>
            </ul>
        </div>
    </div>

    <!-- 右边今日推荐 + 阅读排行榜 -->
    <div class="index-right">
        <%--今日推荐--%>
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-jrtj">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">今日推荐</span>
                </div>
                <a href="http://www.shuxuejia.com/math/parentsClass.html" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <%--推荐列表--%>
            <ul class="unstyled zixun">
                <li>
                    <a class="green_arrow color_two" href="http://www.shuxuejia.com/article_list/focus_rise.htm" target="_blank">聚</a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6931.html" title="快速扩大单词量的简单办法  别说我没说！">快速扩大单词量的简单办法  别说...</a><font>2017-11-10</font>
                </li>
                <li>
                    <a class="green_arrow color_two" href="http://www.shuxuejia.com/article_list/focus_rise.htm" target="_blank">聚</a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6934.html" title="133个英语单词反义词 小学生家长快收藏">133个英语单词反义词 小学生家长...</a><font>2017-11-10</font>
                </li>
                <li>
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/senior_entrance.htm" target="_blank">直</a>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6929.html" title="因为作业太多就减少看书时间 老师家长本末倒置了吧">因为作业太多就减少看书时间 老...</a><font>2017-11-10</font>
                </li>
                <li>
                    <a class="green_arrow color_one" href="http://www.shuxuejia.com/article_list/parent_class.htm" target="_blank">学</a>
                    <a href="http://www.shuxuejia.com/math/parent_class/6928.html" title="下面的18张图背熟，小学数学不用愁">下面的18张图背熟，小学数学不用...</a><font>2017-11-10</font>
                </li>

                <li>
                    <a class="green_arrow color_one" href="http://www.shuxuejia.com/article_list/parent_class.htm" target="_blank">学</a>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6930.html" title="9个坏习惯拉低成绩 80%学生苦不堪言">9个坏习惯拉低成绩 80%学生苦不...</a><font>2017-11-10</font>
                </li>

                <li>
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/senior_entrance.htm" target="_blank">直</a>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6932.html" title="把数学加编辑吓懵的33个会说不会写的字">把数学加编辑吓懵的33个会说不会...</a><font>2017-11-10</font>
                </li>

                <li>
                    <a class="green_arrow color_two" href="http://www.shuxuejia.com/article_list/focus_rise.htm" target="_blank">聚</a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/6933.html" title="文言文10个翻译技巧 超级实用的小升初知识">文言文10个翻译技巧 超级实用的...</a><font>2017-11-10</font>
                </li>

                <li>
                    <a class="green_arrow color_one" href="http://www.shuxuejia.com/article_list/parent_class.htm" target="_blank">学</a>
                    <a href="http://www.shuxuejia.com/math/parent_class/6927.html" title="除了智商和努力 还有一个事情决定孩子的成绩">除了智商和努力 还有一个事情决...</a><font>2017-11-09</font>
                </li>

                <li>
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/senior_entrance.htm" target="_blank">直</a>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6925.html" title="作文结尾如果这么写 不得高分不可能">作文结尾如果这么写 不得高分不...</a><font>2017-11-09</font>
                </li>

                <li class="no_border">
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/senior_entrance.htm" target="_blank">直</a>
                    <a href="http://www.shuxuejia.com/math/senior_entrance/6926.html" title="别小看了初三上学期 这是决定孩子中考成败的关键期！">别小看了初三上学期 这是决定孩...</a><font>2017-11-09</font>
                </li>

            </ul>
        </div>

        <%--阅读排行榜--%>
        <div class="column">
            <div class="row-fluid index_zixun_title clearfix col-readrank">
                <div class="pull-left">
                    <div class="triangle-bottomleft"></div>
                    <span class="column-title">阅读排行榜</span>
                </div>
                <a href="http://www.shuxuejia.com/math/parentsClass.html" class="more pull-right" target="_blank">
                    <span>更多</span>&gt;&gt;
                </a>
            </div>
            <ul class="unstyled zixun">
                <li>
                    <a class="green_arrow color_three" href="http://www.shuxuejia.com/article_list/intrest_math.htm" target="_blank">趣</a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/991.html" title="生活中有趣的6个数学小故事">生活中有趣的6个数学小故事</a><font>2015-06-25</font>
                </li>
                <li>
                    <a class="green_arrow color_one" href="http://www.shuxuejia.com/article_list/parent_class.htm" target="_blank">学</a>
                    <a href="http://www.shuxuejia.com/math/parent_class/1379.html" title="数学加功能新升级 邀请好友赚学币">数学加功能新升级 邀请好友赚学...</a><font>2015-07-29</font>
                </li>
                <li>
                    <a class="green_arrow color_three" href="http://www.shuxuejia.com/article_list/intrest_math.htm" target="_blank">趣</a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/136.html" title="网上流传最火的趣味数学题 来挑战吧">网上流传最火的趣味数学题 来挑...</a><font>2014-09-23</font>
                </li>
                <li>
                    <a class="green_arrow color_three" href="http://www.shuxuejia.com/article_list/intrest_math.htm" target="_blank">趣</a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/2816.html" title="趣味数学题，到底有多少条腿？">趣味数学题，到底有多少条腿？</a><font>2015-12-09</font>
                </li>
                <li>
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/shuxuejianews.htm" target="_blank">数</a>
                    <a href="http://www.shuxuejia.com/math/shuxuejianews/1921.html" title="数学加武林论道:培优课与同步课=学霸和学神对决">数学加武林论道:培优课与同步课=...</a><font>2015-09-22</font>
                </li>
                <li>
                    <a class="green_arrow color_three" href="http://www.shuxuejia.com/article_list/intrest_math.htm" target="_blank">趣</a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/2256.html" title="巧妙的移动，图形里的趣味数学题">巧妙的移动，图形里的趣味数学题</a><font>2015-11-03</font>
                </li>
                <li>
                    <a class="green_arrow color_three" href="http://www.shuxuejia.com/article_list/intrest_math.htm" target="_blank">趣</a>
                    <a href="http://www.shuxuejia.com/math/intrest_math/2080.html" title="让魔方还原速度更快 20步快速还原魔方">让魔方还原速度更快 20步快速还...</a><font>2015-10-23</font>
                </li>
                <li>
                    <a class="green_arrow color_one" href="http://www.shuxuejia.com/article_list/parent_class.htm" target="_blank">学</a>
                    <a href="http://www.shuxuejia.com/math/parent_class/132.html" title="IPAD里适合孩子的数学APP">IPAD里适合孩子的数学APP</a><font>2014-09-18</font>
                </li>
                <li>
                    <a class="green_arrow " href="http://www.shuxuejia.com/article_list/shuxuejianews.htm" target="_blank">数</a>
                    <a href="http://www.shuxuejia.com/math/shuxuejianews/2242.html" title="逆天的小学数学 全班家长没一个做对">逆天的小学数学 全班家长没一个...</a><font>2015-11-03</font>
                </li>
                <li class="no_border">
                    <a class="green_arrow color_two" href="http://www.shuxuejia.com/article_list/focus_rise.htm" target="_blank">聚</a>
                    <a href="http://www.shuxuejia.com/math/focus_rise/1966.html" title="天津的私立小学 要把家长又爱又恨气疯掉的节奏！">天津的私立小学 要把家长又爱又...</a><font>2015-09-25</font>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- END 数学加学习广场 -->

<!-- nav bottom -->
<div class="content-wrapper" id="js-navbottom-wrapper"></div>
<style>.nav-bottom {padding-left: 50px;}</style>
<script>$("#js-navbottom-wrapper").load("./include/nav-bottom.html");</script>

<!-- footer -->
<div id="footer"></div>
<script>$("#footer").load("./include/footer.html");</script>
<!-- <script src="./bootstrap/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="./js/self.js"></script>
</body>

</html>