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
			if(Util.isNotNull(product.subProductTypeId)){
				product.subProductType = producttypeService.get(product.subProductTypeId);
			}
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
			dto.data = productService.get(id);
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
			update(newProduct, oldProduct);
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


	private void update(Product newProduct, Product oldProduct) {
		if(Util.isNotNull(newProduct.name)){
			oldProduct.name = newProduct.name;
		}
		if(Util.isNotNull(newProduct.productTypeId)){
			oldProduct.productType = producttypeService.get(newProduct.productTypeId);
		}
		if(Util.isNotNull(newProduct.subProductTypeId)){
			oldProduct.subProductType = producttypeService.get(newProduct.subProductTypeId);
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
		if(Util.isNotNull(newProduct.drawingNo)){
			oldProduct.drawingNo = newProduct.drawingNo;
		}
		if(Util.isNotNull(newProduct.coreType)){
			oldProduct.coreType = newProduct.coreType;
		}
		if(Util.isNotNull(newProduct.row)){
			oldProduct.row = newProduct.row;
		}
		if(Util.isNotNull(newProduct.pitch)){
			oldProduct.pitch = newProduct.pitch;
		}
		if(Util.isNotNull(newProduct.pins)){
			oldProduct.pins = newProduct.pins;
		}
		if(Util.isNotNull(newProduct.style)){
			oldProduct.style = newProduct.style;
		}
		if(Util.isNotNull(newProduct.ml)){
			oldProduct.ml = newProduct.ml;
		}
		if(Util.isNotNull(newProduct.sec)){
			oldProduct.sec = newProduct.sec;
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
			@RequestParam(value = "productType", required = false) Integer productType,
			@RequestParam(value = "subProductType", required = false) Integer subProductType,
			@RequestParam(value = "status",required = false) final Integer status,			
			@RequestParam(value = "name",required = false) final String name,
			@RequestParam(value = "isNew", required = false) Integer isNew) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = productService.findList(page, size, productType, subProductType, name, isNew, status);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
