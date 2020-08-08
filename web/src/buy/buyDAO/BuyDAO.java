package buy.buyDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import buy.buyDTO.BuyDTO;


public class BuyDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BuyDTO buyDTO = new BuyDTO();
	
	public BuyDAO() {
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
		String sql = "SELECT buy_index FROM buy ORDER BY buy_index DESC";
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
	// buy_mode가 1이면 정가 구매, 2면 이용권 구매 , 3이면 상품 찜하기
	public void buyProduct(String user_id, String product_title, String product_writer,
										String product_image, int product_price, int buy_mode) {
		String sql = "INSERT INTO buy VALUES(?,?,?,?,?,?,now(),?)"; // 상품 or 이용권 구매 or 상품 찜하기
		String sql2 = "UPDATE product SET product_count = product_count + 1 WHERE product_title = ?"; // 상품 구매한 횟수, 인기상품 선정하기 위함
		try {				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, user_id);
			pstmt.setString(3, product_title);
			pstmt.setString(4, product_writer);
			pstmt.setString(5, product_image);
			pstmt.setInt(6, product_price);
			pstmt.setInt(7, buy_mode); // buy_mode가 1이면 정가구매, 2면 이용권 구매, 3이면 찜
			pstmt.executeUpdate();
			
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, product_title);
			pstmt2.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 구매 상품 목록
	public ArrayList<BuyDTO> buyProductView(String user_id, int buy_mode) {
		String sql = "SELECT * FROM buy WHERE buy_user_id = ? AND buy_mode = ?";
		ArrayList<BuyDTO> list = new ArrayList<BuyDTO>(); 
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, buy_mode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BuyDTO buy = new BuyDTO();
				buy.setBuy_index(rs.getInt(1));
				buy.setBuy_user_id(rs.getString(2));
				buy.setBuy_product_title(rs.getString(3));
				buy.setBuy_product_writer(rs.getString(4));
				buy.setBuy_product_image(rs.getString(5));
				buy.setBuy_product_price(rs.getInt(6));
				buy.setBuy_date(rs.getString(7));
				buy.setBuy_mode(rs.getInt(8));
				list.add(buy);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 상품을 구매했는지 여부
	public int buyProductCheck(String product_title, String buy_user_id, int buy_mode) {
		String sql = "SELECT * FROM buy WHERE buy_product_title = ? AND buy_user_id = ? AND buy_mode = ?";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_title);
			pstmt.setString(2, buy_user_id);
			pstmt.setInt(3, buy_mode);
			rs = pstmt.executeQuery();
			if(rs.next()) return 1;	

		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}	
	// 이용권 구매
	public void buyTicket(String buy_user_id, int buy_mode) {
		String sql = "INSERT INTO buy(buy_user_id, buy_mode) VALUES(?,?)";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_user_id);
			pstmt.setInt(2, buy_mode); // buy_mode가 1이면 정가구매, 2면 이용권 구매, 3이면 찜
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 이용권에서 대여 가능한 공간
	public int ticketIndex(String buy_user_id, int buy_mode) {
		String sql = "SELECT * FROM buy WHERE buy_user_id = ? AND buy_mode = ? AND buy_product_title is NULL ORDER BY buy_index ASC LIMIT 1";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_user_id);
			pstmt.setInt(2, buy_mode);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 이용권으로 대여한 상품
	public void buyTicketProduct(String buy_product_title, String buy_product_writer, String buy_product_image,
								int buy_product_price, int buy_index, int buy_mode) {
		String sql = "UPDATE buy SET buy_product_title = ?, buy_product_writer = ?, buy_product_image = ?, buy_product_price = ?, buy_date = DATE_ADD(NOW(), INTERVAL 7 DAY) WHERE buy_index = ? AND buy_mode = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_product_title);
			pstmt.setString(2, buy_product_writer);
			pstmt.setString(3, buy_product_image);
			pstmt.setInt(4, buy_product_price);
			pstmt.setInt(5, buy_index);
			pstmt.setInt(6, buy_mode); // buy_mode가 1이면 정가구매, 2면 이용권 구매, 3이면 찜
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	// 남은 이용권 대여 개수
	public int ticketCount(String buy_user_id, int buy_mode) {
		String sql = "SELECT COUNT(*) FROM buy WHERE buy_user_id = ? AND buy_mode = ? AND buy_product_title is NULL";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_user_id);
			pstmt.setInt(2, buy_mode);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 이용권 기간 만료
	public int ticketExpire(int buy_mode) {
		String sql="DELETE FROM buy WHERE buy_date < now() AND buy_mode=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, buy_mode);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;		
	}
	
}
