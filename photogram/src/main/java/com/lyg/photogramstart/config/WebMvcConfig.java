package com.lyg.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

// web 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// file:///C:/Users/yugis/sts/workspace-spring-tool-suite-4-4.9.0.RELEASE/upload/ 주소로 바꿔줌
		registry
			.addResourceHandler("/upload/**") // jsp에서 /upload/** 호출 시 발동
			.addResourceLocations("file:///"+uploadFolder)
			.setCachePeriod(60*10*6)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
}
