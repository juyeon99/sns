package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//Spring Bean
@Component		// Controller, service, repository
public class FileManagerService {
	public final static String FILE_UPLOAD_PATH = "D:\\web_dev_juyeon\\spring_project\\sns\\workspace\\images/";
//	public final static String FILE_UPLOAD_PATH = "/Users/jenniferhong/OneDrive/spring/sns/images/";	// on MAC
	
	// input: MultipartFile, userLoginId
	// output: String path
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일명이 겹치지 않게 하기 위해서 userLoginId, 현재시간을 경로(폴더)에 붙여준다.
		// 파일 디렉토리 경로	ex) userid_189643326/image.jpg
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";	// ex) userid_189643326/
		String filePath = FILE_UPLOAD_PATH + directoryName;		// ex) D:\\web_dev_juyeon\\spring_project\\memo\\workspace\\images/userid_189643326/
		
		// Directory 만들기
		File directory = new File(filePath);
		if(directory.mkdir() == false) {	// if making dir fails
			return null;
		}
		
		// 파일 업로드: byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes();
			String imageFileName = file.getOriginalFilename();	// TODO 한글인 파일명을 로마자로 변환
			Path path = Paths.get(filePath + imageFileName);
			Files.write(path, bytes);	// path에 파일(bytes)을 업로드
			
			// 이미지 업로드 성공. Return the path. (WebMvcConfig에서 mapping한 이미지 path)
			// ex) http://localhost:8080/images/userid_189643326/image.jpg
			return "/images/" + directoryName + imageFileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
