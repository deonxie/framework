package personal.deon.framework.fuliao.service;


import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gd.thinkjoy.modules.security.utils.Digests;
import cn.gd.thinkjoy.modules.utils.Encodes;
import personal.deon.framework.core.entity.CoreConfig;
import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.CoreConfigService;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.core.service.CoreLoginService;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.repository.FuliaoUserDao;

@Service
@Transactional(readOnly=true)
public class FuliaoUserService extends GenericService<FuliaoUser> {
	@Autowired
	FuliaoUserDao dao;
	@Autowired
	SMSRecordService smsSer;
	static final String DEFAULT_USER_IMG = "fuliao.user.default.head.img";
	
	@Override
	public GenericDao<FuliaoUser> getGenericDao() {
		return dao;
	}
	
	@PostConstruct
	public void initConfig(){
		CoreConfigService.addConstKey(DEFAULT_USER_IMG,"代购平台默认用户头像图片.");
	}

	public String defaultImg() {
		CoreConfig conf = CoreConfigService.getByKey(DEFAULT_USER_IMG);
		return conf==null?null:conf.getValue();
	}
	
	public FuliaoUser findByAccountOrOpenId(String account) {
		return dao.findByAccountOrOpenId(account,FuliaoUser.status_enable);
	}

	/** 注册用户 **/
	@Transactional(readOnly=false)
	public String register(FuliaoUser user,String code){
		StringBuffer error = new StringBuffer();
		if(StringUtils.isBlank(user.getAccount()))
			error.append("未填写账号,");
		int count = dao.findByAccount(user.getAccount());
		if(count>0)
			error.append("已注册,");
		if(!smsSer.checkCode(user.getAccount(),code))
			error.append("验证码错误或已过期,");
		if(StringUtils.isBlank(user.getPlainPwd()))
			error.append("未设置密码,");
		if(StringUtils.trimToEmpty(user.getPlainPwd()).length()<6 || 
				StringUtils.trimToEmpty(user.getPlainPwd()).length()>20)
    		error.append("请输入6-20字符，数字和字母组合的密码,");
		if(error.length()==0){
			if(StringUtils.isBlank(user.getUserName()))
				user.setUserName("匿名");
			user.setRegisterTime(new Date());
			entryptPassword(user);
			dao.save(user);
		}
		return error.toString();
	}
	
	/**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public void entryptPassword(FuliaoUser user) {
        byte[] salt = Digests.generateSalt(CoreLoginService.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPwd().getBytes(), salt, CoreLoginService.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
    
    @Transactional(readOnly=false)
	public String resetPwd(String account, String password, String code) {
    	StringBuffer error = new StringBuffer();
    	if(StringUtils.isBlank(password))
    		error.append("请输入密码,");
    	if(StringUtils.trimToEmpty(password).length()<6 || StringUtils.trimToEmpty(password).length()>20)
    		error.append("请输入6-20字符，数字和字母组合的密码,");
    	if(!smsSer.checkCode(account,code))
			error.append("验证码错误或已过期,");
    	if(error.length()==0){
	    	FuliaoUser user = dao.findByAccountOrOpenId(account, FuliaoUser.status_enable);
	    	if(user != null){
	    		user.setPlainPwd(password);
	    		entryptPassword(user);
	    		dao.save(user);
	    	}
    	}
		return error.toString();
	}

}
