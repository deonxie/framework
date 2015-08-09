package personal.deon.framework.fuliao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.AskBuyInfo;

public interface AskBuyInfoDao extends GenericDao<AskBuyInfo> {

	@Query("select count(id) from AskBuyInfo where id=?1 and ownUser.id=?2")
	int findIsSelfAsk(String askid, String userid);
	@Modifying
	@Query("update AskBuyInfo set status=?2 where id=?1")
	void updateStatus(String id, int status);
	@Modifying
	@Query("update AskBuyInfo set result=?2 where id=?1")
	void updateResultStatus(String id, int result);
	
	List<AskBuyInfo> findByIdIn(List<String> ids);
}
