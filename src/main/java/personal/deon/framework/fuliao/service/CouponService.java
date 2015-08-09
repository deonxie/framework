package personal.deon.framework.fuliao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.Coupon;
import personal.deon.framework.fuliao.repository.CouponDao;

@Service
@Transactional(readOnly=true)
public class CouponService extends GenericService<Coupon> {
	@Autowired
	CouponDao dao;

	@Override
	public GenericDao<Coupon> getGenericDao() {
		return dao;
	}

}
