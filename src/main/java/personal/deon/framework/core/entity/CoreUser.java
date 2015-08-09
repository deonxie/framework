package personal.deon.framework.core.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

@Entity
@Table(name="core_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CoreUser extends AbsEntity {
	private String account;
	private String userName;
	private String salt;
	private String password;
	private String headImg;
	private String telPhone;
	private String address;
	private String email;
	private String plainPwd;
	private Date lastLogin;
	private Date registerTime;
	private int status;
	private List<CoreRole> roles = Lists.newArrayList();
	/**
	 * @return the account
	 */
	@Column(unique=true,nullable=false)
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
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
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
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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
	 * @return the role
	 */
	@ManyToMany
	@JoinTable(name = "core_user_role", joinColumns = {@JoinColumn(name = "USER_ID")},
	inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	public List<CoreRole> getRoles() {
		return roles;
	}
	/**
	 * @param role the role to set
	 */
	public void setRoles(List<CoreRole> roles) {
		this.roles = roles;
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
	
}
