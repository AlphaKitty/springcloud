package com.zyl.cloud.consumer.service.mvc.controller;

import com.zyl.cloud.consumer.service.mvc.remote.FeignRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private FeignRemote feignRemote;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		// 负载均衡1
		return restTemplate.getForEntity("http://PROVIDER/hello", String.class).getBody();

		// 负载均衡2
		// ServiceInstance provider = loadBalancerClient.choose("provider");
		// URI helloUri = URI.create(String.format("http://%s:%s/hello", provider.getHost(), provider.getPort()));
		// return new RestTemplate().getForEntity(helloUri, String.class).getBody();
	}

	@RequestMapping(value = "/feign", method = RequestMethod.GET)
	public String test() {
		// System.out.println(hello);
		return feignRemote.hello();
	}

}
