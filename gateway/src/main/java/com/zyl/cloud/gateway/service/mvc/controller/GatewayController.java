package com.zyl.cloud.gateway.service.mvc.controller;

import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.pojo.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/gateway")
public class GatewayController {

	@Autowired
	private ProviderFeignRemote providerFeignRemote;

	@PostMapping("/icon/list")
	public List<App> listIcons() {
		return providerFeignRemote.listIcons();
	}

}
