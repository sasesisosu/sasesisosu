<!-- 로맨스상품 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>whereBook</title>
<style type="text/css">
	body {
		/* position: relative; */
		height: 100%;
	}		
	#goods{
		border: 1px solid black; 
	}
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<% 
		String user_id = "";
		if((String)session.getAttribute("user_id") != null){
			user_id = (String)session.getAttribute("user_id");
		}		
	%>
	<div id="wrap">
	<div id="search_bar">
        <form action="search.book" method="get">
             <input type="text" name="word" placeholder="찾고 싶은 책이름을 검색하세요" size="50">
             <input type="submit" value="검색">
          </form>               
     </div>
        <div id="category">
            <br>
            <ul id="category_nav">
               <li><a href="romance.book">로맨스전체</a></li>
               <li><a href="romanceMo.book">현대물</a></li>
               <li><a href="romanceHi.book">역사/시대물</a></li>
               <li><a href="romanceFa.book">판타지물</a></li>
            </ul>
            <br>
            <hr>
            <br>          
            <h3>구매없이 대여로 보고 싶은 책을 골라보세요</h3>
            <br>
            <button onclick="location.href='buy_ticket.jsp'">자세히보기</button>
        </div>
        <div id="content">         
        <c:if test="${!(list2 eq null)}">
	        <div id="genre_title" style="padding: 20px 0 0 50px">
		    	<h2 style="text-align:center">로맨스 인기상품 TOP3</h2>
		    </div>		  
		    <!-- 인기상품의 순위를 나타내기 위함 -->
		    <% int num=1; %>
	        <c:forEach var="k" items="${list2 }">
		        	<div id="goods">
		            	<ul>
			            	<li><a href="bookContent.book?product_title=${k.product_title}"><img src="images/${k.product_image}"></a></li>
			            	<li>제목 : <b>${k.product_title }</b></li>
			            	<li>저자 : <b>${k.product_writer }</b></li>
			            	<li>가격 : <b>${k.product_price }</b>원</li>
			            	<li><b><%= num %>위</b></li>
			            	<% num++; %>
			            </ul>
		            </div>	    
		    </c:forEach>
		    <br>
		    <br>
		    <hr>
		    <br>
	    </c:if>
        <div id="genre_title" style="padding: 0 0 0 20px">
	    	<h2 style="text-align:center">${title }</h2>
	    </div>	 	  
        	<c:forEach var="i" items="${list }">
	        	<div id="goods">
	            	<ul>		            	
		            	<li><a href="bookContent.book?product_title=${i.product_title }"><img src="images/${i.product_image }"></a></li><br><br>
		            	<li>제목 : <b>${i.product_title }</b></li>
		            	<li>저자 : <b>${i.product_writer }</b></li>
		            	<li>가격 : <b>${i.product_price }</b>원</li>
		            	<% if(user_id.equals("admin")){ %>
		            	<li>관리자 전용 : <a onclick="if(!confirm('상품을 삭제 하시겠습니까?')){return false;}" href="productDelete.book?product_title=${i.product_title}"><b>상품 삭제</b></a></li>
		            	<%} %>
		            </ul>
	            </div>
	        </c:forEach>
        </div>
    </div>
    
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</body>
</html>