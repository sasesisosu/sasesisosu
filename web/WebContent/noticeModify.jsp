<!-- 마이페이지 - 공지사항 수정 페이지 -->

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
                <h1>공지 & 이벤트</h1>
            </div>
        	<div id="notice_board"> 					
				<table>
				<form action="modifyComplete.do" method="post">
					<c:forEach var="i" items="${contentView }">
			        <table border="1" align="center"> 			        
		                <thead>
		                    <th style="width: 100px; height: 50px;" bgcolor="green" >제목</th>
		                    <td style="width: 400px;"><input type="text" value="${i.board_title }" name="board_title"></td>
		                </thead>
		                <tbody>
		                    <th style="width: 100px; height: 50px;" bgcolor="green">내용</th>
		                    <td><textarea style="width: 800px; height: 350px;" name="board_content">${i.board_content }</textarea></td>
		                </tbody>
		                <input type="hidden" value="${i.board_id }" name="board_id">
		                <input type="hidden" value="${i.board_index }" name="board_index">
						<input type="submit" value="수정">
			        </table>					
					</c:forEach>
				</form>
				<a href="noticeView.do">뒤로가기</a>
			    </table>        	
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>