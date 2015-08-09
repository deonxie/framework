package personal.deon.framework.fuliao.dto;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PageUtilDto {
	private int pageNum =1;
	private int pageSize =10;
	/**用于保存单一值类型条件**/
	private Map<String, Object> field = Maps.newHashMap();
	/**用于保存多值类型条件，用于in查询***/
	private Map<String, List<Object>> fields = Maps.newHashMap();
	private Object content;
	
	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		if(pageNum>1)
			this.pageNum = pageNum;
		else
			this.pageNum = 1;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if(pageSize>1)
			this.pageSize = pageSize;
		else
			this.pageSize = 10;
	}
	/**
	 * @return the field
	 */
	public Map<String, Object> getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void addField(String field, Object value) {
		this.field.put(field, value);
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return (getPageNum()-1)*getPageSize();
	}
	/**
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(Object content) {
		this.content = content;
	}
	/**
	 * @return the fields
	 */
	public Map<String, List<Object>> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void addFields(String field, List<Object> values) {
		this.fields.put(field, values);
	}

	public void parseRequest(HttpServletRequest request){
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			if(name.startsWith("search_")){
				String[] values = request.getParameterValues(name);
				if(values.length==1){
					if(StringUtils.isNotBlank(values[0]))
						addField(name.substring("search_".length()), values[0]);
				}else{
					name = name.substring("search_".length());
					List<Object> list = Lists.newArrayList();
					for(String str : values){
						if(StringUtils.isNotBlank(str))
							list.add(str);
					}
					addFields(name, list);
				}
			}else if(name.startsWith("order_")){
				String order = StringUtils.defaultString(request.getParameter(name), "");
				if(order.equalsIgnoreCase("asc") ||order.equalsIgnoreCase("desc")){
					addField(name.substring("order_".length()),order.toLowerCase());
				}
			}
		}
	}
	/**解析驼峰命名规则将typeName解析为type_name**/
	protected String addUnderscores(String name) {
		StringBuilder buf = new StringBuilder( name.replace('.', '_') );
		for (int i=1; i<buf.length()-1; i++) {
			if (
				Character.isLowerCase( buf.charAt(i-1) ) &&
				Character.isUpperCase( buf.charAt(i) ) &&
				Character.isLowerCase( buf.charAt(i+1) )
			) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}
}
