<!-- 마이페이지 - 공지사항에 대한 내용 -->

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
	<%
		String user_id = (String)session.getAttribute("user_id");
		request.setAttribute("user_id", user_id);
	%>
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
					<c:forEach var="i" items="${contentView }">
			        <table border="1" align="center"> 			        
		                <thead>
		                    <th style="width: 100px; height: 50px;" bgcolor="green" >제목</th>
		                    <td style="width: 400px;">${i.board_title }</td>
		                </thead>
		                <tbody>
		                    <th style="width: 100px; height: 50px;" bgcolor="green">내용</th>
		                    <td style="width: 800px; height: 350px;"><a href="main.jsp">${i.board_content }</a></td>
		                </tbody>
		                <!-- 접속한 아이디가 admin(관리자)일 때 수정, 삭제 가능 -->
		                <c:if test="${user_id eq'admin' }">	
							<a href="noticeModify.do?board_index=${i.board_index }">수정</a>&nbsp;
							<a href="noticeDelete.do?board_index=${i.board_index }">삭제</a>
						</c:if>
			        </table>
					<a href="noticeView.do">뒤로가기</a>
					</c:forEach>
			    </table>        			
        		
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>