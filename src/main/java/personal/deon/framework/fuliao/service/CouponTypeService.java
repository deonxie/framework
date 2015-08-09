package personal.deon.framework.fuliao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.CouponType;
import personal.deon.framework.fuliao.repository.CouponTypeDao;

@Service
@Transactional(readOnly=true)
public class CouponTypeService extends GenericService<CouponType> {
	@Autowired
	CouponTypeDao dao;

	@Override
	public GenericDao<CouponType> getGenericDao() {
		return dao;
	}

}
