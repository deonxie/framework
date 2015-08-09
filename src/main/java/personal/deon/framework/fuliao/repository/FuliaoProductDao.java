package personal.deon.framework.fuliao.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.FuliaoProduct;

public interface FuliaoProductDao extends GenericDao<FuliaoProduct> {

	@Query("select count(*) from FuliaoProduct where id=?1 and shop.shopkeeper.id=?2")
	int findIsSelfProduct(String id, String userid);

	@Query("from FuliaoProduct where status=?1 and id in(?2)")
	List<FuliaoProduct> findProductByIds(int status,Set<String> pids);

}
