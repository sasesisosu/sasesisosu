package friendsSite.service;


import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friendsSite.config.auth.PrincipalDetails;
import friendsSite.dto.StoryCommentDto;
import friendsSite.dto.StoryDto;
import friendsSite.model.Story;
import friendsSite.model.StoryComment;
import friendsSite.model.StoryLike;
import friendsSite.repository.StoryCommentRepository;
import friendsSite.repository.StoryLikeRepository;
import friendsSite.repository.StoryRepository;

@Service
public class StoryService {

	private static final Logger log = LoggerFactory.getLogger(ReplyService.class);
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private StoryCommentRepository storyCommentRepository;

	@Autowired
	private StoryLikeRepository storyLikeRepository;
	
	@Transactional 
	public void storyWrite(HttpServletRequest req, StoryDto storyDto, PrincipalDetails principalDetails) {
		
		log.info("storyWrite start-------------- = {}", principalDetails.getUsername());
		
		UUID uuid = UUID.randomUUID();
		
		String rootPath = req.getSession().getServletContext().getRealPath("/");
		System.out.println("rootPath : " + rootPath);
		String attachPath = "static/image";
		String fileName = uuid+"_"+storyDto.getImageUrl().getOriginalFilename();
		File file = new File(rootPath + attachPath + fileName);
		System.out.println("file : " + rootPath + attachPath + fileName);
		try {			
			storyDto.getImageUrl().transferTo(file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("Method : storyWrite = {}", e.getMessage() );
		}

		Story story = new Story();
		story.setImageUrl("image"+fileName);
		story.setStoryAccount(storyDto.getStoryAccount());
		story.setStoryLikeCount(0);
		story.setUser(principalDetails.getUser());
		
		storyRepository.save(story);
		log.info("storyWrite end-------------- = {}", principalDetails.getUsername());
		
	}
	
	@Transactional(readOnly=true)
	public Page<Story> storyForm(Pageable pageable) {
		return storyRepository.findAll(pageable);
	}
	
	@Transactional
	public void storyComment(StoryCommentDto storyCommentDto, PrincipalDetails principal) {
		StoryComment storyComment = new StoryComment();
		storyComment.setComment(storyCommentDto.getStoryComment());
		storyComment.setUser(principal.getUser());
		Story storyEntity = storyRepository.findById(storyCommentDto.getStoryId()).orElseThrow(()->{
			log.error("Method : storyComment, Message : 스토리 찾기 실패" );
			return new IllegalArgumentException("스토리 찾기 실패");
		});
		storyComment.setStory(storyEntity);
		
		storyCommentRepository.save(storyComment);
		
	}
	
	@Transactional
	public void storyCommentDelete(int commentId) {
		storyCommentRepository.deleteById(commentId);
	}
	
	@Transactional
	public void storyLike(int storyId, PrincipalDetails principal) {
		Story story = storyRepository.findById(storyId).orElseThrow(()->{
			log.error("Method : storyLike, Message : 스토리 찾기 실패" );
			return new IllegalArgumentException("스토리 찾기 실패");
		});
		StoryLike storyLike = new StoryLike();
		storyLike.setUser(principal.getUser());
		storyLike.setStory(story);
		story.setStoryLikeCount(story.getStoryLikeCount()+1);
		storyLikeRepository.save(storyLike);
	}
	
	@Transactional
	public void storyUnLike(int storyId, PrincipalDetails principal) {
		Story story = storyRepository.findById(storyId).orElseThrow(()->{
			log.error("Method : storyUnLike, Message : 스토리 찾기 실패" );
			return new IllegalArgumentException("스토리 찾기 실패");
		});
		storyLikeRepository.storyUnLike(principal.getUser().getId(), storyId);
		story.setStoryLikeCount(story.getStoryLikeCount()-1);
	
	}
}
