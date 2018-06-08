package com.tsinghuadtv.www.controller.oss.api;

import java.util.Map;

import com.tsinghuadtv.www.controller.api.common.ServiceResponse;
import com.tsinghuadtv.www.service.oss.api.OSSUploadResultBO;

public class PreUploadResponse extends ServiceResponse {

	private static final long serialVersionUID = 1599221202431259538L;
	
	private String bucket;
	private String time;
	private Map<String, String> metaDatas;
	private String signature;
	private String ossHostId;
	private String accessKeyId;
	private String contentHostName;
	private String policy;
	private String contentType;
	private String fullName;
	private String origUrl;
	private String url;
	
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
	
	public Map<String, String> getMetaDatas() {
		return metaDatas;
	}
	
	public void setMetaDatas(Map<String, String> metaDatas) {
		this.metaDatas = metaDatas;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
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
	
	public String getPolicy() {
		return policy;
	}
	
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getOrigUrl() {
		return origUrl;
	}
	
	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public PreUploadResponse(OSSUploadResultBO bo) {
		this.accessKeyId = bo.getAccessKeyId();
		this.bucket = bo.getBucket();
		this.contentHostName = bo.getContentHostName();
		this.contentType = bo.getContentType();
		this.fullName = bo.getFullName();
		this.metaDatas = bo.getMetaDatas();
		this.origUrl = bo.getOrigUrl();
		this.ossHostId = bo.getOssHostId();
		this.policy = bo.getPolicy();
		this.signature = bo.getSignature();
		this.time = bo.getTime();
		this.url = bo.getUrl();
	}
	
}
