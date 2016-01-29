package com.cmiracle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Value("${file_upload_path}")
	private String fileUploadPath;

	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/admin/rest/fileUpload", method = RequestMethod.POST)
	public @ResponseBody String singleUpload(
			@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			String url = fileUploadPath + fileName;
			file.transferTo(new File(url));
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 文件下载
	 * @param fileName 文件名
	 * @param suffix 后缀
	 * @param response
	 */
	@RequestMapping(value = "/rest/file/{fileName}.{suffix}", method = RequestMethod.GET)
	public void getFile(@PathVariable("fileName") String fileName,
			@PathVariable("suffix") String suffix,
			HttpServletResponse response) {
		try {
			String completeName = fileName + "." + suffix;
			File file = new File(fileUploadPath + completeName);
			if(file != null){
				// get your file as InputStream
				InputStream is = new FileInputStream(file);
				// copy it to response's OutputStream
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

}
