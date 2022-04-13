package com.zyl.cloud.base.api.provider.remote;

import com.zyl.cloud.base.api.provider.broken.ProviderBrokenController;
import com.zyl.cloud.base.dto.LogUser;
import com.zyl.cloud.base.pojo.App;
import com.zyl.cloud.base.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(value = "provider", fallback = ProviderBrokenController.class)
public interface ProviderFeignRemote {

	@PostMapping(value = "/icon/list")
	List<App> listIcons(@RequestBody Long id);

	@PostMapping(value = "/user/login")
	LogUser getLogUser(@RequestBody User unAuthUser);

	@PostMapping(value = "/user/findRoleByUserId")
	Set<String> findRoleByUserId(@RequestBody Long id);

	@PostMapping(value = "/user/findPermsByUserId")
	Set<String> findPermsByUserId(@RequestBody Long id);

}
