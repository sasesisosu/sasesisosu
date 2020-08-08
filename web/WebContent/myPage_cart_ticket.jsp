<!-- 마이페이지 - 이용권으로 대여한 상품 목록 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
   <jsp:include page="header.jsp"></jsp:include>

   <%
      String user_name = (String)session.getAttribute("user_name");
        request.setAttribute("user_name", user_name);               
      if(user_name == null) {
   %>
         <script type="text/javascript">
            alert("로그인 페이지로 이동합니다.");
            location.href = "login.jsp";
         </script>
   <%
      } else {   
   %>         
         <!-- 내 책장 -->
          <div id="wrap">
              <jsp:include page="sideNav.jsp"></jsp:include>
              <c:set var="ticketExpire" value="${ticketExpire}" />
              <!-- 이용권으로 대여한 책은 7일 후 자동 소멸하게 기능을 넣었으므로 기간이 만료된 책이 있으면 알림문구를 띄우도록 하였음  --> 
           <% 
              int ticketExpire = (int)pageContext.getAttribute( "ticketExpire" );
              if(ticketExpire != 0){                
                PrintWriter script = response.getWriter();
                script.println("<script>");
                 script.println("alert('기간이 만료된 책이 있습니다.')");
                 script.println("location.href='myPage_cart_ticket.buy'");
                script.println("</script>");   
              }
              %>
              <div id="content">                
                  <div id="myPage_title">
                      <h1>이용권 목록</h1>
                      <c:if test="${user_name ne null }">
                         <c:choose>
                           <c:when test="${ticketCount == 0 }">
                           <!-- 이용권이 없을 때 나오는 문구 -->
                               <h2 style="text-align:right">이용권이 없습니다. 이용권을 구매해 주세요</h2>
                          </c:when>
                          <c:otherwise>
                          <!-- 남은 이용권을 보여주는 문구  -->
                               <h2 style="text-align:right">남은 대여 권 수 : ${ticketCount }</h2>
                          </c:otherwise>
                         </c:choose>                       
                      </c:if>                         
                      <c:forEach var="i" items="${list2 }">                      
                         <c:if test="${i.buy_product_title ne null }">  <!-- 이용권으로 대여 한 것만 화면에 보여줌 -->
                               <div id="goods">
                                  <ul>
                                <li><img src="images/${i.buy_product_image}" width="50px" height="50px" /></li>
                                <li>제목 : <b>${i.buy_product_title }</b></li>
                                <li>저자 : <b>${i.buy_product_writer}</b></li>                             
                                <li>가격 : <b>${i.buy_product_price }</b>원</li>
                                <li><b>${i.buy_date }</b>까지</li>      
                                <li><a href="bookArticle.book?product_title=${i.buy_product_title}">내용 보기</a></li>                    
                             </ul>
                          </div>
                    </c:if>
                 </c:forEach>
                  </div>                  
              </div>
          </div>
         
         <jsp:include page="footer.jsp"></jsp:include>
   <%
      }
   %>
</body>
</html>