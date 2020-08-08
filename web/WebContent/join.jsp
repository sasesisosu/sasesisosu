<!-- 회원가입 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
       a{
          color:black;
          text-decoration: none;
       }
        body, html {
            margin: 0 auto;
            padding: 0;
            width: 100%;
            height: 100%;
        }
        .container {
            border: 1px solid rgba(0, 0, 0, 0.1);
            height: 100%;
            min-width: 90%;

            position: relative;
        }
        .container::after {
            width: 100%;
            height: 100%;
            content: "";
            background: url('./images/login_theme.jpg');
            position: absolute;
            top: 0;
            left: 0;

            opacity: 0.6;
            z-index: 1;
        }
        .login {
            position: absolute;
            left: 35%;
            top: 2%;
            width: 600px;
            height: 890px;

            border: 5px solid gray;
            z-index: 2;
        }
        .login h1 {
            text-align: center;
        }
        .login h3 {
            margin: 10px 40px 0 50px;
        }
        .login input[type=text], input[type=password] , input[type=email]{
            margin: 5px 0 20px 28px;
            width: 505px;
            height: 25px;
            padding: 8px;
        }
        .login button, input[type=submit] {
            width: 88%;
            height: 50px;
            text-align: center;
            margin: 10px 0 0 28px
        }
        .pwCheckMessage{
           z-index=10;
        }
    </style>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	// 회원가입 시 아이디 중복체크
	function IDCheck(){
		var user_id = $("#user_id").val();
		$.ajax({
			type: 'POST',
			url: './IDCheck',
			data: {user_id : user_id},
			success: function(result){
				if(result==1) $('#IDCheckMessage').html('사용 가능한 아이디 입니다').css('color', 'green');
				else $('#IDCheckMessage').html('사용할 수 없는 아이디 입니다').css('color', 'red');
				$('#checkModal').modal("hide");
			}
		});
	}
	// 회원가입시 비밀번호와 비밀번호 확인을 입력 할 때 비밀번호가 일치하는지 확인
   function pwCheckFunction(){ // 비밀번호 확인
      var userPW1 = $('#user_password').val();
      var userPW2 = $('#user_password2').val();
      if(userPW1.length < 4) $('#pwCheckMessage').html('비밀번호는 4자리 수 이상이어야 합니다').css('color', 'red');
	  else if(userPW1 == "" || userPW2 == "") $('#pwCheckMessage').html('');
	  else if(userPW1 != userPW2) $('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다').css('color', 'red');
	  else $('#pwCheckMessage').html('비밀번호가 서로 일치합니다').css('color','green');
   }
</script>
<body>
    <div class="container">
        <div class="login">
            <div id="title"><h1>회원가입</h1></div><br>
            <form action="joinAction.jsp" method="post">
                <h3>아이디</h3>
                <div>
	                <input type="text" size="50" name="user_id" id="user_id" placeholder="아이디를 입력하시오." style="width: 355px;"> 	               
	                <button onclick="IDCheck();" type="button" style="width: 100px; height: 50px;">중복체크</button>
                 </div>
                <h5 style="margin-left: 28px;" id="IDCheckMessage"></h5>     
                <h3>비밀번호</h3>
                <input type="password" size="80" name="user_password" id="user_password" onkeyup="pwCheckFunction();" placeholder="비밀번호를 입력하시오."><br>
                <h3>비밀번호 확인</h3>
                <input type="password" size="80" name="user_password2" id="user_password2"onkeyup="pwCheckFunction();" placeholder="비밀번호 확인."><br>
                <h3>이름</h3>
                <input type="text" size="50" name="user_name" placeholder="이름을 입력하시오."><br>
                <h3>이메일</h3>
                <input type="email" size="50" name="user_email" placeholder="이메일을 입력하시오.">
          	    <div><h5 style="margin-left: 28px;" id="pwCheckMessage"></h5></div>
                <input type="submit" value="회원가입">
                <button type="button" onclick="location.href='login.jsp'">돌아가기</button>
            </form>
        </div>
    </div>    
</body>
</html>