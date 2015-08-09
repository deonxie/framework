package personal.deon.framework.fuliao.web.mobile;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.SMSRecordService;

@Controller
@RequestMapping("/mobile/user")
public class MobileUserController extends GenericController {
	@Autowired
	SMSRecordService smsSer;
	@Autowired
	FuliaoUserService fuliaoUserSer;
	
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@RequestParam("account")String account,
			@RequestParam("name")String name,
			@RequestParam("password")String password,
			@RequestParam("code")String code,
			ServletRequest request,Model model){
		FuliaoUser user = new FuliaoUser();
		user.setAccount(account);
		user.setPlainPwd(password);
		user.setUserName(name);
		user.setTelPhone(account);
		String error = fuliaoUserSer.register(user,code);
		if(error.length()>0){
			model.addAttribute("message", error);
			return "mobile/register";
		}
		AuthenticationToken token = new UsernamePasswordToken(user.getAccount(), 
				password, false, request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
			if(subject.isAuthenticated())
				return REDIRECT+"/mobile/usercenter";
		}catch(Exception e){
			model.addAttribute("message", "请检查用户名和密码");
		}
		return "mobile/register";
	}
	
	@RequestMapping(value="forgetpwd",method=RequestMethod.POST)
	public String register(@RequestParam("account")String account,
			@RequestParam("password")String password,
			@RequestParam("code")String code,
			ServletRequest request,Model model){
		String error = fuliaoUserSer.resetPwd(account,password,code);
		if(error.length()>0){
			model.addAttribute("message", error);
			return "mobile/forgetpwd";
		}
		AuthenticationToken token = new UsernamePasswordToken(account, 
				password, false, request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
			if(subject.isAuthenticated())
				return REDIRECT+"/mobile/usercenter";
		}catch(Exception e){
			model.addAttribute("message", "请检查用户名和密码");
		}
		return "mobile/forgetpwd";
	}
	
	@RequestMapping("sendCode")
	@ResponseBody
	public boolean sendCode(@RequestParam("telNum")String tel,
			@RequestParam("type")String type){
		return smsSer.sendCode(tel,type);
	}
}
