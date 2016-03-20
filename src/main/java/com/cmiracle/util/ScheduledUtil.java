package com.cmiracle.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cmiracle.service.ProductTypeService;

@Component
public class ScheduledUtil {
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 清理缓存任务      
	 */
	@Scheduled(fixedRate = 1000*60*60*12) // 12小时
	public void reloadCache() {
		productTypeService.reloadCache();
	}

}
