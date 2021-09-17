<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="userId">아이디</label> 
			<input type="text" class="form-control" placeholder="아이디" id="userId" required="required" >
		</div>
		<div class="form-group">
			<label for="userPwd">비밀번호</label> 
			<input type="password" class="form-control" placeholder="비번" id="userPwd" required="required" >
		</div>
		<div class="form-group">
			<label for="userName">이름</label> 
			<input type="text" class="form-control" placeholder="이름" id="userName" required="required" >
		</div>		
	 	<div class="form-group">
			<label for="userIntro">자기 소개</label> 
			<input type="text" class="form-control" placeholder="안써도됨" id="userIntro">
		</div>
		<div class="form-group">
			<label for="userRelation">관계</label> 
			<input type="text" class="form-control" placeholder="262와 무슨 관계?" id="userRelation" required="required">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입 완료</button>	
</div>
<script src="/friendsSite/js/user.js"></script> 
<%@ include file="../layout/footer.jsp"%>