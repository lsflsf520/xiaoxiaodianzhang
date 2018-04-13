package com.fengshang.xiaodianbao.base.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qcloud.cos.utils.IOUtils;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.constant.GlobalConstant;
import com.xyz.tools.common.utils.BaseConfig;
import com.xyz.tools.common.utils.DateUtil;
import com.xyz.tools.common.utils.ImageUtils;
import com.xyz.tools.common.utils.LogUtils;
import com.xyz.tools.common.utils.RandomUtil;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private final static String FILE_STORAGE_PATH = BaseConfig.getValue("sys.upload.image.path");

	@RequestMapping("/image")
	@ResponseBody
	public ResultModel imgUpload(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取文件需要上传到的路径
		File dir = new File(FILE_STORAGE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		LogUtils.debug("path=%s", FILE_STORAGE_PATH);

		request.setCharacterEncoding("utf-8"); // 设置编码
		// 获得磁盘文件条目工厂
		// DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话,上传大的文件会占用很多内存，
		// 设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
		/**
		 * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上， 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
		 * 然后再将其真正写到对应目录的硬盘上
		 */
		// factory.setRepository(dir);
		// 设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
		// factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		// ServletFileUpload upload = new ServletFileUpload(factory);
		InputStream in = null;
		OutputStream out = null;
		String accessUri = "";
		try {

			/*
			 * List<FileItem> list = upload.parseRequest(request); FileItem picture = null;
			 * for (FileItem item : list) { // 获取表单的属性名字 String name = item.getFieldName();
			 * // 如果获取的表单信息是普通的 文本 信息 if (item.isFormField()) { // 获取用户具体输入的字符串 String value
			 * = item.getString(); request.setAttribute(name, value);
			 * LogUtils.debug("name=%s,value=%s", name, value); } else { picture = item; } }
			 */

			// 自定义上传图片的名字为userId.jpg
			String destPath = genImgPath();
			LogUtils.debug("destPath=%s", destPath);
			accessUri = destPath.replace(
					FILE_STORAGE_PATH.endsWith("/") ? FILE_STORAGE_PATH.substring(0, FILE_STORAGE_PATH.length() - 1)
							: FILE_STORAGE_PATH,
					"");

			// 真正写到磁盘上
			Map<String, MultipartFile> fileMap = request.getFileMap();
			MultipartFile partFile = fileMap.entrySet().iterator().next().getValue();
			saveFileFromInputStream(partFile, destPath);

			/*
			 * File file = new File(destPath); out = new FileOutputStream(file); in =
			 * picture.getInputStream(); int length = 0; byte[] buf = new byte[1024]; //
			 * in.read(buf) 每次读到的数据存放在buf 数组中 while ((length = in.read(buf)) != -1) { //
			 * 在buf数组中取出数据写到（输出流）磁盘上 out.write(buf, 0, length); }
			 */
		} catch (Exception e) {
			LogUtils.error("errorMsg:%s", e, e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}

		Map<String, String> valMap = new HashMap<>();
		valMap.put("accessUri", accessUri);
		valMap.put("accessUrl", GlobalConstant.STATIC_DOMAIN + accessUri);
		return new ResultModel(valMap);
	}

	private String genImgPath() {
		String imgPath = FILE_STORAGE_PATH + (FILE_STORAGE_PATH.endsWith("/") ? "" : "/")
				+ DateUtil.formatDate(new Date(), "yyyy/MM/dd");
		File dir = new File(imgPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String filePath = null;
		do {
			filePath = imgPath + "/" + RandomUtil.randomAlphaNumCode(8) + ".jpg";
			if (!new File(filePath).exists()) {
				break;
			}
		} while (true);

		return filePath;
	}

	/**
	 * <Description>SaveFileFromInputStream: 将http文件流写到本地指定的目录中</Description>
	 * 
	 * @param filePath
	 *            需要被存储的文件名
	 */
	private void saveFileFromInputStream(MultipartFile partFile, String filePath) {

		// ImgErrorCode errorCode = ImgErrorCode.SUCCESS;
		FileOutputStream fs = null;
		InputStream stream = null;
		try {
			stream = partFile.getInputStream();
			byte[] fileBytes = IOUtils.toByteArray(stream);
			if (isIcoSuffix(filePath) || ImageUtils.isImageType(fileBytes)) {
				fs = new FileOutputStream(filePath);
				fs.write(fileBytes);

				LogUtils.debug("file '" + filePath + "' has uploaded success, total bytes " + fileBytes.length);

				// resultObj.addProperty("filePath", filePath);
				// resultObj.addProperty("bytes", fileBytes.length); //
				// 改图片的字节数，以byte为单位
				// resultObj.addProperty("friendlySize",
				// FileUtils.byteCountToDisplaySize(fileBytes.length)); //
				// 改图片文件的友好大小。如果大于1M，则以M为单位；如果大于1K并小于1M，则以K为单位；小于1K，则用以byte为单位
			}
			// else {
			// errorCode = ImgErrorCode.NOT_SUPPORT;
			// }
		} catch (IOException e) {
			LogUtils.error("path:%s, clientIP:%s", e, filePath, ThreadUtil.getClientIP());
			// errorCode = ImgErrorCode.SAVE_IO_EXCEPTION;
		} finally {
			if (fs != null) {
				try {
					fs.flush();
				} catch (IOException e) {
					LogUtils.error("path:%s, clientIP:%s", e, filePath, ThreadUtil.getClientIP());
				}
			}
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					LogUtils.error("path:%s, clientIP:%s", e, filePath, ThreadUtil.getClientIP());
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					LogUtils.error("path:%s, clientIP:%s", e, filePath, ThreadUtil.getClientIP());
				}
			}

		}

		// return errorCode;
	}

	private boolean isIcoSuffix(String fileName) {
		String suffix = FilenameUtils.getExtension(fileName);

		return "ico".equalsIgnoreCase(suffix) || "icon".equalsIgnoreCase(suffix);
	}

}
