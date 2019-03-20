<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp" flush="false"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form class = "search_movie">
      <input type = "text" id = "movieName" name="movieName">
      <input type = "submit" id = "sub" name="sub" value = "영화리뷰찾기">
   </form>
   <div class = "movie_names">
      
   </div>
   <input type="button" name="btn" value="리뷰 작성하기" style="display:none">`
   <script>
      $(document).ready(function(){
         $('.search_movie').on('submit', function(e){
            e.preventDefault();
            var movieName = $('#movieName').val();
            var param = $(this).serialize();
            
            $.ajax({
               url : '/grade/movie/getInfo',
               type : 'GET',
               data : param,
               async : false,
               dataType : "json"
            }).done(function(result) {
               alert("성공");
           		var jsonObj = result;
           		
           		$.each(result, function(key,value){
           			$('.btn').remove();
           			$('.movie_names').empty();
           			console.log(key,value);         			
           			if(key==="items"){
           				console.log(value);   
	        			$.each(value, function(i,v){
	        				let form = gethtmlForm(i,v);
	        				$('.movie_names').append(form);
	        				addBtnEvent(i,v);
	        			});
           			}
           		});
            }).fail(function(fail){
               alert("실패");
            });
         });
         
         function emptyAllChildren(target){
            target.empty();
         }
		
         function gethtmlForm(index,obj){
        	let element = "";
        	element += "<form action='' method='get'>";
        	element += "<input type='hidden' name='title' value='"+obj['title']+"'>";
        	element += "<input type='hidden' name='img' value='"+obj['image']+"'>";
        	element += "<input type='hidden' name='director' value='"+obj['director']+"'>";
        	element += "<input type='hidden' name='actor' value='"+obj['actor']+"'>";
        	element += "<input type='hidden' name='userRating' value='"+obj['userRating']+"'>";
			element += "<div name='title' id = 'title' value ='"+obj['title']+"'>제목 : "+obj['title']+"</div>";
			element += "<img src='"+obj['image']+"' name='img' alt ='이미지없오'></a>";
			element += "<div name='director' value ='"+obj['director']+"'>"+"director : "+obj['director']+"</div>";
			element += "<div name='actor' value ='"+obj['actor']+"'>"+"actor : "+obj['actor']+"</div>";
			element += "<div name='user_rating' value ='"+obj['userRating']+"'>"+"userRating : "+obj['userRating']+"</div>";
			element += "<input type='submit' class='btn' id='btn"+index+"' value='영화정보 보기'>";
			element += "</form>";
			return element;
         }
         
         function addBtnEvent(index, obj){
	         $(document).on('click','#btn'+index, function(e){
	        	e.preventDefault();
	        	
	        	let link = obj['link'];
	        	let temp = link.split("?");
	        	let code = temp[1].substring(5,temp[1].length);
	        	let param = $(this).parent().serialize();
	        	
	        	location.href = "/grade/movie/getMovieInfo?code="+code+"&"+param;
	         });
         }
      });
      
      
   </script>
</body>
</html>