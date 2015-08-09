package personal.deon.framework.fuliao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_shop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FuliaoShop extends AbsEntity {
	/**店铺名称*/
	private String shopName;
	/**店铺简介*/
	private String descript;
	/**联系电话*/
	private String telNum;
	/**联系人*/
	private String telName;
	/**地址*/
	private String address;
	/**状态 0未完善资料，1可用，-1禁用*/
	private int status;
	/**认证失败原因*/
	private String failReason;
	/**创建时间*/
	private Date createTime;
	/**修改时间*/
	private Date updateTime;
	/**店铺登录用户*/
	private FuliaoUser shopkeeper;
	/**其他联系方式**/
	private String otherContact;
	/**店铺商品*/
	private List<FuliaoProduct> products;

	/**状态 0未认证*/
	public static final int STATUS_NO_VERIFY = 0;
	/**状态 1认证通过*/
	public static final int STATUS_VERIFY_PASS = 1;
	/**状态 2认证失败*/
	public static final int STATUS_VERIFY_FAIL = 2;
	/**是平台代售，1**/
	public final static int model_proxy = 1;
	/**非平台代售，0**/
	public final static int model_unproxy = 0;
	
	public FuliaoShop() {
	}
	public FuliaoShop(String id) {
		this.id = id;
	}
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * @return the descript
	 */
	@Column(columnDefinition="text")
	public String getDescript() {
		return descript;
	}
	/**
	 * @param descript the descript to set
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	
	/**
	 * @return the address
	 */
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
	 * @return the failReason
	 */
	@Column(columnDefinition="text")
	public String getFailReason() {
		return failReason;
	}
	/**
	 * @param failReason the failReason to set
	 */
	public void setFailReason(String failReason) {
		this.failReason = failReason;
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
	 * @return the shopkeeper
	 */
	@OneToOne
	public FuliaoUser getShopkeeper() {
		return shopkeeper;
	}
	/**
	 * @param shopkeeper the shopkeeper to set
	 */
	public void setShopkeeper(FuliaoUser shopkeeper) {
		this.shopkeeper = shopkeeper;
	}
	/**
	 * @return the products
	 */
	@OneToMany(mappedBy="shop")
	public List<FuliaoProduct> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(List<FuliaoProduct> products) {
		this.products = products;
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
	/**
	 * @return the telName
	 */
	public String getTelName() {
		return telName;
	}
	/**
	 * @param telName the telName to set
	 */
	public void setTelName(String telName) {
		this.telName = telName;
	}
	/**
	 * @return {@link #otherContact}
	 */
	@Column(columnDefinition="text")
	public String getOtherContact() {
		return otherContact;
	}
	/**
	 * {@link #otherContact}
	 */
	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}
	
}
