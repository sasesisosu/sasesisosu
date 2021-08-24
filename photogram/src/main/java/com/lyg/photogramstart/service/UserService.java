package com.lyg.photogramstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.handler.ex.CustomException;
import com.lyg.photogramstart.handler.ex.CustomValidationApiException;
import com.lyg.photogramstart.repository.SubscribeRepository;
import com.lyg.photogramstart.repository.UserRepository;
import com.lyg.photogramstart.web.dto.user.UserProfileDto;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Transactional(readOnly = true)
	public UserProfileDto userProfile(int pageUserId, int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId);
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		int subscriberCount = subscribeRepository.mSubscriberCount(pageUserId);
		
		dto.setSubscribeState(subscribeState==1);
		dto.setSubscribeCount(subscribeCount);
		dto.setSubscriberCount(subscriberCount);
		
		return dto;
	}
	
	@Transactional
	public User userUpdate(int id, User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		// 영속화
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new CustomValidationApiException("찾을 수 없는 id입니다");
		});
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	} // 더티체킹이 일어나서 업데이트가 됨
}
