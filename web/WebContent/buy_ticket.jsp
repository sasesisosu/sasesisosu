<!-- 이용권 안내 및 구매 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 완료되면 border지우기 -->
<style type="text/css">
#wrapper > div {
   border: 1px solid black;
   padding: 10px;
}
</style>
</head>
<body>
<!-- 이용권 구매하기 전에 이용권에 대한 내용 보여주기 -->
   <jsp:include page="header.jsp"></jsp:include>   
   <div id="wrapper">
      <div id="ticket_title">
        <a onclick="if(!confirm('구매 하시겠습니까?')){return false;}" href="buy_ticket.buy"><h2>이용권 구매하기 Click!</h2></a>
      </div>
      <div id="ticket_subTitle">
      <h2>이용권 이란</h2>
         이용권 구매 시 총 5권을 대여 할 수 있으며 이용권으로 대여 할 시 한 권씩 자동 차감됩니다          
      </div>
      <div id="buy_ticket_info">
         <h2>이용권 추가 구매 가능한가요??</h2>
         이용권은 추가 구매 할 수 있으며 추가 구매 할 시 남은 대여 권 수 에서 5권이 추가됩니다
      </div>
      <div id="buy_ticket_item">
         <h2>정가 구매와 이용권으로 중복 구매 가능한가요??</h2>
         정가 구매나 이용권으로 구매시 구매불가 상품으로 변경됩니다  
      </div>
      <div id="ticket_caution">
         <h2>이용권 주의사항</h2>
         이용권으로 대여 한 책은 7일 동안 열람 가능하며 7일 후에는 자동 소멸됩니다<br />                  
      </div>
   </div>
   
   <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>