<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!-- 책 보기에 대한 기능은 구글링하여 참고하였음 -->
<head>
	<!-- 해당 상품의 제목을 가져옴 -->
	<c:forEach var="j" items="${list }">
			<title>${j.product_title }</title>
	</c:forEach>

	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700i" rel="stylesheet">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<script src="booklet/jquery.easing.1.3.js" type="text/javascript"></script>
	<script src="booklet/jquery.booklet.1.1.0.min.js" type="text/javascript"></script>
	<link href="booklet/jquery.booklet.1.1.0.css" type="text/css" rel="stylesheet" media="screen" />
	<style> 
		* {
			margin: 0;
			padding: 0;
		}
		body {
			background: rgba(200, 200, 200, 0.5);
			color: #444;
			font-size: 12px;
			color: #333;
			font-family: 'Oswald', sans-serif;
		}
        /*본문 글씨*/
		.book_wrapper p {
			font-size: 20px;
			margin: 5px 5px 5px 15px;
		}		
		#e-book-article {
			margin-top: 50px;
		}		
	</style>
</head>
<body>
	<br><br><br><br><br>
	<div class="book_wrapper">
		<c:forEach var="i" items="${list }">		
		<div id="mybook" style="display:none;">		
			<div class="b-load">
			<span style="font: italic bold 1.5em/1em Georgia, serif;">${i.product_title}<img src="images/${i.product_image}" width="100%" height="90%"></span>			
				<!-- 해당 상품(책)에 대한 본문, 내용을 가져옴 -->
				<c:set var="article" value="${article }" /> 				
				<% 
				    String article = (String)pageContext.getAttribute("article");
					String temp = "";			
					int len1 = article.length(); // 내용의 길이
					int len2 = 500;				 // 페이지당 보여줄 내용 길이
					for(int i=0;i<article.length();i++){
							temp += article.charAt(i);
							if(temp.length() == len2){ // 페이지당 500자 까지 보여줄 수 있도록 함
								%>
									<div id="e-book-article">
										<p>
											<%= temp %>
										</p>
									</div>
								<%				
							temp = "";}
					}
				%>	
				<div id="e-book-article">
					<p> <!-- 페이지당 500자 까지 보여주고 나머지 내용 즉, 마지막 페이지를 보여줌-->
						<%= temp %> 
					</p>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<p style="text-align:center;"><a href="myPage_cart_buy.buy">그만 보기</a></p>

	<script type="text/javascript">
		$(function () {
			var $mybook = $('#mybook');
			var $bttn_next = $('#next_page_button');
			var $bttn_prev = $('#prev_page_button');
			var $loading = $('#loading');
			var $mybook_images = $mybook.find('img');
			var cnt_images = $mybook_images.length;
			var loaded = 0;

			$mybook_images.each(function () {
				var $img = $(this);
				var source = $img.attr('src');
				$('<img/>').load(function () {
					++loaded;
					if (loaded == cnt_images) {
						$loading.hide();
						$bttn_next.show();
						$bttn_prev.show();
						$mybook.show().booklet({
							name: null,                            //  
							width: 800,                             //  
							height: 500,                             //   
							speed: 600,                             //  
							direction: 'LTR',                           //  
							//  
							next: $bttn_next,          			//  
							prev: $bttn_prev,          			//  

						});
						Cufon.refresh();
					}
				}).attr('src', source);
			});

		});
	</script>

</body>

</html>