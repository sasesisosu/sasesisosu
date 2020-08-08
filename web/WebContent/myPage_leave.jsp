<!-- 마이페이지 - 회원탈퇴 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
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
<script>
	// 회원 탈퇴 시 비밀번호와 비밀번호 확인을 입력할 때 나오는 문구들 
	function pwCheckFunction(){
		var userPW1 = $('#user_password').val();
		var userPW2 = $('#user_password2').val();
		if(userPW1 == "" || userPW2 == "") $('#pwCheckMessage').html('');
		else if(userPW1 != userPW2) $('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다').css('color', 'red');
		else $('#pwCheckMessage').html('비밀번호가 일치합니다').css('color', 'green');
	}		
</script>
<%
	String user_id = null;
	if(session.getAttribute("user_id") != null){
		user_id = (String) session.getAttribute("user_id");
	}
%>	
	<!-- 내 책장 -->
    <div id="wrap">
        <jsp:include page="sideNav.jsp"></jsp:include>
        <div id="content">
        	<div id="content-wrap">
	        	<div id="myPage_title">
	                <h1>회원탈퇴</h1>
	            </div>
	            <div id="myPage_subTitle">
	            	<h3>비밀번호 확인을 통해 탈퇴가 가능합니다.</h3>
	            </div>
	        	<div id="leave_form">
	        		<form action="withdrawAction.jsp" method="post">
	        		<input type="hidden" name=<%=user_id %>>
	        			<div id="leave_pw">
	        				<h1>비밀번호</h1>
	        				<input type="password" size="40" name="user_password" id="user_password" onkeyup="pwCheckFunction();" placeholder="비밀번호를 입력하시오."><br>
	        			</div>
	        			<hr>
	        			<div id="leave_pw_ok">
	        				<h1>비밀번호 확인</h1>
	        				<input type="password" size="40" name="user_password2" id="user_password2"onkeyup="pwCheckFunction();" placeholder="비밀번호 확인."><br>
	        			</div>
	        			<hr>
	        			<div><h5 style="margin-left: 28px;" id="pwCheckMessage"></h5></div>
	        			<div id="leave_btn">
	        				<button type="submit">회원탈퇴</button>
	        				<button><a href="myPage_main.jsp">돌아가기</a></button>
	        			</div>
	        		</form>
	        	</div>
	        </div>
        </div>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>