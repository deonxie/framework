package personal.deon.framework.fuliao.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import personal.deon.framework.fuliao.dto.PageUtilDto;

@MyBatisRepository
public interface OrderMapper {

	List<Map<String, Object>> findUserOrdersBypage(@Param("page")PageUtilDto page,
			@Param("userid")String userid);

	List<Map<String, Object>> findShopOrdersBypage(@Param("page")PageUtilDto page,
			@Param("userid")String userid);

}
