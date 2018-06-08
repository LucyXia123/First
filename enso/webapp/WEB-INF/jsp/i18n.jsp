<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title" ></spring:message> </title>
</head>
<body>

	<script type="text/javascript">
	function changeLocale(lang){
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + 365);
		document.cookie="lang=" + lang + ";expires=" + exdate.toGMTString();
		location.reload();
	}
	</script>

	<a href="javascript: changeLocale('zh')">中文</a>
	<a href="javascript: changeLocale('en')">英文</a>
	<div style="margin: 0 auto;margin-top: 100px; ">
	
		<spring:message code="desc"></spring:message>
	
	</div>

</body>
</html>
