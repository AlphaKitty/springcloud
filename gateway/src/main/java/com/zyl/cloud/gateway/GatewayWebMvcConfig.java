package com.zyl.cloud.gateway;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 这个类对应web.xml的DispatcherServlet或者springmvc.xml的配置 所以要专门扫描Controller
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zyl.cloud.gateway.service.mvc.controller")
public class GatewayWebMvcConfig implements WebMvcConfigurer {

	/**
	 * 自定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(interceptor)
		//         .addPathPatterns("/**");
	}


	/**
	 * 自定义跨域
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		        // .allowedOriginPatterns("*")
		        // TODO: 2022/5/30 zylTodo 暂时解决跨域问题
		        .allowedOrigins("http://192.168.2.20:8040")
		        .allowedMethods("GET", "POST", "PUT", "OPTION", "DELETE")
		        .allowCredentials(true)
		        .maxAge(3600)
		        .allowedHeaders("*");
	}

}
