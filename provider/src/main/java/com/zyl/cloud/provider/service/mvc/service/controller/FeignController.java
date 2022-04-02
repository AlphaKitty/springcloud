package com.zyl.cloud.provider.service.mvc.service.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.pojo.App;
import com.zyl.cloud.provider.service.mvc.service.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// remote里面的FeignClient注解是为了让消费者发现并注入生产者的bean 而这里的RestController是为了在自身项目中创建bean
// 所以不能加@RequestMapping 而且必须用RestController(不太懂)
@RestController
public class FeignController implements ProviderFeignRemote {

	@Autowired
	private IAppService appService;

	@Override
	public List<App> listIcons() {
		// throw new RuntimeException();
		Wrapper<App> queryWrapper = new QueryWrapper<>();
		return appService.list(queryWrapper);
	}

}
