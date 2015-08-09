package personal.deon.framework.weixin.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import personal.deon.framework.weixin.service.WeixinConfigService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class WeixinMsgUtil {
	static Logger logger = LoggerFactory.getLogger(WeixinMsgUtil.class);
	
	/**
	 * 发送文本类型消息
	 * @param openId
	 * @param word
	 */
	public static void textMsg(String openId,String word){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype","text");
        Map<String, Object> content = Maps.newHashMap();
        content.put("content",word);
		msg.put("text", content);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}
	
	/***
	 * 发送图文的消息
	 * @param openId
	 * @param article
	 */
	public static void newsMsg(String openId,List<Article> article){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype", "news");
		List<Object> items = Lists.newArrayList();
		for(Article art : article){
			Map<String, String> item = Maps.newHashMap();
			item.put("title", art.getTitle());
			item.put("picurl", art.getPicurl());
			item.put("url", art.getUrl());
			item.put("description", art.getDescription());
			items.add(item);
		}
		Map<String, Object> articles = Maps.newHashMap();
		articles.put("articles", items);
		msg.put("news", articles);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}

	/***
	 * 发送图片消息
	 * @param openId
	 * @param mediaId发送的图片的媒体ID
	 */
	public static void imageMsg(String openId,String mediaId){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype", "image");
		Map<String, String> item = Maps.newHashMap();
		item.put("media_id", mediaId);
		msg.put("image", item);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}
	
	/**
	 * 发送语音消息
	 * @param openId
	 * @param mediaId发送的语音的媒体ID
	 */
	public static void voiceMsg(String openId,String mediaId){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype", "voice");
		Map<String, String> item = Maps.newHashMap();
		item.put("media_id", mediaId);
		msg.put("voice", item);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}
	/***
	 * 发送视频消息
	 * @param openId
	 * @param title
	 * @param mediaId发送的视频的媒体ID
	 * @param descr
	 * @param thumbMediaId缩略图的媒体ID
	 */
	public static void videoMsg(String openId,String title,String mediaId,
			String descr,String thumbMediaId){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype", "video");
		Map<String, String> item = Maps.newHashMap();
		item.put("title", title);
		item.put("media_id", mediaId);
		item.put("description", descr);
		item.put("thumb_media_id", thumbMediaId);
		msg.put("video", item);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}
	/***
	 * 发送音乐消息
	 * @param openId
	 * @param title
	 * @param musicUrl
	 * @param hqmusicUrl高品质音乐地址
	 * @param thumbMediaId 缩略图的媒体ID
	 * @param descr音乐描述
	 */
	public static void musicMsg(String openId,String title,String musicUrl,
			String hqmusicUrl,String thumbMediaId,String descr){
		Map<String, Object> msg = Maps.newHashMap();
		msg.put("touser", openId);
		msg.put("msgtype", "music");
		Map<String, String> item = Maps.newHashMap();
		item.put("title", title);
		item.put("musicurl",musicUrl);
		item.put("hqmusicurl", hqmusicUrl);
		item.put("description", descr);
		item.put("thumb_media_id", thumbMediaId);
		msg.put("music", item);
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+
				WeixinConfigService.accessToken(),new JSONObject(msg).toString());
	}
	
	/**
	 * 多图文消息实例
	 * @author DEON
	 *
	 */
	public static class Article{
		String title;
		String picurl;
		String url;
		String description;
		
		public Article(String title,String picurl,String url,String descr) {
			this.title = StringUtils.defaultString(title);
			this.picurl = StringUtils.defaultString(picurl);
			this.url = StringUtils.defaultString(url);
			this.description = StringUtils.defaultString(descr);
		}
		public String getTitle() {
			return title;
		}
		public String getPicurl() {
			return picurl;
		}
		public String getUrl() {
			return url;
		}
		public String getDescription() {
			return description;
		}
	}

	/**
	 * 删除所有自定义菜单
	 * @throws JSONException 
	 */
	public static void deleteMenu() throws JSONException{
		HttpNetUtil.getJson("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+
				WeixinConfigService.accessToken());
	}
	/**
	 * 创建自定义菜单
	 * @param param
	 * @return
	 * @throws JSONException 
	 */
	public static void createMenu(Map<String,Object> param) throws JSONException{
		deleteMenu();
		HttpNetUtil.postJson("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+
				WeixinConfigService.accessToken(), new JSONObject(param).toString());
	}
}
