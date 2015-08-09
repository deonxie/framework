package personal.deon.framework.fuliao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.ProductType;
import personal.deon.framework.fuliao.repository.ProductTypeDao;

@Service
@Transactional(readOnly=true)
public class ProductTypeService extends GenericService<ProductType> {
	@Autowired
	ProductTypeDao dao;
	
	@Override
	public GenericDao<ProductType> getGenericDao() {
		return dao;
	}
	
	
	
	
}
