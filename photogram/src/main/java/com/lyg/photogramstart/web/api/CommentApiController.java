package com.lyg.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.comment.Comment;
import com.lyg.photogramstart.handler.ex.CustomValidationApiException;
import com.lyg.photogramstart.service.CommentService;
import com.lyg.photogramstart.web.dto.CMRespDto;
import com.lyg.photogramstart.web.dto.comment.CommentDto;

@RestController
public class CommentApiController {

	@Autowired
	private CommentService commentService;	
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationApiException("유효성 검사 실패", errorMap);
		}
		
		Comment comment = commentService.commentSave(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 쓰기", comment), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		commentService.commentDelete(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제", null), HttpStatus.OK);
	}
}















