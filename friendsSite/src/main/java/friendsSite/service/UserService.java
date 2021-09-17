package friendsSite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friendsSite.model.User;
import friendsSite.repository.UserRepository;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void userJoin(User user) {
		String userRole = "ROLE_USER";
		String encPwd = bCryptPasswordEncoder.encode(user.getUserPwd());		
		userRepository.userJoin(user.getUserId(), encPwd, user.getUserName(), user.getUserIntro(), user.getUserRelation(), userRole);
		log.info("Method : userJoin, Info : " +user.getUserId() + ", " + user.getUserName());
	}
	
	@Transactional(readOnly=true)
	public int userIdCheck(String userId) {
		User userEntity = userRepository.findByUserId(userId);		
		if(userEntity == null) {
			return 1;
		}else {
			return -1;
		}
	}

	@Transactional
	public void update(User user) {
		User userEntity = userRepository.findById(user.getId()).orElseThrow(()->{
			log.error("Method : update, Message : 회원 찾기 실패" );
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String encPwd = bCryptPasswordEncoder.encode(user.getUserPwd());
		userEntity.setUserPwd(encPwd);
		userEntity.setUserName(user.getUserName());
		userEntity.setUserIntro(user.getUserIntro());
		userEntity.setUserRelation(user.getUserRelation());
		
	}
	
	@Transactional(readOnly=true)
	public User userInfo(int boardUserId) {
		User userEntity = userRepository.findById(boardUserId).orElseThrow(()->{
			log.error("Method : userInfo, Message : 회원 찾기 실패" );
			return new IllegalArgumentException("회원 찾기 실패");
		});
		return userEntity;
	}
}
