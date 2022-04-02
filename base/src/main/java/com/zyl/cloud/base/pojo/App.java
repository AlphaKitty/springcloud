package com.zyl.cloud.base.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 应用表
 *
 * @author 张代富
 * @since 2022-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class App implements Serializable {

	private Long id;

	/**
	 * 图标地址
	 */
	private String icon;

	/**
	 * 图标标题
	 */
	private String title;

	/**
	 * 跳转链接
	 */
	private String url;

	/**
	 * 顺序
	 */
	private Integer sort;

	/**
	 * 图标类型 1网络连接 2本地路径
	 */
	private Integer type;

}
