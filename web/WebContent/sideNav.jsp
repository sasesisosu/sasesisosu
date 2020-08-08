<!-- 사이드바 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="side_nav">
           <ul>
               <br>
               <li>내 정보</li>
               <li><a href="myPage_modify.jsp">정보수정</a></li>
               <li><a href="myPage_partner.jsp">파트너</a></li>
               <li><a href="myPage_leave.jsp">회원탈퇴</a></li>
               <br><br>
               <li>내 책장</li>
               <li><a href="myPage_cart_buy.buy">구매 목록</a></li>
               <li><a href="myPage_cart_ticket.buy">이용권 목록</a></li>
               <li><a href="myPage_cart_pick.buy">찜 목록</a></li>
               <li><a href="myPage_cart_partner.part">파트너 책장</a></li>
               <br><br>
               <li>소식</li>
               <li><a href="noticeView.do">공지 & 이벤트</a></li>
               <li><a href="inquiryView.do">1:1 문의</a></li>               
           </ul>
       </div>
       <%
			  String user_id = (String)session.getAttribute("user_id");
              if(user_id.equals("admin")){ %>
              	<br>
              	<div id="side_nav_admin">
              		<li>관리자</li>
              		<li><a href="upload.jsp">상품 등록</a></li>
              	</div>                  	
       <%} %>