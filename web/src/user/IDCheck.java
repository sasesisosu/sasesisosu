package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/IDCheck")
// 회원가입 시 아이디 중복체크를 하기위한 Servlet화면
public class IDCheck extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserDAO userDAO = new UserDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user_id = request.getParameter("user_id");
		try {
			response.getWriter().write(userDAO.IDCheck(user_id)+"");
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
