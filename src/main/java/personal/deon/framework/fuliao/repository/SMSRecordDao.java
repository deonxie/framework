package personal.deon.framework.fuliao.repository;



import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.SMSRecord;

public interface SMSRecordDao extends GenericDao<SMSRecord> {

	@Query("select max(sendTime) from SMSRecord where phoneNum=?1 and content=?2 and status=?3 and model=?4")
	Date findLastTimeCode(String tel, String code, int status,int model);

	@Query("select max(sendTime) from SMSRecord where phoneNum=?1 and status=?2 and model=?3")
	Date findTelLastTime(String tel,int status,int model);
}
