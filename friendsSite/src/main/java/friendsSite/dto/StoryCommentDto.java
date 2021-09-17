package friendsSite.dto;

import lombok.Data;

@Data
public class StoryCommentDto {
	
	private int storyId;
	private int storyUser;
	private String storyComment;
}
