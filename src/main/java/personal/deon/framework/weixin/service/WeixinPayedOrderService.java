package personal.deon.framework.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.weixin.entity.WeixinPayedOrder;
import personal.deon.framework.weixin.repository.WeixinPayedOrderDao;

@Service
@Transactional(readOnly=true)
public class WeixinPayedOrderService extends GenericService<WeixinPayedOrder> {
	@Autowired
	WeixinPayedOrderDao dao;
	
	@Override
	public GenericDao<WeixinPayedOrder> getGenericDao() {
		return dao;
	}

	public WeixinPayedOrder findByOut_trade_no(String outTradeNo) {
		return dao.findByOut_trade_no(outTradeNo);
	}
	
	public WeixinPayedOrder findByTransaction_id(String transactionId) {
		return dao.findByTransaction_id(transactionId);
	}
}
