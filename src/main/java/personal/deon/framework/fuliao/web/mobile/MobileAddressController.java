package personal.deon.framework.fuliao.web.mobile;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.entity.UserAddress;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.UserAddressService;

@Controller
@RequestMapping("/mobile/address")
public class MobileAddressController extends GenericController {
	@Autowired
	FuliaoUserService userSer;
	@Autowired
	UserAddressService addressSer;
	
	@RequestMapping("list")
	public String list(Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		model.addAttribute("address",addressSer.findByUserId(user.id));
		return "mobile/addressList";
	}
	
	@RequestMapping("delete")
	public String remove(@RequestParam("id")String id){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		List<UserAddress> list = addressSer.findByUserId(user.id);
		if(list!= null){
			for(UserAddress address : list){
				if(address.getId().equals(id)){
					addressSer.delete(id);
					break;
				}
			}
		}
		return REDIRECT+ "/mobile/address/list";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(UserAddress address,Model model){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		if(StringUtils.isNotBlank(address.getAddress())
				&& StringUtils.isNotBlank(address.getReceiveName())
				&& StringUtils.isNotBlank(address.getReceiveTel())){
			address.setCreateTime(new Date());
			address.setOwnUser(new FuliaoUser(user.id));
			addressSer.save(address);
		}
		return REDIRECT+ "/mobile/address/list";
	}
	
	@RequestMapping("default")
	public String defaultAddress(@RequestParam("id")String id){
		ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		List<UserAddress> list = addressSer.findByUserId(user.id);
		if(list!= null){
			for(UserAddress address : list){
				if(address.getId().equals(id)){
					address.setStatus(UserAddress.SATATUS_DEFAULT);
				}else{
					address.setStatus(UserAddress.SATATUS_UNDEFAULT);
				}
			}
			addressSer.save(list);
		}
		return REDIRECT+ "/mobile/address/list";
	}
}
