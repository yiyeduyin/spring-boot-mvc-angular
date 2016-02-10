package com.cmiracle.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.Product;
import com.cmiracle.service.ProductService;
import com.cmiracle.service.ProductTypeService;
import com.cmiracle.util.Util;

@RestController
@RequestMapping(value = "/admin/rest/product")
public class ProductCtrl {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductTypeService producttypeService;
	
	/**
	 * 创建
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String created(@RequestBody Product product) {
		DTO dto = DTO.newDTO();
		try {
			if(!Util.isNotNull(product.productTypeId)){
				dto.errMsg = "产品类型为空";
				dto.errCode = 1;
				return dto.toJson();
			}
			product.productType = producttypeService.get(product.productTypeId);
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

	
	/**
	 * 查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String findById(@PathVariable("id") Long id) {
		DTO dto = DTO.newDTO();
		try {
			Product product = productService.get(id);
			dto.data = product;
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	/**
	 * 更新
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String update(@PathVariable("id") Long id,
			@RequestBody Product newProduct) {
		DTO dto = DTO.newDTO();
		try {
			Product oldProduct =  productService.get(id);
			
			if(Util.isNotNull(newProduct.name)){
				oldProduct.name = newProduct.name;
			}
			if(Util.isNotNull(newProduct.productTypeId)){
				oldProduct.productType = producttypeService.get(newProduct.productTypeId);
			}
			if(Util.isNotNull(newProduct.isNew)){
				oldProduct.isNew = newProduct.isNew;
			}
			if(Util.isNotNull(newProduct.description)){
				oldProduct.description = newProduct.description;
			}
			if(Util.isNotNull(newProduct.spec)){
				oldProduct.spec = newProduct.spec;
			}
			if(Util.isNotNull(newProduct.status)){
				oldProduct.status = newProduct.status;
			}
			oldProduct.icon = newProduct.icon;
			oldProduct.fileName = newProduct.fileName;
			productService.update(oldProduct);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String changeStatus(@PathVariable("id") Long id) {
		DTO dto = DTO.newDTO();
		try {
			productService.delete(id);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @param status
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String list(
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size,
			@RequestParam(value = "status",required = false) final Integer status,			
			@RequestParam(value = "typeName",required = false) final String typeName) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = productService.findList(page, size, typeName, status);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
