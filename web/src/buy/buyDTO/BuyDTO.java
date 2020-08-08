package buy.buyDTO;

public class BuyDTO {
	private int buy_index; // 상품 구매 index primary key
	private String buy_user_id; // 상품 구매한 아이디
 	private String buy_product_title; // 구매한 상품 이름
	private String buy_product_writer; // 구매한 상품 작가
	private String buy_product_image; // 구매한 상품 이미지
	private int buy_product_price; // 구매한 상품 가격
	private String buy_date; // 구매한 상품 날짜
	public int getBuy_index() {
		return buy_index;
	}
	public void setBuy_index(int buy_index) {
		this.buy_index = buy_index;
	}
	public String getBuy_user_id() {
		return buy_user_id;
	}
	public void setBuy_user_id(String buy_user_id) {
		this.buy_user_id = buy_user_id;
	}
	public String getBuy_product_title() {
		return buy_product_title;
	}
	public void setBuy_product_title(String buy_product_title) {
		this.buy_product_title = buy_product_title;
	}
	public String getBuy_product_writer() {
		return buy_product_writer;
	}
	public void setBuy_product_writer(String buy_product_writer) {
		this.buy_product_writer = buy_product_writer;
	}
	public String getBuy_product_image() {
		return buy_product_image;
	}
	public void setBuy_product_image(String buy_product_image) {
		this.buy_product_image = buy_product_image;
	}
	public int getBuy_product_price() {
		return buy_product_price;
	}
	public void setBuy_product_price(int buy_product_price) {
		this.buy_product_price = buy_product_price;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public int getBuy_mode() {
		return buy_mode;
	}
	public void setBuy_mode(int buy_mode) {
		this.buy_mode = buy_mode;
	}
	private int buy_mode;
	public BuyDTO() {
	}	
	
	
}
