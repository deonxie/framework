package personal.deon.framework.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import personal.deon.framework.weixin.entity.WeixinPerOrder;
import personal.deon.framework.weixin.service.WeixinConfigService;


/***
 * 微信支付
 * @author jlusoft
 *
 */
public class WeixinPayUtil {
	protected static Logger logger = LoggerFactory.getLogger(WeixinPayUtil.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 在微信端生成预支付订单
	 * @param perOrder
	 */
	public static void unifiedorder(WeixinPerOrder perOrder){
		String result = HttpNetUtil.postJson(
				"https://api.mch.weixin.qq.com/pay/unifiedorder",toXml(perOrder.fieldValue()));
		perOrder.fieldValue(parseXml(result));
	}
	/***
	 * 查询微信端订单，微信订单号、自定义订单号二者填其一
	 * @param transaction_id 微信订单号
	 * @param out_trade_no 自定义订单号
	 */
	public static Map<String, String> orderquery(String transaction_id,String out_trade_no){
		Map<String,String> fields = Maps.newHashMap();
		if(StringUtils.isNotBlank(transaction_id))
			fields.put("transaction_id", transaction_id);
		if(StringUtils.isNotBlank(out_trade_no))
			fields.put("out_trade_no", out_trade_no);
		fields.put("nonce_str", randomStr());
		String result = HttpNetUtil.postJson(
				"https://api.mch.weixin.qq.com/pay/orderquery",toXml(fields));
		return parseXml(result);
	}
	/**
	 * 关闭订单，商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，
	 * 避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口
	 * @param out_trade_no 自定义订单号
	 * @return
	 */
	public static Map<String,String> closeorder(String out_trade_no){
		Map<String,String> fields = Maps.newHashMap();
		if(StringUtils.isNotBlank(out_trade_no))
			fields.put("out_trade_no", out_trade_no);
		fields.put("nonce_str", randomStr());
		String result = HttpNetUtil.postJson(
				"https://api.mch.weixin.qq.com/pay/closeorder",toXml(fields));
		return parseXml(result);
	}
	/**
	 * 下载历史交易清单,信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
	 * 对账单中涉及金额的字段单位为“元”。
	 * @param bill_date 下载对账单的日期，格式：20140603,必须输入
	 * @param bill_type：ALL，返回当日所有订单信息，默认值
	 * SUCCESS，返回当日成功支付的订单=1
	 * REFUND，返回当日退款订单=2
	 * REVOKED，已撤销的订单=3
	 * @param device_info：微信支付分配的终端设备号，可以不填
	 */
	public static Map<String,String> downloadbill(Date bill_date,
			int bill_type,String device_info){
		Map<String,String> fields = Maps.newHashMap();
		System.out.println(dateFormat.format(bill_date));
		fields.put("bill_date", dateFormat.format(bill_date));
		switch(bill_type){
		case 1:
			fields.put("bill_type", "SUCCESS");
			break;
		case 2:
			fields.put("bill_type", "REFUND");
			break;
		case 3:
			fields.put("bill_type", "REVOKED");
			break;
		default :
				fields.put("bill_type", "ALL");
				break;
		}if(StringUtils.isNotBlank(device_info))
			fields.put("device_info", device_info);
		fields.put("nonce_str", randomStr());
		String result = HttpNetUtil.postJson(
				"https://api.mch.weixin.qq.com/pay/downloadbill",toXml(fields));
		return parseXml(result);
	}

	/***
	 * 生成32位的随机字符串
	 * @return
	 */
	public static String randomStr(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	private static String toXml(Map<String,String> fields){
		StringBuffer sb = new StringBuffer("<xml>");
		if(fields != null){
			String[] params = new String[fields.size()+3];
			fields.put("nonce_str", randomStr());
			int index =0;
			for(String key:fields.keySet()){
				sb.append("<").append(key).append(">")
				.append(fields.get(key)).append("</").append(key).append(">");
				params[index++]=key+"="+fields.get(key);
			}
			sb.append("<appid>").append(WeixinConfigService.getAppid()).append("</appid>")
			.append("<mch_id>").append(WeixinConfigService.getMchid()).append("</mch_id>")
			.append("<nonce_str>").append(fields.get("nonce_str")).append("</nonce_str>");
			params[index++]="appid="+WeixinConfigService.getAppid();
			params[index++]="mch_id="+WeixinConfigService.getMchid();
	    	sb.append("<sign>").append(createSign(params)).append("</sign>");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	/***
	 * 将xml字符串转换为map，单层无嵌套
	 * @param xml
	 * @return
	 */
	public static Map<String, String> parseXml(String xml){
		Map<String, String> mesage = Maps.newHashMap();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml.toString());
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		if(doc !=null){
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list)
				mesage.put(e.getName(), e.getText());
		}
		return mesage;
	}
	
	public static String httpsPost(String url,String param) throws KeyStoreException{
		KeyStore keystore = null;
		SSLContext context = null;
		//加载本地的证书进行https加密传输
		try(FileInputStream instream = new FileInputStream(
				new File(WeixinConfigService.getKeystorepath()+"test.p12"));){
			keystore = KeyStore.getInstance("PKCS12");
			keystore.load(instream, WeixinConfigService.getKeystorepwd().toCharArray());
			context = SSLContexts.custom().loadKeyMaterial(keystore,
					WeixinConfigService.getKeystorepwd().toCharArray()).build();
			HttpClient https = HttpClients.custom().setSslcontext(context).build();
			HttpPost post =  new HttpPost(url);
			post.setHeader("Content-Type", "text/xml");
			
			post.setEntity(new StringEntity(param, "UTF-8"));
			HttpResponse response = https.execute(post);
			HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity, "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/***
	 * 检查数据的准确性
	 * @param sign
	 * @param msg
	 * @return
	 */
	public static boolean checkeSign(Map<String, String> msg) {
		String sign = msg.remove("sign");
		String[] params = new String[msg.size()];
		int index =0;
		for(String key:msg.keySet()){
			params[index++]=key+"="+msg.get(key);
		}
    	return sign.equals(createSign(params));
	}
	/**
	 * 创建js 支付接口参数
	 * @param prepay_id
	 * @return String[appid,timeStamp,nonceStr,package,signType,paySign]
	 */
	public static String[] JSAPIParams(String prepay_id){
		if(StringUtils.isBlank(prepay_id))
			return new String[]{Boolean.FALSE.toString(),"无法生成付款订单"};
		
		String[] params = new String[6];
		params[0] = WeixinConfigService.getAppid();
		params[1] = ""+(new Date().getTime()/1000);
		params[2] =	randomStr();
		params[3] = "prepay_id="+prepay_id;
		params[4] =	"MD5";
		
		String[] field = new String[]{
			"appId="+params[0],
			"timeStamp="+params[1],
	        "nonceStr="+params[2],   
	        "package="+params[3],     
	        "signType="+params[4]   
		};    
		params[5] = createSign(field);
		return params;
	}
	/***
	 * 生成签名
	 * @param params
	 * @return
	 */
	private final static String createSign(String[] params){
		Arrays.sort(params);
    	StringBuffer urlParam = new StringBuffer();
    	for (int i = 0; i < params.length; i++) {
    		urlParam.append(params[i]).append("&");
    	}
    	urlParam.append("key=").append(WeixinConfigService.getTradekey());
    	return SHA1.md5(urlParam.toString()).toUpperCase();
	}
	
	/*****=======================user ====================================***/
	/***
	 * 获取用户的openid
	 * @param code
	 * @return
	 */
	public static Map<String, String> userOpenip(String code){
		String result = HttpNetUtil.getJson("https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+WeixinConfigService.getAppid()+"&secret="+WeixinConfigService.getAppsecret()+ "&code="+code+
				"&grant_type=authorization_code");
		Map<String, String> params = Maps.newHashMap();
		try {
			JSONObject json = new JSONObject(result);
			if(!json.has("errcode")){
				String[] fields = JSONObject.getNames(json);
				if(fields!=null){
					for(String key :fields)
						params.put(key, json.get(key).toString());
				}
			}else
				logger.error("获取用户openid失败，{}",json.getString("errmsg"));
		} catch (JSONException e) {
			logger.error("获取用户openid异常",e);
		}
		return params;
	}
	
	/***
	 * 刷新网页token
	 * refresh_token 通过userOpenip函数中的jsonobject的access_token、refresh_token返回参数中得到
	 * @param refresh_token
	 */
	public static Map<String, String> refreshToken(String refresh_token){
		String result = HttpNetUtil.getJson("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
				+WeixinConfigService.getAppid()+ "&grant_type=refresh_token&refresh_token="+refresh_token);
		Map<String, String> params = Maps.newHashMap();
		try {
			JSONObject json = new JSONObject(result);
			if(!json.has("errcode")){
				String[] fields = JSONObject.getNames(json);
				if(fields!=null){
					for(String key :fields)
						params.put(key, json.get(key).toString());
				}
			}else
				logger.error("刷新网页token失败，{}",json.getString("errmsg"));
		} catch (JSONException e) {
			logger.error("刷新网页token异常",e);
		}
		return params;
	}
}

