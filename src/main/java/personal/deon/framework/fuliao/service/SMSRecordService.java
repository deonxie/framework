package personal.deon.framework.fuliao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.entity.CoreConfig;
import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.CoreConfigService;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.SMSRecord;
import personal.deon.framework.fuliao.repository.FuliaoUserDao;
import personal.deon.framework.fuliao.repository.SMSRecordDao;
import personal.deon.framework.fuliao.util.SendSmsUtil;

@Service
@Transactional(readOnly=true)
public class SMSRecordService extends GenericService<SMSRecord> {
	@Autowired
	SMSRecordDao dao;
	@Autowired
	FuliaoUserDao userDao;
	
	/**短信验证码**/
	public static final int sms_model_code = 9;
	static final String sms_lose_time = "sms.code.lose.time.minutes";
	Random random = new Random();
	
	@PostConstruct
	public void initConfig(){
		CoreConfigService.addConstKey(sms_lose_time,"验证码失效分钟数,正整数，默认为5.");
		SendSmsUtil.initConfig();
	}
	
	public int lostTime(){
		CoreConfig config = CoreConfigService.getByKey(sms_lose_time);
		if(config!=null){
			try{
				return Integer.parseInt(config.getValue());
			}catch(Exception e){}
		}
		return 5;
	}
	
	@Override
	public GenericDao<SMSRecord> getGenericDao() {
		return dao;
	}
	
	@Transactional(readOnly=false)
	public boolean sendCode(String tel,String type){
		if("register".equals(type) && userDao.findByAccount(tel)>0)
			return false;
		if(!checkSendCode(tel))
			return false;
		StringBuffer sb = new StringBuffer();
		sb.append(random.nextInt(9)+1).append(random.nextInt(9)+1)
		.append(random.nextInt(9)+1).append(random.nextInt(9)+1)
		.append(random.nextInt(9)+1).append(random.nextInt(9)+1);
		SendSmsUtil.SmsSendResult result = SendSmsUtil.sendCode(sb.toString(),tel);
		if(result != null){
			SMSRecord record = new SMSRecord();
			record.setContent(sb.toString());
			record.setPhoneNum(tel);
			record.setSendDate(new Date());
			record.setSendTime(record.getSendDate());
			record.setStatus(result.isSuccess()?SMSRecord.send_success:SMSRecord.send_fail);
			record.setRespcode(result.getRespCode());
			record.setSmsid(result.getSmsId());
			record.setModel(sms_model_code);
			dao.save(record);
			return result.isSuccess();
		}
		return false;
	}

	/**检查短信验证码，验证码正确返回true，不正确返回false**/
	public boolean checkCode(String tel, String code) {
		Date now = new Date();
		Date date = dao.findLastTimeCode(tel,code,SMSRecord.send_success,sms_model_code);
		if(date==null)
			return false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, lostTime());
		date = calendar.getTime();
		return now.before(date);
	}
	
	/**检查手机是否可以发送的短信验证码，可以发送返回true，不能发送返回false**/
	public boolean checkSendCode(String tel) {
		Date now = new Date();
		Date date = dao.findTelLastTime(tel,SMSRecord.send_success,sms_model_code);
		if(date==null)
			return true;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, lostTime());
		date = calendar.getTime();
		return date.before(now);
	}
}
