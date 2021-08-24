package com.lyg.photogramstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyg.photogramstart.repository.LikesRepository;

@Service
public class LikesService {

	@Autowired
	private LikesRepository likesRepository;
	
	@Transactional
	public void likes(int imageId, int principalId) {
		likesRepository.mLikes(imageId, principalId);
	}
	
	@Transactional
	public void unLikes(int imageId, int principalId) {
		likesRepository.mUnLikes(imageId, principalId);
	}
}
