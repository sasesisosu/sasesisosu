package friendsSite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import friendsSite.dto.CMRespDto;
import friendsSite.dto.StoryDto;
import friendsSite.service.StoryService;

@RestController
public class StoryApiController {

	@Autowired
	private StoryService storyService;
	
	
}
