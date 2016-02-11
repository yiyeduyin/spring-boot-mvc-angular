package com.cmiracle.controller.admin;

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
import com.cmiracle.domain.ProductType;
import com.cmiracle.service.ProductTypeService;
import com.cmiracle.util.Util;

@RestController
@RequestMapping(value = "/admin/rest/productType")
public class ProductTypeCtrl {
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 创建
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String add(@RequestBody ProductType productType) {
		DTO dto = DTO.newDTO();
		try {
			if(Util.isNotNull(productType.parentProductTypeId)){
				productType.parentProductType = productTypeService.get(productType.parentProductTypeId);
			}
			productType.status = 1;
			productTypeService.save(productType);
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
			
			ProductType productType = productTypeService.get(id);
			dto.data = productType;
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
			@RequestBody ProductType newProductType) {
		DTO dto = DTO.newDTO();
		try {
			ProductType oldProductType =  productTypeService.get(id);
			
			if(Util.isNotNull(newProductType.name)){
				oldProductType.name = newProductType.name;
			}
			if(Util.isNotNull(newProductType.status)){
				oldProductType.status = newProductType.status;
			}
			if(Util.isNotNull(newProductType.parentProductTypeId)){
				oldProductType.parentProductType = productTypeService.get(newProductType.parentProductTypeId);
			}
			productTypeService.update(oldProductType);
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
			productTypeService.delete(id);
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
			@RequestParam(value = "type",required = false) final Integer type,	
			@RequestParam(value = "name",required = false) final String name,
			@RequestParam(value = "parentProductTypeId",required = false) final Integer parentProductTypeId) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = productTypeService.findList(page, size, name, status, type, parentProductTypeId);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	

}
