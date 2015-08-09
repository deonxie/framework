package personal.deon.framework.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import cn.gd.thinkjoy.modules.utils.Encodes;

import com.google.common.collect.Maps;


public class SmsTest {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	static String UTF8 = "utf-8";
	static String base_url = "https://api.ucpaas.com/2014-06-30/Accounts/";
	static String account_sid = "9ac67866d5e8e2de0f753081eaca70ee";
	static String auth_token = "b94b684b901fcb02d24e55ae33924919";
	static String app_id = "13f42800e7d247b4bc4f9d4be408e3c7";
	static String code_template_id = "10860";
	public static void main(String[] args) {
//		send("123132", "18927586311");
		send("123132", "15015906765");
	}
	
	static void send(String code,String tel){
		String result = "";
		Map<String, Object> param = Maps.newHashMap();
		param.put("appId", app_id);
		param.put("templateId", code_template_id);
		param.put("to", tel);
		param.put("param", code);
		
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
			System.out.println("start:="+dateFormat.format(new Date()));
			HttpResponse response = httpClient.execute(postMethod);
			if(response.getStatusLine().getStatusCode()==200)
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				System.out.println("短信联网返回结果{}"+response.getStatusLine().getStatusCode());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end:="+dateFormat.format(new Date()));
		System.out.println(result);
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
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
}
