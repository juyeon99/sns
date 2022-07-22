package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	// Web의 이미지 주소와 실제 파일경로를 mapping해주는 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")	// 이미지의 실제 웹 주소	ex) http://localhost:8080/images/userid_189643326/image.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);	// 실제 파일이 있는 곳
//		.addResourceLocations("file:/" + FileManagerService.FILE_UPLOAD_PATH);		//(on MAC)
	}
}
