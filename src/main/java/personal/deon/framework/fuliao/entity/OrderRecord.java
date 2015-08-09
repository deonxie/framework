package personal.deon.framework.fuliao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_orderrecord")
public class OrderRecord extends AbsEntity {
	/**下单用户**/
	private FuliaoUser ownUser;
	/**创建时间**/
	private Date createTime;
	/**收货人姓名**/
	private String receiveName;
	/**收货人电话**/
	private String receiveTel;
	/**收货地址**/
	private String address;
	/**结束时间**/
	private Date endDate;
	/**处理状态，0处理中，1已发货，2已完成，-1已取消 **/
	private int dealStatus;
	/**支付状态，0未付款，1已付定金，2已付全款，-1退款**/
	private int payStatus;
	/**取消原因**/
	private String reason;
	/**定金**/
	private float handsel;
	/**全款**/
	private float amount;
	/**运费**/
	private float freight;
	/**优惠卷**/
	private Coupon coupon;
	/**订单产品**/
	private List<OrderItems> products;
	/**订单号**/
	private String orderId;
	
	public final static int Deal_Status_Doing = 0;
	public final static int Deal_Status_Send = 1;
	public final static int Deal_Status_End = 2;
	public final static int Deal_Status_Cancel = -1;
	/**0 待支付*/
	public final static int Pay_Status_PAYING = 0;
	/**1 已支付定金*/
	public final static int Pay_Status_Payed_Handsel = 1;
	/**2 已支付全款*/
	public final static int Pay_Status_Payed_Amount = 2;
	/**-1 已退款*/
	public final static int Pay_Status_Cancel = -1;
	
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
	 * @return the receiveName
	 */
	public String getReceiveName() {
		return receiveName;
	}
	/**
	 * @param receiveName the receiveName to set
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	/**
	 * @return the receiveTel
	 */
	public String getReceiveTel() {
		return receiveTel;
	}
	/**
	 * @param receiveTel the receiveTel to set
	 */
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	/**
	 * @return the address
	 */
	@Column(columnDefinition="text")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the payDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param payDate the payDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the products
	 */
	@OneToMany(mappedBy="orderRecord")
	public List<OrderItems> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(List<OrderItems> products) {
		this.products = products;
	}
	/**
	 * @return the reason
	 */
	@Column(columnDefinition="text")
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the dealStatus
	 */
	public int getDealStatus() {
		return dealStatus;
	}
	/**
	 * @param dealStatus the dealStatus to set
	 */
	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}
	/**
	 * @return the payStatus
	 */
	public int getPayStatus() {
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * @return the handsel
	 */
	public float getHandsel() {
		return handsel;
	}
	/**
	 * @param handsel the handsel to set
	 */
	public void setHandsel(float handsel) {
		this.handsel = handsel;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the freight
	 */
	public float getFreight() {
		return freight;
	}
	/**
	 * @param freight the freight to set
	 */
	public void setFreight(float freight) {
		this.freight = freight;
	}
	/**
	 * @return the coupon
	 */
	@ManyToOne
	public Coupon getCoupon() {
		return coupon;
	}
	/**
	 * @param coupon the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
