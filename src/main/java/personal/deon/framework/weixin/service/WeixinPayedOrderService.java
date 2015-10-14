package personal.deon.framework.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.weixin.entity.WeixinPerOrder;
import personal.deon.framework.weixin.repository.WeixinPerOrderDao;

@Service
@Transactional(readOnly=true)
public class WeixinPerOrderService extends GenericService<WeixinPerOrder> {
	@Autowired
	WeixinPerOrderDao dao;
	
	@Override
	public GenericDao<WeixinPerOrder> getGenericDao() {
		return dao;
	}

	public WeixinPerOrder findByOut_trade_no(String outTradeNo) {
		return dao.findByOut_trade_no(outTradeNo);
	}

}
