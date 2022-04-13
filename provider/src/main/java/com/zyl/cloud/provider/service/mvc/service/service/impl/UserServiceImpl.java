package com.zyl.cloud.provider.service.mvc.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.cloud.base.pojo.User;
import com.zyl.cloud.provider.service.mvc.service.mapper.UserMapper;
import com.zyl.cloud.provider.service.mvc.service.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
