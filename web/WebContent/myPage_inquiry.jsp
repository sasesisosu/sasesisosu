<!-- 마이페이지 - 1:1문의 게시판 -->

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
		int pageNumber = 1; // 1:1문의에 대한 페이지
		// 1:1문의 페이지당 5개의 게시글이 등록될 수 있게 함
		int result= bdao.getBoardCount(2); // getBoardCount(2)은 1:1문의에 대한 게시글 개수
		if(result<=5) {
			pageNumber=1;
		}else if(result%5==0){
			pageNumber=result/5;
		}else {
			pageNumber=result/5+1;
		}
		String user_id = (String)session.getAttribute("user_id");
		request.setAttribute("user_id", user_id);
		String name = (String)session.getAttribute("user_name");
		   
	    if(name == null){
	%>
        <script type="text/javascript">
           alert("로그인 페이지로 이동합니다.");
           location.href = "login.jsp";
        </script>
   <% } %>	
	
	<!-- 내 책장 -->
    <div id="wrap">
        <jsp:include page="sideNav.jsp"></jsp:include>
        <div id="content">
        	<div id="myPage_title">
                <h1>1:1 문의</h1>
            </div>
        	<div id="inquiry_board">
        		<table border="1" align="center">
	        		<th width="200px" height="40px" bgcolor="green">작성자</th>
	        			<th width="200px" bgcolor="green">제목</th>
	        			<th width="500px" bgcolor="green">내용</th>
	        			<th width="200px" bgcolor="green">날짜</th>
	        			<!-- 접속한 아이디가 admin(관리자)가 아닐 때 문의하기 가능 -->
	        			<c:if test="${!(user_id eq 'admin') }">
							<a href="inquiry.jsp">문의하기</a>&nbsp;
						</c:if>		        			
						<a href="main.jsp">뒤로가기</a>
						<hr>							
						<c:forEach var="i" items="${list}">
							<tr align="center" height="40px">
								<td>${i.board_id }</td>
								<td><a href="inquiryContentView.do?board_index=${i.board_index }">${i.board_title }</a>		
								<!-- 게시글에 대한 답변이 있는지 확인하기 위함 -->						
								<c:set var="board_index" value="${i.board_index }" />
								<% 
								    int board_index = (int)pageContext.getAttribute( "board_index" );
								// answerView에서 값이 없으면 답변이 없는것
									if(bdao.answerView(board_index) != null){ %> 
										(답변 완료)	
								<% } %>
								<td><div style="max-height:35px; overflow:hidden;">${i.board_content }</div></td>
								<td> ${i.board_reg_date }</td>
							</tr>
						</c:forEach>							
        		</table>
        		<!-- 게시글에 대한 페이징을 보여줌 -->
        		<div style="text-align:center">
	        		<c:forEach var="i" begin="1" end="<%=pageNumber %>">
						<a href="pageInquiry.do?pageNumber=${i }">${i }</a>
					</c:forEach>
				</div>
        	</div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>