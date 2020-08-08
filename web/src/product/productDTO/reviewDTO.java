package product.productDTO;

public class reviewDTO {
	private int review_index; // 리뷰에 대한 index, primary key
	private String product_title; // 작성한 리뷰에 대한 상품 이름
	private String review_writer; // 리뷰를 작성한 아이디
	private String review_content; // 리뷰를 작성한 내용
	private String review_date; // 리뷰를 작성한 날짜
	private String pick_image; // 리뷰 작성 시 좋아요, 싫어요
	
	
	public int getReview_index() {
		return review_index;
	}
	public void setReview_index(int review_index) {
		this.review_index = review_index;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public String getReview_writer() {
		return review_writer;
	}
	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	public String getPick_image() {
		return pick_image;
	}
	public void setPick_image(String pick_image) {
		this.pick_image = pick_image;
	}
	
	
}
