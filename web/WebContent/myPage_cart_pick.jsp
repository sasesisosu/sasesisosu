<!-- 마이페이지 - 찜 목록 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

#pick_board a:hover {
   text-decoration: underline;
}

#pick_item {
   height: 200px;
}

#pick_item td {
   text-align: center;
   border: 2px solid green;
}

#pick_item td:nth-child(1) {
   width: 300px;
}



</style>

</head>
<body>
   
   <jsp:include page="header.jsp"></jsp:include>

   <%
      String user_name = (String)session.getAttribute("user_name");
   
      if(user_name == null) {
   %>
         <script type="text/javascript">
            alert("로그인 페이지로 이동합니다.");
            location.href = "login.jsp";
         </script>
   <%
      } else {   
   %>
          <div id="wrap">
              <jsp:include page="sideNav.jsp"></jsp:include>
              <div id="content">
                 <div id="myPage_title">
                      <h1>찜 목록</h1>
                 </div>
                 <div id="myPage_subTitle">
                     <h3>해당상품을 클릭하면 상세보기와 구매가 가능합니다</h3>
                  </div>     
                  <div id="pick_board">           
                   <table  style="width: 90%; margin: 0 auto; border-collapse: collapse; border-spacing: 0;
                               margin-bottom: 50px;">
                 <c:forEach var="i" items="${list }">
                    <tr id="pick_item">
                       <td>
                          <a href="bookContent.book?product_title=${i.buy_product_title}">
                             <img src="images/${i.buy_product_image}" width="180px" height="170px" />
                          </a>
                       </td>
                       <td>
                          <a href="bookContent.book?product_title=${i.buy_product_title}">
                             <b>${i.buy_product_title }</b>
                             <b>(${i.buy_product_writer})</b>
                          </a>
                       </td>
                       <td>
                          <a href="bookContent.book?product_title=${i.buy_product_title}">
                             <b>${i.buy_product_price }</b>원
                          </a>
                       </td>
                       <td>
                          <a href="bookContent.book?product_title=${i.buy_product_title}">더 보기</a>
                       </td>
                    </tr>
                 </c:forEach>
                 </table>
              </div>                 
              </div>
          </div>
         
         <jsp:include page="footer.jsp"></jsp:include>
   <%
      }
   %>
</body>
</html>