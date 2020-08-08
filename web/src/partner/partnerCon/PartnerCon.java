package partner.partnerCon;

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
import partner.partnerDAO.PartnerDAO;
import partner.partnerDTO.PartnerDTO;
import user.UserDAO;

/**
 * Servlet implementation class PartnerCon
 */
@WebServlet("*.part")
public class PartnerCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionDo(request, response);
	}
	protected void ActionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String contextPath  = request.getContextPath();
		String viewPage = "";
		String command = uri.substring(contextPath.length()); // 페이지 이동시 url에 값
		
		PartnerDAO partnerDAO = new PartnerDAO();
		PartnerDTO partnerDTO = new PartnerDTO();
		BuyDAO buyDAO = new BuyDAO();
		UserDAO userDAO = new UserDAO();
		
		HttpSession session = request.getSession();		
		String user_id = (String)session.getAttribute("user_id"); // 접속해 있는 아이디
		
		try {
			// 파트너 신청버튼 클릭시 기능
			if(command.equals("/response.part")) {
				String request_id = request.getParameter("request_id"); // 신청받은 아이디 설정
				int IDCheck = userDAO.IDCheck(request_id); // 신청받은 아이디가 존재하는지 확인
				String requestID = partnerDAO.requestID(request_id, 2); // 신청받은 아이디가 파트너 상태인지 확인
				String responseID = partnerDAO.responseID(request_id, 2); // 신청한 아이디가 파트너 상태인지 확인
				// 아이디 유무 확인
				if(IDCheck != 0) {
					session.setAttribute("errorType", "partnerIDCheck");
					viewPage = "error.jsp";
				}
				// 상대가 파트너 상태인지 확인				
				else if(requestID != null || responseID != null) {
					session.setAttribute("errorType", "partnerCheck");
					viewPage = "error.jsp";
				}
				// 아무런 에로사항이 없으면 파트너 신청 가능
				else {									
					partnerDAO.partnerResponse(user_id, request_id);
					request.setAttribute("partner_id", request_id);
					request.setAttribute("response_id", user_id);
					viewPage = "myPage_partner.jsp";
				}
			}
			// 파트너 수락할 때 기능
			if(command.equals("/accept.part")) {
				String response_id = request.getParameter("response_id"); // 신청한 아이디
				partnerDAO.partnerComplete(response_id, user_id, 2); // 신청한 아이디와 접속한 아이디(신청받은 사람)를 파트너 상태로 바꿔줌
				viewPage = "myPage_partner.jsp";
			}
			// 파트너신청을 받은 사람이 거절할 때 기능과 파트너 취소 시 기능
			if(command.equals("/deny.part")) {
				partnerDAO.partnerDeny(user_id, 1); // 파트너 신청을 거절 하였을 때 기능 
				partnerDAO.partnerDeny(user_id, 2); // 파트너 상태일 때 취소 기능
				viewPage = "myPage_partner.jsp";
			}
			// 파트너신청을 한 사람이 파트너 신청을 취소할 때 기능과 파트너가 된 상태일 때 취소 시 기능
			if(command.equals("/cancel.part")) {
				partnerDAO.partnerCancel(user_id, 1); // 파트너 신청을 취소 할 때 기능
				partnerDAO.partnerCancel(user_id, 2); // 파트너가 된 상태일 때 취소 기능
				viewPage = "myPage_partner.jsp";
			}
			// 파트너가 된 상태일 때 파트너가 구매한 상품을 보여주기 위한 기능
			if(command.equals("/myPage_cart_partner.part")) {
				String requestID = partnerDAO.requestID(user_id, 2); // 파트너 신청을 받은 아이디
				String responseID = partnerDAO.responseID(user_id, 2); // 파트너 신청을 한 아이디
				ArrayList<BuyDTO> list = new ArrayList<BuyDTO>(); // 구매 목록을 ArrayList로 가져옴
				if(requestID == null) { // 접속한 아이디가 파트너를 신청한 사람 일 때
					list = buyDAO.buyProductView(responseID, 1);
				}else { // 접속한 아이디가 파트너 신청을  받을 사람 일 때
					list = buyDAO.buyProductView(requestID, 1);		
				}							
				request.setAttribute("list", list);

	            viewPage = "myPage_cart_partner.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
		
		
	}

}
