package personal.deon.framework.fuliao.entity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Resource Base Access Control中的资源定义.
 * 
 * @author DEON
 */
public enum FuliaoPermission{
	// 系统设置
	SHOP_ISSHOP("shop:isShop", "是否是商铺", "商铺管理"),
	ASK_MATCH("ask:matching", "匹配产品", "求购管理")
	;

	public String value;
	public String displayName;
	public String type;
	private static Map<String, FuliaoPermission> valueMap = Maps.newHashMap();
	private static Map<String, List<FuliaoPermission>> listMap = Maps.newHashMap();

	static {
		for (FuliaoPermission permission : FuliaoPermission.values()) {
			valueMap.put(permission.value, permission);
			List<FuliaoPermission> permissionList = listMap.get(permission.type);
			if (permissionList == null) {
				permissionList = Lists.newArrayList();
			}
			permissionList.add(permission);
			listMap.put(permission.type, permissionList);
		}
	}

	FuliaoPermission(String value, String displayName, String type) {
		this.value = value;
		this.displayName = displayName;
		this.type = type;
	}

	public static FuliaoPermission parse(String value) {
		return valueMap.get(value);
	}

	public static List<FuliaoPermission> parseList(String type) {
		return listMap.get(type);
	}

	/**
	 * 按照分类拿去所有的Permission
	 * 
	 * @return
	 */
	public static Map<String, List<FuliaoPermission>> getListMap() {
		return listMap;
	}

	/**
	 * 按照value拿去所有的Permission
	 * 
	 * @return
	 */
	public static Map<String, FuliaoPermission> getValueMap() {
		return valueMap;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
