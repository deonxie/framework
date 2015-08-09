package personal.deon.framework.fuliao.service;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.entity.FuliaoShop;
import personal.deon.framework.fuliao.repository.FuliaoProductDao;
import personal.deon.framework.fuliao.repository.mybatis.FuliaoProductMapper;

@Service
@Transactional(readOnly=true)
public class FuliaoProductService extends GenericService<FuliaoProduct> {
	@Autowired
	FuliaoProductDao dao;
	@Autowired
	FuliaoProductMapper mapper;
	
	
	public List<Map<String,Object>> fuliaoproductIndexPage(PageUtilDto page) {
		return mapper.fuliaoproductIndexPage(page);
	}
	

	@Override
	public GenericDao<FuliaoProduct> getGenericDao() {
		return dao;
	}

	public List<Map<String,Object>> selfProductPage(PageUtilDto page, String userid) {
		return mapper.selfProductPage(page,userid);
	}


	public List<Map<String, Object>> findLikeTarget(FuliaoProduct product) {
		return mapper.findLikeTarget(product);
	}

	public int findIsSelfProduct(String id, String userid) {
		return dao.findIsSelfProduct(id,userid);
	}

	public List<Map<String, Object>> findSelfShopElectProduct(FuliaoShop shop) {
		return mapper.findSelfShopElectProduct(shop);
	}

	/**按商铺名称，分类产品**/
	public Map<String,List<FuliaoProduct>> findProductByIds(Set<String> pids) {
		if(pids == null || pids.size()<1)
			return null;
		List<FuliaoProduct> products = dao.findProductByIds(FuliaoProduct.status_enable,pids);
		if(products !=null){
			Map<String,List<FuliaoProduct>> map = Maps.newHashMap();
			for(FuliaoProduct tmp : products){
				List<FuliaoProduct> list = map.get(tmp.getShop().getShopName());
				if(list == null)
					list = Lists.newArrayList();
				list.add(tmp);
				map.put(tmp.getShop().getShopName(), list);
			}
			return map;
		}
		return null;
	}

	/**按商铺id，分类产品**/
	public Map<String, List<FuliaoProduct>> findProductGroupByIds(
			Set<String> pids) {
		if(pids == null || pids.size()<1)
			return null;
		List<FuliaoProduct> products = dao.findProductByIds(FuliaoProduct.status_enable,pids);
		if(products !=null){
			Map<String,List<FuliaoProduct>> map = Maps.newHashMap();
			for(FuliaoProduct tmp : products){
				List<FuliaoProduct> list = map.get(tmp.getShop().getId());
				if(list == null)
					list = Lists.newArrayList();
				list.add(tmp);
				map.put(tmp.getShop().getId(), list);
			}
			return map;
		}
		return null;
	}

	public List<Map<String, Object>> homePageProducts() {
		return mapper.homePageProducts();
	}

		
}
