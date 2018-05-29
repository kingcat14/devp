package com.yunkang.saas.common.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

/**
 * 用户密码加密工具
 * 
 * @auditor XinmingYan @time 2017年6月9日 下午6:14:31 @versions 0.0.1
 * @packagePath com.yunkanghealth.ykuser.shiro.PasswordEncrypt.java
 */
public class PasswordEncrypt {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	public static String md5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] encryptData = md5.digest(str.getBytes(DEFAULT_URL_ENCODING));
			String md5Result = Hex.encodeHexString(encryptData);
			return md5Result;
		} catch (Exception e) {
			throw new RuntimeException("Error: PasswordEncrypt.md5 msg:{}", e);
		}
	}

	public static void main(String[] args) {
		System.out.println(md5("111111"));
	}

}
