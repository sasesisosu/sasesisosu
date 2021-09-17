<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" class="form-control" placeholder="제목" id="title">
		</div>

		 <div class="form-group">
		  <label for="content">내용</label>
		  <textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div> 

	</form>
	<button id="btn-save" class="btn btn-primary">글쓰기 완료</button><a href="/friendsSite/boardForm" class="btn btn-secondary">돌아가기</a>
</div>

<script>
  $('.summernote').summernote({
    placeholder: '내용 입력',
    tabsize: 2,
    height: 300
  });
</script>
<script src="/friendsSite/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>