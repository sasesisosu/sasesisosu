package com.lyg.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	@NotBlank
	private String content;
	
	@NotNull
	private Integer imageId;
}
