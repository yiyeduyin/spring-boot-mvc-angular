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
import com.cmiracle.domain.Engineerings;
import com.cmiracle.service.EngineeringsService;
import com.cmiracle.util.Util;

@RestController
@RequestMapping(value = "/admin/rest/engineerings")
public class EngineeringsCtrl {

	@Autowired
	private EngineeringsService engineeringsService;

	/**
	 * 创建
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String add(@RequestBody Engineerings Engineerings) {
		DTO dto = DTO.newDTO();
		try {
			engineeringsService.save(Engineerings);
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String findById(@PathVariable("id") Long id) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = engineeringsService.get(id);
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
			@RequestBody Engineerings newEngineerings) {
		DTO dto = DTO.newDTO();
		try {
			Engineerings oldEngineerings =  engineeringsService.get(id);
			
			if(Util.isNotNull(newEngineerings.name)){
				oldEngineerings.name = newEngineerings.name;
			}
			if(Util.isNotNull(newEngineerings.orderd)){
				oldEngineerings.orderd = newEngineerings.orderd;
			}
			oldEngineerings.picture = newEngineerings.picture;
			engineeringsService.update(oldEngineerings);
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
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String changeStatus(@PathVariable("id") Long id) {
		DTO dto = DTO.newDTO();
		try {
			engineeringsService.delete(id);
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
			@RequestParam(value = "name", required = false) final String name) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = engineeringsService.findList(page, size, name);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
