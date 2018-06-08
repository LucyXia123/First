<%@ page contentType="text/html;charset=utf-8"%>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="keywords" content="同方,在线教育,同方凌讯">
            <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
            <title>同方教育在线-记者站我的收藏</title>
            <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />

            <link rel="stylesheet" href="./css/base.css" />
            <link rel="stylesheet" href="./css/index.css" />
            <link rel="stylesheet" href="./css/schoolReport/theme_ask.css">
          
        </head>
<body>
        <nav class="navbar">
        <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
        <img class="logo" src="./images/logo.jpg" title="同方教育在线" />
        </a>
        <div id="navbar" class="navbar-collapse collapse">
        <jsp:include page="./include/nav.jsp" flush="true" />
        <jsp:include page="./include/nav-right.jsp" flush="true" />
        </div>
        </div>
        </nav>
        <div class="ask_box">
	<span class="ct">创建任务</span>
	<form action="" id="ask_form">
		<div class="item">
			<div id="item_content" class="item_content">
				<label>*任务类型:</label>
				<select id="first_input">
					<option value="">主题报道</option>
				</select>
			</div>

			<div class="item">
				<div id="item_task" class="item_content">
					<label>*输入主题:</label>
					<input type="text" value="">
				</div>
			</div>
			<div class="item">
				<div id="item_shape" class="item_content">
					<label>*报道形式:</label>
					<div id="item_shape_buttonBox">
						<input type="radio" name="tuwen" value="tuwen" id="tuwen" checked>
						<label for="man">图文</label>
						<input type="radio" name="luying" value="luying" id="luying">
						<label for="luying">录音</label>
					</div>
				</div>
			</div>
			<div class="item">
				<div id="item_title" class="item_content">
					<label>*任务标题:</label>
					<input type="text" value="">
				</div>
			</div>
			<div class="item">
				<div id="item_standard" class="item_content">
					<label>*完成标准:</label>
					<select id="item_standard_input">
						<option value="1">1</option>
					</select><span>篇报道</span>
				</div>
				<div class="item">
					<div id="item_deadtime" class="item_content">
						<label>*有效期:</label>
						<input type="text" value="">
					</div>
				</div>
				<div class="item">
					<div id="item_statement" class="item_content">
						<label>*状态:</label>
						<div id="buttonBox">
							<input type="radio" name="sex" value="man" id="man" checked>
							<label for="man">开启</label>
							<input type="radio" name="sex" value="female" id="female">
							<label for="female">关闭</label>
						</div>
					</div>
				</div>
				<button id="save">保存</button>
			</div>
			</div>
	</form>
</div>
 <script type="text/javascript" src="./js/school-report/jquery.min.js"></script>
 <script type="text/javascript" src="./js/school-report/schoolReport_shouye.js"></script>
</body>
</html>