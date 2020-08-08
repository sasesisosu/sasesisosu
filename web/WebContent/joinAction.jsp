<!-- 회원가입시 MVC1패턴 이용 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="user_id" />
<jsp:setProperty name="user" property="user_password" />
<jsp:setProperty name="user" property="user_name" />
<jsp:setProperty name="user" property="user_email" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(user.getUser_id()==null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디를 입력해 주세요')");
			script.println("history.back()");
			script.println("</script>");
		}else if(user.getUser_password()==null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호를 입력해 주세요')");
			script.println("history.back()");
			script.println("</script>");
		}else if(user.getUser_password().length()<4){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호는 4자리수 이상이어야 합니다')");
			script.println("history.back()");
			script.println("</script>");
		}else if(user.getUser_name()==null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이름를 입력해 주세요')");
			script.println("history.back()");
			script.println("</script>");
		}else if(user.getUser_email()==null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일을 입력해 주세요')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디 입니다.')");
				script.println("history.back()");
				script.println("</script>");
			}else{
				PrintWriter script = response.getWriter();	
				script.println("<script>");
				script.println("alert('가입 성공!')");
				script.println("location.href='main.jsp'");
				script.println("</script>");
			}
		}
	%>
</body>
</html>