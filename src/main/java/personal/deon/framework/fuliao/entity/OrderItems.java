package personal.deon.framework.fuliao.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_orderitems")
public class OrderItems extends AbsEntity {
	/**产品id**/
	private String productId;
	/**产品图片 */
	private String imgName;
	/** 需求描述 */
	private String requirement;
	/**购买数量**/
	private int requestNum;
	/**总价**/
	private float totalPrice;
	private String productName;
	/**价格**/
	private float price;
	/**单位**/
	private String units;
	/**备注**/
	private String remark;
	
	/**订单id**/
	private OrderRecord orderRecord;
	/**供货商家**/
	private ShopOrderRecord shopOrder;
	
	/**
	 * @return the orderRecord
	 */
	@ManyToOne
	public OrderRecord getOrderRecord() {
		return orderRecord;
	}
	/**
	 * @param orderRecord the orderRecord to set
	 */
	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the imgName
	 */
	public String getImgName() {
		return imgName;
	}
	/**
	 * @param imgName the imgName to set
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * @return the requirement
	 */
	@Column(columnDefinition="text")
	public String getRequirement() {
		return requirement;
	}
	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	/**
	 * @return the requestNum
	 */
	public int getRequestNum() {
		return requestNum;
	}
	/**
	 * @param requestNum the requestNum to set
	 */
	public void setRequestNum(int requestNum) {
		this.requestNum = requestNum;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
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
	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the shopOrder
	 */
	@ManyToOne
	public ShopOrderRecord getShopOrder() {
		return shopOrder;
	}
	/**
	 * @param shopOrder the shopOrder to set
	 */
	public void setShopOrder(ShopOrderRecord shopOrder) {
		this.shopOrder = shopOrder;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
