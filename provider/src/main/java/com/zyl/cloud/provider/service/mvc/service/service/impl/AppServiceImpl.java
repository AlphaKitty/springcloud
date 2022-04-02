package com.zyl.cloud.provider.service.mvc.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.cloud.base.pojo.App;
import com.zyl.cloud.provider.service.mvc.service.mapper.AppMapper;
import com.zyl.cloud.provider.service.mvc.service.service.IAppService;
import org.springframework.stereotype.Service;

/**
 * 应用表 服务实现类
 *
 * @author 张代富
 * @since 2022-04-01
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {

}
