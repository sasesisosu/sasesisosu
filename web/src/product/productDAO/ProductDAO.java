package product.productDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import product.productDTO.ProductDTO;


public class ProductDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProductDTO productDTO = new ProductDTO();

	public ProductDAO() {
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
		String sql = "SELECT product_index FROM product ORDER BY product_index DESC";
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
	// 상품을 장르별로 보여주기 위한 SQL문
	public ArrayList<ProductDTO> productGenreView(String product_genre){
		String sql = "SELECT * FROM product WHERE product_genre = ?";
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, product_genre);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();				
				product.setProduct_index(rs.getInt(1));
				product.setProduct_title(rs.getString(2));
				product.setProduct_price(rs.getInt(3));
				product.setProduct_genre(rs.getString(4));
				product.setProduct_category(rs.getString(5));
				product.setProduct_writer(rs.getString(6));
				product.setProduct_publisher(rs.getString(7));
				product.setProduct_content(rs.getString(8));
				product.setProduct_image(rs.getString(9));
				product.setProduct_article(rs.getString(10));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 상품을 카테고리별로 보여주기 위한 SQL문
	public ArrayList<ProductDTO> productCateView(String product_category){
		String sql = "SELECT * FROM product WHERE product_category = ?";
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, product_category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();				
				product.setProduct_index(rs.getInt(1));
				product.setProduct_title(rs.getString(2));
				product.setProduct_price(rs.getInt(3));
				product.setProduct_genre(rs.getString(4));
				product.setProduct_category(rs.getString(5));
				product.setProduct_writer(rs.getString(6));
				product.setProduct_publisher(rs.getString(7));
				product.setProduct_content(rs.getString(8));
				product.setProduct_image(rs.getString(9));
				product.setProduct_article(rs.getString(10));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 해당 상품의 목록을 보여주기 위한 SQL문
	public ArrayList<ProductDTO> bookContent(String product_title) {
		String sql = "SELECT * FROM product WHERE product_title = ?";		
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_title);
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				ProductDTO product = new ProductDTO();				
				product.setProduct_index(rs.getInt(1));
				product.setProduct_title(rs.getString(2));
				product.setProduct_price(rs.getInt(3));
				product.setProduct_genre(rs.getString(4));
				product.setProduct_category(rs.getString(5));
				product.setProduct_writer(rs.getString(6));
				product.setProduct_publisher(rs.getString(7));
				product.setProduct_content(rs.getString(8));
				product.setProduct_image(rs.getString(9));
				product.setProduct_article(rs.getString(10));
				list.add(product);
			}

		}catch(Exception e) {
			e.printStackTrace();		
		}
		return list;
	}
	// 해당 상품의 내용, 본문을 보여주기 위한 SQL문
	public String bookArticle(String product_title) {
		String sql = "SELECT product_article FROM product WHERE product_title = ?";		
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_title);
			rs = pstmt.executeQuery();			
			if(rs.next()) return rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return null;
	}
	// 상품 등록을 위한 SQL문
	public void productRegister(String product_title, String product_price, String product_genre, String product_category, 
						String product_writer, String product_publisher, String product_content, String product_image, String product_article) {
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, product_title);
			pstmt.setString(3, product_price);
			pstmt.setString(4, product_genre);
			pstmt.setString(5, product_category);
			pstmt.setString(6, product_writer);
			pstmt.setString(7, product_publisher);
			pstmt.setString(8, product_content);
			pstmt.setString(9, product_image);
			pstmt.setString(10, product_article);
			pstmt.setInt(11, 0);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 상품 삭제하기
	public int productDelete(String product_title) {
		String sql="DELETE FROM product WHERE product_title = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, product_title);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	// 상품을 검색하였을 때 일치하는 상품을 보여주기 위함
	public ArrayList<ProductDTO> productSearch(String word){
		String sql = "SELECT * FROM product WHERE product_title LIKE ?";
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, "%"+word+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();				
				product.setProduct_index(rs.getInt(1));
				product.setProduct_title(rs.getString(2));
				product.setProduct_price(rs.getInt(3));
				product.setProduct_genre(rs.getString(4));
				product.setProduct_category(rs.getString(5));
				product.setProduct_writer(rs.getString(6));
				product.setProduct_publisher(rs.getString(7));
				product.setProduct_content(rs.getString(8));
				product.setProduct_image(rs.getString(9));
				product.setProduct_article(rs.getString(10));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 인기상품, 구매를 많이 한 상품을 장르별로 3개까지 보여주기 위함 
	public ArrayList<ProductDTO> bestProduct(String product_genre){
		String sql = "SELECT * FROM product WHERE product_genre = ? ORDER BY product_count DESC LIMIT 3;";
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, product_genre);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO product = new ProductDTO();				
				product.setProduct_index(rs.getInt(1));
				product.setProduct_title(rs.getString(2));
				product.setProduct_price(rs.getInt(3));
				product.setProduct_genre(rs.getString(4));
				product.setProduct_category(rs.getString(5));
				product.setProduct_writer(rs.getString(6));
				product.setProduct_publisher(rs.getString(7));
				product.setProduct_content(rs.getString(8));
				product.setProduct_image(rs.getString(9));
				product.setProduct_article(rs.getString(10));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
