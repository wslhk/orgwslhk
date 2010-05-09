package org.wslhk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * check string format 
 * example email ,phone and so on
 * 
 * @author wslhk@163.com
 *
 */
public class FormatCheck {
	static String username="^[a-zA-Z][a-zA-Z0-9_]{5,15}$";
	static String email="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	/**
	 * 验证
	 * @param regex 正则表达式
	 * @param str 需要验证的内容
	 * @return
	 */
	public static boolean check(String regex,String str){
		Pattern p=Pattern.compile(regex);
	    Matcher m=p.matcher(str);
	    //System.out.println(m.find());
		return m.find();
	}

	/**
	 * 验证用户名
	 * @param str
	 * @return
	 */
	public static boolean checkUsername(String str){
		return check(username,str);
	}

	/**
	 * check email
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str){
		return check(email,str);
	}

	public static void main(String[] str){
		String username2="as@a.dds";

	    System.out.println(checkEmail(username2));
		//return false;
	}
}
