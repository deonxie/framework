package personal.deon.framework.fuliao.repository;


import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.OrderRecord;

public interface OrderRecordDao extends GenericDao<OrderRecord> {

	@Query("from OrderRecord where id=?1 and ownUser.id=?2")
	OrderRecord findIdAndUserid(String id, String userid);
	
}
