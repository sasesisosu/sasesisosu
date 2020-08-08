<!-- 마이페이지 - 공지사항 게시판 -->

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
<%

	BoardDAO bdao = new BoardDAO();
	int pageNumber = 1;
	int result= bdao.getBoardCount(1);
	if(result<=5) {
		pageNumber=1;
	}else if(result%5==0){
		pageNumber=result/5;
	}else {
		pageNumber=result/5+1;
	}

	String user_id = (String)session.getAttribute("user_id");
	String name= (String)session.getAttribute("user_name");
	
	request.setAttribute("user_id", user_id);

%>
    <div id="wrap">
    	<% if(name!=null){ %> <!-- 로그인상태 일 때 사이드바를 보여줌 -->
        	<jsp:include page="sideNav.jsp"></jsp:include>
        <%}else{ %> <!-- 비로그인시 사이드바는 안보여주고 공지 & 이벤트만 보여줌 -->
        <div id="side_nav" style="height: 235px; top: 10%;" >
            <ul>
                <br><br><br>
                <li style="font-size: 1.8em; font-weight: bold; text-decoration: none; list-style: disc;">소식</li>
                <li><a href="noticeView.do">공지 & 이벤트</a></li>                
            </ul>
        </div>
        <% } %>
        <div id="content">
        	<div id="myPage_title">
                <h1>공지 & 이벤트</h1>
            </div>
        	<div id="notice_board">
        		<table border="1" align="center">        			
        			<th width="200px" height="40px" bgcolor="green">작성자</th>
        			<th width="200px" bgcolor="green">제목</th>
        			<th width="500px" bgcolor="green">내용</th>
        			<th width="200px" bgcolor="green">날짜</th>
        			<!-- 접속한 아이디가 admin(관리자)일 때 공지사항 글쓰기 가능 -->
        			<c:if test="${user_id eq 'admin' }">
						<a href="noticeWriter.jsp">글쓰기</a>&nbsp;
					</c:if>		        			
					<a href="main.jsp">뒤로가기</a>
						<hr>					
						<c:forEach var="i" items="${list}">
							<tr align="center" height="40px">								
								<td>관리자</td>
								<td><a href="contentView.do?board_index=${i.board_index }">${i.board_title }</a></td>
								<td><div style="max-height:35px; overflow:hidden;">${i.board_content }</div></td>
								<td> ${i.board_reg_date }</td>
							</tr>
						</c:forEach>					
        		</table>
        		<div style="text-align:center">
	        		<c:forEach var="i" begin="1" end="<%=pageNumber %>">
						<a href="page.do?pageNumber=${i }">${i }</a>&nbsp;
					</c:forEach>
				</div>
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>