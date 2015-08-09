package personal.deon.framework.fuliao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.StoreProduct;
import personal.deon.framework.fuliao.repository.StoreProductDao;

@Service
@Transactional(readOnly=true)
public class StoreProductService extends GenericService<StoreProduct> {
	@Autowired
	StoreProductDao dao;

	@Override
	public GenericDao<StoreProduct> getGenericDao() {
		return dao;
	}

	public int findIsStoreed(String userid, String productid) {
		return dao.findIsStoreed(userid, productid);
	}
	
	@Transactional(readOnly=false)
	public void removeStoreProduct(String id) {
		dao.removeStoreProduct(id);
	}
}
