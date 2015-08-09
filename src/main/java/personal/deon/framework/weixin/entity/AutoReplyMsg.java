package personal.deon.framework.weixin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import personal.deon.framework.core.entity.AbsEntity;

/**
 * 微信自动回复消息
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_autoreply")
public class AutoReplyMsg extends AbsEntity{
	
	/**关键字**/
	private String keyword;
	private String touser;
	/**text,image,voice,video,music,news**/
	private String msgtype;
	private String content;
	private WeixinMaterial mediaid;
	private String title;
    private String description;
    private String musicurl;
    private String hqmusicurl;
    private String url;
    private String picurl;
	private Date updateDate;
	private int option;
	
	public static final int STARIT_WITH = 1;
	public static final int END_WITH = 2;
	public static final int CONTAIN = 3;
	public static final int EQUALS = 4;

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the touser
	 */
	@Transient
	public String getTouser() {
		return touser;
	}
	/**
	 * @param touser the touser to set
	 */
	public void setTouser(String touser) {
		this.touser = touser;
	}
	/**
	 * @return the msgtype
	 */
	public String getMsgtype() {
		return msgtype;
	}
	/**
	 * @param msgtype the msgtype to set
	 */
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
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
	 * @return the media_id
	 */
	@ManyToOne
	public WeixinMaterial getMediaid() {
		return mediaid;
	}
	/**
	 * @param media_id the media_id to set
	 */
	public void setMediaid(WeixinMaterial mediaid) {
		this.mediaid = mediaid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the musicurl
	 */
	public String getMusicurl() {
		return musicurl;
	}
	/**
	 * @param musicurl the musicurl to set
	 */
	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}
	/**
	 * @return the hqmusicurl
	 */
	public String getHqmusicurl() {
		return hqmusicurl;
	}
	/**
	 * @param hqmusicurl the hqmusicurl to set
	 */
	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}
	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}
	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}
	
	public boolean checkMsg(String str){
		if(str==null)
			return false;
		switch (option) {
		case STARIT_WITH:
			return str.startsWith(keyword);
		case END_WITH:
			return str.endsWith(keyword);
		case CONTAIN:
			return str.contains(keyword);
		case EQUALS:
			return str.equals(keyword);
		default:
			return false;
		}
	}
	
}
