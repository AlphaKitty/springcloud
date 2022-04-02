package com.zyl.cloud.base.api.provider.remote;

import com.zyl.cloud.base.api.provider.broken.ProviderBrokenController;
import com.zyl.cloud.base.pojo.App;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "provider", fallback = ProviderBrokenController.class)
public interface ProviderFeignRemote {

	@PostMapping(value = "/app/icon/list")
	List<App> listIcons();

}
