<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">	
		<div class="form-group">			
			<label for="userName">이름</label> 
			<input value="${userInfo.userName }" class="form-control"readonly>
		</div>
		<div class="form-group">
			<label for="userIntro">자기 소개</label> 
			<input value="${userInfo.userIntro }" class="form-control" readonly>
		</div>
		<div class="form-group">
			<label for="userRelation">262와 관계</label> 
			<input value="${userInfo.userRelation }" class="form-control" readonly>
		</div>
		<a href="/friendsSite/board/${boardId}/${principal.user.id}" class="btn btn-secondary" >돌아가기</a>	
</div>

<%@ include file="../layout/footer.jsp"%>