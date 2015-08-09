package personal.deon.framework.fuliao.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.entity.FuliaoShop;

@MyBatisRepository
public interface FuliaoProductMapper {
	List<Map<String, Object>> homePageProducts();

	List<Map<String, Object>> fuliaoproductIndexPage(@Param("page")PageUtilDto page);

	List<Map<String, Object>> selfProductPage(@Param("page")PageUtilDto page, @Param("userid")String userid);

	List<Map<String, Object>> findLikeTarget(@Param("product")FuliaoProduct product);

	List<Map<String, Object>> findSelfShopElectProduct(@Param("shop")FuliaoShop shop);


}
