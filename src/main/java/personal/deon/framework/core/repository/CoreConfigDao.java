package personal.deon.framework.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import personal.deon.framework.core.entity.CoreConfig;

/**
 */
public interface CoreConfigDao extends GenericDao<CoreConfig>{
	
	CoreConfig findByKey(String key);
	
	@Query("from CoreConfig where key like ?1 order by index asc")
	List<CoreConfig> findByLikeKey(String key);
	@Query("select value from CoreConfig where key like ?1 order by index asc")
	List<String> findByLikeKeyOfString(String string);
}
