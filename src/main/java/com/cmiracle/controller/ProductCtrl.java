package com.cmiracle.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.Product;
import com.cmiracle.service.ProductService;

@RestController
public class ProductCtrl {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 创建
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/rest/product/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String created(@RequestBody Product product) {
		DTO dto = DTO.newDTO();
		try {
			product.created = new Date();
			product.status = 1;
			productService.save(product);
			return dto.toJson();
		} catch (Exception e) {
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}

}
