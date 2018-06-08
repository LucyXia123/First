package com.tsinghuadtv.www.controller.oss.api;

public class PreUploadRequest {
	
	private String sourceCode;
	private String fileName;
	private String httpMethod;
	private String fileMd5;
	private String contentType;
	private String userType;
	private int fileSize;
	private Boolean isUseOrigFileName;
	private Boolean isUseSourceCodeAsPath;
	private Integer height;
	private Integer width;
	private Boolean isWatermark;
	
	public Boolean getIsWatermark() {
		return isWatermark;
	}
	public void setIsWatermark(Boolean isWatermark) {
		this.isWatermark = isWatermark;
	}
	public Boolean getIsUseSourceCodeAsPath() {
		return isUseSourceCodeAsPath;
	}
	public void setIsUseSourceCodeAsPath(Boolean isUseSourceCodeAsPath) {
		this.isUseSourceCodeAsPath = isUseSourceCodeAsPath;
	}
	public Boolean getIsUseOrigFileName() {
		return isUseOrigFileName;
	}
	public void setIsUseOrigFileName(Boolean isUseOrigFileName) {
		this.isUseOrigFileName = isUseOrigFileName;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getFileMd5() {
		return fileMd5;
	}
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
}
