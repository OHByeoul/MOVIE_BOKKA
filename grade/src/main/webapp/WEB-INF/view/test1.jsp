<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!-- <link rel="stylesheet" type="text/css" href="css/test4.css">-->
<!-- <script src="myscripts.js"></script> -->
<!------ Include the above in your HEAD tag ---------->
<style>
/*    --------------------------------------------------
	:: General
	-------------------------------------------------- */
body {
	font-family: 'Open Sans', sans-serif;
	color: #353535;
}

.content h1 {
	text-align: center;
}

.content .content-footer p {
	color: #6d6d6d;
	font-size: 12px;
	text-align: center;
}

.content .content-footer p a {
	color: inherit;
	font-weight: bold;
}

/*	--------------------------------------------------
        :: Table Filter
        -------------------------------------------------- */
.panel {
	border: 1px solid #ddd;
	background-color: #fcfcfc;
}

.panel .btn-group {
	margin: 15px 0 30px;
}

.panel .btn-group .btn {
	transition: background-color .3s ease;
}
/* .table-filter {
        background-color: #fff;
        border-bottom: 1px solid #eee;
    } */
.table-filter tbody tr:hover {
	cursor: pointer;
	background-color: #eee;
}

.table-filter tbody tr td {
	padding: 10px;
	vertical-align: middle;
	border-top-color: #eee;
}

.table-filter tbody tr.selected td {
	background-color: #eee;
}

.table-filter tr td:first-child {
	width: 38px;
}

.table-filter tr td:nth-child(2) {
	width: 35px;
}
/* .ckbox {
        position: relative;
    }
    .ckbox input[type="checkbox"] {
        opacity: 0;
    }
    .ckbox label {
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }
    .ckbox label:before {
        content: '';
        top: 1px;
        left: 0;
        width: 18px;
        height: 18px;
        display: block;
        position: absolute;
        border-radius: 2px;
        border: 1px solid #bbb;
        background-color: #fff;
    }
    .ckbox input[type="checkbox"]:checked + label:before {
        border-color: #2BBCDE;
        background-color: #2BBCDE;
    }
    .ckbox input[type="checkbox"]:checked + label:after {
        top: 3px;
        left: 3.5px;
        content: '\e013';
        color: #fff;
        font-size: 11px;
        font-family: 'Glyphicons Halflings';
        position: absolute;
    } */
/* .table-filter .star {
        color: #ccc;
        text-align: center;
        display: block;
    } */
/* .table-filter .star.star-checked {
        color: #F0AD4E;
    }
    .table-filter .star:hover {
        color: #ccc;
    }
    .table-filter .star.star-checked:hover {
        color: #F0AD4E;
    } */
.table-filter .media-photo {
	width: 35px;
}

.table-filter .media-body {
	display: block;
	/* Had to use this style to force the div to expand (wasn't necessary with my bootstrap version 3.3.6) */
}

.table-filter .media-meta {
	font-size: 11px;
	color: #999;
}

.table-filter .media .title {
	color: #2BBCDE;
	font-size: 14px;
	font-weight: bold;
	line-height: normal;
	margin: 0;
}

.table-filter .media .title span {
	font-size: .8em;
	margin-right: 20px;
}

.table-filter .media .title span.pagado {
	color: #5cb85c;
}

.table-filter .media .title span.pendiente {
	color: #f0ad4e;
}

.table-filter .media .title span.cancelado {
	color: #d9534f;
}

.table-filter .media .summary {
	font-size: 14px;
}
</style>
</head>
<body>
	<form action="" id="search_form" method="get">
		<input type="text" class="search-query" name="movieName" id="search"
			placeholder="검색">
		<button class="btn btn-warning" id="search_btn" type="submit">검색</button>
	</form>

	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-container">
							<table class="table table-filter" id="movie_names">
								<tbody>
									<div class="template" style="display: none">
									<tr>
										<td>
											<div class="media">
												<a href="#" class="pull-left"> <img src="" class="media-photo">
												</a>
												<div class="media-body">
													<!-- <span class="media-meta pull-right">Febrero 13, 2016</span> -->
													<h4 class="title">
														<!-- Lorem Impsum -->
														<!-- <span class="pull-right pagado">(Pagado)</span> -->
													</h4>
													<p class="summary"></p>
													<!-- <button class = "btn btn-primary btn-sm pull-right" type="submit" >영화정보찾기
                                                        </button> -->
												</div>
											</div>
										</td>
									</tr>
									</div>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg -->
				<div class="content-footer"></div>
			</div>
		</div>
	</div>
	<script>
    $(document).ready(function(){
        $('#search_form').on('submit', function(e){
            e.preventDefault();
            var movieName = $('#search').val();
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
                        $('#movie_names > tbody').empty();
                        console.log(key,value);         			
                        if(key==="items"){
                            console.log(value);   
                            $.each(value, function(i,obj){
                                let form = gethtmlForm(i,obj);
                              	console.log(form);
                              	form.find('.media_photo').attr("src", obj['image']);
                              	form.find('.title').text(obj['title']);
                              	form.find('.summary').text(obj['userRating']);
                                
                                $('#movie_names > tbody').append(form);
                                addBtnEvent(i,obj);
                            });
                        }
                    });
            }).fail(function(fail){
                alert("실패");
            });
        });

        function gethtmlForm(index,obj){
            let element = "";
            let $temp = $('.template').clone();
            console.log($temp);
       
            element += "<input type='hidden' name='title' value='"+obj['title']+"'>";
        	element += "<input type='hidden' name='img' value='"+obj['image']+"'>";
        	element += "<input type='hidden' name='director' value='"+obj['director']+"'>";
        	element += "<input type='hidden' name='actor' value='"+obj['actor']+"'>";
            element += "<input type='hidden' name='userRating' value='"+obj['userRating']+"'>";  
            let button = "<button class = 'btn btn-primary btn-sm pull-right' id='btn"+index+"' type='submit' >영화정보찾기</button>";
            $temp.append(button);
            $temp.append(element);
           	$temp.show();
            return $temp;
        	// element += "<form action='' method='get'>";
        	// element += "<input type='hidden' name='title' value='"+obj['title']+"'>";
        	// element += "<input type='hidden' name='img' value='"+obj['image']+"'>";
        	// element += "<input type='hidden' name='director' value='"+obj['director']+"'>";
        	// element += "<input type='hidden' name='actor' value='"+obj['actor']+"'>";
        	// element += "<input type='hidden' name='userRating' value='"+obj['userRating']+"'>";
			// element += "<div name='title' id = 'title' value ='"+obj['title']+"'>제목 : "+obj['title']+"</div>";
			// element += "<img src='"+obj['image']+"' name='img' alt ='이미지없오'></a>";
			// element += "<div name='director' value ='"+obj['director']+"'>"+"director : "+obj['director']+"</div>";
			// element += "<div name='actor' value ='"+obj['actor']+"'>"+"actor : "+obj['actor']+"</div>";
			// element += "<div name='user_rating' value ='"+obj['userRating']+"'>"+"userRating : "+obj['userRating']+"</div>";
			// element += "<input type='submit' class='btn' id='btn"+index+"' value='영화정보 보기'>";
			// element += "</form>";
			
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