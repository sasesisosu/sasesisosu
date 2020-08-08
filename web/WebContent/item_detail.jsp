<!-- 제품 상세보기 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="product.productDAO.reviewDAO" %>
<%@ page import="buy.buyDAO.BuyDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
      #wrapper {   
            display: grid;
            
            grid-template-columns: repeat(4, 1fr);
            grid-template-rows: minmax(100px, auto);
            
        }
        
        button {
           background-color: rgba(0, 128, 0, 0.5);
           border: 3px solid green;
           color: white;
           
           font-weight: bold;
        }
        
        button:hover {
           background-color: green;
        }
        
      .box {
            padding: 15px;
            /* color: white; */
            font-weight: bold;
            text-align: center;
            /* border: 1px solid black; */
            
         }
      
        .box1 {
            grid-column: 1/3;
            
            height: 400px;
            
            margin-top: 20px;
        }
     
        .box2 {
            grid-column: 3/5;
            
            height: 400px;
            margin-top: 20px;
            
            border: 3px solid green;
        }
        .box3 {
            grid-column: 1/5;
            
            height: 250px;
            /* border: 3px solid green; */
            
            margin: 30px 0;
        }
        .box4 {
            grid-column: 1/2;
            
            height: 200px;
            border: 3px solid green;
        }
     
        .box5 {
            grid-column: 2/3;
            
            height: 200px;
            
            border: 3px solid green;
            border-right: 2px solid green;
            margin-left: 50px;
        } 
        .box6 {
            grid-column: 3/5;
            
            height: 200px;
            border-top: 3px solid green;
            border-bottom: 3px solid green;
            border-right: 3px solid green;
        }
        
        .box7 {
            grid-column: 1/2;
            
            height: 80px;
            margin: 0;
            
            border-right: 2px solid green;
            
            margin: 60px 0;
        }
        
        .box8 {
            grid-column: 2/5;
            
            height: 80px;
            
            margin: 60px 0;
        }
        
        .box9 {
            grid-column: 1/5;
            
            height: 1200px;
            border: 1px solid black;
            
            margin: 30px 0;
        }
        
        .box img {
           width: 250px;
           height: 300px;
           
           margin-top: 50px;
        }
        
        #item_category, #item_title {
           margin: 30px 0;
        }
        
        #item_title h1 {
           font-size: 3.0em;
        }
        
        #item_category span {
           font-size: 0.8em;
        }
        
        #item_category a:hover {
         text-decoration: underline;
      }
      
      #item_writer span, #item_publisher span {
         font-size: 0.6em;
      }
        
        #box_title {
           text-align: left;
           
           font-size: 1.8em;
           margin: 10px 50px 15px 50px;
        }
        
        #item_story {
           margin: 40px 5px;
        }
        
        #ticket_info {
           margin-top: 55px;
        }
        
        #ticket_info button {
           padding: 15px;
        }
        
        .box5 h1 {
           margin-top: 65px;
        }
        
        .box6 {
           text-align: right;
        }
        
        .box6 span {
           height: 30px;
        }
        
        .box6 span:nth-child(1) {
         border-right: 1px solid green;
         padding-right: 20px;
         
         font-size: 1.8em;
      }
      
      .box6 span:nth-child(2) {
         padding-left: 50px;
         
         font-size: 1.2em;
      }
      
      #price_info {
         margin-top: 20px;
      }
      
      #buying_btn {
         margin-top: 40px;
      }
      
      #buying_btn button {
         width: 150px; height: 60px;
      }
      
      #buying_btn button:last-child {
         margin-left: 50px;
      }
      
      .box7 h3 {
         padding-top: 10px;
      }
        
        #input_review {
           /* margin-top: 8px; */
           position: relative;
        }
        
        #input_review textarea {
        
           /* position:absolute;
           
           left: 0;
           top: 0;
            */
            
           margin-right: 50px;
            
           width: 600px;
           height: 55px;
           
           resize: none; 
           
        }
        
        #input_review button {
        
           /* position: absolute;
           
           right: 20px;
           top: 0; */
           
           width: 100px;
           height: 90px;
           
        }
        
        #radio {
           margin-right: 620px;
        }
        
        #box9_wrap {
           width: 100%;
           
           display: grid;
           grid-template-columns: repeat(4, 1fr);
            grid-template-rows: minmax(100px, auto);
  
        }
        
        #review_box1 {
           grid-column: 1/2;
           height: 150px;
           
           margin: 30px 0 30px 30px;
           border-bottom: 3px solid gray;
           position: relative;
        }
        
        #review_box2 {
           grid-column: 2/5;
           height: 150px;
           
           margin: 30px 30px 30px 0;
           border-bottom: 3px solid gray;
           
           position: relative;
        }
        
        #review_box1 img {
           width: 80px; height: 60px;
           margin: 0; padding 0;
           
           position: absolute;
           top: 0;
           left: 50px;
        }
        
        #review_box1 h4 {
           position: absolute;
           bottom: 30px;
           left: 71px;
           
        }
        
        #review_box1 h5 {
           position: absolute;
           bottom: 5px;
           left: 26px;
           
        }
        
        #review_box2_content {
           position: absolute;
           left: 30px;
           top: 50px;
           
        }
        
        
        
