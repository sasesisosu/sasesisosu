<!-- 문의에 대한 내용을 보여주는 페이지 -->

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
					<c:forEach var="i" items="${contentView }">
			        <table border="1" align="center"> 			        
		                <thead>
		                    <th style="width: 100px; height: 50px;" bgcolor="green" >제목</th>
		                    <td style="width: 400px;">${i.board_title }</td>
		                </thead>
		                <tbody>
		                    <th style="width: 100px; height: 50px;" bgcolor="green">내용</th>
		                    <td style="width: 800px; height: 350px;">${i.board_content }</td>
		                </tbody>
		                <!-- 접속한 아이디가 admin(관리자)일 때 삭제기능 -->
		                <c:if test="${user_id eq'admin' }">	
							<a href="inquiryDelete.do?board_index=${i.board_index }">삭제&nbsp;</a>														
						</c:if>
						<!-- 접속한 아이디가 게시글 작성자일 때 삭제기능 -->
						<c:if test="${i.board_id eq user_id }">
							<a href="inquiryModify.do?board_index=${i.board_index }">수정&nbsp;</a>
						</c:if>
					
					<c:forEach var="j" items="${answerView }">
						<c:if test="${empty j.comment_content }">
							<!-- 접속한 아이디가 admin(관리자)일 때 답변할 수 있음 -->
							 <c:if test="${user_id eq'admin' }">	
								<a href="inquiryAnswer.do?board_index=${i.board_index }">답변&nbsp;</a>
							</c:if>
						</c:if>						
						<a href="inquiryView.do">뒤로가기</a>
							<tfoot>
								<c:if test="${!empty j.comment_content }">
				                    <th style="width: 100px; height: 50px;" bgcolor="green" >답변</th>
				                    <td style="width: 400px;">		                    	
										${j.comment_content } <p style="text-align: right; inline-block:none"> ${j.comment_date }</p>
				                    </td>
			                    </c:if>	
				            </tfoot>
			            </c:forEach>
					</c:forEach>
			    </table>        			
        		
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>