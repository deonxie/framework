package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

/**
 * 优惠劵
 * @author jlusoft
 *
 */
@Entity
@Table(name="fuliao_coupon")
public class Coupon extends AbsEntity {
	/**优惠金额**/
	private float money;
	/**领取时间**/
	private Date obtainTime;
	/**使用时间**/
	private Date userTime;
	/**失效时间**/
	private Date invalidTime;
	/**0可用，1已使用，-1失效**/
	private int status;
	/**最低消费金额**/
	private float minExpenditure;
	/**领取用户**/
	private FuliaoUser ownUser;
	/**优惠劵类型**/
	private CouponType couponType;
	
	/**
	 * @return the money
	 */
	public float getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(float money) {
		this.money = money;
	}
	/**
	 * @return the obtainTime
	 */
	public Date getObtainTime() {
		return obtainTime;
	}
	/**
	 * @param obtainTime the obtainTime to set
	 */
	public void setObtainTime(Date obtainTime) {
		this.obtainTime = obtainTime;
	}
	/**
	 * @return the userTime
	 */
	public Date getUserTime() {
		return userTime;
	}
	/**
	 * @param userTime the userTime to set
	 */
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
	/**
	 * @return the invalidTime
	 */
	public Date getInvalidTime() {
		return invalidTime;
	}
	/**
	 * @param invalidTime the invalidTime to set
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
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
	public void setOwnUser(FuliaoUser ownUser) {
		this.ownUser = ownUser;
	}
	/**
	 * @return the couponType
	 */
	@ManyToOne
	public CouponType getCouponType() {
		return couponType;
	}
	/**
	 * @param couponType the couponType to set
	 */
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	/**
	 * @return the minExpenditure
	 */
	public float getMinExpenditure() {
		return minExpenditure;
	}
	/**
	 * @param minExpenditure the minExpenditure to set
	 */
	public void setMinExpenditure(float minExpenditure) {
		this.minExpenditure = minExpenditure;
	}
	
}
