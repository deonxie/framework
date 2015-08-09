package personal.deon.framework.weixin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

/**
 * 微信自定义按钮
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_menu")
public class WeixinMenu extends AbsEntity{
	/**菜单标题，不超过16个字节，子菜单不超过40个字节**/
	private String name;
	/**菜单的响应动作类型view、click**/
	private String type;
	/** click等点击类型必须,菜单KEY值，用于消息接口推送，不超过128字节
	 *  view类型必须,url网页链接，用户点击菜单可打开链接，不超过256字节
	 */
	private String key;
	/**media_id类型和view_limited类型必须,调用新增永久素材接口返回的合法media_id**/
	private WeixinMaterial mediaId;
	/**上一级菜单；一级菜单数组，个数应为1~3个**/
	private WeixinMenu parent;
	/***子级菜单；二级菜单数组，个数应为1~5个***/
	private List<WeixinMenu> child;
	/***排序**/
	private int index;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the mediaId
	 */
	@ManyToOne
	public WeixinMaterial getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(WeixinMaterial mediaId) {
		this.mediaId = mediaId;
	}
	/**
	 * @return the parent
	 */
	@ManyToOne
	public WeixinMenu getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(WeixinMenu parent) {
		this.parent = parent;
	}
	/**
	 * @return the child
	 */
	@OneToMany(mappedBy="parent",cascade=CascadeType.ALL)
	public List<WeixinMenu> getChild() {
		return child;
	}
	/**
	 * @param child the child to set
	 */
	public void setChild(List<WeixinMenu> child) {
		this.child = child;
	}
	/**
	 * @return the index
	 */
	@Column(columnDefinition="integer default 0")
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
