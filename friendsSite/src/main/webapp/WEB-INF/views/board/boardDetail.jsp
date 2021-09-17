<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
		<a href="/friendsSite/boardForm" class="btn btn-secondary">돌아가기</a>
		<c:if test="${board.user.id == principal.user.id || principal.user.userRole == 'ROLE_ADMIN'}">
			<a href="/friendsSite/boardUpdate/${board.id}" class="btn btn-warning">수정</a>
			<input type="hidden" id="id" value="${board.id }" />
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>	
		<br/><br/>
		<div>
			작성자 : <span><i><a href="/friendsSite/userInfo/${board.user.id }/${board.id}">${board.user.userName }</a></i></span>
			<br/> 
			조회수 : <span><i>${board.count }</i></span>
		</div>
		<br/>
		<div>
			<h3>${board.title }</h3>
		</div>
		<hr />
		 <div>
		  <div>${board.content } </div>
		</div> 
		<hr />
		<div class="card">
		<form>
			<input type="hidden" id="boardId" value="${board.id }"/>	
			<input type="hidden" id="userId" value="${principal.user.id }"/>			
			<div class="card-body">
				<textarea id="content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
		
		</div>
		<div class="card">
			<div class="card-header">
			
			<c:set var="Best" value="${replyBest.id}"/>
			<c:if test="${not empty Best}">
			<h5 class="btn btn-warning badge">BEST 댓글</h5>
				${replyBest.content }
				<div class="d-flex">					
					<h5 class="btn btn-primary badge">추천:${replyBest.likes }</h5>
					<h5 class="btn btn-danger badge">비추천:${replyBest.hate }</h5>
					<c:set var="replyDate" value="${replyBest.createDate}"/>
					&nbsp;>
					<div class="font-italic"><a href="/friendsSite/userInfo/${replyBest.user.id }/${board.id}">${replyBest.user.userName} &nbsp;</a></div>
					<div class="font-italic">| &nbsp;${fn:substring(replyDate,0,16) } &nbsp;</div>				
				</div>
			</c:if>
				
			</div>
			
			
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.reply }">
					<li id="reply-${reply.id }" class="list-group-item">
						${reply.content }<br/><br/>
						<div class="d-flex">	
							<a href="/friendsSite/replyLike/${reply.id}/${board.id }/${principal.user.id }/1" class="btn btn-primary badge" >추천:${reply.likes }</a>
							<a href="/friendsSite/replyLike/${reply.id}/${board.id }/${principal.user.id }/0" class="btn btn-danger badge">비추천:${reply.hate }</a>
							&nbsp;>
							<c:set var="replyDate" value="${reply.createDate}"/>
							<div class="font-italic"><a href="/friendsSite/userInfo/${reply.user.id }/${board.id}">${reply.user.userName} &nbsp;</a></div>
							<div class="font-italic">| &nbsp;${fn:substring(replyDate,0,16) } &nbsp;</div>
							<c:if test="${reply.user.id == principal.user.id || principal.user.userRole == 'ROLE_ADMIN'}">
								<input type="hidden" id="userId" value="${principal.user.id }"/>
								<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
							</c:if>
							
						</div>
					</li>
				</c:forEach>
				
			</ul>
		</div>
		
</div>

<script src="/friendsSite/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>