package com.zyl.cloud.base.api.provider.broken;

import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.pojo.App;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// remote里面的FeignClient注解是为了让消费者发现并注入生产者的bean 而这里的RestController是为了在自身项目中创建bean
@RestController
public class ProviderBrokenController implements ProviderFeignRemote {

	@Override
	public List<App> listIcons() {
		return null;
	}

}
