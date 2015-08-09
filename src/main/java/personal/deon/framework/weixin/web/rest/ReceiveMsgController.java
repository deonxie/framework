package personal.deon.framework.weixin.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import personal.deon.framework.weixin.service.AutoReplyMsgService;
import personal.deon.framework.weixin.service.WeixinConfigService;
import personal.deon.framework.weixin.util.SHA1;


@RestController
@RequestMapping("/mobile/weixin/receive")
public class ReceiveMsgController{
	static Logger logger = LoggerFactory.getLogger(ReceiveMsgController.class);
	@Autowired
	AutoReplyMsgService msgSer;
	
	@RequestMapping(value="",produces={MediaType.APPLICATION_XML_VALUE})
	public String weixin(HttpServletRequest request){
		String msg = parseMsg(request);
		if(StringUtils.isBlank(msg))
			return verify(request);
		return msg;
	}
	
	private final String verify(HttpServletRequest request){
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp =	request.getParameter("timestamp");
		String token = WeixinConfigService.getToken();
		String digest = SHA1.getSHA1(token, timestamp, nonce);
		if (digest.equalsIgnoreCase(signature)){
			return echostr;
		}
		logger.error("signature({});echostr({});nonce({});timestamp({}),digest({}),check fail",
				signature,echostr,nonce,timestamp,digest);
		return "";
	}
	
	private final String parseMsg(HttpServletRequest request){	 
		Map<String, String> msg = parseXml(request);
		StringBuffer xml = new StringBuffer();
		if(msg != null){
			String type = msg.get("MsgType");
			switch(type.toUpperCase()){
			case "TEXT":
				msgSer.deailTextMsg(msg.get("FromUserName"),msg.get("Content"));
				xml.append("<xml><ToUserName><![CDATA[").append(msg.get("FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[").append(msg.get("ToUserName"))
				.append("]]></FromUserName><CreateTime>").append(msg.get("CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
				return xml.toString();
			case "VOICE":
				xml.append("<xml><ToUserName><![CDATA[").append(msg.get("FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[").append(msg.get("ToUserName"))
				.append("]]></FromUserName><CreateTime>").append(msg.get("CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
				return xml.toString();
			case "LOCATION":
			case "LINK":
			case "IMAGE":
			case "VIDEO":
			case "SHORTVIDEO":
				msgSer.deailTextMsg(msg.get("FromUserName"),"");
				break;
			case "EVENT":
				msgSer.deailTextMsg(msg.get("FromUserName"),msg.get("Event"));
				break;
			}
		}
		return "";
	}
	
	private final Map<String, String> parseXml(HttpServletRequest request){
		Document doc = null;
		try(InputStream inputStream = request.getInputStream()) {
			StringBuffer xml = new StringBuffer();
			byte[] data = new byte[1024];
			int read = 0;
			while((read=inputStream.read(data))>-1)
				xml.append(new String(data,0,read,"UTF-8"));
			
			logger.info("msg:"+xml.toString());
			if(xml.length()>0){
				doc = DocumentHelper.parseText(xml.toString());
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Element> list = root.elements();
				
				Map<String, String> mesage = Maps.newHashMap();
				for (Element e : list)
					mesage.put(e.getName(), e.getText());
				return mesage;
			}	
		}catch (IOException e) {
			logger.error("系统读取微信推送信息异常", e);
		} catch (DocumentException e) {
			logger.error("xml 解析异常", e);
		}
		return null;
	}
	
}
