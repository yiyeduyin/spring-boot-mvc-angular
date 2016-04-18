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
import com.cmiracle.domain.Contact;
import com.cmiracle.service.ContactService;

@RestController
@RequestMapping(value = "/admin/rest/contact")
public class ContactCtrl {
	
	@Autowired
	private ContactService contactService;
	
	/**
	 * 创建
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String created(@RequestBody Contact contact) {
		DTO dto = DTO.newDTO();
		try {
			contactService.save(contact);
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
			dto.data = contactService.get(id);
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
			@RequestBody Contact newContact) {
		DTO dto = DTO.newDTO();
		try {
			Contact oldContact =  contactService.get(id);
			oldContact.icon = newContact.icon;
			oldContact.name = newContact.name;
			oldContact.detailName = newContact.detailName;
			oldContact.address = newContact.address;
			oldContact.mobile = newContact.mobile;
			oldContact.fax = newContact.fax;
			oldContact.website = newContact.website;
			oldContact.orderIndex = newContact.orderIndex;
			contactService.update(oldContact);
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
	public String delete(@PathVariable("id") Long id) {
		DTO dto = DTO.newDTO();
		try {
			contactService.delete(id);
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
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size) {
		DTO dto = DTO.newDTO();
		try {
			dto.data = contactService.findList(page, size);
			return dto.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
