package com.cmiracle.controller.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.service.EngineeringsService;

@RestController
@RequestMapping(value = "/rest/engineerings")
public class VisitorEngineeringsCtrl {

	@Autowired
	private EngineeringsService engineeringsService;

	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String list() {
		DTO dto = DTO.newDTO();
		try {
			dto.data = engineeringsService.findList(1, 99, null);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
