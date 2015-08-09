package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_producttype")
public class ProductType extends AbsEntity {
	private String typeName;
	private Date updateTime;

	public ProductType() {
	}
	public ProductType(String id) {
		this.id = id;
	}

	/**
	 * @return the typeName
	 */
	@Column(unique=true)
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
