package product.productCon;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import buy.buyDAO.BuyDAO;
import product.productDAO.ProductDAO;
import product.productDAO.reviewDAO;
import product.productDTO.ProductDTO;
import product.productDTO.reviewDTO;


@WebServlet("*.book")
public class productCon extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ActionDo(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ActionDo(request, response);
   }
   protected void ActionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
   
      ProductDAO productDAO = new ProductDAO();
      ProductDTO productDTO = new ProductDTO();
      
      BuyDAO buyDAO = new BuyDAO();
      
      String uri = request.getRequestURI();
      String contextPath  = request.getContextPath();
      String viewPage = "";
      String command = uri.substring(contextPath.length()); // 페이지 이동시 url에 값
   
      reviewDAO rDao = new reviewDAO();
	  reviewDTO rDto = new reviewDTO();
		
		
		HttpSession session = request.getSession();		
		String review_writer = (String)session.getAttribute("user_id");	 // 접속해 있는 아이디, 리뷰작성 아이디
		String product_title = (String)session.getAttribute("product_title"); // 작성한 리뷰의 상품 이름		
		String review_content = request.getParameter("review_content"); // 작성한 리뷰의 상품 내용
		String pick_image = request.getParameter("radio_state"); // 리뷰 작성시 선택한 좋아요, 싫어요 확인		

		rDto.setProduct_title(product_title);
		rDto.setReview_writer(review_writer);
		rDto.setReview_content(review_content);
		rDto.setPick_image(pick_image);
		
		int reviewPageNumber = 1; // 해당 상품에 대한 리뷰의 페이지
						
		boolean result = false; // 리뷰 작성 시 내용이 없으면 false, error 페이지로 이동
		
      try {
    	  // 장르가 소설인 상품의 목록을 다 보여줌
         if(command.equals("/novel.book")) {            
            ArrayList<ProductDTO> list = productDAO.productGenreView("소설");   
            request.setAttribute("list", list);
            request.setAttribute("title", "소설 전체");
            // 장르가 소설인 상품의 인기상품 3개를 보여줌
			ArrayList<ProductDTO> list2 = productDAO.bestProduct("소설");
			request.setAttribute("list2", list2);
            
            viewPage = "novel.jsp";            
         }if(command.equals("/novelKo.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("국내 소설");   
            request.setAttribute("list", list);
            request.setAttribute("title", "국내 소설");
            viewPage = "novel.jsp";
         }if(command.equals("/novelJa.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("일본 소설");   
            request.setAttribute("list", list);
            request.setAttribute("title", "일본 소설");
            viewPage = "novel.jsp";
         }if(command.equals("/novelDe.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("추리 소설");   
            request.setAttribute("list", list);
            request.setAttribute("title", "추리 소설");
            viewPage = "novel.jsp";
         }if(command.equals("/novelSF.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("SF 소설");   
            request.setAttribute("list", list);
            request.setAttribute("title", "SF 소설");
            viewPage = "novel.jsp";
         }
         // 장르가 판타지인 상품을 전부 보여줌
         if(command.equals("/fantasy.book")) {            
            ArrayList<ProductDTO> list = productDAO.productGenreView("판타지");   
            request.setAttribute("list", list);
            request.setAttribute("title", "판타지 전체");
            // 장르가 판타지인 인기상품 3개를 보여줌
            ArrayList<ProductDTO> list2 = productDAO.bestProduct("판타지");
			request.setAttribute("list2", list2);
            
            viewPage = "fantasy.jsp";
         }if(command.equals("/fantasyLe.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("정통 판타지");   
            request.setAttribute("list", list);
            request.setAttribute("title", "정통물");
            viewPage = "fantasy.jsp";
         }if(command.equals("/fantasyFu.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("퓨전 판타지");   
            request.setAttribute("list", list);
            request.setAttribute("title", "퓨전물");
            viewPage = "fantasy.jsp";
         }if(command.equals("/fantasyMo.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("현대 판타지");   
            request.setAttribute("list", list);
            request.setAttribute("title", "현대물");
            viewPage = "fantasy.jsp";
         }if(command.equals("/fantasyCh.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("무협 판타지");   
            request.setAttribute("list", list);
            request.setAttribute("title", "무협물");
            viewPage = "fantasy.jsp";
         }
         // 장르가 로맨스인 상품을 전부 보여줌
         if(command.equals("/romance.book")) {            
            ArrayList<ProductDTO> list = productDAO.productGenreView("로맨스");   
            request.setAttribute("list", list);
            request.setAttribute("title", "로맨스 전체");
            // 장르가 로맨스인 인기상품 3개를 보여줌
            ArrayList<ProductDTO> list2 = productDAO.bestProduct("로맨스");
			request.setAttribute("list2", list2);
            
            viewPage = "romance.jsp";
         }if(command.equals("/romanceMo.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("현대 로맨스");   
            request.setAttribute("list", list);
            request.setAttribute("title", "현대물");
            viewPage = "romance.jsp";
         }if(command.equals("/romanceHi.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("역사 로맨스");   
            request.setAttribute("list", list);
            request.setAttribute("title", "역사/시대물");
            viewPage = "romance.jsp";
         }if(command.equals("/romanceFa.book")) {            
            ArrayList<ProductDTO> list = productDAO.productCateView("판타지 로맨스");   
            request.setAttribute("list", list);
            request.setAttribute("title", "판타지물");
            viewPage = "romance.jsp";
         }
         // 상품보기를 눌렀을 때 상품내용과 리뷰를 보여줌
         if(command.equals("/bookContent.book")) {
        	product_title = request.getParameter("product_title");
			ArrayList list = productDAO.bookContent(product_title); // 해당상품 관련 내용을 ArrayList에 저장
            request.setAttribute("list", list);
            // 처음 보여줄 리뷰의 페이지
            reviewPageNumber=1; 
            if(request.getParameter("reviewPageNumber") != null) {
				reviewPageNumber = Integer.parseInt(request.getParameter("reviewPageNumber"));
			} 
			ArrayList<reviewDTO> reviewList = rDao.review_view(reviewPageNumber, product_title); // 해당 상품의 리뷰를 ArrayList에 저장
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("reviewPageNumber", reviewPageNumber); 
            
 			viewPage = "item_detail.jsp";
         }
         // 해당 상품(책)에 대한 내용보기를 클릭하면 책의 내용, 본문을 보여줌
         if(command.equals("/bookArticle.book")) {
        	 product_title = request.getParameter("product_title");

        	 ArrayList list = productDAO.bookContent(product_title);             
        	 String article = productDAO.bookArticle(product_title);

        	 request.setAttribute("list", list);
        	 request.setAttribute("article", article);
        	 viewPage = "bookArticle.jsp";
         }
         // 해당 상품에 대한 리뷰 작성하기
         if(command.equals("/review_write.book")) { 		
    	 	product_title = request.getParameter("product_title");
    	 	request.setAttribute("product_title", product_title);
    	 	// result가 false면 리뷰내용이 없는 상태
    	 	result = rDao.review_write(product_title, review_writer, review_content, pick_image); 				
			if(result == false) {
				session.setAttribute("errorType", "reviewContent");
				viewPage = "error.jsp";
			} else {					
				viewPage = "bookContent.book";
			}
 		 } 		
         // 작성한 리뷰 삭제하기
 		 if(command.equals("/review_delete.book")) { 		
			int review_index = Integer.parseInt(request.getParameter("review_index"));
			
			rDao.review_delete(review_index);
			viewPage = "bookContent.book";
 		 }
 		 // 상품 검색할때 검색한 내용 보여주기
 		 if(command.equals("/search.book")) {
		 	String word = request.getParameter("word");
		 	ArrayList list = productDAO.productSearch(word);
            request.setAttribute("list", list);
            viewPage = "search.jsp";
 		 }
 		 // 등록되어 있는 상품을 삭제하기 (관리자 전용)
 		 if(command.equals("/productDelete.book")) {
 			 product_title = request.getParameter("product_title");
 			 productDAO.productDelete(product_title);
 			 viewPage = "novel.book";
 		 }
 		 // 해당 상품(책)에 대한 미리보기를 클릭하면 책의 내용, 본문을 일부 보여줌 
 		 if(command.equals("/bookPreview.book")) {
 			 product_title = request.getParameter("product_title");

	       	 ArrayList list = productDAO.bookContent(product_title);             
	       	 String article = productDAO.bookArticle(product_title);
	
	       	 request.setAttribute("list", list);
	       	 request.setAttribute("article", article);
	       	 viewPage = "bookPreview.jsp";
 		 }
 		 
       }catch(Exception e) {
         e.printStackTrace();
      }
      
      RequestDispatcher dis = request.getRequestDispatcher(viewPage);
      dis.forward(request, response);
   }
}