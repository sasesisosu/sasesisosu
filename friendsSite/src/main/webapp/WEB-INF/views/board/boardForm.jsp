<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/> <!-- 회원 정보 저장 -->
</sec:authorize>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<c:forEach var="board" items="${boards.content }">
		<div class="card m-2">
			<div class="card-body">
				<h6 align="right">작성자: ${board.user.userName } | 조회수: ${board.count } | 등록일: ${board.createDate }</h6><h4>${board.title }<b style="color:red">[${board.replyCount}]</b></h4>
				<a href="/friendsSite/board/${board.id }/${principal.user.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<li class="pagination justify-content-start" ><a class="nav-link" href="/friendsSite/boardWrite">글쓰기</a></li>   
	<ul class="pagination justify-content-center">
	  <c:choose>  		
	  	<c:when test="${boards.first }">
	  		<li class="page-item"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
	  	</c:when>
		<c:when test="${boards.last }">
			<li class="page-item"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
	  	</c:when>
	  	<c:otherwise>
	  		<li class="page-item"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
	  		<li class="page-item"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
	  	</c:otherwise>
	  </c:choose>	
	</ul> 
	<ul class="pagination justify-content-center">
		 ${boards.number + 1}/${boards.totalPages } Page
	</ul>
	</div>
	
	
</body>
</html>
<%@ include file="../layout/footer.jsp"%>