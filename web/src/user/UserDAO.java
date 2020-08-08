package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public User user = new User();	

	public UserDAO() {
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
	// 아이디 회원가입을 위한 기능
	public int join(User user) {
		String sql = "INSERT INTO user VALUES (?,?,?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getIndex());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_id());
			pstmt.setString(4, user.getUser_password());
			pstmt.setString(5, user.getUser_email());
			
			return pstmt.executeUpdate(); 
		}catch(Exception e) {
			e.printStackTrace();			
		}
		return -1; 
	}
	// 유저에 대한 DB에서 index자동 생성 
	public int getIndex() {
		String sql = "SELECT user_index FROM user ORDER BY user_index DESC";
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
	// 로그인을 위한 SQL문
	public int login(String user_id, String user_password) throws SQLException {
		String sql = "SELECT user_password FROM user WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(user_password)) {
					return 1; 
				}
				else return 0; 
			}
			return -1;
		}catch(Exception e) {
			e.printStackTrace();
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();			
		}
		return -2;
	}
	// 회원정보 수정 시 접속한 id만 넘어와서 유저이름과, 이메일을 넘겨오기 위하여 만든 SQL문
	public void info(String user_id) throws SQLException {
		String sql = "SELECT user_name, user_email FROM user WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUser_name( rs.getString("user_name") );
				user.setUser_email( rs.getString("user_email") );
			}			
		}catch(Exception e) {
			e.printStackTrace();
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();			
		}
	}
	// 회원정보 수정을 위한 SQL문
	public int modify(String user_id, String user_password, String user_name, String user_email) throws SQLException {
		String sql = "UPDATE user SET user_password = ?, user_name = ?, user_email = ? WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_password);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_email);
			pstmt.setString(4, user_id);
			pstmt.executeUpdate();
			return 1; 
		}catch(Exception e) {
			e.printStackTrace();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();	
		}
		return -1; 
	}
	// 회원 탈퇴를 위한 SQL문
	public int withdraw(String user_id, String user_password, String user_password2) throws SQLException {
		String sql = "SELECT user_password FROM user WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(user_password)) {
					if(user_password.equals(user_password2)) {
						String sql2= "DELETE FROM user WHERE user_id = ?";
						try {
							PreparedStatement pstmt = conn.prepareStatement(sql2);
							pstmt.setString(1, user_id);
							pstmt.executeUpdate();
							return 0;
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					else return 1; 
				}
				else return 2; 
			}
			return -1; 
		}catch(Exception e) {
			e.printStackTrace();
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();			
		}
		return -2; 
	}
	// 회원가입 시 ID중복체크를 하기 위함 
	public int IDCheck(String user_id) throws SQLException {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) return 0; 
			else return 1; 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null ) rs.close();
				if(pstmt != null ) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; 
	}
}
