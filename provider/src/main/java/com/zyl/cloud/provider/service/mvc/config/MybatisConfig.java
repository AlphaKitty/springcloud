package com.zyl.cloud.provider.service.mvc.config;

import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisConfig
 * Description: Mybatis配置类
 * Author: zyl
 * Date: 2019/10/16 11:03
 */
@Configuration
@MapperScan(value = "com.zyl.cloud.provider.service.mvc.service.mapper")
public class MybatisConfig {

	@Bean
	// 分页注入
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	// 逻辑删除
	public LogicDeleteByIdWithFill logicSqlInjector() {
		return new LogicDeleteByIdWithFill();
	}

}
