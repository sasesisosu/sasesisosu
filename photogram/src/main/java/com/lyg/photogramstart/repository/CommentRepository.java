package com.lyg.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyg.photogramstart.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
