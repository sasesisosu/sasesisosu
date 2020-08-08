package product.productDTO;

public class ProductDTO {
	private int product_index; // 상품에 대한 index primary key
	private String product_title; // 상품의 이름 
	private int product_price; // 상품의 가격
	private String product_genre; // 상품의 장르
	private String product_category; // 상품의 카테고리
	private String product_writer; // 상품의 저자
	private String product_publisher; // 상품의 출판사
	private String product_content; // 상품의 소개
	private String product_image; // 상품의 이미지
	private String product_article; // 상품의 본문
	private int product_count; // 상품의 구매 횟수
	
	public String getProduct_article() {
		return product_article;
	}
	public void setProduct_article(String product_article) {
		this.product_article = product_article;
	}
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getProduct_index() {
		return product_index;
	}
	public void setProduct_index(int product_index) {
		this.product_index = product_index;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_genre() {
		return product_genre;
	}
	public void setProduct_genre(String product_genre) {
		this.product_genre = product_genre;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_writer() {
		return product_writer;
	}
	public void setProduct_writer(String product_writer) {
		this.product_writer = product_writer;
	}
	public String getProduct_publisher() {
		return product_publisher;
	}
	public void setProduct_publisher(String product_publisher) {
		this.product_publisher = product_publisher;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	
}
