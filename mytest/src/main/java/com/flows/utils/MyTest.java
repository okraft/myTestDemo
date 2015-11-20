package com.flows.utils;



import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.codec.Base64;


public class MyTest {
	
	public static void main(String[] args) {
		
	}

	/**
	 * Base 64 转换 
	 */
	private static void Base64Test() {
		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes());
		String str2 = Base64.decodeToString(base64Encoded);
		System.out.println( str.equals(str2) );
	}

	/**
	 * 转换测试
	 */
	private static void StringEscapeUtilsTest() {
		String sql="1' or '1'='1";  
//        System.out.println("防SQL注入:"+StringEscapeUtils.escapeSql(sql)); //防SQL注入  
          
        System.out.println("转义HTML,注意汉字:"+StringEscapeUtils.escapeHtml3("<font>chen磊  xing</font>"));    //转义HTML,注意汉字  
        System.out.println("反转义HTML:"+StringEscapeUtils.unescapeHtml3("&lt;font&gt;chen磊  xing&lt;/font&gt;"));  //反转义HTML  
          
        System.out.println("转成Unicode编码："+StringEscapeUtils.escapeJava("陈磊兴"));     //转义成Unicode编码  
          
        System.out.println("转义XML："+StringEscapeUtils.escapeXml("<name>陈磊兴</name>"));   //转义xml  
        System.out.println("反转义XML："+StringEscapeUtils.unescapeXml("&lt;name&gt;陈磊兴&lt;/name&gt;"));    //转义xml  
	}

}
