<!-- 문의에 대한 답변을 작성하는 페이지 -->

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
                <h1>1:1 문의 답변</h1>
            </div>
        	<div id="notice_board"> 					
				<table>
					<form action="inquiryAnswerComplete.do" method="post">
						<c:forEach var="i" items="${contentView }">
					        <table border="1" align="center">
					                <tbody>
					                    <th style="width: 100px; height: 50px;" bgcolor="green">답변</th>
					                    <td><input style="width: 800px; height: 350px;" type="text" name="comment_content"></td>
					                </tbody>
					        </table>
					        <input type="hidden" value="${i.board_index }" name="board_index"><br>
							<input type="submit" value="답변">
							<a href="inquiryView.do">뒤로가기</a>
						</c:forEach>
			        </form>
			    </table>  
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>