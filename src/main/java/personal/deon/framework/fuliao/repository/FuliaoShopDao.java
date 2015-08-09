package personal.deon.framework.fuliao.repository;


import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.FuliaoShop;

public interface FuliaoShopDao extends GenericDao<FuliaoShop> {
	
	@Query("from FuliaoShop where shopkeeper.id=?1 ")
	FuliaoShop findShopByUserId(String userid);
	
}
