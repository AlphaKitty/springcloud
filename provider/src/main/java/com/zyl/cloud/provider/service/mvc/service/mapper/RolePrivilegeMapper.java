package com.zyl.cloud.provider.service.mvc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.cloud.base.pojo.RolePrivilege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 角色-权限表 Mapper 接口
 *
 * @author 张代富
 * @since 2022-04-07
 */
public interface RolePrivilegeMapper extends BaseMapper<RolePrivilege> {

	@Select("select distinct p.perms" +
	        " from privilege p," +
	        "     role_privilege rp," +
	        "     user_role ur" +
	        " where ur.user_id = #{userId}" +
	        "  and ur.role_id = rp.role_id" +
	        "  and rp.role_id = p.id" +
	        "  and p.perms is not null" +
	        "  and p.perms != ''")
	Set<String> findPermsByUserId(@Param("userId") Long id);

}
