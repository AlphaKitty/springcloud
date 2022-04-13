package com.zyl.cloud.base.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

	/**
	 * id
	 */
	// @TableId(value = "user_id")
	private Long id;

	/**
	 * 登录名
	 */
	private String name;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * mac地址
	 */
	private String mac;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 用户状态 1正常 0禁用
	 */
	private Integer state;

}
