package personal.deon.framework.fuliao.web;

import java.util.Date;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoShop;
import personal.deon.framework.fuliao.service.FuliaoShopService;

@Controller
@RequestMapping("/fuliao/shops")
public class FuliaoShopsController extends GenericController {
	@Autowired
	FuliaoShopService ser;
	
	@RequestMapping(value = "")
	public String userList(PageRequest pageRequest,ServletRequest request, 
			Model model) {
		Page<FuliaoShop> shops = ser.search(
			Servlets.getParametersStartingWith(request, SEARCH_PERFIX),pageRequest);
		model.addAttribute(PAGE, shops);
		model.addAttribute(PAGE_REQUEST, pageRequest);
		return "fuliao/fuliaoshopList";
	}
	
	@RequestMapping("update")
	@RequiresPermissions("user:edit")
	public String update(@RequestParam("id")String id,Model model){
		model.addAttribute(ENTITY, ser.get(id));
		return "fuliao/fuliaoshopForm";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@RequiresPermissions("shop:edit")
	public String save(@ModelAttribute("initshop")FuliaoShop shop){
		shop.setUpdateTime(new Date());
		ser.save(shop);
		return REDIRECT+"/fuliao/shops";
	}
	
	@ModelAttribute("initshop")
	public FuliaoShop init(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isNotBlank(id))
			return ser.get(id);
		return new FuliaoShop();
	}
	
}
