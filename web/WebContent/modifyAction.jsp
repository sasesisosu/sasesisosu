<!-- 회원정보 수정 할 때 기능 -->
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
		int result = userDAO.modify((String)session.getAttribute("user_id"), request.getParameter("user_password"),
				request.getParameter("user_name"), request.getParameter("user_email"));
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터 베이스 오류')");
			script.println("history.back()");
			script.println("</script>");
		}
		else{
			if(request.getParameter("user_password").length() < 4 || request.getParameter("user_password").equals("")){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호는 4자리 수 이상이어야 합니다')");
				script.println("history.back()");
				script.println("</script>");
			}else if(request.getParameter("user_name").equals("")){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이름을 입력해 주세요')");
				script.println("history.back()");
				script.println("</script>");
			}else if(request.getParameter("user_email").equals("")){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이메일을 입력해 주세요')");
				script.println("history.back()");
				script.println("</script>");
			}else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원정보 수정 성공!')");
				script.println("history.back()");
				script.println("</script>");
			}	
		}
	%>
</body>
</html>