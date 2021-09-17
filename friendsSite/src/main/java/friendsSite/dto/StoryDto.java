package friendsSite.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class StoryDto {

	private MultipartFile imageUrl;	
	private String storyAccount;
}
