package com.flows.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class PatternUtil {
	
	public static boolean isUrlMatcher(String regexUrl,String url){
		if(StringUtils.isBlank(regexUrl) || StringUtils.isBlank(url)){
			return false;
		}
		Pattern p=Pattern.compile(regexUrl); 
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

}
