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
import personal.deon.framework.fuliao.entity.FuliaoPermission;
import personal.deon.framework.fuliao.entity.FuliaoShop;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.service.FuliaoShopService;
import personal.deon.framework.fuliao.service.FuliaoUserService;

@Controller
@RequestMapping("/fuliao/users")
public class FuliaoUserController extends GenericController {
	@Autowired
	FuliaoUserService ser;
	@Autowired
	FuliaoShopService shopSer;
	
	@RequestMapping(value = "")
	@RequiresPermissions("fluser:view")
	public String userList(PageRequest pageRequest,ServletRequest request, 
			Model model) {
		Page<FuliaoUser> roles = ser.search(
			Servlets.getParametersStartingWith(request, SEARCH_PERFIX),pageRequest);
		model.addAttribute(PAGE, roles);
		model.addAttribute(PAGE_REQUEST, pageRequest);
		return "fuliao/fuliaouserList";
	}
	
	@RequestMapping("addrole")
	@RequiresPermissions("fluser:edit")
	public String addrole(@RequestParam("id")String id,Model model){
		model.addAttribute(ENTITY, ser.get(id));
		model.addAttribute("allPermissisons", FuliaoPermission.values());
		return "fuliao/fuliaouserRole";
	}
	
	@RequestMapping(value="saverole",method=RequestMethod.POST)
	@RequiresPermissions("fluser:edit")
	public String authorization(@RequestParam("id")String id,
			@RequestParam("permissions")String[] permissions){
		FuliaoUser user = ser.get(id);
		if(user!=null){
			StringBuffer sb = new StringBuffer();
			for(String str: permissions){
				sb.append(str).append(FuliaoUser.split);
				if(FuliaoPermission.SHOP_ISSHOP.value.equals(str)){
					FuliaoShop shop = shopSer.findUserShop(id);
					if(shop==null){
						shop = new FuliaoShop();
						shop.setCreateTime(new Date());
						shop.setShopkeeper(user);
						shopSer.save(shop);
					}
				}
			}
			sb.substring(0, sb.length()-1);
			user.setPermissions(sb.toString());
			ser.save(user);
		}
		return REDIRECT+"/fuliao/users";
	}
	
	@RequestMapping("toadd")
	@RequiresPermissions("fluser:view")
	public String toaddFuliaoUser(@ModelAttribute("inituser")FuliaoUser user,Model model){
		model.addAttribute(ENTITY, user);
		return "fuliao/fuliaouserForm";
	}
	
	@RequestMapping(value="tosave",method=RequestMethod.POST)
	@RequiresPermissions("fluser:edit")
	public String tosaveFuliaoUser(@ModelAttribute("inituser")FuliaoUser user){
		if(StringUtils.isBlank(user.getId())){
			user.setRegisterTime(new Date());
			user.setPlainPwd(user.getAccount().substring(user.getAccount().length()-6));
			ser.entryptPassword(user);
		}
		ser.save(user);
		return REDIRECT+"/fuliao/users";
	}
	
	@ModelAttribute("inituser")
	public FuliaoUser init(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isNotBlank(id)){
			return ser.get(id);
		}
		return new FuliaoUser();
	}
}
