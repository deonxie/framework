package personal.deon.framework.fuliao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import personal.deon.framework.core.entity.AbsEntity;
/**
 * 对核心用户实体的扩展实例
 * @author jlusoft
 */

@Entity
@Table(name="fuliao_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FuliaoUser extends AbsEntity {
	/**登录账户*/
	private String account;
	/**微信账号**/
	private String openId;
	/**用户名称 */
	private String userName;
	private String salt;
	/**密码 */
	private String password;
	/**头像 */
	private String headImg;
	/**联系电话 */
	private String telPhone;
	/**地址 */
	private String address;
	private String email;
	/**临时密码 */
	private String plainPwd;
	private Date lastLogin;
	private Date registerTime;
	private int status;
	/**在显示求购的等级，等级越高显示越靠前*/
	private int askLevel;
	/**在显示店铺的等级，等级越高显示越靠前*/
	private int shopLevel;
	/**vip等级，等级越高代购处理越快*/
	private int vipLevel;
	/**vip等级时间*/
	private Date endDateVip;
	/**用户的权限**/
	private String permissions;
	/**转账账号*/
	public static final int status_enable = 0;
	public static final int status_disable = 1;
	public static final String split = ",";
	
	public FuliaoUser() {
	}
	
	public FuliaoUser(String id) {
		this.id = id;
	}
	
	
	/**
	 * @return the account
	 */
	@Column(unique=true)
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the headImg
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * @param headImg the headImg to set
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
	 * @return the telPhone
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * @param telPhone the telPhone to set
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the plainPwd
	 */
	@Transient
	public String getPlainPwd() {
		return plainPwd;
	}

	/**
	 * @param plainPwd the plainPwd to set
	 */
	public void setPlainPwd(String plainPwd) {
		this.plainPwd = plainPwd;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}

	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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
	 * @return the askLevel
	 */
	@Column(columnDefinition="integer default 0")
	public int getAskLevel() {
		return askLevel;
	}
	/**
	 * @param askLevel the askLevel to set
	 */
	public void setAskLevel(int askLevel) {
		this.askLevel = askLevel;
	}
	/**
	 * @return the shopLevel
	 */
	@Column(columnDefinition="integer default 0")
	public int getShopLevel() {
		return shopLevel;
	}
	/**
	 * @param shopLevel the shopLevel to set
	 */
	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}
	/**
	 * @return the vipLevel
	 */
	@Column(columnDefinition="integer default 0")
	public int getVipLevel() {
		return vipLevel;
	}
	/**
	 * @param vipLevel the vipLevel to set
	 */
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	
	/**
	 * @return the endDateVip
	 */
	@Temporal(TemporalType.DATE)
	public Date getEndDateVip() {
		return endDateVip;
	}

	/**
	 * @param endDateVip the endDateVip to set
	 */
	public void setEndDateVip(Date endDateVip) {
		this.endDateVip = endDateVip;
	}
	/**
	 * @return the openId
	 */
	@Column(unique=true)
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the permissions
	 */
	@Column(columnDefinition="text")
	public String getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Transient
    public List<String> getPermissionList() {
        if (StringUtils.isNotBlank(permissions)) {
        	return ImmutableList.copyOf(StringUtils.split(permissions, split));
        } else {
            return Lists.newArrayList();
        }
    }
}
