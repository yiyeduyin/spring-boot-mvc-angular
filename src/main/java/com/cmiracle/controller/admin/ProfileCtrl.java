package com.cmiracle.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.SystemProperty;
import com.cmiracle.service.SystemPropertyService;

@RestController
@RequestMapping(value = "/admin/rest/system")
public class ProfileCtrl {
	
	@Value("${profileCode}")
	private String profileCode;
	
	@Value("${titleCode}")
	private String titleCode;

	@Autowired
	private SystemPropertyService systemPropertyService;

	/**
	 * 更新
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String add(@RequestBody JSONObject property) {
		DTO dto = DTO.newDTO();
		try {
			SystemProperty oldProperty = systemPropertyService.findByCode(profileCode);
			if(oldProperty == null){
				oldProperty = new SystemProperty();
				oldProperty.code = profileCode;
			}
			oldProperty.icon = property.getString("icon");
			oldProperty.value = property.getString("value");
			systemPropertyService.save(oldProperty);
			return dto.toJson();
		} catch (Exception e) {
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
	
	
	/**
	 * 更新
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String updateByCode(@PathVariable("code") String code, @RequestBody JSONObject property) {
		DTO dto = DTO.newDTO();
		try {
			SystemProperty oldProperty = systemPropertyService.findByCode(code);
			if(oldProperty == null){
				oldProperty = new SystemProperty();
				oldProperty.code = code;
			}
			oldProperty.icon = property.getString("icon");
			oldProperty.value = property.getString("value");
			systemPropertyService.save(oldProperty);
			return dto.toJson();
		} catch (Exception e) {
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
	
}
