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
	<c:set var="rank" value="0"/>
	<c:forEach var="board" items="${boards.content }">
	<c:set var="rank" value="${rank+1 }"/>
		
		<div class="card m-2"> 
		<div class="card-header">
			<h5 style="color:red">Best ${rank}</h5>
		</div>
			<div class="card-body">
				<h6 align="right">작성자: ${board.user.userName } | 조회수: ${board.count } | 등록일: ${board.createDate }</h6><h4 class="card-title">${board.title }<b style="color:red">[${board.replyCount}]</b></h4>
				<a href="/friendsSite/board/${board.id }/${principal.user.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	</div>
	
	
</body>
</html>
<%@ include file="../layout/footer.jsp"%>