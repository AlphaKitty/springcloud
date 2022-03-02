package com.zyl.cloud.provider.service.mvc.service;

import com.zyl.cloud.consumer.service.mvc.remote.FeignRemote;
import org.springframework.web.bind.annotation.RestController;

// remote里面的FeignClient注解是为了让消费者发现并注入生产者的bean 而这里的RestController是为了在自身项目中创建bean
@RestController
public class FeignController implements FeignRemote {

	@Override
	public String hello() {
		// throw new RuntimeException();
		return "success";
	}

}
