<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/> <!-- 회원 정보 저장 -->
</sec:authorize>
 
<!DOCTYPE html>
<html lang="en">
<head>
<title>262홈페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>

<script>
function test(){
	console.log('zz');
	alert('바보');
	alert('바보ㅋ');
	alert('바보ㅋㅋ');
	location.href="/friendsSite/";
}
</script>
<style>
	nav#header{
		position:sticky;
		top:0;
		z-index:1;
	}
</style>
<body>
	<nav id="header" class="navbar navbar-expand-md bg-dark navbar-dark">
		
		
		<c:choose>
			<c:when test='${empty principal }'>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/friendsSite/loginForm">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/friendsSite/joinForm">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
				<a class="navbar-brand" href="/friendsSite/index">홈</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<li class="nav-item"><a class="nav-link" href="/friendsSite/story">Photogram</a></li>
					<li class="nav-item"><a class="nav-link" href="/friendsSite/boardForm">우리들의 이야기</a></li>
					<li class="nav-item"><a class="nav-link" href="/friendsSite/boardBest">인기 이야기</a></li>
					<li class="nav-item"><a class="nav-link" href="/friendsSite/updateForm">회원정보</a></li>
					<li class="nav-item"><a class="nav-link" href="/friendsSite/logout">로그아웃</a></li>
				</ul>
					<li class="nav-item"><button type="button" id="btn" onclick="test();" class="btn btn-default btn-xs">클릭금지</button></li>
			</c:otherwise>
		</c:choose>
			
		</div>
	</nav>
	<br />