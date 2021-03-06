<!-- 마이페이지 - 구매한 상품 목록 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   
      if(user_name == null) { // 비로그인시 로그인 페이지로 이동
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
              <div id="content">
                 <div id="myPage_title">
                      <h1>구매 목록</h1>
                      <div>
	                      <c:forEach var="i" items="${list }">
		                      <div id="goods">
			                      <ul>
									  <li><img src="images/${i.buy_product_image}" width="50px" height="50px" /></li>
									  <li>제목 : <b>${i.buy_product_title }</b></li>
									  <li>저자 : <b>${i.buy_product_writer}</b></li>									  
									  <li>가격 : <b>${i.buy_product_price }</b>원</li>		
									  <li><a href="bookArticle.book?product_title=${i.buy_product_title}">내용 보기</a></li>							  
								  </ul>
							  </div>
						  </c:forEach>
					  </div>
                  </div>                 
              </div>
          </div>
         
         <jsp:include page="footer.jsp"></jsp:include>
   <%
      }
   %>
</body>
</html>