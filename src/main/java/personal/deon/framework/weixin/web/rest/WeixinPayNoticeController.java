package personal.deon.framework.weixin.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import personal.deon.framework.weixin.entity.WeixinPayedOrder;
import personal.deon.framework.weixin.service.WeixinPayedOrderService;
import personal.deon.framework.weixin.util.WeixinPayUtil;


@RestController
@RequestMapping("/mobile/weixin/paynotice")
public class WeixinPayNoticeController{
	static Logger logger = LoggerFactory.getLogger(WeixinPayNoticeController.class);
	@Autowired
	WeixinPayedOrderService worderSer;
	
	@RequestMapping(value="",produces={MediaType.APPLICATION_XML_VALUE})
	public String weixin(HttpServletRequest request){
		Map<String, String> msg = parseXml(request);
		if("SUCCESS".equals(msg.get("return_code")) &&
			"SUCCESS".equals(msg.get("result_code"))){
			if(WeixinPayUtil.checkeSign(msg)){
				WeixinPayedOrder worder = worderSer.findByTransaction_id(msg.get("transaction_id"));
				if(worder == null){
					worder = new WeixinPayedOrder();
				}
				worder.fieldValue(msg);
				worderSer.save(worder);
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
			}else{
				logger.warn("通知消息体被篡改");
			}
		}
		return "<xml><return_code><![CDATA[FAIL]]></return_code>"
				+ "<return_msg><![CDATA[返回数据错误]]></return_msg></xml>";
	}
	
	
	
	private final Map<String, String> parseXml(HttpServletRequest request){
		Map<String, String> mesage = Maps.newHashMap();
		Document doc = null;
		try(InputStream inputStream = request.getInputStream()) {
			StringBuffer xml = new StringBuffer();
			byte[] data = new byte[1024];
			int read = 0;
			while((read=inputStream.read(data))>-1)
				xml.append(new String(data,0,read,"UTF-8"));
			
			logger.info("pay order notice:"+xml.toString());
			doc = DocumentHelper.parseText(xml.toString());
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list)
				mesage.put(e.getName(), e.getText());
		}catch (IOException e) {
			logger.error("支付结果通用通知异常", e);
		} catch (DocumentException e) {
			logger.error("支付结果通用通知xml 解析异常", e);
		}
		return mesage;
	}
	
}
