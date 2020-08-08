package partner.partnerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import buy.buyDTO.BuyDTO;
import partner.partnerDTO.PartnerDTO;
import product.productDTO.ProductDTO;


public class PartnerDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public PartnerDTO partnerDTO = new PartnerDTO();

	public PartnerDAO() {
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
	// 파트너 신청하였을 때 DB에 저장, 파트너 신청하면 10분의 시간이 주어짐
	public void partnerResponse(String response_id, String request_id) {
		String sql = "INSERT INTO partner VALUES(?,?,?,?,DATE_ADD(NOW(), INTERVAL 10 MINUTE))";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, partnerDTO.getPartner_index());
			pstmt.setString(2, response_id);
			pstmt.setString(3, request_id);
			pstmt.setInt(4, 1);			
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 상대가 파트너 상태인지 확인하는 SQL문, partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public int partnerCheck(String response_id, String request_id, int partner_state) {
		String sql = "SELECT * FROM partner WHERE response_id = ? AND request_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, response_id);
			pstmt.setString(2, request_id);
			pstmt.setInt(3, partner_state);
			rs = pstmt.executeQuery();
			if(rs.next()) return 1;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 파트너 신청 시 상대방 아이디를 가져옴, partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public String requestID(String response_id, int partner_state) {
		String sql = "SELECT request_id FROM partner WHERE response_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, response_id);
			pstmt.setInt(2, partner_state);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getString(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 파트너를 신청 받았을 때 파트너 신청한 사람 아이디를 가져옴 , partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public String responseID(String request_id, int partner_state) {
		String sql = "SELECT response_id FROM partner WHERE request_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request_id);
			pstmt.setInt(2, partner_state);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getString(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 파트너 신청 후 10분 지나면 자동 취소 하기위해 남은 시간을 가져옴, partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public String requestTime(String user_id, int partner_state) {
		String sql = "SELECT partner_request_time FROM partner WHERE response_id = ? AND partner_state = ?";
		String sql2 = "SELECT partner_request_time FROM partner WHERE request_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, user_id);
			pstmt.setInt(2, partner_state);			
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getString(1);
			else {
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);	
				pstmt2.setString(1, user_id);
				pstmt2.setInt(2, partner_state);	
				rs = pstmt2.executeQuery();
				if(rs.next()) return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 파트너 신청 후 10분이 지나면 DB에서 자동 소멸, partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public void partnerExpire(int partner_state) {
		String sql="DELETE FROM partner WHERE partner_request_time < now() AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, partner_state);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	// 파트너 신청을 받으면 거절 기능, 거절하면 DB에서 request_id와 거절한 아이디가 일치하고 partner_state가 1인 상태 DB를 삭제
	public void partnerDeny(String request_id, int partner_state) {
		String sql="DELETE FROM partner WHERE request_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, request_id);
			pstmt.setInt(2, partner_state);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	// 파트너가 된 상태일때 파트너를 취소하는 기능, partner_state는 1이면 파트너 신청 or 신청받은 상태, 2면 파트너가 된 상태
	public void partnerCancel(String response_id, int partner_state) {
		String sql="DELETE FROM partner WHERE response_id = ? AND partner_state = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, response_id);
			pstmt.setInt(2, partner_state);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	// 파트너 수락 시 partner_state가 1이었던 상태를 2로 변환하여 파트너 상태임을 알려줌
	public int partnerComplete(String response_id, String request_id, int partner_state) {
		String sql = "UPDATE partner SET partner_state = ? WHERE response_id = ? AND request_id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, partner_state);
			pstmt.setString(2, response_id);
			pstmt.setString(3, request_id);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return -1;	
	}
	// 파트너가 구매한 상품들을 보여주기 위함
	public ArrayList<BuyDTO> partnerProductView(String user_id, int buy_mode) {
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
}
	
