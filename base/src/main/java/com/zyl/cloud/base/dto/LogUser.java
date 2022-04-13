package com.zyl.cloud.base.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.base.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * 带角色信息的用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogUser extends User implements Serializable {

	/**
	 * 用户对应的角色集合
	 */
	@TableField(exist = false)
	// @OneToMany
	// @JoinColumn(name = "user_id", referencedColumnName = "role_id")
	private Set<Role> roles;

}
