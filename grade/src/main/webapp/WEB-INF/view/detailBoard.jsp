<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../partial/header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="board" value="${board}"/>
	<div> 
		작성자 : <input type="text" readonly = "readonly" value="${board.id}">
		제목 : <input type="text" readonly = "readonly" value="${board.title}">
	</div>
	<div>
	내용 : <textarea rows="15" cols="70" readonly="readonly">${board.content}</textarea>
	</div>
	<div class = "comment">
		
	</div>
	<form class = "comment_form">
		<textarea rows="4" cols="50" name = "textarea"></textarea>
		<input type="submit" id="comment_btn" value="댓글입력">
	 </form>
</body>
<script>
	
window.onload = function(event){
	var commentBtn = $('#comment_btn').on('click', function(){
		let getComment = $("input[name=textarea]").value;
		addComment(getComment);
	});
	
	var json = {
			"id" : 1123,
			"content" : "주옥같다"
	}
	var jstring = JSON.stringify(json);
	function addComment(comment){
		event.preventDefault();
		$.ajax({
			url : '/grade/addComment',
			type : 'GET',
			contentType : "application/json",
			data : {"jsonData" : jstring}
		}).done(function(success){
			console.log(success);
			console.log("성공");
		}).fail(function(fail){
			console.log(fail);
			console.log("실패");
		});
		return false;
	}
}
</script>
</html>