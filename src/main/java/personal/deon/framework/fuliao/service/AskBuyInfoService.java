package personal.deon.framework.fuliao.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.AskBuyInfo;
import personal.deon.framework.fuliao.repository.AskBuyInfoDao;
import personal.deon.framework.fuliao.repository.mybatis.AskBuyInfoMapper;

@Service
@Transactional(readOnly=true)
public class AskBuyInfoService extends GenericService<AskBuyInfo> {
	@Autowired
	AskBuyInfoDao dao;
	@Autowired
	AskBuyInfoMapper mapper;
	
	@Override
	public GenericDao<AskBuyInfo> getGenericDao() {
		return dao;
	}

	public List<Map<String, Object>> selfAskbuyinfoList(PageUtilDto page,String userid) {
		return mapper.selfAskbuyinfoList(page,userid);
	}
	
	public List<Map<String, Object>> macthAskbuyList(PageUtilDto page) {
		return mapper.macthAskbuyList(page);
	}
	
	public int findIsSelfAsk(String askid, String userid) {
		return dao.findIsSelfAsk(askid,userid);
	}
	
	@Transactional(readOnly=false)
	public void updateStatus(String id, int status) {
		dao.updateStatus(id,status);
	}

	@Transactional(readOnly=false)
	public void updateResultStatus(String id, int result) {
		dao.updateResultStatus(id, result);
	}
	public List<Map<String,Object>> matchingProduct(PageUtilDto page) {
		return mapper.matchingProduct(page);
		
	}

	public List<AskBuyInfo> findByIds(List<String> ids) {
		return dao.findByIdIn(ids);
	}
}
