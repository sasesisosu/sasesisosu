<!-- 마이페이지 - 회원정보수정 
	아이디는 변경 불가, 이름, 이메일은 회원 가입 시 입력했던 값으로 가져옴-->

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

   <%
      String user_name = (String)session.getAttribute("user_name");
   
      if(user_name == null) {
   %>
         <script type="text/javascript">
            alert("로그인 페이지로 이동합니다.");
            location.href = "login.jsp";
         </script>
   <%
      } else {   
      UserDAO userDAO = new UserDAO();
      String user_id = (String)session.getAttribute("user_id");
   
      userDAO.info(user_id);
   
      session.setAttribute("user_name", userDAO.user.getUser_name());
      session.setAttribute("user_email", userDAO.user.getUser_email());
   %>
       <div id="wrap">
          <jsp:include page="sideNav.jsp"></jsp:include>
           <div id="content">
              <div id="content-wrap">
                 <div id="myPage_title">
                      <h1>정보수정</h1>
                  </div>
                  <div id="myPage_subTitle">
                     <h3>회원정보를 수정하는 공간입니다.</h3>
                  </div>
                 
                 <div id="modify_form">
                    <hr>
                    <div id="modify_id">
                       <h1>아이디</h1>
                       <h3><%= user_id %></h3>
                    </div>
                    <form action="modifyAction.jsp" method="post">
                    <hr>
                    <div id="modify_name">
                       <h1>이름</h1>
                       <input type="text" size="15" name="user_name" value='<%= session.getAttribute("user_name") %>'>
                    </div>
                    <hr>                 
                       <div id="modify_pw">
                          <h1>비밀번호</h1>
                          <input type="password" size="40" name="user_password" id="user_password" onkeyup="pwCheckFunction();" placeholder="비밀번호를 입력하시오.">
                       </div>
                       <hr>
                       <div id="modify_pw_ok">
                          <h1>비밀번호 확인</h1>
                          <input type="password" size="40" name="user_password2" id="user_password2" onkeyup="pwCheckFunction();" placeholder="비밀번호를 확인">
                       </div>
                       <br><hr>
                       <div id="modify_email">
                          <h1>이메일</h1>
                          <input type="email" size="30" name="user_email" value='<%= session.getAttribute("user_email") %>'>
                       </div>
                       <hr>
                       <div><h5 style="margin-left: 28px;" id="pwCheckMessage"></h5></div>
                       <div id="modify_btn">
                          <button type="submit">정보수정</button>
                          <button type="reset">취소</button>
                       </div>
                    </form>
                 </div>
              </div>
           </div>
       </div>
      
      <jsp:include page="footer.jsp"></jsp:include>
   <%
      }
   %>
   
   <script>
      function pwCheckFunction(){ // 비밀번호 확인
         var userPW1 = $('#user_password').val();
         var userPW2 = $('#user_password2').val();
         if(userPW1.length < 4) $('#pwCheckMessage').html('비밀번호는 4자리 수 이상이어야 합니다').css('color', 'red');
         else if(userPW1 == "" || userPW2 == "") $('#pwCheckMessage').html('');
         else if(userPW1 != userPW2) $('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다').css('color', 'red');
         else $('#pwCheckMessage').html('비밀번호가 서로 일치합니다').css('color','green');
      }
   </script>
</body>
</html>