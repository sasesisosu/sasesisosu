<!-- 리뷰 작성 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- controller에서 각종 에러 발생 시 한번에 처리 -->
   <%
      String error = (String)session.getAttribute("errorType");   
      if(error.equals("reviewContent")) {
   %>
         <!-- 리뷰 not null(review_content) 에러 -->
         <script type="text/javascript">
            alert("리뷰내용을 적어주세요");
            history.back();
         </script>
   <%      
      } else if(error.equals("inquiryContent")) {
   %>
         <!-- 1:1문의 not null(title, content) 에러 -->
         <script type="text/javascript">
            alert("제목과 내용은 필수기재 사항입니다.");
            history.back();
         </script>          
   <%
      } else if(error.equals("partnerCheck")){
   %>
  		 <!-- 파트너 신청 시 이미 파트너가 있는 아이디한테 신청을 할 때 -->
   		 <script type="text/javascript">
            alert("이미 파트너 상태인 사용자 입니다.");
            location.href="myPage_partner.jsp";
         </script>        
   <% } else if(error.equals("partnerIDCheck")){%>
   		<!-- 파트너 신청 시 없는 아이디한테 신청 할 때 -->
   		 <script type="text/javascript">
            alert("존재하지 않는 아이디 입니다.");
            location.href="myPage_partner.jsp";
         </script>
   <%} %>  	
   
</body>
</html>