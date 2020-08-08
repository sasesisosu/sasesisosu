<!-- 로그인에 대한 기능 -->
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
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUser_id(), user.getUser_password());
		if(result == 1){
			PrintWriter script = response.getWriter();
			session.setAttribute("user_id", user.getUser_id());
			session.setAttribute("user_password", user.getUser_password());
			session.setAttribute("user_name", user.getUser_name());
			session.setAttribute("user_email", user.getUser_email());
			script.println("<script>");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}else if(result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀렸습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하는 아이디가 없습니다')");
			script.println("history.back()");
			script.println("</script>");	
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터 베이스 오류')");
			script.println("history.back()");
			script.println("</script>");
		}
		
	%>
</body>
</html>