package com.lyg.photogramstart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model) {
		User userEntity = userService.userProfile(id);
		model.addAttribute("user", userEntity);
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

//		model.addAttribute("principal", principalDetails.getUser());
		return "user/update";
	}
}
