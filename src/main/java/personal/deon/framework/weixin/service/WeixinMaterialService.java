package personal.deon.framework.weixin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.weixin.entity.WeixinMaterial;
import personal.deon.framework.weixin.repository.WeixinMaterialDao;

@Service
@Transactional(readOnly=true)
public class WeixinMaterialService extends GenericService<WeixinMaterial>{
	
	@Autowired
	WeixinMaterialDao dao;

	@Override
	public GenericDao<WeixinMaterial> getGenericDao() {
		return dao;
	}


}
