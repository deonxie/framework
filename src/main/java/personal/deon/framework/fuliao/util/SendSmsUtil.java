package personal.deon.framework.fuliao.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import personal.deon.framework.core.service.CoreConfigService;

import com.google.common.collect.Maps;

import cn.gd.thinkjoy.modules.utils.Encodes;

public class SendSmsUtil {
	private static final Logger logger = LoggerFactory.getLogger(SendSmsUtil.class);
	private static final String UTF8 = "utf-8";
	final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	final static String base_url_key = "send.sms.base.url";
	final static String account_sid_key = "send.sms.base.account.sid";
	final static String auth_token_key = "send.sms.base.auth.token";
	final static String app_id_key = "send.sms.base.app.id";
	final static String code_template_id_key = "send.sms.base.code.template.id";
	static String base_url = "";
	static String account_sid = "";
	static String auth_token = "";
	static String app_id = "";
	static String code_template_id = "";
	
	/** 设置短信的必须得配置 **/
	public static void initConfig(){
		CoreConfigService.addConstKey(base_url_key, "云之讯短信发送域名");
		CoreConfigService.addConstKey(account_sid_key, "云之讯短信账号编号");
		CoreConfigService.addConstKey(auth_token_key, "云之讯短信验证标示符");
		CoreConfigService.addConstKey(app_id_key, "云之讯短信平台注册的app编号");
		CoreConfigService.addConstKey(code_template_id_key, "云之讯验证码短信模板编号");
	}
	
	/**
	 * 重新加载短信配置
	 */
	public static void loadConfig(){
		base_url = CoreConfigService.getValue(base_url_key);
		account_sid = CoreConfigService.getValue(account_sid_key);
		auth_token = CoreConfigService.getValue(auth_token_key);
		app_id = CoreConfigService.getValue(app_id_key);
		code_template_id = CoreConfigService.getValue(code_template_id_key);
		if(StringUtils.isBlank(base_url))
			logger.error("请配置发送短信的参数");
	}
	
	/**
	 * 发送短信验证码
	 * @param code
	 * @param tel
	 * @return
	 */
	public static SmsSendResult sendCode(String code,String tel){
		if(StringUtils.isBlank(base_url))
			loadConfig();
		
		String resp = sendSMS(code,tel,code_template_id);
		if(StringUtils.isBlank(resp))
			return null;
		String respCode = null,smsid = null;
		try{
			JSONObject json = new JSONObject(resp).getJSONObject("resp");
			respCode = json.getString("respCode");
			smsid = json.getJSONObject("templateSMS").getString("smsId");
		}catch(Exception e){
			logger.error("短信方式失败 {}",e.getMessage());
		}
		return new SmsSendResult(respCode,smsid);
	}
	
	private static String sendSMS(String content,String tel,String templeId) {
		String result = "";
		Map<String, Object> param = Maps.newHashMap();
		param.put("appId", app_id);
		param.put("templateId", templeId);
		param.put("to", tel);
		param.put("param", content);
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("templateSMS", param);
		
		String sms = new JSONObject(params).toString();
		HttpClient httpClient = HttpClients.createDefault();
		Date date = new Date();
		HttpPost postMethod = new HttpPost(base_url+account_sid+"/Messages/templateSMS?sig="+createSig(date));
		postMethod.addHeader("Content-Type", "application/json;charset=utf-8");
		postMethod.addHeader("Accept", "application/json");
		postMethod.addHeader("Authorization", createAuth(date));
		
		try{
			postMethod.setEntity(new StringEntity(sms,"UTF-8"));
			HttpResponse response = httpClient.execute(postMethod);
			if(response.getStatusLine().getStatusCode()==200)
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				logger.warn("短信联网返回结果{}",response.getStatusLine().getStatusCode());
			return result;
		} catch (UnsupportedEncodingException e) {
			logger.error("短信转换为utf-8编码错误",e);
		} catch (ClientProtocolException e) {
			logger.error("短信服务端不支持https 协议",e);
		} catch (IOException e) {
			logger.error("读取响应信息异常",e);
		}
		return result;
	}

	/**
	 * REST API 验证参数
	 * 
	 * @return
	 */
	public static String createSig(Date date) {
		return md5Digest(account_sid + auth_token + dateFormat.format(date));

	}

	/**
	 * 创建包头验证信息
	 * 
	 * @param date
	 * @return
	 */
	public static String createAuth(Date date) {
		return base64Encoder(account_sid + ":" + dateFormat.format(date));
	}

	/**
	 * MD5数字签名 定义数字签名方法可用：MD5, SHA-1
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Digest(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byte2HexStr(md.digest(src.getBytes(UTF8)));
		} catch (NoSuchAlgorithmException e) {
			logger.error("短信签名加密不支持MD5算法", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("短信签名加密不支持utf-8编码", e);
		}
		return null;
	}

	/**
	 * BASE64编码
	 */
	public static String base64Encoder(String src) {
		try {
			return Encodes.encodeBase64(src.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
			logger.error("短信64位验证信息不支持utf-8编码", e);
		}
		return null;
	}

	/**
	 * BASE64解码
	 */
	public static String base64Decoder(String dest) {
		try {
			return new String(Encodes.decodeBase64(dest), UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error("短信64位验证信息不支持utf-8解码", e);
		}
		return null;
	}

	/**
	 * 字节数组转化为大写16进制字符串
	 */
	private static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s.toUpperCase());
		}
		return sb.toString();
	}
	
	public static class SmsSendResult{
		private String respCode;
		private String smsId;
		private boolean success;
		
		public SmsSendResult(String respCode,String smsId) {
			this.respCode = respCode;
			this.smsId = smsId;
			success = "000000".equals(respCode);
		}

		/**
		 * @return the respCode
		 */
		public String getRespCode() {
			return respCode;
		}

		/**
		 * @return the smsId
		 */
		public String getSmsId() {
			return smsId;
		}

		/**
		 * @return the success
		 */
		public boolean isSuccess() {
			return success;
		}
		
	}
}
