<!-- 회원 탈퇴에 대한 기능 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%				
		UserDAO userDAO = new UserDAO();
		int result = userDAO.withdraw((String)session.getAttribute("user_id"), request.getParameter("user_password"),
									 	request.getParameter("user_password2"));
		if(result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원 탈퇴 성공')");
			script.println("location.href='login.jsp'");
			script.println("</script>");
		}else if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 같지 않습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == 2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다')");
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