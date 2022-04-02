package com.zyl.cloud.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 服务调用 如果要找服务提供者的feignClient 所以要加提供者的包路径 但好像会导致feign因为循环调用集成不了hystrix
@EnableFeignClients(basePackages = {"com.zyl.cloud.base.api.provider.remote"})
// Hystrix服务熔断
@EnableCircuitBreaker
// Hystrix监控
@EnableHystrixDashboard
// 服务注册
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"com.zyl.cloud.consumer.service.mvc",
		"com.zyl.cloud.base.api.provider.broken"
})
public class ConsumerApplication {

	// @Bean
	// public RestTemplate restTemplate(RestTemplateBuilder builder) {
	// 	return builder.build();
	// }

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 解决Feign Failed opening connection to http://localhost:8890/hystrix.stream : 404 : HTTP/1.1 404
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}

}
