package board.BoardDTO;

public class BoardDTO {
	private int board_index; // 게시글 primary key
	private String board_id; // 게시글 작성시 아이디
	private String board_title; // 제목
	private String board_content; // 내용
	private String board_reg_date; // 게시글 등록 날짜
	private int board_mode; // board_mode가 1이면 공지사항, 2면 1:1 문의
	
	
	private int comment_index; // 답변 primary key
	private String comment_id; // 답변한 아이디 (관리자)
	private String comment_content; // 답변 내용
	private String comment_date; // 답변한 날짜
	
	
	public int getComment_index() {
		return comment_index;
	}
	public void setComment_index(int comment_index) {
		this.comment_index = comment_index;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public int getBoard_index() {
		return board_index;
	}
	public void setBoard_index(int board_index) {
		this.board_index = board_index;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(String board_reg_date) {
		this.board_reg_date = board_reg_date;
	}
	public int getBoard_mode() {
		return board_mode;
	}
	public void setBoard_mode(int board_mode) {
		this.board_mode = board_mode;
	}
	
}
