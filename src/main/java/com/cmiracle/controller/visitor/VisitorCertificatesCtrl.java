package com.cmiracle.controller.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cmiracle.comment.DTO;
import com.cmiracle.comment.MediaType;
import com.cmiracle.service.CertificatesService;

@RestController
@RequestMapping(value = "/rest/certificates")
public class VisitorCertificatesCtrl {

	@Autowired
	private CertificatesService certificatesService;

	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String list() {
		DTO dto = DTO.newDTO();
		try {
			dto.data = certificatesService.findList(1, 99, null);
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
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8)
	public @ResponseBody String test() {
		try {
			JSONObject object = new JSONObject();
			object.put("key", "test");
			return object.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
