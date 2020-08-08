package product.productDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import product.productDTO.reviewDTO;

public class reviewDAO {

	private Connection conn;
	private ResultSet rs;

	public reviewDAO() {
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
		String sql = "SELECT review_index FROM review ORDER BY review_index DESC";
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
	// 해당 상품의 리뷰 개수를 구하기 위함 
	public int getBoardCount(String product_title) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM review WHERE product_title = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_title);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;		
	}
	// 리뷰 작성하기
	public boolean review_write(String product_title, String review_writer, String review_content, String pick_image) {		
		boolean result = false; // false면 제목, 내용이 비어있는 상태		
		String sql = "INSERT INTO review(product_title, review_writer, review_content, review_date, pick_image) VALUES(?,?,?,now(),?)";
		
		if(review_content.equals("")) {
			 return result;
		} 
		else {
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, product_title);
				pstmt.setString(2, review_writer);
				pstmt.setString(3, review_content);
				pstmt.setString(4, pick_image);
				
				int dbresult = pstmt.executeUpdate();
				if(dbresult > 0) {
					result = true;
				}				
			} catch(Exception e) {
				e.printStackTrace();
			}			
			return result;
		}
	}
	// 해당 상품에 대한 작성한 리뷰를 보여주기 위함 
	public ArrayList<reviewDTO> review_view(int reviewPageNumber, String product_title){
		String sql = "SELECT * FROM review WHERE review_index < ? AND product_title = ? order by review_index DESC LIMIT 5";

		ArrayList<reviewDTO> reviewList = new ArrayList<reviewDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (reviewPageNumber - 1) * 5);
			pstmt.setString(2, product_title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reviewDTO review  = new reviewDTO();
				review.setReview_index(rs.getInt(1));
				review.setProduct_title(rs.getString(2));
				review.setReview_writer(rs.getString(3));
				review.setReview_content(rs.getString(4));
				review.setReview_date(rs.getString(5));
				review.setPick_image(rs.getString(6));
				reviewList.add(review);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviewList;
	}
	// 작성한 리뷰 삭제
	public int review_delete(int review_index) {
		String sql = "DELETE FROM review WHERE review_index = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_index);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return -1;
	}
	// 리뷰 작성 할 때 좋아요 or 싫어요의 개수를 구함
	public int getLikeHateCount(String product_title, String pick_image) {
		String sql = "SELECT COUNT(*) FROM review WHERE product_title = ? AND pick_image = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_title);
			pstmt.setString(2, pick_image);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1);			
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;		
	}
	
}
