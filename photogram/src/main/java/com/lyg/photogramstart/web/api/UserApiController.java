package com.lyg.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.handler.ex.CustomValidationApiException;
import com.lyg.photogramstart.service.SubscribeService;
import com.lyg.photogramstart.service.UserService;
import com.lyg.photogramstart.web.dto.CMRespDto;
import com.lyg.photogramstart.web.dto.subscribe.SubscribeDto;
import com.lyg.photogramstart.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscribeService subscribeService;
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
									@AuthenticationPrincipal PrincipalDetails principalDetails){
		User userEntity = userService.profileImageUrlUpdate(principalId, profileImageFile);
		principalDetails.setUser(userEntity);
		return new ResponseEntity<>(new CMRespDto<>(1, "프로필 사진 변경", null), HttpStatus.OK);		
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDto> subscribeDto = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 가져오기", subscribeDto), HttpStatus.OK);
		
	}
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, @Valid UserUpdateDto userUpdateDto,
						BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationApiException("유효성 검사 실패", errorMap);
		}else {
			User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity);
			return new CMRespDto<>(1, "회원수정 완료", userEntity);
		}
		
		
	}
}
