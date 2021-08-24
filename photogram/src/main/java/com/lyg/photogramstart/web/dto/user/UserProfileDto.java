package com.lyg.photogramstart.web.dto.user;

import com.lyg.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	
	private boolean pageOwnerState;
	private int imageCount;
	private boolean subscribeState;
	private int subscribeCount;
	private int subscriberCount;
	private User user;
}
