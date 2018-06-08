package com.tsinghuadtv.www.controller.oss;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsinghuadtv.www.controller.oss.api.PreUploadRequest;
import com.tsinghuadtv.www.controller.oss.api.PreUploadResponse;
import com.tsinghuadtv.www.log.Log;
import com.tsinghuadtv.www.service.oss.OSSService;
import com.tsinghuadtv.www.service.oss.api.IOSSService;
import com.tsinghuadtv.www.service.oss.api.OSSUploadBO;
import com.tsinghuadtv.www.service.oss.api.OssUtils;

@Controller
@RequestMapping("/aliyun/oss")
public class OSSController {
	private static Log LOG = Log.getLog(OSSService.class);
	
	private static final String POST_METHOD = "POST"; 
	
	@Autowired
	private IOSSService ossService;
	
	@RequestMapping(value = "/preUpload", method = {RequestMethod.POST} ) 
	@ResponseBody
	public PreUploadResponse preUpload(@RequestBody PreUploadRequest preUploadRequest) throws Exception {
		
		OSSUploadBO ossUploadBO = genParamBO(preUploadRequest);
		
		PreUploadResponse response = new PreUploadResponse(ossService.preUploadPublic(ossUploadBO, true));
		
		return response;
	}
	
	private OSSUploadBO genParamBO(PreUploadRequest preUploadRequest) throws Exception {
		OSSUploadBO ossUploadBO = new OSSUploadBO();
		
		String sourceCode = preUploadRequest.getSourceCode();
		String fileName = preUploadRequest.getFileName();
		String fileMd5 = preUploadRequest.getFileMd5();
		String httpMethod = preUploadRequest.getHttpMethod();
		String contentType = preUploadRequest.getContentType();
		Boolean isUseOrigFileName = preUploadRequest.getIsUseOrigFileName();
		Boolean isUseSourceCodeAsPath = preUploadRequest.getIsUseSourceCodeAsPath();
		Boolean isWatermark = preUploadRequest.getIsWatermark();

		String style = "";
		String height = "";
		String width = "";
		if (preUploadRequest.getHeight() != null) {
			height = preUploadRequest.getHeight() + "h";
		}
		if (preUploadRequest.getWidth() != null) {
		    width = preUploadRequest.getWidth().toString() + "w";
		}
		if (StringUtils.isNotBlank(height)) {
			style = height;
			if (StringUtils.isNotBlank(width)) {
				style = style + "_" + width;
			}
		} else {
			style = width;
		}
		
		if (StringUtils.isBlank(sourceCode) || StringUtils.isBlank(fileName)) {
			LOG.error("source code or file name is null!");
			throw new RuntimeException();
		}
		
		int indexOfPoint = fileName.lastIndexOf(".");
        String extension = fileName.substring(++indexOfPoint);
		if(!Boolean.TRUE.equals(isUseOrigFileName)) {
	       fileName = System.currentTimeMillis() + "." + extension; 
		}
        
		if (StringUtils.isBlank(fileMd5)) {
			fileMd5 = "";
		}
		if (StringUtils.isBlank(httpMethod)) {
			httpMethod = POST_METHOD;
		}
		if (StringUtils.isBlank(contentType)) {
			contentType = extension;
		}
		
		String filePath = "";
		if(!Boolean.TRUE.equals(isUseSourceCodeAsPath)) {
			filePath = OssUtils.getRelativePath(sourceCode);
		} else {
			filePath = sourceCode;
		}
		
		StringBuilder fullName = new StringBuilder();
		fullName = fullName.append(filePath).append("/").append(fileName);
		String userType= preUploadRequest.getUserType();

		ossUploadBO.setHttpMethod(httpMethod);
		ossUploadBO.setFileMd5(fileMd5);
		ossUploadBO.setContentType(contentType);
		ossUploadBO.setFilePath(filePath);
		ossUploadBO.setFileName(fileName);		
		ossUploadBO.setUserType(userType);
		ossUploadBO.setFullName(fullName);
		ossUploadBO.setIsWatermark(isWatermark);
		if (StringUtils.isNotEmpty(style)) {
			ossUploadBO.setStyle(style);
		}
		
		return ossUploadBO;
	 
	}
}
