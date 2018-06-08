package com.tsinghuadtv.www.service.oss;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.common.auth.HmacSHA1Signature;
import com.tsinghuadtv.www.log.Log;
import com.tsinghuadtv.www.service.oss.api.IOSSService;
import com.tsinghuadtv.www.service.oss.api.OSSUploadBO;
import com.tsinghuadtv.www.service.oss.api.OSSUploadResultBO;
import com.tsinghuadtv.www.service.oss.api.OssUtils;

@Service
public class OSSService implements IOSSService {
	protected Log LOG = Log.getLog(OSSService.class);

	private static final String POLICY = "{\n\"expiration\": \"2120-01-01T12:00:00.000Z\",\n\"conditions\": [\n[\"content-length-range\", 0, 104857600]\n]\n}\n";
	private static final String UTF8 = "utf-8";
	private static final String POST = "POST";
	
	@Value("${aliyun.accesskey.id:accessKeyId}")
	private String accessKeyId;

	@Value("${aliyun.accesskey.secret:accessKeySecret}")
	private String accessKeySecret;

	@Value("${oss.aliyun.bucketName:bucketName}")
	private String bucketName;

	@Value("${oss.aliyun.public.host.id:hostId}")
	private String publicOssHostId;

	@Value("${oss.aliyun.internal.host.id:hostId}")
	private String internalOssHostId;

	@Value("${oss.aliyun.content.host.name:contentHostName}")
	private String contentHostName;

	@Value("${oss.aliyun.img.host.name:imgHostName}")
	private String imgHostName;
	
	@Override
	public OSSUploadResultBO preUploadPublic(OSSUploadBO ossUploadBO, boolean flag) throws Exception {
		return preUpload(ossUploadBO, null, null, flag, publicOssHostId);
	}

	@Override
	public OSSUploadResultBO preUploadInternal(OSSUploadBO ossUploadBO, 
			String employeeCode, String employeeName, boolean flag) throws Exception {
		return preUpload(ossUploadBO, employeeCode, employeeName, flag, internalOssHostId);
	}

	private OSSUploadResultBO preUpload(OSSUploadBO ossUploadBO, String employeeCode, String employeeName, boolean flag,
			String ossHostId) throws Exception {
		// httpMethod 不能为null
		String httpMethod = ossUploadBO.getHttpMethod();
		String fileMd5 = ossUploadBO.getFileMd5();
		String contentType = ossUploadBO.getContentType();
		String filePath = ossUploadBO.getFilePath();
		String fileName = ossUploadBO.getFileName();
		String style = ossUploadBO.getStyle();
		Boolean isWatermark = ossUploadBO.getIsWatermark();
		String userType = ossUploadBO.getUserType();
		StringBuilder fullName = ossUploadBO.getFullName();
		String extension = ossUploadBO.getExtension();

		StringBuilder origUrl = new StringBuilder(contentHostName).append("/").append(filePath).append("/")
				.append(fileName);
		StringBuilder url = new StringBuilder(origUrl);
		if (StringUtils.isNotBlank(httpMethod)) {
			if (StringUtils.isNotBlank(style)) {
				url = new StringBuilder(imgHostName).append("/").append(filePath).append("/").append(fileName)
						.append("@").append(style);
			} else {
				if (null != isWatermark && isWatermark.equals(Boolean.TRUE)) {
					url = new StringBuilder(imgHostName).append("/").append(filePath).append("/").append(fileName)
							.append("@watermark=1&object=d2F0ZXJtYXJrL2xvZ28ucG5n&p=5&t=50");
				} else {
					url = new StringBuilder(imgHostName).append("/").append(filePath).append("/").append(fileName);
				}
			}
		}

		String gmtNow = OssUtils.getGmtNow();

		Map<String, String> signParamMap = null;
		
		StringBuilder content = new StringBuilder();
		if (POST.equalsIgnoreCase(httpMethod)) {
			content.append(new String(Base64.encodeBase64(POLICY.getBytes())));
//			LOG.info("the policy is:{}.", POLICY);
//			LOG.info("the base64 is:{}.", Base64.encodeBase64String(POLICY.getBytes()));
			if (flag) {
				signParamMap = getSignParamMap(userType, employeeCode, employeeName);
			}
		} else {
			signParamMap = getSignParamMap(userType);
			
			content.append(httpMethod).append("\n").append(fileMd5).append("\n").append(contentType).append("\n")
					.append(gmtNow).append("\n").append(getMetaData(signParamMap)).append("/").append(bucketName)
					.append("/").append(filePath).append("/").append(fileName);

		}

		String signature = (new HmacSHA1Signature()).computeSignature(accessKeySecret, content.toString());

		signature = "OSS " + accessKeyId + ":" + signature;

		OSSUploadResultBO ossUploadResultBO = new OSSUploadResultBO();

		ossUploadResultBO.setBucket(bucketName);
		ossUploadResultBO.setTime(gmtNow);
		ossUploadResultBO.setFileName(fileName);
		ossUploadResultBO.setFilePath(filePath);
		ossUploadResultBO.setImgHostName(imgHostName);
		ossUploadResultBO.setSignature(signature);
		ossUploadResultBO.setOssHostId(ossHostId);
		ossUploadResultBO.setContentHostName(contentHostName);
		ossUploadResultBO.setAccessKeyId(accessKeyId);
		ossUploadResultBO.setPolicy(POLICY);
		ossUploadResultBO.setFullName(fullName.toString());
		ossUploadResultBO.setOrigUrl(origUrl.toString());
		ossUploadResultBO.setUrl(url.toString());
		if (signParamMap != null) {
			ossUploadResultBO.setMetaDatas(signParamMap);
		}
		if (StringUtils.isNotEmpty(extension)) {
			ossUploadResultBO.setContentType(extension);
		} else {
			ossUploadResultBO.setContentType(contentType);
		}

		return ossUploadResultBO;
	}
	
