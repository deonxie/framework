package personal.deon.framework.fuliao.web.mobile;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.fuliao.entity.OrderRecord;
import personal.deon.framework.fuliao.service.OrderRecordService;
import personal.deon.framework.weixin.dto.WeixinPerOrderDto;
import personal.deon.framework.weixin.service.WeixinConfigService;
import personal.deon.framework.weixin.util.WeixinPayUtil;

@Controller
@RequestMapping("/mobile/payfor")
public class MobilePayController {
	Logger logger = LoggerFactory.getLogger(MobilePayController.class);
	@Autowired
	OrderRecordService orderSer;
	/**
	 * 调用支付接口
	 * @param orderId
	 * @param code 
	 * 1支付定金
	 * @return
	 */
	@RequestMapping("one")
	public String payHandsel(@RequestParam("state")String orderId,
			@RequestParam("code")String code,Model model){
		model.addAttribute("params",  WeixinPayUtil.JSAPIParams(perOrder(orderId, code, 1)));
		model.addAttribute("orderid", orderId);
		model.addAttribute("option", 1);
		return "mobile/weixinPay";
	}
	/**
	 * 调用支付接口
	 * @param orderId
	 * @param code 2支付余额
	 * @return
	 */
	@RequestMapping("two")
	public String payBalance(@RequestParam("state")String orderId,
			@RequestParam("code")String code,Model model){
		model.addAttribute("params",  WeixinPayUtil.JSAPIParams(perOrder(orderId, code, 2)));
		model.addAttribute("orderid", orderId);
		model.addAttribute("option", 2);
		return "mobile/weixinPay";
	}
	/**
	 * 调用支付接口
	 * @param orderId
	 * @param code 
	 * 3支付全款
	 * @return
	 */
	@RequestMapping("all")
	public String payAmount(@RequestParam("state")String orderId,
			@RequestParam("code")String code,Model model){
		model.addAttribute("params",  WeixinPayUtil.JSAPIParams(perOrder(orderId, code, 3)));
		model.addAttribute("orderid", orderId);
		model.addAttribute("option", 3);
		return "mobile/weixinPay";
	}
	
	@RequestMapping("paystatus")
	@ResponseBody
	public void updateStatus(@RequestParam("id")String orderId,
			@RequestParam("option")int option){
		Map<String, String> param = WeixinPayUtil.orderquery(null, orderId);
		if(StringUtils.defaultString(param.get("result_code")).equals("SUCCESS")){
			int amount = Integer.parseInt(param.get("total_fee"));
			OrderRecord order = orderSer.get(orderId);
			int fee = 0;
			if(order !=null){
				switch(amount){
				case 1:
					fee = (int)(order.getHandsel()*100);
					if(amount>= fee)
						order.setPayStatus(OrderRecord.Pay_Status_Payed_Handsel);
					break;
				case 2:
					fee = (int)((order.getAmount()-order.getHandsel())*100);
					if(amount>= fee)
						order.setPayStatus(OrderRecord.Pay_Status_Payed_Amount);
					break;
				case 3:
					fee = (int)(order.getAmount()*100);
					if(amount>= fee)
						order.setPayStatus(OrderRecord.Pay_Status_Payed_Amount);
					break;
				}
				orderSer.save(order);
			}
		}
	}
	/**
	 * Prepay_id 获取订单预支付id
	 * @param orderId
	 * @param code
	 * @param option
	 * @return
	 */
	private final String perOrder(String orderId,String code,int option){
		String openid = WeixinPayUtil.userOpenip(code).get("openid");
		if(StringUtils.isNotBlank(openid)){
			return createOrder(orderId,openid,option);
		}
		return null;
	}
	/***
	 * Prepay_id 获取订单预支付id
	 * @param orderId
	 * @param openid
	 * @param option
	 * @return
	 */
	private final String createOrder(String orderId,String openid,int option){
		OrderRecord order = orderSer.get(orderId);
		if(order!=null){
			int fee = 0;
			switch(option){
			case 1:
				if(order.getPayStatus() == OrderRecord.Pay_Status_PAYING)
					fee = (int) (order.getHandsel()*100);
				logger.info("one:{}",fee);
				break;
			case 2:
				if(order.getPayStatus() == OrderRecord.Pay_Status_Payed_Handsel)
					fee = (int) ((order.getAmount()-order.getHandsel())*100);
				logger.info("two:{}",fee);
				break;
			case 3:
				if(order.getPayStatus() == OrderRecord.Pay_Status_PAYING)
					fee = (int) (order.getAmount()*100);
				logger.info("all:{}",fee);
				break;
			}
			if(fee<=0)
				return null;
			WeixinPerOrderDto worder = new WeixinPerOrderDto();
			worder.setAttach(""+option);
			worder.setBody("订单号:"+order.getOrderId());
			worder.setNotify_url(WeixinConfigService.getWeburl()+"/mobile/weixin/paynotice");
			worder.setOpenid(openid);
			worder.setOut_trade_no(order.getId());
			worder.setSpbill_create_ip(WeixinConfigService.getServerIp());
			worder.setTotal_fee(fee);
			worder.setTrade_type("JSAPI");
			worder.setDetail("支付订单："+order.getOrderId());
			WeixinPayUtil.unifiedorder(worder);
			logger.info("得到支付id：{}",worder.getPrepay_id());
			return worder.getPrepay_id();
		}
		return null;
	}
}
