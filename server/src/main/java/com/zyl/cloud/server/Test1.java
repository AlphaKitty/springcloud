package com.zyl.cloud.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "test.zyl")
public class Test1 {

	private String var1;

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

}
