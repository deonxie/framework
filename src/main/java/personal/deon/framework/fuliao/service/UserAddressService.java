package personal.deon.framework.fuliao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.UserAddress;
import personal.deon.framework.fuliao.repository.UserAddressDao;

@Service
@Transactional(readOnly=true)
public class UserAddressService extends GenericService<UserAddress> {
	@Autowired
	UserAddressDao dao;

	@Override
	public GenericDao<UserAddress> getGenericDao() {
		return dao;
	}

	public List<UserAddress> findByUserId(String userid) {
		return dao.getByUserId(userid);
	}

}
