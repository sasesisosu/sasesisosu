<!-- 마이페이지 - 파트너 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="partner.partnerDAO.PartnerDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html, body {
	width: 100%; height: 100%;
}
#state_partner a, input[type=submit] {
   background-color: rgba(0, 128, 0, 0.5);
   border: 3px solid green;
   color: white;
   
   font-weight: bold;
}
        
#state_partner a:hover, input[type=submit]:hover {
   background-color: green;
}
#content{
	background-image: url('images/partner.jpg');
	background-size: 100%;
	color: white;
	opacity: 0.8;
}
.button {
   background-color: rgba(0, 128, 0, 0.5);
   border: 3px solid green;
   color: white;
   
   font-weight: bold;
}        
.button:hover {
   background-color: green;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>	
	<!-- 내 책장 -->
    <div id="wrap">
        <jsp:include page="sideNav.jsp"></jsp:include>
        <div id="content">        	
        	<div id="myPage_title">
                <h1>파트너</h1>                      
    		<%	    		
    			String user_id = (String)session.getAttribute("user_id"); // 접속해있는 아이디
	    		PartnerDAO partnerDAO = new PartnerDAO();
	    		
	    		partnerDAO.partnerExpire(1); // 파트너 신청 받은 후 10분 뒤 자동 취소, 숫자 1은 파트너 대기 상태
	    		String responseID = partnerDAO.responseID(user_id, 1); // 파트너 신청한 아이디
	    		String requestID = partnerDAO.requestID(user_id, 1); // 파트너 신청 받은 아이디	    		
	    		
	    		String remainTime = partnerDAO.requestTime(user_id, 1); // 파트너 신청 받은 후 남은 시간
	    		
	    		String responseID2 = partnerDAO.responseID(user_id, 2); // 파트너가 되었을때 신청한 아이디
	    		String requestID2 = partnerDAO.requestID(user_id, 2); // 파트너가 되었을때 신청 받은 아이디
	    		
	    		if(responseID2 != null){%> <!-- 파트너가 되었을 때 responseID2가 존재하면 접속한 아이디가 신청한 아이디임 -->
	    		<b><%=responseID2 %></b>님과 파트너가 되었습니다.&nbsp;ㅡ&nbsp;<a class="button" href="deny.part">파트너 취소</a>
	    		
	    		<%}else if(requestID2 != null){ %> <!-- 파트너가 되었을 때 requestID2가 존재하면 접속한 아이디가 신청받은 아이디임 -->
	    		<b><%= requestID2 %></b>님과 파트너가 되었습니다.&nbsp;ㅡ&nbsp;<a class="button" href="cancel.part">파트너 취소</a>
	    		
	    	<% }else if(responseID == null && requestID != null){%>
	    		<b><%= requestID%></b>님에게 파트너 신청 중 입니다&nbsp;ㅡ&nbsp;<a class="button" href="cancel.part">취소</a>
	    		
	    	<%}else if(responseID != null && requestID == null){ %>
	    		<b><%= responseID%></b>님이 파트너 신청을 하였습니다 (<%= remainTime %> 에 자동 취소 됩니다)&nbsp;ㅡ&nbsp;
	    		<a class="button" href="accept.part?response_id=<%= responseID%>">수락</a>&nbsp;|&nbsp;<a class="button" href="deny.part">거절</a>
	    	
	    	<%}else{ %>
	    		책을 공유하고 싶은 상대에게 파트너를 신청 하세요<br>
        		<form action="response.part" method="post">
        			<input type="text" name="request_id" id="request_id" placeholder="아이디를 입력하세요." style="width: 200px;">
        			<input type="submit" value="신청하기">
        		</form>
        	<%} %>       
        	<br /><br /><br /><br />
        	<br /><br /><br /><br />
        		<div style="font-size: 20px;">
	        		&nbsp;파트너와 책을 공유하세요!<br />	<br />
	        		&nbsp;- 구매한 책을 서로 공유할 수 있습니다<br /><br />
	        		&nbsp;- 이용권으로 대여한 책은 공유 할 수 없습니다<br /><br />
	        		&nbsp;- 파트너 신청 후 10분이 지나면 자동 취소 됩니다
        		</div>   
        	</div>  	       	
        </div>
    </div>	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>