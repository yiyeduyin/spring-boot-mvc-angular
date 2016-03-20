package com.cmiracle.controller.visitor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.domain.Message;
import com.cmiracle.service.MessageService;

@RestController
@RequestMapping(value = "/rest/message")
public class VisitorMessageCtrl {

	@Autowired
	private MessageService messageService;

	/**
	 * 创建
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8)
	@ResponseBody
	public String add(@RequestBody Message message) {
		DTO dto = DTO.newDTO();
		try {
			message.status = 1;
			message.created = new Date();
			messageService.save(message);
			return dto.toJson();
		} catch (Exception e) {
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}
	}
}
