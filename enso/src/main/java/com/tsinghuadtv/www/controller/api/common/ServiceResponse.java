package com.tsinghuadtv.www.controller.api.common;

import java.io.Serializable;

import com.tsinghuadtv.www.util.ServiceResponseUtility;

public class ServiceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	//100000表示成功
	private String resultCode;

	//
	private String resultMessage;

	// 为每一个错误产生一个临时近似唯一的ID，这个ID会记录到日志里面，方便错误定位
	private String errorId;

	public ServiceResponse() {
		resultCode = ServiceResponseUtility.CODE_SUCCESS;
		resultMessage = "成功";
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

}
