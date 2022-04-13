package com.zyl.cloud.base.util.common;

import java.util.regex.Pattern;

public class UserUtil {

	public final static String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\\d{8}$";
	public final static String EMAIL_PATTERN = "\"^\\\\w+([-+.]\\\\w+)*@\\\\w+([-.]\\\\w+)*\\\\.\\\\w+([-.]\\\\w+)*$\"";

	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(email).matches();
	}


	public static boolean isPhone(String phone) {
		if (phone == null || phone.length() < 1 || phone.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		return pattern.matcher(phone).matches();
	}

}
