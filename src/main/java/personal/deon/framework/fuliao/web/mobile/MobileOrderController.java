package personal.deon.framework.fuliao.web.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.AskBuyInfo;
import personal.deon.framework.fuliao.entity.AskBuyMappingProduct;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.entity.OrderItems;
import personal.deon.framework.fuliao.entity.ShopOrderRecord;
import personal.deon.framework.fuliao.entity.UserAddress;
import personal.deon.framework.fuliao.entity.OrderRecord;
import personal.deon.framework.fuliao.service.AskBuyInfoService;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.OrderItemsService;
import personal.deon.framework.fuliao.service.ShopOrderRecordService;
import personal.deon.framework.fuliao.service.UserAddressService;
import personal.deon.framework.fuliao.service.OrderRecordService;
import personal.deon.framework.weixin.service.WeixinConfigService;

@Controller
@RequestMapping("/mobile/order")
public class MobileOrderController extends GenericController {
	@Autowired
	FuliaoProductService ser;
	@Autowired
	OrderRecordService orderSer;
	@Autowired
	OrderItemsService itemsSer;
	@Autowired
	UserAddressService addressSer;
	@Autowired
	AskBuyInfoService askSer;
	@Autowired
	ShopOrderRecordService shopOrderSer;
	
	@RequestMapping("user")
	public String userOrderList(PageUtilDto page,HttpServletRequest request,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		page.parseRequest(request);
		if(!page.getField().containsKey("option"))
			page.addField("option", 1);
		page.setContent(orderSer.findUserOrdersBypage(page,user.id));
		model.addAttribute(PAGE, page);
		model.addAttribute("appid",WeixinConfigService.getAppid());
		return "mobile/orderList";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create(@RequestParam("askid")String[] askids,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		model.addAttribute("address",addressSer.findByUserId(user.id));
		model.addAttribute("askids", askids);
		return "mobile/orderForm";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String saveOrder(@RequestParam("askid")String[] askids,
			@RequestParam("addressId")String addressId,
			@RequestParam(value="couponId",required=false )String couponId,
			@RequestParam(value="remark",required=false)String remark){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		UserAddress address = addressSer.get(addressId);
		List<String> ids = Lists.newArrayList();
		for(String id : askids)
			ids.add(id);
		List<AskBuyInfo> asks = askSer.findByIds(ids);
		if(asks != null){
			OrderRecord order = new OrderRecord();
			order.setCreateTime(new Date());
			order.setOwnUser(new FuliaoUser(user.id));
			order.setAddress(address.getAddress());
			order.setReceiveName(address.getReceiveName());
			order.setReceiveTel(address.getReceiveTel());
			order.setDealStatus(OrderRecord.Deal_Status_Doing);
			order.setPayStatus(OrderRecord.Pay_Status_PAYING);
			order.setOrderId(createOrderId());
			
			List<OrderItems> orderItems = Lists.newArrayList();
			Map<String,ShopOrderRecord> shopOrders = Maps.newHashMap();
			for(AskBuyInfo ask: asks){
				order.setHandsel(order.getHandsel()+ask.getHandsel());
				order.setAmount(order.getAmount()+ask.getAmount());
				ask.setOrderStatus(AskBuyInfo.Oreder_Created);
				if(ask.getMatchParoduct() ==null)
					continue;
				
				for(AskBuyMappingProduct match : ask.getMatchParoduct()){
					OrderItems item = new OrderItems();
					item.setRequestNum(match.getBookNum());
					item.setPrice(match.getPrice());
					item.setProductId(match.getProduct().getId());
					item.setRequirement(ask.getRequirement());
					item.setRemark(remark);
					item.setImgName(match.getProduct().getCoverimg());
					item.setUnits(match.getProduct().getUnits());
					item.setTotalPrice(match.getAmount());
					item.setOrderRecord(order);
					orderItems.add(item);
					
					if(!shopOrders.containsKey(match.getProduct().getShop().getId())){
						ShopOrderRecord shoporder = new ShopOrderRecord();
						shoporder.setCreateTime(new Date());
						shoporder.setOwnShop(match.getProduct().getShop());
						shoporder.setAddress(address.getAddress());
						shoporder.setReceiveName(address.getReceiveName());
						shoporder.setReceiveTel(address.getReceiveTel());
						shoporder.setAmount(shoporder.getAmount()+match.getAmount());
						shoporder.setOrderId(createOrderId());
						shoporder.getProducts().add(item);
						shopOrders.put(match.getProduct().getShop().getId(), shoporder);
						item.setShopOrder(shoporder);
					}else{
						ShopOrderRecord shoporder = shopOrders.get(match.getProduct().getShop().getId());
						shoporder.setAmount(shoporder.getAmount()+match.getAmount());
						shoporder.getProducts().add(item);
						item.setShopOrder(shoporder);
					}
				}
			}
			orderSer.save(order);
			shopOrderSer.save(new ArrayList<ShopOrderRecord>(shopOrders.values()));
			itemsSer.save(orderItems);
			askSer.save(asks);
		}
		return REDIRECT+ "/mobile/usercenter";
	}
	
	@RequestMapping("detail")
	public String orderDetail(@RequestParam("id")String id,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		model.addAttribute(ENTITY, orderSer.get(id));
		return "mobile/orderDetail";
	}
	
	@RequestMapping("cancel")
	public String orderCancel(@RequestParam("id")String id,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		OrderRecord order = orderSer.findIdAndUserid(id, user.id);
		if(order !=null && order.getDealStatus()== OrderRecord.Deal_Status_Doing){
			order.setDealStatus(OrderRecord.Deal_Status_Cancel);
			orderSer.save(order);
		}else{
			model.addAttribute("message", "只能取消未发货状态的订单");
		}
		return REDIRECT+"/mobile/order/user";
	}
	
	private final String createOrderId(){
		StringBuffer sb = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Random random = new Random();
		for(int i=0; i<10;i++)
			sb.append(random.nextInt(10));
		return sb.toString();	
	}
}
