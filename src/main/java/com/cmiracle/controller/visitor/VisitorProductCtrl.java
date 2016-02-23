package com.cmiracle.controller.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.Product;
import com.cmiracle.service.ProductService;

@RestController
@RequestMapping(value = "/rest/product")
public class VisitorProductCtrl {

	@Autowired
	private ProductService productService;

	/**
	 * 查找
	 * 
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
	 * 分页查询
	 * 
	 * @param page
	 * @param size
	 * @param status
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String list(@RequestParam(value = "pageNo", defaultValue = "1", required = false) Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size,
			@RequestParam(value = "name", required = false) final String name,
			@RequestParam(value = "productType", required = false) Integer productType,
			@RequestParam(value = "subProductType", required = false) Integer subProductType,
			@RequestParam(value = "drawingNo", required = false) final String drawingNo,
			@RequestParam(value = "isNew", required = false) Integer isNew) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = productService.findList(page, size, productType, subProductType, name, drawingNo, isNew, 1);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
