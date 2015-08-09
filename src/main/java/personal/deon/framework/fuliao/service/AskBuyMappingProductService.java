package personal.deon.framework.fuliao.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.AskBuyMappingProduct;
import personal.deon.framework.fuliao.repository.AskBuyMappingProductDao;

@Service
@Transactional(readOnly=true)
public class AskBuyMappingProductService extends GenericService<AskBuyMappingProduct> {
	@Autowired
	AskBuyMappingProductDao dao;

	
	@Override
	public GenericDao<AskBuyMappingProduct> getGenericDao() {
		return dao;
	}


	public List<AskBuyMappingProduct> findByAskId(String askid) {
		return dao.findByAskId(askid);
	}
	
	public int findByAskIdForCount(String askid) {
		return dao.findByAskIdForCount(askid);
	}

	public int findByAskIdAndProductId(String aid, String pid) {
		return dao.findByAskIdAndProductId(aid,pid);
	}

	
}
