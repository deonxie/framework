package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_storeproduct")
public class StoreProduct extends AbsEntity{
	private FuliaoUser ownUser;
	private FuliaoProduct product;
	private Date createTime;
	private String remark;
	/**
	 * @return the ownUser
	 */
	@ManyToOne
	public FuliaoUser getOwnUser() {
		return ownUser;
	}
	/**
	 * @param ownUser the ownUser to set
	 */
	public void setOwnUser(FuliaoUser user) {
		this.ownUser = user;
	}
	/**
	 * @return the shop
	 */
	@ManyToOne
	public FuliaoProduct getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(FuliaoProduct product) {
		this.product = product;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the remark
	 */
	@Column(columnDefinition="text")
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
