package com.zyl.cloud.base.api.provider.remote;

import com.zyl.cloud.base.api.provider.broken.ProviderBrokenController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider", fallback = ProviderBrokenController.class)
public interface ProviderFeignRemote {

	@GetMapping(value = "/test/hello")
	String hello();

}
