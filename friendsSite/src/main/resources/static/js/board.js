let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{ 
			this.save();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{
			this.update();
		});
		$("#btn-reply-save").on("click", ()=>{
			this.replySave();
		});
		$("#btn-reply-delete").on("click", ()=>{
			this.replyDelete();
		});
		
	},
	
	save:function(){
		let data = {
			title:$("#title").val(),
			content:$("#content").val()
		};
		if(!data.title){
			alert('제목을 입력하세여!');
			location.href="/friendsSite/boardWrite";
			return;
		}else if(!data.content){
			alert('내용을 입력하세야');
			location.href="/friendsSite/boardWrite";
			return;
		}
		console.log(data);

		$.ajax({
			type:"POST",
			url:"/friendsSite/boardWrite",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8", 
			dataType:"json"
		}).done(res=>{
			alert('글쓰기 완료');
			location.href="/friendsSite/boardForm";	
		}).fail(error=>{
			alert('으악');
		});
		
	},
	
	
	deleteById:function(){
		if(confirm("정말 삭제하시겠습니까?") == true){
	        let id = $("#id").val();

			$.ajax({
				type:"DELETE",
				url:"/friendsSite/boardDelete/"+id,
				dataType:"json"
			}).done(res=>{
				alert('글삭제 완료');
				location.href="/friendsSite/boardForm";	
			}).fail(error=>{
				alert('댓글있는 게시글은 삭제 불가능');
				location.href="/friendsSite/boardForm";
			});
	    }else{
	        return ;
	    }
	
	},
	
	
	update:function(){
		let id = $("#id").val();
		let data = {
			title:$("#title").val(),
			content:$("#content").val(),
		};		
		console.log(id);
		console.log(data);
		$.ajax({
			type:"PUT",
			url:"/friendsSite/boardUpdate/"+id,
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8", 
			dataType:"json"
		}).done(res=>{
			alert('글수정 완료');
			location.href="/friendsSite/boardForm";	
		}).fail(error=>{
			alert('으악');
		});
		
	},
	
	
	replySave:function(){
		let data = {
			boardId:$("#boardId").val(),
			userId:$("#userId").val(),
			content:$("#content").val()
		};
		
		console.log(data);
		$.ajax({
			type:"POST",
			url:`/friendsSite/boardReply/${data.boardId}`,
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8", 
			dataType:"json"
		}).done(res=>{
			alert('댓글 작성 완료');
			location.href=`/friendsSite/board/${data.boardId}/${data.userId}`;	
		}).fail(error=>{
			alert('으악');
		});
		
	},

	
	replyDelete:function(){

		let replyId = $("#replyId").val();
		let boardId = $("#boardId").val();
		let userId = $("#userId").val();

		$.ajax({
			type:"DELETE",
			url:"/friendsSite/replyDelete/"+replyId+"/"+boardId+"/"+userId,
			dataType:"json"
		}).done(res=>{
			alert('댓글 삭제 완료');
			location.href="/friendsSite/board/"+boardId+"/"+userId;
		}).fail(error=>{
			alert('으악');
		});
		
	},
	
	replyDelete:function(boardId, replyId){
		let userId = $("#userId").val(); 
		if(confirm("정말 삭제하시겠습니까?") == true){
			$.ajax({
				type:"DELETE",
				url:`/friendsSite/replyDelete/${boardId}/${replyId}`,
				dataType:"json"
			}).done(res=>{
				alert('댓글 삭제 완료');
				location.href=`/friendsSite/board/${boardId}/${userId}`;	
			}).fail(error=>{
				alert('으악');
			});
		}else{
	        return ;
	    }
	},
	
	
}


index.init();
