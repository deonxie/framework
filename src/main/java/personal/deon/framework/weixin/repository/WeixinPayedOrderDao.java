package personal.deon.framework.weixin.repository;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.weixin.entity.WeixinPerOrder;

public interface WeixinPerOrderDao extends GenericDao<WeixinPerOrder> {

	@Query("from WeixinPerOrder where Out_trade_no=?1")
	WeixinPerOrder findByOut_trade_no(String outTradeNo);

}
