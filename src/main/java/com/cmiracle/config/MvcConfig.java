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
        registry.addViewController("/profile").setViewName(frontPageRoutePrefix + "profile");
        registry.addViewController("/productType").setViewName(frontPageRoutePrefix + "productType");
        registry.addViewController("/products").setViewName(frontPageRoutePrefix + "products");
        registry.addViewController("/engineerings").setViewName(frontPageRoutePrefix + "engineerings");
        registry.addViewController("/certificates").setViewName(frontPageRoutePrefix + "certificates");
        registry.addViewController("/about").setViewName(frontPageRoutePrefix + "about");
        registry.addViewController("/products_list").setViewName(frontPageRoutePrefix + "products_list");
        registry.addViewController("/products_detail").setViewName(frontPageRoutePrefix + "products_detail");
        registry.addViewController("/message").setViewName(frontPageRoutePrefix + "message");
    }
	
	
	
	
}
