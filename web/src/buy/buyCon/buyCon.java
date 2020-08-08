package buy.buyCon;

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
import buy.buyDTO.BuyDTO;
import product.productDAO.ProductDAO;
import product.productDTO.ProductDTO;


@WebServlet("*.buy")
public class buyCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionDo(request, response);
	}
	protected void ActionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		BuyDAO buyDAO = new BuyDAO();
		BuyDTO buyDTO = new BuyDTO();
		
		ProductDAO productDAO = new ProductDAO();
		
		String uri = request.getRequestURI();
		String contextPath  = request.getContextPath();
		String viewPage = "";
		String command = uri.substring(contextPath.length()); // 페이지 이동시 url에 값
		
		HttpSession session = request.getSession();		
		String user_id = (String)session.getAttribute("user_id"); // 접속해 있는 아이디
		// 상품 제목, 작가, 이미지, 내용, 가격 설정한 값을 가져옴
		String product_title = request.getParameter("product_title");				
		String product_writer = request.getParameter("product_writer");
		String product_image = request.getParameter("product_image");
		String product_content = request.getParameter("product_content");		
		int product_price = 0;
		if(request.getParameter("product_price") != null) {
			product_price = Integer.parseInt(request.getParameter("product_price"));
		}		
		if(user_id == null) { viewPage = "login.jsp"; } // 비로그인시 로그인 페이지로 이동
		else {	// 로그인 상태		
			try {
				// 정가 구매 목록 보여주기
				if(command.equals("/myPage_cart_buy.buy")) {							
					ArrayList<BuyDTO> list = buyDAO.buyProductView(user_id, 1);	// 접속한 아이디에 대한 정가구매 목록을 보여줌 숫자가 1이면 정가구매
					request.setAttribute("list", list);
	
		            viewPage = "myPage_cart_buy.jsp";					
				}
				// 이용권 구매 목록 보여주기
				if(command.equals("/myPage_cart_ticket.buy")) {			
					ArrayList<BuyDTO> list2 = buyDAO.buyProductView(user_id, 2);					
					request.setAttribute("list2", list2);
					
					int ticketCount = buyDAO.ticketCount(user_id, 2);
					request.setAttribute("ticketCount", ticketCount);
					
					int ticketExpire = buyDAO.ticketExpire(2);
					request.setAttribute("ticketExpire", ticketExpire);

					viewPage = "myPage_cart_ticket.jsp";				
				}
				// 상품 구매하기
				if(command.equals("/buy_product.buy")) {							
					buyDAO.buyProduct(user_id, product_title, product_writer, product_image, product_price, 1);
					ArrayList<BuyDTO> list = buyDAO.buyProductView(user_id, 1);					
					request.setAttribute("list", list);
	
		            viewPage = "myPage_cart_buy.jsp";					
				}
				// 상품 구매페이지로 이동
				if(command.equals("/buy_item.buy")) {						
					request.setAttribute("product_title", product_title);
					request.setAttribute("product_writer", product_writer);
					request.setAttribute("product_image", product_image);
					request.setAttribute("product_price", product_price);
					request.setAttribute("product_content", product_content);

					viewPage = "buy_item.jsp";		
				}
				// 이용권 구매하기
				if(command.equals("/buy_ticket.buy")) {
				/* 이용권을 구매하면 대여할 수 있는 책 권수를 5권으로 지정, DB table을 5개 생성하여  
					아이디, buy_mode에 2를 대입하고 나머지는 null값을 준다. 이용권으로 대여를 하게 되면 
					null값에 있는 product_title, product_content 등  넣어주게 된다.
				*/
					for(int i=0;i<5;i++) { 
						buyDAO.buyTicket(user_id, 2);
					}					
					viewPage = "buy_ticket_complete.jsp";
				}
				// 이용권으로 상품 대여하기				
				if(command.equals("/buy_ticket_product.buy")) {
					int index = buyDAO.ticketIndex(user_id, 2); // 이용권구매시 생성된 table에서 이용권을 사용할 수 있는(null값이 없는) index를 구함 

					buyDAO.buyTicketProduct(product_title, product_writer, product_image, product_price, index, 2); // 비어있는 null에 상품 관련 이름을 대입
					ArrayList<BuyDTO> list2 = buyDAO.buyProductView(user_id, 2);					
					request.setAttribute("list2", list2);
					
					int ticketCount = buyDAO.ticketCount(user_id, 2); // 이용권으로 대여 시 남은 대여권 개수 반환 
					request.setAttribute("ticketCount", ticketCount); 
					
					int ticketExpire = buyDAO.ticketExpire(2); // 이용권으로 대여 시 대여 한 책 남은 기간 반환(7일)
					request.setAttribute("ticketExpire", ticketExpire);
					
					viewPage = "myPage_cart_ticket.jsp";				
				}
				// 찜 하기(장바구니)
				if(command.equals("/product_pick.buy")) {
					buyDAO.buyProduct(user_id, product_title, product_writer, product_image, product_price, 3);	
		            viewPage = "myPage_cart_pick.buy";	
				}
				// 찜한 목록 보여주기
				if(command.equals("/myPage_cart_pick.buy")) {
					ArrayList<BuyDTO> list = buyDAO.buyProductView(user_id, 3);					
					request.setAttribute("list", list);
	
		            viewPage = "myPage_cart_pick.jsp";	
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
