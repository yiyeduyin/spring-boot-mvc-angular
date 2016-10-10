package com.cmiracle.controller.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.SystemProperty;
import com.cmiracle.service.SystemPropertyService;

@RestController
@RequestMapping(value = "/rest/system")
public class VisitorProfileCtrl {
	
	@Value("${profileCode}")
	private String profileCode;
	
	@Value("${titleCode}")
	private String titleCode;

	@Autowired
	private SystemPropertyService systemPropertyService;

	

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String find() {
		DTO dto = DTO.newDTO();
		try {
			SystemProperty profile = systemPropertyService.findByCode(profileCode);
			dto.data = profile;
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String findByCode(@PathVariable("code") String code) {
		DTO dto = DTO.newDTO();
		try {
			SystemProperty profile = systemPropertyService.findByCode(code);
			dto.data = profile;
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}


}
