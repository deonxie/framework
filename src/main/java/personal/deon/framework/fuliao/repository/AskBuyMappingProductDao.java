package personal.deon.framework.fuliao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.fuliao.entity.AskBuyMappingProduct;

public interface AskBuyMappingProductDao extends GenericDao<AskBuyMappingProduct> {

	@Query("from AskBuyMappingProduct where askbuy.id=?1")
	List<AskBuyMappingProduct> findByAskId(String askid);

	@Query("select count(id) from AskBuyMappingProduct where askbuy.id=?1 and product.id=?2")
	int findByAskIdAndProductId(String aid, String pid);
	
	@Query("select count(id) from AskBuyMappingProduct where askbuy.id=?1")
	int findByAskIdForCount(String askid);


}
