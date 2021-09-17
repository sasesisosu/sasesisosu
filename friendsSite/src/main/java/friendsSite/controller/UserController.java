package friendsSite.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import friendsSite.config.auth.PrincipalDetails;
import friendsSite.model.KakaoProfile;
import friendsSite.model.OAuthToken;
import friendsSite.model.User;
import friendsSite.service.KakaoService;
import friendsSite.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/index")
	public String home() {
		System.out.println("index");
		return "index";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		System.out.println("joinForm");
		return "user/joinForm";
	}
	
	@GetMapping("/kakao/login")
	public String kakaoLogin(String code) {
		
		OAuthToken oauthToken = kakaoService.kakaoToken(code);
		
		KakaoProfile kakaoProfile = kakaoService.kakaoProfile(oauthToken);
		
		String temPwd = ""; // 카카오 임시 비밀번호로 보안 필수!
		System.out.println("kakao1 : " + kakaoProfile.getId().toString());
		System.out.println("kakao2 : " + kakaoProfile.getProperties().getNickname());
		
		User kakaoUser = User.builder()
				.userId("kakao_"+kakaoProfile.getId().toString())
				.userPwd(temPwd)
				.userName(kakaoProfile.getProperties().getNickname())
				.userRelation("친구")
				.build();
		
		int idCheck = userService.userIdCheck(kakaoUser.getUserId());
		if(idCheck==1) {
			userService.userJoin(kakaoUser);
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUserId(), kakaoUser.getUserPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}

	@GetMapping("/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		model.addAttribute("principal", principal);
		return "user/updateForm";
	}
	
	@GetMapping("/userInfo/{boardUserId}/{boardId}")
	public String userInfo(@PathVariable(value="boardUserId") int boardUserId, @PathVariable(value="boardId") int boardId, 
							Model model) {
		User userInfo = userService.userInfo(boardUserId);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("boardId", boardId);
		return "user/userInfo";
	}
	
}