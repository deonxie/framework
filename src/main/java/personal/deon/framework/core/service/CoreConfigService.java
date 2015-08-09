package personal.deon.framework.core.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import personal.deon.framework.core.entity.CoreConfig;
import personal.deon.framework.core.repository.CoreConfigDao;
import personal.deon.framework.core.repository.GenericDao;

@Service
@Transactional(readOnly=true)
public class CoreConfigService extends GenericService<CoreConfig> {
	/**系统必须配置的常量参数*/
	final static Map<String,String> constKey = Maps.newHashMap();
	final static Map<String, CoreConfig> allConfg = Maps.newHashMap();
	@Autowired
	CoreConfigDao dao;
	
	@PostConstruct
	public void init(){
		Iterable<CoreConfig> list = dao.findAll();
		if(null == list)
			return;
		for(CoreConfig conf : list){
			allConfg.put(conf.getKey(), conf);
		}
	}
	
	@Override
	@Transactional(readOnly=false)
	public void save(CoreConfig entity) {
		dao.save(entity);
		allConfg.put(entity.getKey(), entity);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void delete(String id) {
		CoreConfig conf = dao.findOne(id);
		dao.delete(id);
		allConfg.remove(conf.getKey());
	}
	
	public static CoreConfig getByKey(String key){
		return allConfg.get(key);
	}

	public static List<CoreConfig> findStartWithKey(String key) {
		List<CoreConfig> list = Lists.newArrayList();
		for(String tmp : allConfg.keySet()){
			if(tmp.startsWith(key))
				list.add(allConfg.get(tmp));
		}
		return list;
	}
	public static List<String> findStartWithKeyOfString(String key) {
		List<String> list = Lists.newArrayList();
		for(String tmp : allConfg.keySet()){
			if(tmp.startsWith(key))
				list.add(allConfg.get(tmp).getValue());
		}
		return list;
	}
	/**获取系统必须配置的key**/
	public static Map<String,String> getConstKey(){
		for(CoreConfig conf: allConfg.values()){
			if(constKey.containsKey(conf.getKey()));
				constKey.remove(conf.getKey());
		}
		return ImmutableMap.copyOf(constKey);
	}
	/**添加系统必须配置的key**/
	public static void addConstKey(String key,String descript){
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(descript))
			constKey.put(key, descript);
	}

	public static String getValue(String key) {
		CoreConfig tmp = allConfg.get(key);
		return tmp==null?null:tmp.getValue();
	}

	@Override
	public GenericDao<CoreConfig> getGenericDao() {
		return dao;
	}
}
