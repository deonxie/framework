package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_useraddress")
public class UserAddress extends AbsEntity {
	/**用户id**/
	private FuliaoUser ownUser;
	/**地址名称**/
	private String address;
	/**是否是默认地址 0非默认 1默认**/
	private int status;
	/**创建时间**/
	private Date createTime;
	/**收货人姓名**/
	private String receiveName;
	/**收货人电话**/
	private String receiveTel;
	
	public final static int SATATUS_UNDEFAULT = 0;
	public final static int SATATUS_DEFAULT = 1;
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
	
}
