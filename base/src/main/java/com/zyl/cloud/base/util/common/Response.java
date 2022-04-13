package com.zyl.cloud.base.util.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

	// 是否成功
	private boolean ok;
	// 错误码
	private int code;
	// 附加信息
	private String message;
	// 返回实体
	private Object data;

}
