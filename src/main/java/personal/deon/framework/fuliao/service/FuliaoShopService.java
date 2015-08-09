package personal.deon.framework.fuliao.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.FuliaoShop;
import personal.deon.framework.fuliao.repository.FuliaoShopDao;

@Service
@Transactional(readOnly=true)
public class FuliaoShopService extends GenericService<FuliaoShop> {
	@Autowired
	FuliaoShopDao dao;
	

	@Override
	public GenericDao<FuliaoShop> getGenericDao() {
		return dao;
	}

	public FuliaoShop findUserShop(String userid) {
		return dao.findShopByUserId(userid);
	}

}
