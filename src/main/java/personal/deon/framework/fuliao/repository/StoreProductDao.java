package personal.deon.framework.fuliao.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.StoreProduct;

public interface StoreProductDao extends GenericDao<StoreProduct> {

	@Query("select count(id) from StoreProduct where ownUser.id=?1 and product.id=?2")
	int findIsStoreed( String userid,String productid);

	@Modifying
	@Query("delete from StoreProduct where id=?1 ")
	void removeStoreProduct(String id);

}
