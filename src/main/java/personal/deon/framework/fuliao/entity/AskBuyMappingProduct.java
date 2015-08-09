package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;
/**
 * 求购产品与系统产品匹配记录
 * @author jlusoft
 */

@Entity
@Table(name="fuliao_askbuymappingproduct")
public class AskBuyMappingProduct extends AbsEntity {
	/**求购 */
	private AskBuyInfo askbuy;
	/**产品*/
	private FuliaoProduct product;
	/** 协商后的单价 */
	private float price;
	/** 协商后的总价 */
	private float amount;
	/** 单位 */
	private String units;
	/** 预定数量 */
	private int bookNum;
	/** 创建时间 */
	private Date createTime;
	private String remark;
	
	/**
	 * @return the askbuy
	 */
	@ManyToOne
	public AskBuyInfo getAskbuy() {
		return askbuy;
	}
	/**
	 * @param askbuy the askbuy to set
	 */
	public void setAskbuy(AskBuyInfo askbuy) {
		this.askbuy = askbuy;
	}
	/**
	 * @return the product
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
	 * @return the bookNum
	 */
	public int getBookNum() {
		return bookNum;
	}
	/**
	 * @param bookNum the bookNum to set
	 */
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
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
