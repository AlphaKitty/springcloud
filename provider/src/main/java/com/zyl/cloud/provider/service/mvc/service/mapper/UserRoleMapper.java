package com.zyl.cloud.provider.service.mvc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.base.pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 用户-角色表 Mapper 接口
 *
 * @author 张代富
 * @since 2022-04-07
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	@Select("select b.*" +
	        " from user_role a," +
	        "     role b" +
	        " where a.role_id = b.id")
	@ResultMap(value = "BaseResultMap")
	Set<Role> loginByUserParam(Long userId);

	@Select("select b.name" +
	        " from user_role a," +
	        "     role b" +
	        " where a.role_id = b.id and a.user_id = #{userId}")
	Set<String> findRoleByUserId(@Param("userId") Long id);

}