</style>
</head>
<body>
   <%
 	  session.setAttribute("product_title", request.getParameter("product_title"));
      String user_id = (String)session.getAttribute("user_id");
      
 	  reviewDAO rDao = new reviewDAO();
 	  BuyDAO buyDAO = new BuyDAO();
      
      int reviewPageNumber = 1; // 해당 상품에 대한 리뷰 페이지
   	  // 리뷰 페이지당 5개의 게시글이 등록될 수 있게 함
      int result= rDao.getBoardCount(request.getParameter("product_title")); // getBoardCount는 리뷰에 대한 게시글 개수
      if(result<=5) {
         reviewPageNumber=1;
      }else if(result%5==0){
         reviewPageNumber=result/5;
      }else {
         reviewPageNumber=result/5+1;
      }
      
   %>
   <jsp:include page="header.jsp"></jsp:include>
   <c:forEach var="i" items="${list }">
   <div id="wrapper">
        <div class="box box1">
           <img alt="표지" src="images/${i.product_image }"><br /><br />
           <a href="bookPreview.book?product_title=${i.product_title}">미리보기</a>
        </div>        	
        <div class="box box2">
           <div id="item_category">
              <a href="novel.book">${i.product_genre }</a> > <span>${i.product_category }</span>            
           </div>
           <div id="item_title">
              <h1> ${i.product_title }</h1>
           </div>
           <br>
           <div id="item_writer">
              <h2>${i.product_writer }<span>저</span></h2>
           </div>
           <div id="item_publisher">
              <h2>${i.product_publisher }<span>출판</span></h2>
           </div>
        </div>
        <div class="box box3">
           <div id="box_title">
              책 소개 
           </div>
           <hr>
           <div id="item_story">
              ${i.product_content }
			
           </div>
        </div>
        <div class="box box4">
           <div id="ticket_info">
              <h3>이용권이 없으신가요?</h3><br>
              <button onclick="location.href='buy_ticket.jsp'">구매하러가기</button>
           </div>
        </div>
        <div class="box box5">
           <h1>구매</h1>
        </div>
        <div class="box box6">
           <div id="price_info">
              <span>판매가</span><span>${i.product_price }</span>원
           </div>
           <br><br>
           <div id="buying_btn">              
              <c:set var="product_title" value="${i.product_title }" /> 
				<% 
					int ticketCount = buyDAO.ticketCount(user_id, 2); // 사용자가 이용권이 있는지 확인하기 위함				
				    String product_title = (String)pageContext.getAttribute( "product_title" ); 
					
					if(buyDAO.buyProductCheck(product_title, user_id, 1) == 1){ %> <!-- 해당 상품을 구매하였으면 나오는 문구 --> 
						이미 구매한 상품입니다	
				<% }else if(ticketCount == 0){ // 만약 사용할 수 있는 이용권이 0인 경우
					if(buyDAO.buyProductCheck(product_title, user_id, 3) == 1){%> <!-- 해당 상품을 찜하였으면 나오는 문구 -->
				 		<h3>이미 찜 한 상품입니다.</h3>
				  <%}else{ %> <!-- 해당 상품을 찜을 하지 않았을 때 나오는 문구 -->
				 		<h3 style="text-align:right"><a href="product_pick.buy?product_title=${i.product_title}&product_price=${i.product_price}&product_image=${i.product_image}&product_writer=${i.product_writer}">찜 하기</a></h3>
				  <%} %>
				  <!-- 사용할 수 있는 이용권이 없을 때 나오는 문구 -->
				  		<a href="buy_ticket.jsp">이용권이 없습니다</a> |
				  		<a href="buy_item.buy?product_title=${i.product_title}&product_price=${i.product_price}&product_image=${i.product_image}&product_writer=${i.product_writer}&product_content=${i.product_content}">정가구매</a>
			  <%}else{ // 사용 할 수 있는 이용권이 있을 경구
			  		if(buyDAO.buyProductCheck(product_title, user_id, 3) == 1){%> <!-- 해당 상품을 찜하였으면 나오는 문구 -->
				 		<h3>이미 찜 한 상품입니다.</h3>				  
				  <%}else{ %> <!-- 해당 상품을 찜을 하지 않았을 때 나오는 문구 -->
				 		<h3 style="text-align:right"><a href="product_pick.buy?product_title=${i.product_title}&product_price=${i.product_price}&product_image=${i.product_image}&product_writer=${i.product_writer}">찜 하기</a></h3>
				  <%} %>
				  <!-- 해당 상품을 구매, 이용권으로 대여 하지 않았을 때 경우  --> 
				  <a onclick="if(!confirm('구매 하시겠습니까?')){return false;}" href="buy_ticket_product.buy?product_title=${i.product_title}&product_price=${i.product_price}&product_image=${i.product_image}&product_writer=${i.product_writer}&product_content=${i.product_content}">이용권 구매</a> |
	              <a href="buy_item.buy?product_title=${i.product_title}&product_price=${i.product_price}&product_image=${i.product_image}&product_writer=${i.product_writer}&product_content=${i.product_content}">정가구매</a>
              <% } %>
           </div>
        </div>
        
        <div class="box box7">
           <h3>리뷰</h3>
        </div>
        <div class="box box8">
           <div id="input_review">
              <form action="review_write.book" method="post">
                 <%
                    if(user_id == null) { // 리뷰 작성 시 비로그인 상태
                       
                 %>
                       <textarea name="review_content" placeholder="로그인이 필요한 서비스입니다."></textarea>
                       <button type="button" onclick="location.href='login.jsp'"><span>등록</span></button>
                 <%
                    } else { // 로그인 상태
                 %>
                       <textarea name="review_content" placeholder="무분별한 작성 및 비방 스포일러성 리뷰는 삭제될 수 있습니다."></textarea>
                       <input type="hidden" name="product_title" value="${i.product_title}">
                       <input type="submit" value="등록">
                 <%
                    }
                 %>                 
                 <div id="radio">    
                  <input type="radio" name="radio_state" value="like.PNG" checked="checked">좋아요
                  <input type="radio" name="radio_state" value="hate.PNG">싫어요
                 </div>
              </form>
           </div>
        </div>        
        <% 
           	int likeCount = rDao.getLikeHateCount(product_title, "like.PNG");
           	int hateCount = rDao.getLikeHateCount(product_title, "hate.PNG");
           	if(likeCount != 0 || hateCount != 0){
        %>
        	<!-- 해당 상품에 대한 좋아요, 싫어요의 개수를 보여줌 -->
           	<b style="text-align:center">좋아요:<%= likeCount %> 싫어요 :<%= hateCount %></b>
        <% }else{%>
           	해당상품의 등록된 리뷰가 없습니다.
        <%} %>
        <div class="box box9">
           <div id="box9_wrap">           
             <c:forEach var="j" items="${ reviewList }">                 
                 <div id="review_box1">
                    <img src="images/${ j.pick_image }">
                    <c:set var="review_writer" value="${ j.review_writer }" />
                    <h4><c:out value="${ review_writer }" /></h4>
                    <h5>${ j.review_date }</h5>
                 </div>
                 <div id="review_box2">
                    <div id="review_box2_content">
                       ${ j.review_content }
                    </div>
                    <%
                       /* review_writer를 가져와 변수에 담음 */
                       String writer = (String)pageContext.getAttribute("review_writer");
                       if(user_id != null){
                          if((writer.equals(user_id)) || (user_id.equals("admin"))) {
                    %>                    
                          <div id="review_box2_delete">
                             <form action="review_delete.book" method="post">
                                <input type="text" name ="review_index" value="${ j.review_index }" hidden="true">
                                <input type="text" name ="product_title" value="${ j.product_title}" hidden="true">
                                <button type="submit">삭제</button>
                             </form>
                          </div>   
                  <%
                          }
                       }                       
                  %>
                 </div>
              </c:forEach>
              <br><br>
              <div style="text-align:center; grid-column: 1/5;">
              	<c:forEach var="j" begin="1" end="<%= reviewPageNumber %>">                              		
                 	<a href="bookContent.book?product_title=${i.product_title}&reviewPageNumber=${j}">${j }</a>
              	</c:forEach>
            </div>
           </div>
        </div>
    </div>
   </c:forEach>
   <jsp:include page="footer.jsp"></jsp:include>
   
</body>
</html>