package com.lyg.photogramstart.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.handler.ex.CustomApiException;
import com.lyg.photogramstart.service.SubscribeService;
import com.lyg.photogramstart.web.dto.CMRespDto;

@RestController
public class SubscribeApiController {

	@Autowired
	private SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, 
										@PathVariable int toUserId){		
		try {
			subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독한 유저입니다.");
		}
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독", null), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, 
										@PathVariable int toUserId){
		subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독 취소", null), HttpStatus.OK);
	}
}
