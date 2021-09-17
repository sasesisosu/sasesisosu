package friendsSite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import friendsSite.dto.CMRespDto;
import friendsSite.model.User;
import friendsSite.service.UserService;

@RestController
public class UserApiController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	// @Valid @RequestBody User user, BindingResult bindingResult
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user) {
		HttpStatus httpStatus = HttpStatus.OK;
		
		int idCheck = userService.userIdCheck(user.getUserId());
		
		if(idCheck!=1) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		userService.userJoin(user);
		return new ResponseEntity<>(new CMRespDto<>(idCheck, "!!!", null), httpStatus);
	}	

	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user){
		userService.update(user);
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseEntity<>(new CMRespDto<>(1, "!!!", null), HttpStatus.OK);
	}
}
