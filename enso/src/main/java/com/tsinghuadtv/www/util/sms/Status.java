package com.tsinghuadtv.www.util.sms;


public class Status {
	
    private String msg;
    private String code;

	public Status(String msg, String code)
	{
		super();
		this.msg = msg;
		this.code = code;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}

}
