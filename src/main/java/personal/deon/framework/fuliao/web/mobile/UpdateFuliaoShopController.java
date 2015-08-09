package personal.deon.framework.fuliao.web.mobile;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoShop;
import personal.deon.framework.fuliao.service.FuliaoShopService;

@Controller
@RequestMapping("/mobile/shop/option")
public class UpdateFuliaoShopController extends GenericController {
	@Autowired
	FuliaoShopService ser;
	
	@RequestMapping("detail")
	public String shopDetail(@ModelAttribute("initshop")FuliaoShop shop,Model model){
		ShiroUser user = shiroUser();
		if(user==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission("shop:isShop"))
			return "mobile/noright";
		
		model.addAttribute(ENTITY, shop);
		return "mobile/shopDetail";
	}
	
	@RequestMapping("update")
	public String update(@ModelAttribute("initshop")FuliaoShop shop,Model model){
		ShiroUser user = shiroUser();
		if(user==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission("shop:isShop"))
			return "mobile/noright";
		
		model.addAttribute(ENTITY, shop);
		return "mobile/shopForm";
	}
	
	@RequestMapping("save")
	public String save(@ModelAttribute("initshop")FuliaoShop shop,Model model){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission("shop:isShop"))
			return "mobile/noright";
		
		if(StringUtils.isNoneBlank(shop.getId()))
			ser.save(shop);
		return REDIRECT+"/mobile/shop/option/detail";
	}
	
	@ModelAttribute("initshop")
	public FuliaoShop initShop(){
		ShiroUser user = shiroUser();
		if(user != null)
			return ser.findUserShop(user.id);
		return new FuliaoShop();
	}
}
