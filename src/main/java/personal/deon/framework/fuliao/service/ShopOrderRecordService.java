package personal.deon.framework.fuliao.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.ShopOrderRecord;
import personal.deon.framework.fuliao.repository.ShopOrderRecordDao;
import personal.deon.framework.fuliao.repository.mybatis.OrderMapper;

@Service
@Transactional(readOnly=true)
public class ShopOrderRecordService extends GenericService<ShopOrderRecord> {
	@Autowired
	ShopOrderRecordDao dao;
	@Autowired
	OrderMapper mapper;
	
	@Override
	public GenericDao<ShopOrderRecord> getGenericDao() {
		return dao;
	}
	public List<Map<String,Object>> findShopOrdersBypage(PageUtilDto page, String userid) {
		return mapper.findShopOrdersBypage(page, userid);
	}
}
