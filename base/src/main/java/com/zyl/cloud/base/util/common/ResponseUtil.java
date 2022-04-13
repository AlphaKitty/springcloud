package com.zyl.cloud.base.util.common;

/**
 * 标准出口
 */
public class ResponseUtil {

	private static final String SUCCESS = "success";
	private static final int SUCCESS_CODE = 1;

	private ResponseUtil() {
	}

	public static Response success(Object data) {
		return success(SUCCESS, data);
	}

	public static Response success(String info, Object data) {
		return new Response(true, SUCCESS_CODE, info, data);
	}

	public static Response error(int code, String errorMessage, Object data) {
		return new Response(false, code, errorMessage, data);
	}

	public static Response error(String errorMessage, Object data) {
		return new Response(false, 0, errorMessage, data);
	}

}
