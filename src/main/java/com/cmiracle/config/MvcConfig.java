package com.cmiracle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * 前端页面路径
	 */
	private final String frontPageRoutePrefix = "front/views/";

	/**
	 * 后台页面路径
	 */
	private final String backstagePageRoutePrefix = "backstage/views/";

	
	/**
	 * 设置view属性
	 * @return
	 */
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver () {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

	/**
	 * 添加view
	 */
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		
		//管理后台
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName(backstagePageRoutePrefix + "index");
        
        
        
        //前端
        registry.addViewController("/").setViewName(frontPageRoutePrefix + "index");
        
    }
	
	
	
}