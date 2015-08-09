package personal.deon.framework.fuliao.web.mobile;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.core.entity.CoreConfig;
import personal.deon.framework.core.service.CoreConfigService;
import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.FuliaoUserService;

@Controller
@RequestMapping("/mobile")
public class FuliaoIndexController extends GenericController{
	@Autowired
	FuliaoProductService productSer;
	@Autowired
	FuliaoUserService userSer;
	static final String ASDIMGS_KEY = "Fuliao.Index.adsImgs";
	
	@PostConstruct
	public void initConfig(){
		CoreConfigService.addConstKey(ASDIMGS_KEY,
				"辅料微信首页推广图片，多张可以在key后加数字，如Fuliao.Index.adsImgs2");
	}
	
	@RequestMapping("")
	public String index(Model model){
		List<CoreConfig> adsImgs = CoreConfigService.findStartWithKey(ASDIMGS_KEY);
		model.addAttribute("adsImgs", adsImgs);
		List<Map<String, Object>> products = productSer.homePageProducts();
		model.addAttribute("products", products);
		return "mobile/index";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String mobileLogin(){
		return "mobile/login";
	}
	
	@RequestMapping(value="ajaxlogin",method=RequestMethod.POST)
	@ResponseBody
	public boolean ajaxLogin(@RequestParam("username")String userName,
			@RequestParam("password")String plainPwd,ServletRequest request){
		FuliaoUser user = userSer.findByAccountOrOpenId(userName);
		if(user == null)
			return false;
		
		AuthenticationToken token = new UsernamePasswordToken(user.getAccount(), 
				plainPwd, false, request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
			return subject.isAuthenticated();
		}catch(Exception e){
		}
		return false;
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String mobileLogin(@RequestParam("username")String userName,
			@RequestParam("password")String plainPwd,
			ServletRequest request,Model model){
		FuliaoUser user = userSer.findByAccountOrOpenId(userName);
		if(user == null){
			model.addAttribute("message", "请检查用户名是否正确");
			model.addAttribute("username",userName);
			return "mobile/login";
		}
		AuthenticationToken token = new UsernamePasswordToken(user.getAccount(), 
				plainPwd, false, request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
			if(subject.isAuthenticated())
				return REDIRECT+"/mobile/usercenter";
		}catch(Exception e){
			model.addAttribute("message", "请检查密码是否正确");
		}
		model.addAttribute("username",userName);
		return "mobile/login";
	}
	
	@RequestMapping("logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "mobile/login";
	}
	
	@RequestMapping("path/{jsp}")
	public String mobileJsp(@PathVariable("jsp")String file){
		return "mobile/"+file;
	}
	
	@RequestMapping("usercenter")
	public String userCenter(Model model){
		ShiroUser user = shiroUser();
		if(	user== null)
			return "mobile/login";
		model.addAttribute(ENTITY, userSer.get(user.id));
		model.addAttribute("userImg", userSer.defaultImg());
		return "mobile/userCenter";
	}
}
