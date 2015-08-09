package personal.deon.framework.fuliao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;
/**
 * 求购信息
 * @author jlusoft
 */

@Entity
@Table(name="fuliao_askbuyinfo")
public class AskBuyInfo extends AbsEntity {
	/**求购图片 */
	private String imgName;
	/** 需求描述 */
	private String requirement;
	/** 单位 */
	private String units;
	/** 需求数量 */
	private long requestNum;
	/** 创建时间 */
	private Date createTime;
	/** 产品状态 0未处理，1客服已处理、 2用户确定*/
	private int status;
	/** 0未匹配,1客服正常匹配完成，-1客服找不到相应产品*/
	private int result;
	/**0未下单，1已生成订单***/
	private int orderStatus;
	/** 产品类型 */
	private ProductType productType;
	/** 所属用户 **/
	private String telNum;
	/**发布用户**/
	private FuliaoUser ownUser;
	/**定金**/
	private float handsel;
	/**总价**/
	private float amount;
	/**备注**/
	private String remark;
	private String coverimg;
	/**已匹配的产品*/
	private List<AskBuyMappingProduct> matchParoduct;
	
	/**正在处理 0*/
	public final static int status_doing = 0;
	/**客服已处理 1*/
	public final static int status_finish = 1;
	/**2用户最终确定*/
	public final static int status_usersure = 2;
	/**找到产品完成 1*/
	public final static int result_find =1;
	/**找不到产品 -1*/
	public final static int result_notfind = -1;
	/**已下单*/
	public static final int Oreder_Created = 1;
	
	public AskBuyInfo() {
		
	}
	public AskBuyInfo(String id) {
		this.id = id;
	}
	
	/**
	 * @return the requirement
	 */
	@Column(nullable=false,columnDefinition="text")
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
	 * @return the requestNum
	 */
	public long getRequestNum() {
		return requestNum;
	}
	/**
	 * @param requestNum the requestNum to set
	 */
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
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
	 * @return the productType
	 */
	@ManyToOne
	public ProductType getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	/**
	 * @return the telNum
	 */
	@Column(nullable=false)
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
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the handsel
	 */
	@Column(columnDefinition="float default 0")
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
	 * @return the amount
	 */
	@Column(columnDefinition="float default 0")
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
	 * @return the matchParoduct
	 */
	@OneToMany(mappedBy="askbuy")
	public List<AskBuyMappingProduct> getMatchParoduct() {
		return matchParoduct;
	}
	/**
	 * @param matchParoduct the matchParoduct to set
	 */
	public void setMatchParoduct(List<AskBuyMappingProduct> matchParoduct) {
		this.matchParoduct = matchParoduct;
	}
	/**
	 * @return the orderStatus
	 */
	@Column(columnDefinition="integer default 0")
	public int getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return {@link #coverimg}
	 */
	public String getCoverimg() {
		return coverimg;
	}
	/**
	 * {@link #coverimg}
	 */
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	
}
