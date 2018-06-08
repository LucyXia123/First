package com.tsinghuadtv.www.service.oss.api;

public class OSSUploadBO {
	public String httpMethod;
	public String fileMd5;
	public String contentType;
	public String filePath;
	public String fileName;
	public String style;
	public Boolean isWatermark;
	public String userType;
	public StringBuilder fullName;
	public String extension;
	public String tmpZipFileName;
	public String sourceCode;

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Boolean getIsWatermark() {
		return isWatermark;
	}

	public void setIsWatermark(Boolean isWatermark) {
		this.isWatermark = isWatermark;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public StringBuilder getFullName() {
		return fullName;
	}

	public void setFullName(StringBuilder fullName) {
		this.fullName = fullName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTmpZipFileName() {
		return tmpZipFileName;
	}

	public void setTmpZipFileName(String tmpZipFileName) {
		this.tmpZipFileName = tmpZipFileName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

}
