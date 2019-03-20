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
               data : param
            }).done(function(result) {
               alert("성공");
               console.log(result);
               let $names = $('.movie_names');
               emptyAllChildren($names);
              $('.movie_names').append(result);
              addBtn();
            }).fail(function(fail){
               alert("실패");
            });
         });
         
         function emptyAllChildren(target){
            target.empty();
         }
         function addBtn(){
            let len = $('.search_list_1 li').length;
            let btnArray = [];
            for(let i = 0; i<len; i++){
               let cloneBtn = $('input[name="btn"]').clone(); 
               cloneBtn.attr("id","btn"+i);
               cloneBtn.attr("name","btn");
               cloneBtn.css("display","inline");
               btnArray.push(cloneBtn);
            }
            
            for(let i = 0; i<len; i++){
               $('.search_list_1 li').eq(i).append(btnArray[i]);
            }
            
            for(let i = 0; i<len; i++){
               $(document).on('click','#btn'+i, function(){
                     let movieCode = $(this).parent().children("p").children("a").attr("href");
                     location.href = "/grade/movie/searchDetail?movieCode="+movieCode;
               });
            }
         }
      });
      
      
   </script>
</body>
</html>