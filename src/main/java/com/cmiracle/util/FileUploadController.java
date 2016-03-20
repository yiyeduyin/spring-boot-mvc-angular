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

import com.cmiracle.comment.DTO;

@RestController
public class FileUploadController {

	@Value("${file_upload_path}")
	private String fileUploadPath;

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/admin/rest/fileUpload", method = RequestMethod.POST)
	public @ResponseBody String singleUpload(@RequestParam("file") MultipartFile file) {
		DTO dto = DTO.newDTO();
		try {
			String uploadPath = getfileUploadPath();
			createdFolderIfNotExists(uploadPath);
			String fileName = file.getOriginalFilename();
			String url = uploadPath + fileName;
			file.transferTo(new File(url));
			dto.data = fileName;
			return dto.toJson();
		} catch (IOException e) {
			e.printStackTrace();
			dto.errMsg = "error";
			dto.errCode = -1;
			return dto.toJson();
		}

	}

	/**
	 * 文件下载
	 * 
	 * @param fileName
	 *            文件名
	 * @param suffix
	 *            后缀
	 * @param response
	 */
	@RequestMapping(value = "/rest/file/{fileName}.{suffix}", method = RequestMethod.GET)
	public void getFile(@PathVariable("fileName") String fileName, @PathVariable("suffix") String suffix,
			HttpServletResponse response) {
		try {
			String completeName = fileName + "." + suffix;
			setFileType(suffix, response, completeName);
			
			File file = new File(getfileUploadPath() + completeName);
			if (file != null) {
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

	/**
	 * 设置返回文件类型
	 * @param suffix
	 * @param response
	 * @param completeName
	 */
	private void setFileType(String suffix, HttpServletResponse response, String completeName) {
		if (suffix.equals("pdf")) {
			response.setContentType("application/pdf");
		}
		if (!suffix.equals("pdf") && !suffix.equals("gif") && !suffix.equals("jpg") && !suffix.equals("jpeg")
				&& !suffix.equals("png")) { //不是照片,pdf则下载
			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			// 2.设置文件头，下载的文件名
			response.setHeader("Content-Disposition", "attachment;fileName=" + completeName);
		}
	}
	
	private String getfileUploadPath(){
		return System.getProperty("user.dir") + fileUploadPath;
	}

	/**
	 * 如果目标路径不存在则创建
	 * 
	 * @param path
	 */
	private void createdFolderIfNotExists(String path) {
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
	}

}
