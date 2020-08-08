package board.BoardDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.BoardDTO.BoardDTO;
import user.User;


public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public User user = new User();
	public BoardDTO boardDTO = new BoardDTO();
	
	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String dbID="root";
			String dbPassword="1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public int getNext() {
		String sql = "SELECT board_index FROM board ORDER BY board_index DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1) + 1;
			return 1;
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return -1;
	}
	// board_mode가 1이면 공지사항, 2면 1:1 문의로 작성
	public boolean registration(String id, String title, String content, int board_mode) {
		
		boolean result = false;
		int dbresult = 0;

		if(title.equals("") || content.equals("")) {
			return result;
		} else {
			result = true;
			String sql = "INSERT INTO board VALUES(?,?,?,?,now(),?)";
			try {				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, id);
				pstmt.setString(3, title);
				pstmt.setString(4, content);
				pstmt.setInt(5, board_mode); // board_mode가 1이면 공지사항 2면 1:1 문의				
				dbresult = pstmt.executeUpdate();				
				
				return result;	
			}catch(Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	// mode가 1이면 공지사항 보여주기, 2면 1:1 문의 보여주기
	public ArrayList<BoardDTO> view(int pageNumber, int mode){
		String sql = "SELECT * FROM board WHERE board_mode = ? AND board_index < ?  ORDER BY board_index DESC LIMIT 5";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mode);
			pstmt.setInt(2, getNext() - (pageNumber - 1) * 5);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();				
				board.setBoard_index(rs.getInt(1));
				board.setBoard_id(rs.getString(2));
				board.setBoard_title(rs.getString(3));
				board.setBoard_content(rs.getString(4));
				board.setBoard_reg_date(rs.getString(5));
				board.setBoard_mode(rs.getInt(6));
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// board_mode에 따라 공지사항, 1:1문의 게시글 개수 구해오기
	public int getBoardCount(int board_mode) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM board WHERE board_mode = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_mode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;		
	}
	// board_mode에 따라 공지사항, 1:1 문의 내용 보여주기
	public BoardDTO contentView(int board_index, int board_mode) {
		String sql = "SELECT * FROM board WHERE board_index = ? AND board_mode = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_index);
			pstmt.setInt(2, board_mode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setBoard_index(rs.getInt(1));
				board.setBoard_id(rs.getString(2));
				board.setBoard_title(rs.getString(3));
				board.setBoard_content(rs.getString(4));
				board.setBoard_reg_date(rs.getString(5));
				board.setBoard_mode(rs.getInt(6));
				return board;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// board_mode에 따라 공지사항, 1:1 문의 수정하기
	public int modify(int board_index, String board_title, String board_content, int board_mode) {
		String sql = "UPDATE board SET board_title = ?, board_content = ? WHERE board_Index = ? AND board_mode = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setInt(3, board_index);
			pstmt.setInt(4, board_mode);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;		
	}
	// board_mode에 따라 공지사항, 1:1 문의 삭제하기
	public int delete(int board_index, int board_mode) {
		String sql="DELETE FROM board WHERE board_index = ? AND board_mode = ?";
		String sql2="DELETE FROM comment WHERE board_index = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, board_index);
			pstmt.setInt(2, board_mode);
			
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, board_index);
			pstmt2.executeUpdate();
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 1:1 문의에 대한 답변 달기
	public int answer(String id, String content, int board_index) {
		String sql = "INSERT INTO comment VALUES(?,?,?,?,now())";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentGetNext());
			pstmt.setInt(2, board_index);
			pstmt.setString(3, id);
			pstmt.setString(4, content);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 답글에 대한 index 증가
	public int commentGetNext() {
		String sql = "SELECT comment_index FROM comment ORDER BY comment_index DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1) + 1;
			return 1;
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return -1;
	}
	// 답글에 대한 답변 보여주기
	public BoardDTO answerView(int board_index) {
		String sql = "SELECT * FROM comment WHERE board_index = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_index);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BoardDTO comment = new BoardDTO();
				comment.setComment_index(rs.getInt(1));
				comment.setBoard_index(rs.getInt(2));
				comment.setComment_id(rs.getString(3));
				comment.setComment_content(rs.getString(4));
				comment.setComment_date(rs.getString(5));
				return comment;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


