<!-- 홈(main) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<link href="https://fonts.googleapis.com/css2?family=Galada&family=Nanum+Myeongjo:wght@700&display=swap" rel="stylesheet">

<style>
   .onepage1 {
       background: url('./images/bg9.jpg') no-repeat bottom;
       background-size: cover;    
   }   
   .onepage3 {
       background: url('./images/novel_main.jpg') no-repeat center;
       background-size: cover;
   }
   .onepage4 {
       background: url('./images/fantasy_main.jpg') no-repeat center;
       background-size: cover;
   }
   .onepage5 {
       background: url('./images/romance_main.jpg') no-repeat center;
       background-size: cover;
   }   
   .onepage1 h1 {
      padding-left: 20px;
      font-size: 1.2em;
   }   
   .onepage1 p {
      margin-top: 100px;
      /* font-size: 0.9em */;
   }   
   #notice1 {
      background: url('./images/notice_ticket.png') no-repeat center;
       background-size: cover;
   }   
   #subtitle {
      font-family: 'Jua', sans-serif;
   }   
</style>
</head>
<body>   
   <%           
      String user_id = null;
      if(session.getAttribute("user_id") != null){
         user_id = (String) session.getAttribute("user_id");
      }
   
      UserDAO userDAO = new UserDAO();
      userDAO.info(user_id);
      session.setAttribute("user_name", userDAO.user.getUser_name());
   %>
   
   <!-- header -->
    <div id="head">
        <div id="header">
            <!-- 이미지랑 이름 -->
            <a href="main_index.html">
                <img src="images/sign1.png" alt="상표">
            </a>
            <a href="main.jsp">WhereBook</a>
        </div>
        <div id="fix-nav">
            <!-- 네비바 - 스크롤되도 고정 -->
            <ul class="fix-nav">
                <li><a href="#main">home</a></li>
                <li><a href="#event">notice</a></li>
                <li><a href="#book-kind1">소설</a></li>
                <li><a href="#book-kind2">판타지</a></li>
                <li><a href="#book-kind3">로맨스</a></li>
            </ul>
            <%
               if(user_id == null) {
            %>
               <ul>
                   <li><a href="join.jsp">회원가입</a></li>
                   <li><a href="login.jsp">로그인</a></li>
               </ul>
            <%
               } else {
            %>
                  <ul>
                      <li><a href="logout.jsp">로그아웃</a></li>
                      <li><a href="myPage_modify.jsp">마이페이지</a></li>
                  </ul>
            <%   
               }
            %>
        </div>
    </div>   
    <!-- onepage section -->
    <div id="onepage">
        <section id="main" class="onepage1">
            <div id="caption">
                <div id="title"><h1>WhereBook</h1></div>
                <div id="subtitle"><p>언제 어디서든 책을 읽어보세요</p></div>
            </div>
        </section>
        <section id="event" class="onepage2">
           <!-- 클래스명은 변경하면 안 됨 -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" id="notice1">
                       <div id="caption">
                         <div id="title"><h1 style="font-size: 1.0em;">책값이 부담이신가요?</h1></div>
                         <br><br><br>
                         <div id="subtitle"><p style="font-size: 0.9em;">대여해서 볼 수 있는 이용권을 이용해보세요</p></div>
                     </div>
                       <div id="more">
                         <a href="buy_ticket.jsp">상품보기</a>
                     </div>
                    </div>
                    <div class="swiper-slide"><img src="http://oldmidi.cdn3.cafe24.com/p/0312.jpg"></div>
                    <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0436.jpg"></div>
                    <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0542.jpg"></div>
                    <div class="swiper-slide"><img src="https://ktsmemo.cafe24.com/p/0112.jpg"></div>
                    <div class="swiper-slide" style="font-size:50pt;">- 끝 -</div>
                </div>

                <!-- 네비게이션 -->
                <div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
                <div class="swiper-button-prev"></div><!-- 이전 버튼 -->

                <!-- 페이징 -->
                <div class="swiper-pagination"></div>
            </div>
        </section>
        <section id="book-kind1" class="onepage3">
            <div id="caption">
                <div id="title"><h1>소설</h1></div>
                <div id="subtitle"><p>진한 감성에 빠지다</p></div>
            </div>
            <div id="more">
                <a href="novel.book">상품보기</a>
            </div>
        </section>
        <section id="book-kind2" class="onepage4">
            <div id="caption">
                <div id="title"><h1>판타지</h1></div>
                <div id="subtitle"><p>새로운 세상을 꿈꾸다</p></div>
            </div>
            <div id="more">
                <a href="fantasy.book">상품보기</a>
            </div>
        </section>
        <section id="book-kind3" class="onepage5">
            <div id="caption">
                <div id="title"><h1>로맨스</h1></div>
                <div id="subtitle"><p>달달한 러브 스토리를 그린다</p></div>
            </div>
            <div id="more">
                <a href="romance.book">상품보기</a>
            </div>
        </section>
    </div>

    <!-- footer -->
    <jsp:include page="footer.jsp"></jsp:include>
    <script src="js/jquery-2.2.4.min.js"></script>
    <script src="js/jquery.scrollTo.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
    <!-- <script src="js/bootstrap.min.js"></script> -->
   <script>
      $(function() {
         $('.fix-nav a').click(function(e) { // 링크를 클릭 했을때
            $.scrollTo(this.hash || 100, 1500); // 해시가 있는 위치(클릭한 링크의 해시값이 있는 섹션으로 스크롤)
            e.preventDefault(); // 링크의 원래 기능을 off
         });

          $(function(){
                   $(window).scroll(function(){  //스크롤하면 아래 코드 실행
                       var num = $(this).scrollTop();  // 스크롤값
                       if( num > 120 ){  // 스크롤을 36이상 했을 때
                           $("#fix-nav").css("position","fixed");
                           $("#fix-nav").css("top","0");
                           $("#fix-nav").css("width","100%");
                           $("#fix-nav").css("line-height","3.3");
                           $("#fix-nav").css("min-width","1080px");
                       }else{
                           $("#fix-nav").css("position","relative");
                           $("#fix-nav").css("line-height","50px");
                       }
                   });
               });
         
            new Swiper('.swiper-container', {
                slidesPerView : 1, // 동시에 보여줄 슬라이드 갯수
                spaceBetween : 0, // 슬라이드간 간격
                slidesPerGroup : 1, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

                // 그룹수가 맞지 않을 경우 빈칸으로 메우기
                // 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
                loopFillGroupWithBlank : true,

                loop : false, // 무한 반복
                
                autoplay: {
                    delay: 4500,
                },

                pagination : { // 페이징
                    el : '.swiper-pagination',
                    clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
                },
                navigation : { // 네비게이션
                    nextEl : '.swiper-button-next', // 다음 버튼 클래스명
                    prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
                },
                });
            
      });
    </script>
</body>
</html>