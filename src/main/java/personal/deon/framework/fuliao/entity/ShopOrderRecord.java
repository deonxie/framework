package personal.deon.framework.fuliao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_shoporderrecord")
public class ShopOrderRecord extends AbsEntity {
	/**收货人姓名**/
	private String receiveName;
	/**收货人电话**/
	private String receiveTel;
	/**收货地址**/
	private String address;
	/**发货日期**/
	private Date sendTime;
	/**快递公司**/
	private String sendCompany;
	/**快递单号**/
	private String sendCode;
	/**定金**/
	private float handsel;
	/**全款**/
	private float amount;
	/**创建时间**/
	private Date createTime;
	/**交货时间**/
	private Date endDate;
	/**状态，0处理中，1已完成，-1已取消**/
	private int dealStatus;
	/**付款状态：0未付款，1已付款**/
	private int payStatus;
	/**取消原因**/
	private String reason;
	/**商铺**/
	private FuliaoShop ownShop;
	/**订单详情**/
	private List<OrderItems> products = Lists.newArrayList();
	private String orderId;
	
	public final static int Deal_Status_Doing = 0;
	public final static int Deal_Status_Finsh = 1;
	public final static int Deal_Status_Cancel = -1;
	public final static int Pay_Status_PAYING = 0;
	public final static int Pay_Status_PAYED = 1;
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
	 * @return the sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * @return the sendCompany
	 */
	public String getSendCompany() {
		return sendCompany;
	}
	/**
	 * @param sendCompany the sendCompany to set
	 */
	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany;
	}
	/**
	 * @return the sendCode
	 */
	public String getSendCode() {
		return sendCode;
	}
	/**
	 * @param sendCode the sendCode to set
	 */
	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
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
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the ownShop
	 */
	@ManyToOne
	public FuliaoShop getOwnShop() {
		return ownShop;
	}
	/**
	 * @param ownShop the ownShop to set
	 */
	public void setOwnShop(FuliaoShop ownShop) {
		this.ownShop = ownShop;
	}
	/**
	 * @return the products
	 */
	@OneToMany(mappedBy="shopOrder")
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
