package com.tsinghuadtv.www.service.oss.api;

public interface IOSSService {

	public OSSUploadResultBO preUploadPublic(OSSUploadBO ossUploadBO, boolean flag) throws Exception;
	
	public OSSUploadResultBO preUploadInternal(OSSUploadBO ossUploadBO, String employeeCode, String employeeName, boolean flag) throws Exception;

}
