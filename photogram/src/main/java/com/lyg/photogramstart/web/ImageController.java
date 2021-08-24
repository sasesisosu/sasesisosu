package com.lyg.photogramstart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lyg.photogramstart.config.auth.PrincipalDetails;
import com.lyg.photogramstart.domain.image.Image;
import com.lyg.photogramstart.handler.ex.CustomValidationException;
import com.lyg.photogramstart.service.ImageService;
import com.lyg.photogramstart.web.dto.image.ImageUploadDto;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	@GetMapping({"/", "/image/story"})
	public String story() {
		return "image/story";
	}
	
	@GetMapping("/image/popular")
	public String popular(Model model) {
		
		List<Image> images = imageService.popular();
		
		model.addAttribute("images", images);
		
		return "image/popular";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(imageUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
		}
		imageService.imageUpload(imageUploadDto, principalDetails);
		
		return "redirect:/user/" + principalDetails.getUser().getId();
	}
}


















