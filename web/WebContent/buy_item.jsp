<!-- E-Book 구매창 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 상품을 구매하기전 구매여부를 묻는 페이지 -->
   <jsp:include page="header.jsp"></jsp:include>
   <div id="wrapper">
      <div class="box" id="caution">
    <img src="images/${product_image}"><br />
         제목 : ${product_title}<br />
         저자 : ${product_writer}<br />
         상품설명 : ${product_content}<br />
      </div>
      <div class="box" id="price_info">
         주의사항 : 정가 구매시 환불이 안됩니다. 정가 구매가 어려우신 분들은 이용권을 이용해 보세요
      </div>
      <div class="box" id="buy_ticket">
         이용권 구매
      </div>
      <div class="box" id="buy_price">
         <a onclick="if(!confirm('구매 하시겠습니까?')){return false;}" href="buy_product.buy?product_title=${product_title}&product_price=${product_price}&product_image=${product_image}&product_writer=${product_writer}">정가 구매 하기</a> : ${product_price }원
      </div>
   </div>
   
   <jsp:include page="footer.jsp"></jsp:include>   
</body>
</html>