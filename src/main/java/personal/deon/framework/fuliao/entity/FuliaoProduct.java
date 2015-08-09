package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_Product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FuliaoProduct extends AbsEntity{
	/** 产品名称 */
	private String name;
	/** 产品材质 */
	private String texture;
	/** 产品描述 */
	private String descript;
	/** 单位 */
	private String units;
	/** 库存数量 */
	private long stockNum;
	/** 产品货源类型 0现货，1样板 */
	private int model;
	/** 单价 */
	private float price;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 产品状态 0正常，1下架 */
	private int status;
	/** 产品类型 */
	private ProductType type;
	/** 销售量 */
	private long saleNum;
	/** 用户评分 */
	private long gradeScore;
	/** 所属商铺 **/
	private FuliaoShop shop;
	/**产品图集（多张图片用‘,’分割；第一张为封面；） */
	private String imgNames;
	/**产品排序*/
	private int index;
	/**产品备注，如购买100件 10元/件；买1000件 9元/件**/
	private String remark;
	/**封面图片**/
	private String coverimg;
	
	public static final int status_enable = 0;
	public static final int status_disable = 1;
	public static final String split = ",";
	
	public FuliaoProduct() {
	}
	public FuliaoProduct(String id) {
		this.id = id;
	}
	
	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the {@link #descript}
	 */
	@Column(columnDefinition="text")
	public String getDescript() {
		return descript;
	}
	/**
	 * @param descript the {@link #descript} to set
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * @return the {@link #units}
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the {@link #units} to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the {@link #stockNum}
	 */
	public long getStockNum() {
		return stockNum;
	}
	/**
	 * @param stockNum the {@link #stockNum} to set
	 */
	public void setStockNum(long stockNum) {
		this.stockNum = stockNum;
	}
	/**
	 * @return the {@link #model}
	 */
	public int getModel() {
		return model;
	}
	/**
	 * @param model the {@link #model} to set
	 */
	public void setModel(int model) {
		this.model = model;
	}
	/**
	 * @return the {@link #price}
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the {@link #price} to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the {@link #createTime}
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the {@link #createTime} to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the {@link #updateTime}
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the {@link #updateTime} to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the {@link #status}
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the {@link #status} to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the {@link #type}
	 */
	@ManyToOne
	public ProductType getType() {
		return type;
	}
	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(ProductType type) {
		this.type = type;
	}
	
	
	/**
	 * @return the {@link #saleNum}
	 */
	public long getSaleNum() {
		return saleNum;
	}
	/**
	 * @param saleNum the {@link #saleNum} to set
	 */
	public void setSaleNum(long saleNum) {
		this.saleNum = saleNum;
	}
	/**
	 * @return the {@link #gradeScore}
	 */
	public long getGradeScore() {
		return gradeScore;
	}
	/**
	 * @param gradeScore the {@link #gradeScore} to set
	 */
	public void setGradeScore(long gradeScore) {
		this.gradeScore = gradeScore;
	}
	
	/**
	 * @return the {@link #texture}
	 */
	@Column(columnDefinition="text")
	public String getTexture() {
		return texture;
	}
	/**
	 * @param texture the {@link #texture} to set
	 */
	public void setTexture(String texture) {
		this.texture = texture;
	}
	/**
	 * @return the shop
	 */
	@ManyToOne
	public FuliaoShop getShop() {
		return shop;
	}
	/**
	 * @param shop the shop to set
	 */
	public void setShop(FuliaoShop shop) {
		this.shop = shop;
	}
	/**
	 * @return the imgNames
	 */
	@Column(columnDefinition="text")
	public String getImgNames() {
		return imgNames;
	}
	/**
	 * @param imgNames the imgNames to set
	 */
	public void setImgNames(String imgNames) {
		this.imgNames = imgNames;
	}	
	/**
	 * @return the index
	 */
	@Column(columnDefinition="integer default 0")
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * @return {@link #remark}
	 */
	@Column(columnDefinition="text")
	public String getRemark() {
		return remark;
	}
	/**
	 * {@link #remark}
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * {@link #coverimg}
	 */
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	/**
	 * {@link #coverimg}
	 */
	public String getCoverimg(){
		return this.coverimg;
	}
	
	@Transient
	public String[] getImgs(){
		if(StringUtils.isNotBlank(this.imgNames)){
			return StringUtils.split(this.imgNames, ",");
		}
		return null;
	}
}
