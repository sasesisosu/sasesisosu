package friendsSite.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@NotBlank(message="아이디를 입력하세요")
//	@Size(max=20,message="너무 길다")
	private String userId;
	
//	@NotBlank(message="비밀번호를 입력하세요")
	private String userPwd;
	
//	@NotBlank(message="이름을 입력하세요")
	private String userName;
	
	private String userIntro;
	
//	@NotBlank(message="관계를 입력하세요")
	private String userRelation;
	
	private String userRole;
	
	@Transient
	private Timestamp createDate;
	
	
}
