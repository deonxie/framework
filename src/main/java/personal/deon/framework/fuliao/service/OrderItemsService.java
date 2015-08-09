package personal.deon.framework.fuliao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.entity.OrderItems;
import personal.deon.framework.fuliao.repository.OrderItemsDao;

@Service
@Transactional(readOnly=true)
public class OrderItemsService extends GenericService<OrderItems> {
	@Autowired
	OrderItemsDao dao;

	@Override
	public GenericDao<OrderItems> getGenericDao() {
		return dao;
	}

}
