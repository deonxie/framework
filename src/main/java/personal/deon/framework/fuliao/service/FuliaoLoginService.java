package personal.deon.framework.fuliao.service;

import personal.deon.framework.core.service.CoreLoginService;
import personal.deon.framework.fuliao.entity.FuliaoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 */
@Service
public class FuliaoLoginService extends CoreLoginService{
	@Autowired
	protected FuliaoUserService fuliaoUserSer; 
    
    @Override
	public UserInfo findUserInfo(String account){
		UserInfo info = super.findUserInfo(account);
		if(info !=null)
			return info;
		
		FuliaoUser fuliaoUser = fuliaoUserSer.findByAccountOrOpenId(account);
		if(fuliaoUser !=null){
			info = new UserInfo();
			info.setId(fuliaoUser.getId());
			info.setUsername(fuliaoUser.getUserName());
			info.setPassword(fuliaoUser.getPassword());
			info.setSalt(fuliaoUser.getSalt());
			info.getPermissions().addAll(fuliaoUser.getPermissionList());
		}
		return info;
	}
    
   
    
}
