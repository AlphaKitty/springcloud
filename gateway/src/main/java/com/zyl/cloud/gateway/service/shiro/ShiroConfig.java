package com.zyl.cloud.gateway.service.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	@Bean
	public CustomDBRealm customDBRealm() {
		CustomDBRealm customDBRealm = new CustomDBRealm();
		customDBRealm.setCredentialsMatcher((token, info) -> {
			UsernamePasswordToken userToken = (UsernamePasswordToken) token;
			//要验证的明文密码
			String plaintext = new String(userToken.getPassword());
			//数据库中的加密后的密文
			String hashed = extractEncodedPassword(info.getCredentials().toString());

			return BCrypt.checkpw(plaintext, hashed);
		});
		return customDBRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(customDBRealm());
		return securityManager;
	}

	//Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> map = new HashMap<>();
		//登出
		map.put("/logout", "logout");
		//对所有用户认证
		map.put("/**", "authc");
		//登录
		shiroFilterFactoryBean.setLoginUrl("/user/login");
		//首页
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	private String extractEncodedPassword(String prefixEncodedPassword) {
		int start = prefixEncodedPassword.indexOf("}");
		return prefixEncodedPassword.substring(start + 1);
	}

}
