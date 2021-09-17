package friendsSite.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import friendsSite.config.auth.PrincipalDetails;
import friendsSite.dto.StoryCommentDto;
import friendsSite.dto.StoryDto;
import friendsSite.model.Story;
import friendsSite.model.StoryComment;
import friendsSite.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	private StoryService storyService;
	
	@GetMapping("/story")
	public String story(Model model, @PageableDefault(sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("story", storyService.storyForm(pageable));		
		return "story/story";				
	}
	
	@GetMapping("/storyWrite")
	public String storyForm() {
		return "story/story";
	}
	
	@PostMapping("/storyWrite")
	public String storyWrite(HttpServletRequest req, StoryDto storyDto, @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
		storyService.storyWrite(req, storyDto, principal);		

		return "index";
	}
	
	@PostMapping("/storyComment")
	public String storyComment(Model model, @PageableDefault(sort="id", direction=Sort.Direction.DESC) Pageable pageable,
					StoryCommentDto storyCommentDto, @AuthenticationPrincipal PrincipalDetails principal) {

		storyService.storyComment(storyCommentDto, principal);
		model.addAttribute("story", storyService.storyForm(pageable));
		return "story/story";
	}
	
	
	@PostMapping("/storyCommentDelete")
	public String storyCommentDelete(StoryComment storyComment, Model model, @PageableDefault(sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		storyService.storyCommentDelete(storyComment.getId());	
		
		model.addAttribute("story", storyService.storyForm(pageable));
		return "story/story";
	}
	
	@PostMapping("/storyLike")
	public String storyLike(Model model, @PageableDefault(sort="id", direction=Sort.Direction.DESC) Pageable pageable,
					Story story, @AuthenticationPrincipal PrincipalDetails principal) {

		storyService.storyLike(story.getId(), principal);			

		model.addAttribute("story", storyService.storyForm(pageable));
		return "story/story";
	}
	
	@PostMapping("/storyUnLike")
	public String storyUnLike(Model model, @PageableDefault(sort="id", direction=Sort.Direction.DESC) Pageable pageable,
			Story story, @AuthenticationPrincipal PrincipalDetails principal) {
		
		storyService.storyUnLike(story.getId(), principal);	

		model.addAttribute("story", storyService.storyForm(pageable));
		return "story/story";
	}


}
