package com.zyl.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	// 网关服务可以通过正常网关路径+服务名+服务地址访问其他服务
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
