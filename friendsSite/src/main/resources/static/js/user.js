let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});

		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
		$("#btn-event").on("click", ()=>{ 
			this.event();
		});
	},
	
	save:function(){
		let data = {
			userId:$("#userId").val(),
			userPwd:$("#userPwd").val(),
			userName:$("#userName").val(),
			userIntro:$("#userIntro").val(),
			userRelation:$("#userRelation").val()
		};
		if(!data.userId || data.userId.length > 20){
			alert('아이디 20자 이하로 적어주셈');
			location.href="/friendsSite/joinForm";
			return;
		}else if(!data.userPwd){
			alert('비밀번호를 입력하세요');
			location.href="/friendsSite/joinForm";
			return;
		}else if(!data.userName || data.userName.length > 10){
			alert('이름 10자 이하로 적어주세요');
			location.href="/friendsSite/joinForm";
			return;
		}else if(data.userIntro.length > 100){
			alert('소개를 100자 이하로 적어주세요')
			location.href="/friendsSite/joinForm";
			return;
		}else if(!data.userRelation || data.userRelation.length > 10){
			alert('관계를 10자 이하로 적어주세요')
			location.href="/friendsSite/joinForm";
			return;
		}
		console.log(data);

		$.ajax({
			type:"POST",
			url:"/friendsSite/join",
			data:JSON.stringify(data), 
			contentType:"application/json;charset=utf-8", 
			dataType:"json" 
		}).done(res=>{
			alert('회원가입 완료');
			location.href="/friendsSite/";	
		}).fail(error=>{
			alert('이미 존재하는 아이디입니다');
		});
		
	},

	update:function(){
		let data = {
			id:$("#id").val(),
			userId:$("#userId").val(),
			userPwd:$("#userPwd").val(),
			userName:$("#userName").val(),
			userIntro:$("#userIntro").val(),
			userRelation:$("#userRelation").val()
		};
		if(!data.userPwd){
			alert('비밀번호를 입력하세요');
			location.href="/friendsSite/updateForm";
			return;
		}else if(!data.userName || data.userName.length > 10){
			alert('이름 10자 이하로 적어주세요');
			location.href="/friendsSite/updateForm";
			return;
		}else if(data.userIntro.length > 100){
			alert('소개를 100자 이하로 적어주세요')
			location.href="/friendsSite/updateForm";
			return;
		}else if(!data.userRelation || data.userRelation.length > 10){
			alert('관계를 10자 이하로 적어주세요')
			location.href="/friendsSite/updateForm";
			return;
		}
		
		

		$.ajax({
			type:"PUT",
			url:"/friendsSite/update",
			data:JSON.stringify(data), 
			contentType:"application/json;charset=utf-8",
			dataType:"json" 
		}).done(res=>{
			alert('회원수정 완료');
			location.href="/friendsSite/";	
		}).fail(error=>{
			alert('으악');
		});
		
	},
	
}

index.init();