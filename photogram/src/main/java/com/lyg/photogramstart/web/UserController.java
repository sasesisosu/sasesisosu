package com.lyg.photogramstart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.service.UserService;
import com.lyg.photogramstart.web.dto.user.UserProfileDto;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto userEntity = userService.userProfile(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", userEntity);
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

//		model.addAttribute("principal", principalDetails.getUser());
		return "user/update";
	}
}
