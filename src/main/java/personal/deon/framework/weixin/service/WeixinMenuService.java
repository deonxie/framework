package personal.deon.framework.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.weixin.entity.WeixinMenu;
import personal.deon.framework.weixin.repository.WeixinMenuDao;

@Service
@Transactional(readOnly=true)
public class WeixinMenuService extends GenericService<WeixinMenu> {
	
	@Autowired
	WeixinMenuDao dao;

	@Override
	public GenericDao<WeixinMenu> getGenericDao() {
		return dao;
	}
}
