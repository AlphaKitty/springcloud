package com.zyl.cloud.gateway.service.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/gateway")
public class GatewayController {

	@GetMapping("/get/msg")
	public String getMessage() {
		return "null";
	}

}
