<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/friendsSite/login" method="post">
		<div class="form-group">
			<label for="userId">아이디</label> 
			<input type="text" name="username" class="form-control" placeholder="아이디" id="userId" required="required">
		</div>

		<div class="form-group">
			<label for="userPwd">비밀번호</label> 
			<input type="password" name="password" class="form-control" placeholder="비밀번호" id="userPwd" required="required">
		</div>

		<button class="btn btn-primary">로그인</button>
		<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=cddff5f5f89f6fa1a4db1ed662af495e&redirect_uri=http://localhost:8080/friendsSite/kakao/login&response_type=code"><img height="38px" src="/friendsSite/image/kakao_login_button.png"></a> --> 
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=cddff5f5f89f6fa1a4db1ed662af495e&redirect_uri=http://www.sasesisosm.com/friendsSite/kakao/login&response_type=code"><img height="38px" src="/friendsSite/image/kakao_login_button.png"></a>
	</form>
	
</div>
<%@ include file="../layout/footer.jsp"%>