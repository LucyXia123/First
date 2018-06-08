package com.tsinghuadtv.www.util.sms;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class Test {

	public static Status sendSMSQZ(String mobiles, String content) {

		Status status = new Status("", "200");
		// String smsSvcUrl = "http://114.55.2.130:8082/SmsApi/sdk/SMS"; // 服务器URL
		String smsSvcUrl = "http://api.yangyi.info:8082/SmsApi/sdk/SMS"; // 服务器URL

		String uid = "gdtfszds"; // 账号gdtfszds密码hkfzd9wif5ogjygwxxrcn3nkpjp48y26
		String psw = "hkfzd9wif5ogjygwxxrcn3nkpjp48y26"; // 密码

		String urlencContent;
		try {
			urlencContent = URLEncoder.encode(content, "UTF-8");

			psw = MD5.getMD5Str(psw).toUpperCase();

			String postData = "uid=" + uid + "&psw=" + psw + "&mobiles=" + mobiles + "&msgid=" + new Date().getTime()
					+ "&msg=" + urlencContent;
			URL myurl = new URL(smsSvcUrl);
			URLConnection urlc = myurl.openConnection();
			urlc.setReadTimeout(1000 * 30);
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setAllowUserInteraction(false);

			DataOutputStream server = new DataOutputStream(urlc.getOutputStream());
			System.out.println("发送数据=" + postData);
			System.out.println("**************************");
			server.write(postData.getBytes("UTF-8"));
			server.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF-8"));
			String resXml = "", s = "";
			while ((s = in.readLine()) != null)
				resXml = resXml + s + "\r\n";
			in.close();
			System.out.println(resXml);// {"rstCode":100}
			JSONObject jsonobject = JSONObject.fromObject(resXml);
			if ("100".equals(jsonobject.getString("rstCode"))) {
				status.setCode("200");
				status.setMsg("短信发送成功!");
			} else {
				status.setCode("400");
				status.setMsg("短信发送失败!");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static void main(String[] args) {
//		String content = "用户您好：友情提示您所使用的无线数字机顶盒将于2017年6月24日到期，请及时续费客服电话17091101291。【泉州广电同方通知】";
//		String content="用户您好：同方凌讯在线教育平台验证码1234。【同方教育通知】"+"";
		String content="1234";
		Status re = SMS.sendSMSQZ("13691150835", content);
		Gson g = new Gson();
		System.out.println(g.toJson(re));
	}

}
