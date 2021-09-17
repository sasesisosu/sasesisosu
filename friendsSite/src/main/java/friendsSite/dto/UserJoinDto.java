//package friendsSite.dto;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import friendsSite.model.User;
//import lombok.Data;
//
//
//@Data
//public class UserJoinDto {
//	@Size(min=2, max=20)
//	@NotBlank
//	private String userId;
//	@NotBlank
//	private String userPwd;
//	@NotBlank
//	private String userName;
//
//	
//	public User toEntity() {
//		return User.builder()
//				.userId(userId)
//				.userPwd(userPwd)
//				.userName(userName)
//				.build();
//	}
//}
