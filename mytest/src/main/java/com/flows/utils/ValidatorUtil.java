package com.flows.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

	public final static String TELEPHONE_VALIDATOR = "^[1][3|5|8][0-9]{9}$";
	public final static String CODE_VALIDATOR = "^[A-Za-z\\_]{1}[A-Za-z0-9\\_\\-]{5,31}$";
	public final static String EMAIL_VALIDATOR = "\\w[-\\w.+]*@([A-Za-z0-9][-_A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
	public final static String PASSWORD_VALIDATOR = "^[A-Za-z0-9]{6,32}$";
	
	public static boolean isMobile(String mobile) {
		if (null == mobile || "".equals(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile(TELEPHONE_VALIDATOR);
		Matcher m = p.matcher(mobile);
		return m.find();
	}

	public static boolean isCode(String code) {
		if (null == code || "".equals(code)) {
			return false;
		}
		Pattern p = Pattern.compile(CODE_VALIDATOR);
		Matcher m = p.matcher(code);
		return m.find();
	}

	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		Pattern p = Pattern.compile(EMAIL_VALIDATOR);
		Matcher m = p.matcher(email);
		return m.find();
	}
	
	public static boolean isPassword(String password) {
		if(null == password || "".equals(password)) {
			return false;
		}
		Pattern p = Pattern.compile(PASSWORD_VALIDATOR);
		Matcher m = p.matcher(password);
		return m.find();
	}
	
	public static void main(String[] args) {
		String str ="\\w[-\\w.+]*@([A-Za-z0-9][-_A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher("1111@a_-a.com");
		System.out.println(matcher.matches());
		
	}
}
