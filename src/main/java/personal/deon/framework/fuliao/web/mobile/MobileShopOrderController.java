package personal.deon.framework.fuliao.web.mobile;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.ShopOrderRecordService;

@Controller
@RequestMapping("/mobile/shoporder")
public class MobileShopOrderController extends GenericController {
	@Autowired
	ShopOrderRecordService shopOrderSer;
	@Autowired
	FuliaoUserService userSer;
	
	
	@RequestMapping("")
	public String shopOrderList(PageUtilDto page,HttpServletRequest request,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		if(!page.getField().containsKey("option"))
			page.addField("option", 1);
		page.parseRequest(request);
		page.setContent(shopOrderSer.findShopOrdersBypage(page,user.id));
		model.addAttribute(PAGE, page);
		return "mobile/shopOrderList";
	}
	
	@RequestMapping("detail")
	public String orderDetail(@RequestParam("id")String id,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		model.addAttribute(ENTITY, shopOrderSer.get(id));
		return "mobile/shopOrderDetail";
	}
	
	@RequestMapping(value="send",method=RequestMethod.POST)
	public String send(@RequestParam("id")String id,@RequestParam("sendCompany")String sendCompany,
			@RequestParam("sendCode")String sendCode,HttpServletRequest request,RedirectAttributes model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		StringBuffer error = new StringBuffer();
		if(StringUtils.isBlank(sendCompany) || StringUtils.isBlank(sendCode))
			error.append("没有填写快递公司和快递单号");
		
	
		if(error.length()>0){
			model.addFlashAttribute("error", error);
			return REDIRECT+"/mobile/shoporder/detail?id="+id;
		}
		return REDIRECT+"/mobile/shoporder";
	}
	

	@RequestMapping(value="cancel",method = RequestMethod.POST)
	public String orderCancel(@RequestParam("id")String id,
			@RequestParam("reason")String reason,RedirectAttributes model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		if(StringUtils.isBlank(reason)){
			model.addFlashAttribute("error", "必须填写取消订单的原因");
			return REDIRECT+"/mobile/mobile/shoporder/detail?id="+id;
		}
		return REDIRECT+"/mobile/shoporder";
	}
	
}
