<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id }" />
		<input type="hidden" id="userId" value="${principal.user.userId }" />
		
		<c:set var="kakaoId" value="${principal.user.userId }"/>

		<c:choose>			
			<c:when test="${fn:contains(kakaoId, 'kakao')}" >
				<label for="userPwd">카카오 아이디는 비밀번호 변경 불가</label> 
			</c:when> 
			<c:otherwise>
				<div class="form-group">			
					<label for="userId">아이디</label> 
					<input type="text" value="${principal.user.userId }" class="form-control" readonly>
				</div>
				<div class="form-group">
					<label for="userPwd">비밀번호</label> 
					<input type="password" class="form-control" placeholder="비번" id="userPwd">
				</div>
			</c:otherwise>
		</c:choose>		
		<div class="form-group">			
			<label for="userName">이름</label> 
			<input type="text" value="${principal.user.userName }" class="form-control" placeholder="이름" id="userName">
		</div>
		<div class="form-group">
			<label for="userIntro">자기 소개</label> 
			<input type="text" value="${principal.user.userIntro }" class="form-control" placeholder="안써도됨" id="userIntro">
		</div>
		<div class="form-group">
			<label for="userRelation">관계</label> 
			<input type="text" value="${principal.user.userRelation }" class="form-control" placeholder="262와 무슨 관계?" id="userRelation" required="required">
		</div>	
		
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정 완료</button><a href="/friendsSite/boardForm" class="btn btn-secondary">돌아가기</a>
</div>
<script src="/friendsSite/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>