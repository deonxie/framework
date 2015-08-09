package personal.deon.framework.fuliao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.UserAddress;

public interface UserAddressDao extends GenericDao<UserAddress> {

	@Query("from UserAddress where ownUser.id=?1")
	List<UserAddress> getByUserId(String userid);

}
