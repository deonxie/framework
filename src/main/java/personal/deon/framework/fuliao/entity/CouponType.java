package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

/**
 * 优惠劵类型
 * @author jlusoft
 *
 */
@Entity
@Table(name="fuliao_coupontype")
public class CouponType extends AbsEntity {
	/**优惠类型名称**/
	private String couponName;
	/**发放总金额**/
	private float allMoney;
	/**开始时间**/
	private Date benginTime;
	/**结束时间**/
	private Date endTime;
	/**发放总数量**/
	private long totality;
	/**优惠类型描述**/
	private String descrpiton;
	/**
	 * @return the couponName
	 */
	public String getCouponName() {
		return couponName;
	}
	/**
	 * @param couponName the couponName to set
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	/**
	 * @return the allMoney
	 */
	public float getAllMoney() {
		return allMoney;
	}
	/**
	 * @param allMoney the allMoney to set
	 */
	public void setAllMoney(float allMoney) {
		this.allMoney = allMoney;
	}
	/**
	 * @return the benginTime
	 */
	public Date getBenginTime() {
		return benginTime;
	}
	/**
	 * @param benginTime the benginTime to set
	 */
	public void setBenginTime(Date benginTime) {
		this.benginTime = benginTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the totality
	 */
	public long getTotality() {
		return totality;
	}
	/**
	 * @param totality the totality to set
	 */
	public void setTotality(long totality) {
		this.totality = totality;
	}
	/**
	 * @return the descrpiton
	 */
	@Column(columnDefinition="text")
	public String getDescrpiton() {
		return descrpiton;
	}
	/**
	 * @param descrpiton the descrpiton to set
	 */
	public void setDescrpiton(String descrpiton) {
		this.descrpiton = descrpiton;
	}
	
}
