package com.lyg.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.handler.ex.CustomValidationApiException;
import com.lyg.photogramstart.service.UserService;
import com.lyg.photogramstart.web.dto.CMRespDto;
import com.lyg.photogramstart.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
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
