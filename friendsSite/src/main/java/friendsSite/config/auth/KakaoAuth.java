package friendsSite.config.auth;

import lombok.Getter;

@Getter
public class KakaoAuth {
	// 보안 필수
	private final String grantType = "";
	private final String clientId = "";
	// 개발용
//	private final String redirectUri = "http://localhost:8080/friendsSite/kakao/login";
	// 상용
	private final String redirectUri = "http://www.sasesisosm.com/friendsSite/kakao/login";
	
	
}
