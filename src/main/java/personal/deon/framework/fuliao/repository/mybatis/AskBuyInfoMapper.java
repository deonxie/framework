package personal.deon.framework.fuliao.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import personal.deon.framework.fuliao.dto.PageUtilDto;

@MyBatisRepository
public interface AskBuyInfoMapper {
	/**用户查看自己已发布的求购消息，分页查询**/
	List<Map<String, Object>> selfAskbuyinfoList(@Param("page")PageUtilDto page,@Param("userid")String userId);
	/**未匹配产品的求购列表**/
	List<Map<String, Object>> macthAskbuyList(@Param("page")PageUtilDto page);
	/**匹配求购的产品**/
	List<Map<String, Object>> matchingProduct(@Param("page")PageUtilDto page);
	
}
