package friendsSite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReplyDto {
	private int userId;
	private int boardId;
	private String content;
	
}
