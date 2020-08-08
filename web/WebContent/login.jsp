<!-- 로그인 할 때  페이지-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Galada&family=Nanum+Myeongjo:wght@700&display=swap" rel="stylesheet">
    <style>
    	a {
    		color: black;
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
            top: 10%;
            width: 600px;
            height: 750px;

            border: 5px solid gray;
            z-index: 2;
        }

        .login h1 {
            font-family: 'Galada', cursive;
            color: green;
            text-align: center;
        }

        .login h3 {
            margin: 10px 40px 0 50px;
        }

        .login input[type=text], input[type=password] {
            margin: 10px 0 0 28px;
            width: 505px;
            height: 30px;
            padding: 8px;
        }

        .login button, input[type=submit] {
            width: 88%;
            height: 50px;
            text-align: center;
            margin: 10px 0 0 28px
        }

    </style>
</head>
<body>
	<div class="container">
        <div class="login">
            <div id="title"><h1>WhereBook</h1></div><br>
            <form action="loginAction.jsp" method="post">
                <h3>아이디</h3><br>
                <input type="text" name="user_id" size="50" placeholder="아이디를 입력합니다."><br><br>
                <h3>패스워드</h3><br>
                <input type="password" name="user_password" size="80" placeholder="비밀번호를 입력합니다."><br><br><br><br><br>
                <input type="submit" value="로그인"><br><br>                
                <button type="button" onclick="location.href='join.jsp'">회원가입</button><br><br>
                <button type="button" onclick="location.href='main.jsp'">홈</button>
            </form>
        </div>
    </div>
</body>
</html>