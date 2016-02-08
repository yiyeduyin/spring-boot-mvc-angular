package com.cmiracle.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 密码加密
 * @author chenjiarong
 *
 */
@Service
public class PasswordEncodeUtil {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	
	@Value("${encryptKey}")
	private String encryptKey;

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public String encode(String encryptText) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return Hex.encodeHexString(mac.doFinal(text));
	}
}
