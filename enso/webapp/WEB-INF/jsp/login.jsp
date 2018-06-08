<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.title"></spring:message></title>
</head>
<body>

	<script type="text/javascript">
	var i = 1;
	function changecode(){
		document.getElementById('vcode').src="vcode?v=" + (i++);
	}
	</script>

	<div style="margin: 0 auto;margin-top: 100px; ">
	
		<form action="checkcode" method="post">
			<input type="text" name="vcode">
			<input id="vcode" type="image" src="vcode" onclick="changecode()">
			<input type="submit">
		</form>
		
		${error }
		
	</div>

</body>
</html>