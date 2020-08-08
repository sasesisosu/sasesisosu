<!-- 상품에 대한 검색 페이지 -->

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
	#search_bar {
	   position:absolute;
	   border: 3px solid green;
	   
	   top : 100px;
	   float: left;
	    margin: 20px;
	   padding: 10px 10px 10px 40px;
	   
	   width: 19%;
	   
	}	
	#search_bar input[type=text] {   
	   width: 80%;
	   
	}
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
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
               <li><a href="novel.book">소설전체</a></li>
               <li><a href="novelKo.book">국내소설</a></li>
               <li><a href="novelJa.book">일본소설</a></li>
               <li><a href="novelDe.book">추리소설</a></li>
               <li><a href="novelSF.book">SF소설</a></li>
            </ul>
            <br>
            <hr>
            <br>
            <h3>구매없이 대여로 보고 싶은 책을 골라보세요</h3>
            <br><br>
            <button onclick="location.href='buy_ticket.jsp'">자세히보기</button>
        </div>
        <div id="content">
        <div id="genre_title" style="padding: 50px 0 0 100px">
	    	<h2>검색 목록</h2>
	    </div>	        
	        <c:forEach var="i" items="${list }">
	        	<div id="goods">
	            	<ul>
		            	<li><a href="bookContent.book?product_title=${i.product_title}"><img src="images/${i.product_image}"></a></li><br><br>
		            	<li>제목 : <b>${i.product_title }</b></li>
		            	<li>저자 : <b>${i.product_writer }</b></li>
		            	<li>가격 : <b>${i.product_price }</b>원</li>
		            </ul>
	            </div>
	        </c:forEach>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    
        
</body>
</html>