	private Map<String, String> getSignParamMap(String userType) throws Exception {
		return getSignParamMap(userType, null, null);
	}

	private Map<String, String> getSignParamMap(String userType, String employeeCode, String employeeName) throws Exception {

		Map<String, String> signParamMap = new TreeMap<String, String>();
		
		if (StringUtils.isBlank(employeeName) || StringUtils.isBlank(employeeCode)) {
			return signParamMap;			
		}

		signParamMap.put("x-oss-meta-createdbyusercode", employeeCode);
		signParamMap.put("x-oss-meta-createdbyusername", URLEncoder.encode(employeeName, UTF8));
		signParamMap.put("x-oss-meta-createdtime", URLEncoder.encode((new Date().toString()), UTF8));

		return signParamMap;
	}

	private String getMetaData(Map<String, String> signParamMap) throws Exception {

		if (signParamMap.isEmpty()) {
			return "";
		}

		List<NameValuePair> params = new LinkedList<NameValuePair>(); // 字典序!

		Set<Map.Entry<String, String>> signEntries = signParamMap.entrySet();
		for (Map.Entry<String, String> entry : signEntries) {
			params.add(new BasicNameValuePair(entry.getKey().toLowerCase(), entry.getValue()));
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append(':');
			try {
				sb.append(URLEncoder.encode(params.get(i).getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sb.append('\n');
		}

		return sb.toString();
	}
	
	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getPublicOssHostId() {
		return publicOssHostId;
	}

	public void setPublicOssHostId(String publicOssHostId) {
		this.publicOssHostId = publicOssHostId;
	}

	public String getInternalOssHostId() {
		return internalOssHostId;
	}

	public void setInternalOssHostId(String internalOssHostId) {
		this.internalOssHostId = internalOssHostId;
	}

	public String getContentHostName() {
		return contentHostName;
	}

	public void setContentHostName(String contentHostName) {
		this.contentHostName = contentHostName;
	}

	public String getImgHostName() {
		return imgHostName;
	}

	public void setImgHostName(String imgHostName) {
		this.imgHostName = imgHostName;
	}

	public static void main(String[] args) throws Exception {
		
		OSSService handler = new OSSService();
		
		handler.setPublicOssHostId("http://oss-cn-beijing.aliyuncs.com");
		handler.setInternalOssHostId("http://oss-cn-beijing.aliyuncs.com");
		handler.setAccessKeyId("");
		handler.setAccessKeySecret("");
		handler.setBucketName("");
		handler.setContentHostName("http://content.cytest002.com");
		handler.setImgHostName("http://content.cytest002.com");
		
	}
}
