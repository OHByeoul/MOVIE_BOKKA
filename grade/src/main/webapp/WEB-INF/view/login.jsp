<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp"/>
<body>
	<div class = "login">
		<form action="/grade/login" method="get">
			<input type="text" name = "id" placeholder="id염">
			<input type="password" name="password" placeholder="password염">
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>