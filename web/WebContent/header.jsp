<!-- main을 제외한 나머지 페이지에서 보일 헤더 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Galada&family=Nanum+Myeongjo:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
</head>
<body>
	<div id="head">
            <%
	            String prev_path = request.getServletPath().substring(1);
	    		session.setAttribute("prev_path", prev_path);
            
	            String user_id = null;
	    		if(session.getAttribute("user_id") != null){
	    			user_id = (String) session.getAttribute("user_id");
	    		}	
	    		UserDAO userDAO = new UserDAO();
	    		userDAO.info(user_id);
	    		session.setAttribute("user_name", userDAO.user.getUser_name());
            %>	
            	<div id="header">
	            	<!-- 이미지랑 이름 -->
	            	<a href="main.jsp">
	                <img src="images/sign1.png" alt="상표">
	           		 </a>
	            	<a href="main.jsp">WhereBook</a>
	            	<% if(user_id!=null){ %>
		            <b style="float:right"><%= session.getAttribute("user_name") %>님 접속중</b>
		            <% } %>
        		</div>
        		<div id="fix-nav">
           			 <!-- 네비바 - 스크롤되도 고정 -->
           			 <ul class="fix-nav">
		                <li><a href="main.jsp">home</a></li>
		                <li><a href="novel.book">소설</a></li>
		                <li><a href="fantasy.book">판타지</a></li>
		                <li><a href="romance.book">로맨스</a></li>
           			 </ul>
					<%
						if(user_id==null){
					%>
						<ul>
						    <li><a href="join.jsp">회원가입</a></li>
						    <li><a href="login.jsp">로그인</a></li>
						</ul>
					<% }else{ %>
						<ul>
							<li><a href="logout.jsp">로그아웃</a></li>
						    <li><a href="myPage_modify.jsp">마이페이지</a></li>						    
						</ul>
					<% } %>            		
    </div>
    <!-- 스크롤바 고정하기 위한 기능, 구글링하여 참고하였음 -->
    <script src="js/jquery-2.2.4.min.js"></script>
    <script>
        $(function(){
                $(window).scroll(function(){  //스크롤하면 아래 코드 실행
                    var num = $(this).scrollTop();  // 스크롤값
                    if( num > 120 ){  // 스크롤을 36이상 했을 때
                        $("#fix-nav").css("position","fixed");
                        $("#fix-nav").css("top","0");
                        $("#fix-nav").css("width","100%");
                        $("#fix-nav").css("line-height","3.3");
                        $("#fix-nav").css("min-width","1080px");
                    }else{
                        $("#fix-nav").css("position","relative");
                        $("#fix-nav").css("line-height","50px");
                    }
                });
            });
    </script>
</body>
</html>