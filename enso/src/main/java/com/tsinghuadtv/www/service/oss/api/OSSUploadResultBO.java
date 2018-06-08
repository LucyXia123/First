package com.tsinghuadtv.www.service.oss.api;

import java.util.Map;

public class OSSUploadResultBO {
	
	private String bucket;
	private String time;
	private Map<String, String> metaDatas;
	private String fileName;
	private String filePath;
	private String signature;
	private String ossHostId;
	private String accessKeyId;
	private String contentHostName;
	private String imgHostName;
	private String policy;
	private String contentType;
	private String style;
	private String fullName;
	private String url;
	private String origUrl;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrigUrl() {
		return origUrl;
	}

	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public String getBucket() {
		return bucket;
	}
	
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Map<String, String> getMetaDatas() {
		return metaDatas;
	}

	public void setMetaDatas(Map<String, String> metaDatas) {
		this.metaDatas = metaDatas;
	}

	public String getOssHostId() {
		return ossHostId;
	}

	public void setOssHostId(String ossHostId) {
		this.ossHostId = ossHostId;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getContentHostName() {
		return contentHostName;
	}

	public void setContentHostName(String contentHostName) {
		this.contentHostName = contentHostName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getImgHostName() {
		return imgHostName;
	}

	public void setImgHostName(String imgHostName) {
		this.imgHostName = imgHostName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}
