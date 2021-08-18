package com.lyg.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException{

	// 객체 구분
	private static final long serialVersionUID = 1L;

	
	public CustomException(String message) {
		super(message);
	}

}
