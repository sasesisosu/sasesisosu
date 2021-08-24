package com.lyg.photogramstart.web.dto.subscribe;

import com.lyg.photogramstart.domain.user.User;
import com.lyg.photogramstart.web.dto.user.UserProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

	private int userId;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;
	private Integer equalUserState;
	
}
