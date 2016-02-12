package com.cmiracle.controller.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.service.ProductTypeService;

@RestController
@RequestMapping(value = "/rest/productType")
public class VisitorProductTypeCtrl {

	@Autowired
	private ProductTypeService productTypeService;

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
			@RequestParam(value = "type",required = false) final Integer type,	
			@RequestParam(value = "name",required = false) final String name,
			@RequestParam(value = "parentProductTypeId",required = false) final Integer parentProductTypeId) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = productTypeService.findList(page, size, name, 1, type, parentProductTypeId);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}

}
