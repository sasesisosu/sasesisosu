<!-- 문의 작성하는 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="board.BoardDAO.BoardDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html, body {
	width: 100%; height: 100%;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 내 책장 -->
    <div id="wrap">
        <jsp:include page="sideNav.jsp"></jsp:include>
        <div id="content">
        	<div id="myPage_title">
                <h1>1:1 문의</h1>
            </div>
        	<div id="notice_board"> 					
				<table>
					<form action="inquiry.do" method="post">
			        <table border="1" align="center">
			                <thead>
			                	<input type="hidden" name="${user_id }">
			                    <th style="width: 100px; height: 50px;" bgcolor="green" >제목</th>			                    
			                    <td><input type="text" name="board_title" style="width: 400px;"></td>
			                </thead>
			                <tbody>
			                    <th style="width: 100px; height: 50px;" bgcolor="green">문의 내용</th>
			                    <td><textarea style="width: 800px; height: 350px;" name="board_content"></textarea></td>
			                </tbody>
			        </table>
			        <input type="submit" value="문의 하기">
					<a href="inquiryView.do">뒤로가기</a>
			        </form>
			    </table>        			
        		
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>