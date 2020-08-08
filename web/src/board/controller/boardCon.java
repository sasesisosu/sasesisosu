package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO.BoardDAO;
import board.BoardDTO.BoardDTO;

/**
 * Servlet implementation class writeCon
 */
@WebServlet("*.do")
public class boardCon extends HttpServlet {
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
				

		BoardDAO bdao = new BoardDAO();
		BoardDTO board = new BoardDTO();		

		HttpSession session = request.getSession();		
		String board_id = (String)session.getAttribute("user_id"); // 접속해 있는 아이디

		String board_title = request.getParameter("board_title"); 
		String board_content = request.getParameter("board_content");		
		String comment_content = request.getParameter("comment_content");		
		
		int board_index;
		if(request.getParameter("board_index") == null) {
			board_index = 1;
		}else {
			board_index = Integer.parseInt(request.getParameter("board_index"));
		}

		board.setBoard_index(board_index);
		board.setBoard_id(board_id);
		board.setBoard_title(board_title);
		board.setBoard_content(board_content); // board에 대한 index, 아이디, 제목, 내용을 boardDTO에 저장

		int pageNumber = 1; // 공지사항에 대한 페이지		
		int inquiryPageNumber = 1; // 문의글에 대한 페이지
		
		boolean dbResult = false; // 게시글작성시 제목, 내용이 비어있으면 false, 아니면 true로 error페이지로 이동하게끔 함
		
		try {
			// 공지사항 페이지 보기
			if(command.equals("/noticeView.do")) {
				pageNumber = 1; // 공지사항에 들어갈 때 보여주는 첫 페이지
				ArrayList<BoardDTO> list = bdao.view(pageNumber, 1); // view(보여줄 페이지, 1(공지사항))을 ArrayList에 넣어 반환
				request.setAttribute("list", list);
				viewPage = "myPage_notice.jsp";
			}
			// 공지사항 작성 (관리자만 가능)
			if(command.equals("/write.do")) {				
				bdao.registration(board_id, board_title, board_content, 1);
				ArrayList<BoardDTO> list = bdao.view(pageNumber, 1);				
				request.setAttribute("pageNumber", pageNumber);
				request.setAttribute("list", list);
				viewPage = "myPage_notice.jsp";
			}
			// 게시글 전체를 볼 때  1,2,3... 페이지가 있을때 숫자를 클릭하면 그 숫자가 넘어와서 그 페이지에 대한 게시글을 보여줌 
			if(command.equals("/page.do")) {		
				if(request.getParameter("pageNumber") != null){
					pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				}				
				ArrayList<BoardDTO> list = bdao.view(pageNumber, 1);		
				request.setAttribute("pageNumber", pageNumber);	
				request.setAttribute("list", list);
				viewPage = "myPage_notice.jsp";
			}
			// 공지사항 내용 보기
			if(command.equals("/contentView.do")) {
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 1));	
				request.setAttribute("contentView", contentView);

				viewPage = "noticeContentView.jsp";
			}			
			// 공지사항 수정
			if(command.equals("/noticeModify.do")) { 
				ArrayList contentView = new ArrayList(); 
				contentView.add(bdao.contentView(board_index, 1));
				request.setAttribute("contentView", contentView);

				viewPage = "noticeModify.jsp"; 
			} 
			// 공지사항 수정 완료
			if(command.equals("/modifyComplete.do")) {
				bdao.modify(board_index, board_title, board_content, 1);
				
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 1));
				request.setAttribute("contentView", contentView);

				viewPage = "noticeContentView.jsp";			
			}
			// 공지사항 삭제
			if(command.equals("/noticeDelete.do")) {
				bdao.delete(board_index, 1);
				ArrayList<BoardDTO> list = bdao.view(pageNumber, 1);	
				
				request.setAttribute("pageNumber", pageNumber);	
				request.setAttribute("list", list);
				viewPage = "myPage_notice.jsp";
			}
			
			// 1:1 문의 페이지 보기
			if(command.equals("/inquiryView.do")) {
				inquiryPageNumber = 1;
				ArrayList<BoardDTO> list = bdao.view(inquiryPageNumber, 2);	
				request.setAttribute("list", list);
				viewPage = "myPage_inquiry.jsp";
			}
			// 1:1 문의하기 페이지
			if(command.equals("/inquiry.do")) {
				dbResult = bdao.registration(board_id, board_title, board_content, 2);
				
				if(dbResult == false) { // false면 내용이나 제목을 작성 안했을 때 에러 페이지로 이동
					session.setAttribute("errorType", "inquiryContent");
					viewPage = "error.jsp";
				} else {				
					ArrayList<BoardDTO> list = bdao.view(inquiryPageNumber, 2);	
					
					request.setAttribute("pageNumber", inquiryPageNumber);	
					request.setAttribute("list", list);
					viewPage = "myPage_inquiry.jsp";
				}
			}
			// 1:1 문의 내용 보기
			if(command.equals("/inquiryContentView.do")) {
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 2));	
				request.setAttribute("contentView", contentView);

				ArrayList answerView = new ArrayList();
				answerView.add(bdao.answerView(board_index));
				
				request.setAttribute("answerView", answerView);
				
				viewPage = "inquiryContentView.jsp";
			}
			// 1:1문의 수정하기
			if(command.equals("/inquiryModify.do")) {
				ArrayList contentView = new ArrayList(); 
				contentView.add(bdao.contentView(board_index, 2));
				request.setAttribute("contentView", contentView);

				viewPage = "inquiryModify.jsp"; 
			}
			// 1:1문의 수정 완료
			if(command.equals("/inquiryComplete.do")) {
				bdao.modify(board_index, board_title, board_content, 2);
				
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 2));
				request.setAttribute("contentView", contentView);

				viewPage = "inquiryView.do";
			}
			// 1:1문의 전체를 볼 때  1,2,3... 페이지가 있을때 숫자를 클릭하면 그 숫자가 넘어와서 그 페이지에 대한 게시글을 보여줌
			if(command.equals("/pageInquiry.do")) {
				if(request.getParameter("pageNumber") != null){
					pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				}
				
				ArrayList<BoardDTO> list = bdao.view(pageNumber, 2);		
				request.setAttribute("pageNumber", pageNumber);	
				request.setAttribute("list", list);
				viewPage = "myPage_inquiry.jsp";
			}
			// 1:1문의 삭제
			if(command.equals("/inquiryDelete.do")) {
				bdao.delete(board_index, 2);
				viewPage = "inquiryView.do";
			}
			// 1:1문의 답변
			if(command.equals("/inquiryAnswer.do")) {
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 2));
				request.setAttribute("contentView", contentView);
				viewPage = "inquiryAnswer.jsp";
			}
			// 1:1문의 답변 완료
			if(command.equals("/inquiryAnswerComplete.do")) {
				ArrayList contentView = new ArrayList();
				contentView.add(bdao.contentView(board_index, 2));	
				request.setAttribute("contentView", contentView);				
				
				bdao.answer(board_id, comment_content, board_index);
				
				ArrayList answerView = new ArrayList();
				answerView.add(bdao.answerView(board_index));				
				request.setAttribute("answerView", answerView);
				
				viewPage = "inquiryContentView.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
