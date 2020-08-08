<!-- 상품 등록 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table.type05 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    margin: 20px 10px;    
  	width: 700px;
}
table.type05 th {
    width: 200px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #efefef;
}
table.type05 td {
    width: 250px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
    <div id="wrap">
        <jsp:include page="sideNav.jsp"></jsp:include>
        <div id="content">
        	<div id="content-wrap">
	        	<div id="myPage_title">
	                <h1>상품 등록</h1>
	            </div>	   	        	 	
	        		<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">
	        		<table class="type05">  
	        		<tr>
	        			<th scope="row">책 제목</th>
	        			<td><input type="text" name="product_title"></td>
	        		</tr>	
	        		<tr>
	        			<th scope="row">저자</th>
	        			<td><input type="text" name="product_writer"></td>
	        		</tr>
	        		<tr>
	        			<th scope="row">출판사</th>
	        			<td><input type="text" name="product_publisher"></td>
	        		</tr>
	        		<tr>
	        			<th scope="row">가격</th>
	        			<td><input type="text" name="product_price">원</td>
	        		</tr>
	        		<tr>
	        			<th scope="row">장르</th>
	        			<td>
		        			<input type="radio" name="product_genre" value="소설" id="g1">소설
		        			<input type="radio" name="product_genre" value="판타지" id="g2">판타지
		        			<input type="radio" name="product_genre" value="로맨스" id="g3">로맨스<br />	        			
	        				<select name="product_category" id="g11" disabled="disabled">
								<option value="국내 소설">국내 소설</option>
								<option value="일본 소설">일본 소설</option>
								<option value="추리 소설">추리 소설</option>
								<option value="SF 소설">SF 소설</option>
							</select>						
							<select name="product_category" id="g22" disabled="disabled">
								<option value="정통 판타지">정통물</option>
								<option value="퓨전 판타지">퓨전물</option>
								<option value="현대 판타지">현대물</option>
								<option value="무협 판타지">무협물</option>
							</select>						
							<select name="product_category" id="g33" disabled="disabled">
								<option value="현대 로맨스">현대물</option>
								<option value="역사 로맨스">역사/시대물</option>
								<option value="판타지 로맨스">판타지물</option> 
							</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th scope="row">책 소개</th>
	        			<td><textarea name="product_content" style="width:250px"></textarea></td>
	        			
	        		</tr>
	        		<tr>	
	        		</tr>
	        		<tr>
	        			<th scope="row">책 이미지(이미지 파일 업로드)</th>
	        			<td><input type="file" name="product_image"></td>
	        		</tr>
	        		<tr>
	        			<th scope="row">책 내용(책 내용 관련 파일 업로드)</th>
	        			<td><input type="file" name="product_article"></td>
	        		</tr>
					<tr>
						<td><input type="submit" value="상품 업로드"></td>
					</tr>					
					</table>
					</form>			        	
	        </div>
        </div>
    </div>	
	<script>
	 	$("#g1").click(function(){
	        $("#g22").attr("disabled", "disabled");
	        $("#g33").attr("disabled", "disabled");
	        $("#g11").removeAttr("disabled");
	    });
	    $("#g2").click(function(){
	        $("#g22").removeAttr("disabled");
	        $("#g33").attr("disabled", "disabled");
	        $("#g11").attr("disabled", "disabled");
	    });

	    $("#g3").click(function(){
	        $("#g33").removeAttr("disabled");
	        $("#g22").attr("disabled", "disabled");
	        $("#g11").attr("disabled", "disabled");
	    });
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>