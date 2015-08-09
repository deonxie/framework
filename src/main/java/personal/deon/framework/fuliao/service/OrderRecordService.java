package personal.deon.framework.fuliao.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.OrderRecord;
import personal.deon.framework.fuliao.repository.OrderRecordDao;
import personal.deon.framework.fuliao.repository.mybatis.OrderMapper;

@Service
@Transactional(readOnly=true)
public class OrderRecordService extends GenericService<OrderRecord> {
	@Autowired
	OrderRecordDao dao;
	@Autowired
	OrderMapper mapper;

	@Override
	public GenericDao<OrderRecord> getGenericDao() {
		return dao;
	}

	public List<Map<String,Object>> findUserOrdersBypage(PageUtilDto page,String userid) {
		return mapper.findUserOrdersBypage(page,userid);
	}

	public OrderRecord findIdAndUserid(String id, String userid) {
		return dao.findIdAndUserid(id, userid);
	}
}
