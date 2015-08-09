package personal.deon.framework.fuliao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="fuliao_smsrecord")
public class SMSRecord extends AbsEntity{
	/**发送号码**/
	private String phoneNum;
	/**发送时间**/
	private Date sendTime;
	private Date sendDate;
	/**发送内容**/
	private String content;
	/**发送状态 0发送失败，1发送成功**/
	private int status;
	/**短信类型 9短信验证码，8订单通知**/
	private int model;
	/**发送用户**/
	private String sendName;
	/**短信发送 响应编号**/
	private String respcode;
	/**短信发送 短信平台对应的编号**/
	private String smsid;
	
	public static final int send_success = 1;
	public static final int send_fail = 0;
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the model
	 */
	public int getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(int model) {
		this.model = model;
	}
	/**
	 * @return the sendName
	 */
	public String getSendName() {
		return sendName;
	}
	/**
	 * @param sendName the sendName to set
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	/**
	 * @return the sendDate
	 */
	@Temporal(TemporalType.DATE)
	public Date getSendDate() {
		return sendDate;
	}
	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	/**
	 * @return the respcode
	 */
	public String getRespcode() {
		return respcode;
	}
	/**
	 * @param respcode the respcode to set
	 */
	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}
	/**
	 * @return the smsid
	 */
	public String getSmsid() {
		return smsid;
	}
	/**
	 * @param smsid the smsid to set
	 */
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
	
}
