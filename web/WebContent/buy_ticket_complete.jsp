<!-- E-Book 구매창 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#wrapper {   
   display: grid;
   
   grid-template-columns: repeat(4, 1fr);
   grid-template-rows: minmax(100px, auto);
}
.box {
   padding: 15px;
   /* color: white; */
   font-weight: bold;
   text-align: center;
   border: 1px solid black;
   
   grid-column: 1/5;
}
</style>
</head>
<body>
<!-- 이용권 구매 완료시 페이지 -->
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrapper">
      <div class="box" id="caution">
      <h1>이용권 구매 완료!</h1>
      <img src="images/ticket.jpg" width="700px" height="500px"><br />
	  <a href="novel.book">책 대여하러 가기</a>
      </div>
      <div class="box" id="price_info">
         <h3>이용권은 계정당 5권을 대여 할 수 있으며</h3><br /> 
         <h3>7일 동안 열람이 가능하다</h3><br />
         <h3>대여 후 7일 뒤에는 자동 소멸된다</h3><br />
         <h4>!이용권은 사용하지 않아도 소멸되지 않음!</h4>
      </div>
   </div>
   
   <jsp:include page="footer.jsp"></jsp:include>   
</body>
</html>