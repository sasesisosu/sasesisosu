package friendsSite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import friendsSite.config.auth.KakaoAuth;
import friendsSite.model.KakaoProfile;
import friendsSite.model.OAuthToken;

@Service
public class KakaoService {

	private static final Logger log = LoggerFactory.getLogger(KakaoService.class);
	
	public OAuthToken kakaoToken(String code) {
		log.info("kakaoToken start--------------");
		KakaoAuth kakaoAuth = new KakaoAuth();
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", kakaoAuth.getGrantType());
		params.add("client_id", kakaoAuth.getClientId());
		params.add("redirect_uri", kakaoAuth.getRedirectUri());
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			log.error("kakaoToken, Message : " + e.getMessage());
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			log.error("kakaoToken, Message : " + e.getMessage());
			e.printStackTrace();
		}
		log.info("kakaoToken end--------------");
		return oauthToken;
	}
	
	public KakaoProfile kakaoProfile(OAuthToken oauthToken) {
		log.info("kakaoProfile start--------------");
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			log.error("kakaoProfile, Message : " + e.getMessage());
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			log.error("kakaoProfile, Message : " + e.getMessage());
			e.printStackTrace();
		}		
		log.info("kakaoProfile end--------------");
		return kakaoProfile;
	}
}
