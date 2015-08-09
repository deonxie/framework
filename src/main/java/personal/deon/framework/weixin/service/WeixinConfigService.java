package personal.deon.framework.weixin.service;

import java.io.IOException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import personal.deon.framework.core.service.CoreConfigService;
import personal.deon.framework.weixin.util.HttpNetUtil;


@Service
public class WeixinConfigService{
	static final String WEB_URL="weixin.website.url";
	static final String TOKEN ="weixin.token";
	static final String APP_ID="weixin.app.id";
	static final String APP_SECRET = "weixin.app.secret";
	static final String AES_KEY = "weixin.encoding.aes.key";
	static final String MSG_IS_SERCET = "weixin.msg.is.sercet";
	static final String mch_id = "weixin.shop.mch.id";
	static final String trade_key = "weixin.shop.trade.key";
	static final String key_store_path = "weixin.shop.key.store.path";
	static final String key_store_pwd = "weixin.shop.key.store.pwd";
	static final String server_ip = "system.server.ip";
	static Logger logger = LoggerFactory.getLogger(WeixinConfigService.class);
	static String weburl,token,appid,appsecret,aeskey,isSercet,mchid;
	static String tradekey,keystorepath,keystorepwd,serverip;
	
	final static String AccessToken = "AccessToken";
	final static String JsApiToken = "JsApiToken";
	private static CacheManager cacheManager;
	
	static{
		try{
			cacheManager = CacheManager.create(new ClassPathResource("/ehcache/ehcache-weixin.xml").getInputStream());
			cacheManager.addCache(new Cache(AccessToken, 1, false, false, 7100, 7100));
			cacheManager.addCache(new Cache(JsApiToken, 1, false, false, 7100, 7100));
		} catch (CacheException e) {
			logger.error("微信网络访问缓存管理器 加载失败",e);
		} catch (IOException e) {
			logger.error("微信网络访问缓存管理器 配置文件加载失败",e);
		}
	}
	
	public void init(){
		CoreConfigService.addConstKey(WEB_URL, "当前网站的网址");
		CoreConfigService.addConstKey(TOKEN, "微信公众号开发者中心的Token(令牌)");
		CoreConfigService.addConstKey(APP_ID, "微信公众号开发者中心的AppID(应用ID)");
		CoreConfigService.addConstKey(mch_id, "微信支付分配的商户号");
		CoreConfigService.addConstKey(APP_SECRET,"微信公众号开发者中心的AppSecret(应用密钥)");
		CoreConfigService.addConstKey(AES_KEY,"微信公众号开发者中心的EncodingAESKey(消息加解密密钥)");
		CoreConfigService.addConstKey(MSG_IS_SERCET,"微信公众号收、发消息是否需要加密，需要加密(true),默认不加密(false)");
		CoreConfigService.addConstKey(trade_key, "微信支付分配API的商户KEY");
		CoreConfigService.addConstKey(key_store_path, "微信支付分配的商户证书路径");
		CoreConfigService.addConstKey(key_store_pwd, "微信支付分配的商户证书密码");
		CoreConfigService.addConstKey(server_ip, "系统外网IP地址");
	}
	
	/**
	 * 获取微信的访问码,使用缓存对象保存，微信会7200秒更新一次验证码，验证码过期需要重新联网获取
	 * @return token
	 */
	public static String accessToken(){
		String token = null;
		Cache cache = cacheManager.getCache(AccessToken);
		Element element = cache.get(AccessToken);
		if(null == element){
			String result = HttpNetUtil.getJson(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+getAppid()+"&secret="+getAppsecret());
			try {
				token = new JSONObject(result).getString("access_token");
			} catch (JSONException e) {
				logger.error("获取access token异常,{}",e);
			}
			cache.put(new Element(AccessToken, token));
			logger.info("微信：AccessToken:{}",token);
			return token;
		}
		
		if(null != element.getObjectValue()){
			token = element.getObjectValue().toString();
		}
		return token;
	}
	
	/**
	 * 获取Js Api token ，7100秒更新一次
	 * @return
	 */
	public static String jsApiToken(){
		String jsapiToken = null;
		Cache cache = cacheManager.getCache(JsApiToken);
		Element element = cache.get(JsApiToken);
		if(null == element){
			String result = HttpNetUtil.getJson(
					"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+accessToken()+ "&type=jsapi");
			try {
				jsapiToken = new JSONObject(result).getString("ticket");
			} catch (JSONException e) {
				logger.error("获取js api token异常,{}",e);
			}
			cache.put(new Element(JsApiToken, jsapiToken));
			logger.info("微信：jsApiToken:{}",jsapiToken);
			return jsapiToken;
		}
		
		if(null != element.getObjectValue()){
			jsapiToken = element.getObjectValue().toString();
		}
		return jsapiToken;
	}

	/**
	 * @return the weburl
	 */
	public static String getWeburl() {
		if(StringUtils.isBlank(weburl))
			weburl = CoreConfigService.getValue(WEB_URL);
		return weburl;
	}

	/**
	 * @return the token
	 */
	public static String getToken() {
		if(StringUtils.isBlank(token))
			token = CoreConfigService.getValue(TOKEN);
		return token;
	}

	/**
	 * @return the appid
	 */
	public static String getAppid() {
		if(StringUtils.isBlank(appid))
			appid = CoreConfigService.getValue(APP_ID);
		return appid;
	}

	/**
	 * @return {@link #mchid}
	 */
	public static String getMchid() {
		if(StringUtils.isBlank(mchid))
			mchid = CoreConfigService.getValue(mch_id);
		return mchid;
	}

	/**
	 * @return the appsecret
	 */
	public static String getAppsecret() {
		if(StringUtils.isBlank(appsecret))
			appsecret = CoreConfigService.getValue(APP_SECRET);
		return appsecret;
	}

	/**
	 * @return the aeskey
	 */
	public static String getAeskey() {
		if(StringUtils.isBlank(aeskey))
			aeskey = CoreConfigService.getValue(AES_KEY);
		return aeskey;
	}

	/**
	 * @return the isSercet
	 */
	public static boolean isSercet(){
		if(StringUtils.isBlank(isSercet)){
			if("true".equals(CoreConfigService.getValue(MSG_IS_SERCET)) 
					&& StringUtils.isNotBlank(getAeskey()))
				 isSercet = "true";
			 else
				 isSercet = "false";
		}
		return "true".equals(isSercet);
	}

	/**
	 * @return {@link #tradekey}
	 */
	public static String getTradekey() {
		if(StringUtils.isBlank(tradekey))
			tradekey = CoreConfigService.getValue(trade_key);
		return tradekey;
	}

	/**
	 * @return {@link #keystorepath}
	 */
	public static String getKeystorepath() {
		if(StringUtils.isBlank(keystorepath))
			keystorepath = CoreConfigService.getValue(key_store_path);
		return keystorepath;
	}

	/**
	 * @return {@link #keystorepwd}
	 */
	public static String getKeystorepwd() {
		if(StringUtils.isBlank(keystorepwd))
			keystorepwd = CoreConfigService.getValue(key_store_pwd);
		return keystorepwd;
	}
	/**
	 * @return {@link #serverip}
	 */
	public static String getServerIp() {
		if(StringUtils.isBlank(serverip))
			serverip = CoreConfigService.getValue(server_ip);
		return serverip;
	}
	
}
