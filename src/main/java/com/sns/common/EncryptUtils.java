package com.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {		// md5 방식 암호화
	// input: 비밀번호, output: 해싱된 비밀번호
	public static String md5(String message) {		// static이므로 new 필요X
		String encData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		
			byte[] bytes = message.getBytes();
	        md.update(bytes);
	        byte[] digest = md.digest();
	        
	        for(int i = 0; i < digest.length; i++ ) {
	            encData += Integer.toHexString(digest[i]&0xff);
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encData;
	}
}