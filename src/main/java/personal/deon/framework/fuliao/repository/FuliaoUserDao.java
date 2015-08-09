package personal.deon.framework.fuliao.repository;


import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.FuliaoUser;

public interface FuliaoUserDao extends GenericDao<FuliaoUser> {

	@Query("from FuliaoUser where account=?1 or openId=?1 and status=?2")
	FuliaoUser findByAccountOrOpenId(String account,int status);

	@Query("select count(*) from FuliaoUser where account=?1")
	int findByAccount(String account);

}
