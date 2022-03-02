package com.zyl.cloud.consumer.service.mvc.remote;

import com.zyl.cloud.consumer.service.mvc.controller.FeignBrokenController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider", fallback = FeignBrokenController.class)
public interface FeignRemote {

	@GetMapping(value = "/test/hello")
	String hello();

}
