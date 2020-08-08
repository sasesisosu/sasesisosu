package partner.partnerDTO;

public class PartnerDTO {
	private int partner_index; // 파트너 index primary key
	private String response_id; // 파트너 신청을 한 아이디
	private String request_id; // 파트너 신청을 받은 아이디
	private int partner_state; // partner_state가 1이면 파트너 신청과정인 상태, 2면 파트너가 된 상태
	private String partner_request_date; // 파트너 신청 후 10분의 시간이 주어짐, 10분뒤 자동 소멸
	public PartnerDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getPartner_index() {
		return partner_index;
	}
	public void setPartner_index(int partner_index) {
		this.partner_index = partner_index;
	}
	
	public String getResponse_id() {
		return response_id;
	}
	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public int getPartner_state() {
		return partner_state;
	}
	public void setPartner_state(int partner_state) {
		this.partner_state = partner_state;
	}
	public String getPartner_request_date() {
		return partner_request_date;
	}
	public void setPartner_request_date(String partner_request_date) {
		this.partner_request_date = partner_request_date;
	}
	
	
}
