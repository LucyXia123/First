package com.tsinghuadtv.www.util;

public class ResponseUtil {
	private Integer result;
	private String cause;

	public ResponseUtil() {
		super();
		this.cause = "";
	}
	
	public ResponseUtil(Integer result, String cause) {
		super();
		this.result = result;
		this.cause = cause;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
