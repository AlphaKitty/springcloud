package com.zyl.cloud.provider.service.mvc.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.provider.service.mvc.service.mapper.RoleMapper;
import com.zyl.cloud.provider.service.mvc.service.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
