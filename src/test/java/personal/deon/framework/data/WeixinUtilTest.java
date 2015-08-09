package personal.deon.framework.data;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import personal.deon.framework.fuliao.entity.OrderRecord;
import personal.deon.framework.fuliao.util.ImageUtil;
import personal.deon.framework.weixin.entity.WeixinPerOrder;
import personal.deon.framework.weixin.service.WeixinConfigService;
import personal.deon.framework.weixin.util.HttpNetUtil;
import personal.deon.framework.weixin.util.WeixinPayUtil;

public class WeixinUtilTest {
	public static void main(String[] args) {
		WeixinPerOrder worder;
		worder= new WeixinPerOrder();
		worder.setAttach(""+1);
		worder.setBody("订单号:"+"123123214354353");
		worder.setNotify_url("http://www.fuliao168.com/fuliao"+"/mobile/weixin/paynotice");
		worder.setOpenid("o1c8wt40T2yDoCqxCkBJqg8G0yW4");
		worder.setOut_trade_no("orderid");
		worder.setSpbill_create_ip("120.25.210.86");
		worder.setTotal_fee(1);
		worder.setTrade_type("JSAPI");
		worder.setDetail("支付订单："+"123123214354353");
		worder.setCash_fee(null);
		WeixinPayUtil.unifiedorder(worder);
		
	}
	private static String toXml(Map<String,String> fields){
		StringBuffer sb = new StringBuffer("<xml>");
		if(fields != null){
			String[] params = new String[fields.size()+2];
			int index =0;
			for(String key:fields.keySet()){
				sb.append("<").append(key).append(">")
				.append(fields.get(key)).append("</").append(key).append(">");
				params[index++]=key+"="+fields.get(key);
			}
			sb.append("<appid>").append("wxdb9710f998e23b63").append("</appid>")
			.append("<mch_id>").append("1259839001").append("</mch_id>");
			params[index++]="appid="+"wxdb9710f998e23b63";
			params[index++]="mch_id="+"1259839001";
//	    	sb.append("<sign>").append(createSign(params)).append("</sign>");
		}
		sb.append("</xml>");
		return sb.toString();
	}
}

