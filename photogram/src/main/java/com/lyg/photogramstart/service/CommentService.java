package com.lyg.photogramstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyg.photogramstart.domain.comment.Comment;
import com.lyg.photogramstart.domain.image.Image;
import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.handler.ex.CustomApiException;
import com.lyg.photogramstart.repository.CommentRepository;
import com.lyg.photogramstart.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Comment commentSave(String content, int imageId, int userId) {
		
		Image image = new Image();		
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없음");
		});		
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public Comment commentDelete(int id) {
		try {
			commentRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
		return null;
	}
	
}
