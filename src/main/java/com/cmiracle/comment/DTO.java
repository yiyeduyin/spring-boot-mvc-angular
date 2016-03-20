package com.cmiracle.comment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class DTO {

	@JSONField(name = "errcode")
	public Integer errCode  = 0 ;
	
	@JSONField(name = "errmsg")
	public String errMsg = "success";
	
	public Object data;
	
	public static DTO newDTO(){
		return new DTO();
	}
	
	public String toJson(){
		return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
	}
	
}
