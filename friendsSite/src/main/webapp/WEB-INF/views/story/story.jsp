<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/> <!-- 회원 정보 저장 -->
</sec:authorize>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
$(document).ready(function(){
	var fileTarget = $('.filebox .upload-hidden'); 
	fileTarget.on('change', function(){
	// 값이 변경되면 
	if(window.FileReader){ // modern browser 
		var filename = $(this)[0].files[0].name; 
	} else { // old IE 
		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	} // 추출한 파일명 삽입 
	$(this).siblings('.upload-name').val(filename); 
	}); 
});

</script>
<style>
	.filebox input[type="file"] {
		 position: absolute; 
		 width: 1px; 
		 height: 1px; 
		 padding: 0; 
		 margin: -1px; 
		 overflow: hidden; 
		 clip:rect(0,0,0,0);
		 border: 0; 
	 }.filebox label {
	  	display: inline-block;
	  	padding: .5em .75em; 
	  	color: #999; 
	  	font-size: inherit; 
	  	line-height: normal; 
	  	vertical-align: middle; 
	  	background-color: #3333FF; 
	  	cursor: pointer; 
	  	border: 1px solid #ebebeb; 
	  	border-bottom-color: #e2e2e2; 
	  	border-radius: .25em; 
	 } /* named upload */
	 .filebox .upload-name {
	 	display: inline-block; 
	 	padding: .5em .75em; /* label의 패딩값과 일치 */ 
	 	font-size: inherit; 
	 	font-family: inherit; 
	 	line-height: normal; 
	 	vertical-align: middle; 
	 	background-color: #f5f5f5; 
	 	border: 1px solid #ebebeb; 
	 	border-bottom-color: #e2e2e2; 
	 	border-radius: .25em; 
	 	-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
	 	-moz-appearance: none; appearance: none; 
	 }


</style>
<body>
	<div class="container">

	<form action="/friendsSite/storyWrite" method="post" enctype="multipart/form-data">
	
	<div class="filebox"> 
		 <input class="upload-name" value="사진선택" readonly>
		 <label for="ex_filename">업로드</label>
		 <input type="file" name="imageUrl" multiple="multiple" id="ex_filename" class="upload-hidden" required="required"/>
    </div>

		<input type="text" class="form-control" placeholder="사진설명" name="storyAccount" required="required" >
		<input class="btn btn-primary" type="submit" value="게시">
		<!-- <button id="btn-story-write" class="btn btn-primary">사진등록</button> -->
	</form>

	<hr/>
	<c:forEach var="story" items="${story.content }">
			<div class="card m-2">
				<div class="card-body">
					
					<img height="80%" width="100%" src="static/${story.imageUrl }">
					
					<c:set var="heart" value='1' />
					<c:forEach var="like" items="${story.storyLike }">
						<c:choose>
							<c:when test="${like.user.id eq principal.user.id}">
								<c:set var="heart" value='0' />
							</c:when>
						</c:choose>						
					</c:forEach>
					<p style="color:blue" class="font-italic">by ${story.user.userName }</p>
					<c:if test="${heart == 0 }">
					<form action="/friendsSite/storyUnLike" method="post">						
						<input type="image" width="3%" src="/friendsSite/image/red_heart.png"/ >
					</c:if>
					<c:if test="${heart == 1 }">
					<form action="/friendsSite/storyLike" method="post">						
						<input type="image" width="3%" src="/friendsSite/image/white_heart.png"/ >
					</c:if>					
					 +like : ${story.storyLikeCount }
					 <input type="hidden" name="id" value="${story.id }"/>
					</form>
					
					<h6 align="center">${story.storyAccount }</h6>
					</br>
					<c:forEach var="comment" items="${story.storyComment }">
					<form action="/friendsSite/storyCommentDelete" method="post">
					<hr/>
					<h6><font size="3">${comment.comment }</font></h6><font size="2"><h7 align="right" class="font-italic"> ${comment.user.userName } &nbsp; |  &nbsp;${comment.createDate }</font></h7>				
					
					<c:if test="${comment.user.id == principal.user.id || principal.user.userRole == 'ROLE_ADMIN'}">
					
						<input type="hidden" id="userId" value="${principal.user.id }"/>
						<input type="hidden" name="id" value="${comment.id }"/>
						<input type="submit" value="삭제">
					</form>
					</c:if>
					</c:forEach>
					<form action="/friendsSite/storyComment" method="post">
						<input type="hidden" name="storyId" value="${story.id }">
						<input type="hidden" name="storyUser" value="${story.user.id }">
						<input type="text" class="form-control" placeholder="댓글" name="storyComment" required="required" >
						<input class="btn btn-primary" type="submit" value="작성">
					</form>		
					
				</div>
			</div>
			
	</c:forEach>
	
	</div>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>