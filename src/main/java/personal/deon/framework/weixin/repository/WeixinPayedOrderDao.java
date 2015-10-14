package personal.deon.framework.weixin.repository;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.weixin.entity.WeixinPayedOrder;

public interface WeixinPayedOrderDao extends GenericDao<WeixinPayedOrder> {

	@Query("from WeixinPayedOrder where out_trade_no=?1")
	WeixinPayedOrder findByOut_trade_no(String outTradeNo);
	@Query("from  WeixinPayedOrder where transaction_id =?1")
	WeixinPayedOrder findByTransaction_id(String transactionId);

}
