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
        
        
        
        //前端  中文
        registry.addViewController("/").setViewName(frontPageRoutePrefix + "index");
        registry.addViewController("/profile").setViewName(frontPageRoutePrefix + "profile");
        registry.addViewController("/productType").setViewName(frontPageRoutePrefix + "productType");
        registry.addViewController("/products").setViewName(frontPageRoutePrefix + "products");
        registry.addViewController("/engineerings").setViewName(frontPageRoutePrefix + "engineerings");
        registry.addViewController("/certificates").setViewName(frontPageRoutePrefix + "certificates");
        registry.addViewController("/about").setViewName(frontPageRoutePrefix + "about");
        registry.addViewController("/search").setViewName(frontPageRoutePrefix + "search");
        registry.addViewController("/message").setViewName(frontPageRoutePrefix + "message");
        
      //前端  英文
        registry.addViewController("/e_").setViewName(frontPageRoutePrefix + "e_index");
        registry.addViewController("/e_profile").setViewName(frontPageRoutePrefix + "e_profile");
        registry.addViewController("/e_productType").setViewName(frontPageRoutePrefix + "e_productType");
        registry.addViewController("/e_products").setViewName(frontPageRoutePrefix + "e_products");
        registry.addViewController("/e_engineerings").setViewName(frontPageRoutePrefix + "e_engineerings");
        registry.addViewController("/e_certificates").setViewName(frontPageRoutePrefix + "e_certificates");
        registry.addViewController("/e_about").setViewName(frontPageRoutePrefix + "e_about");
        registry.addViewController("/e_search").setViewName(frontPageRoutePrefix + "e_search");
        registry.addViewController("/e_message").setViewName(frontPageRoutePrefix + "e_message");
    }
	
	
	
	
}
