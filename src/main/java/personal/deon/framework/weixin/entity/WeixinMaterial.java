package personal.deon.framework.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import personal.deon.framework.core.entity.AbsEntity;

@Entity
@Table(name="weixin_material")
public class WeixinMaterial extends AbsEntity{
	private String mediaId;//新增的永久素材的media_id
	private String url;//新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
	private String mediaType;
	/**素材的标题*/
	private String title;
	/**素材的描述*/
	private String descri;
	private String filePath;
	private Date uploadDate;
	
	public static enum Type{
		IMAGE, VOICE, VIDEO,THUMB;
		
		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
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
	 * @return the descri
	 */
	@Column(columnDefinition="text")
	public String getDescri() {
		return descri;
	}
	/**
	 * @param descri the descri to set
	 */
	public void setDescri(String descri) {
		this.descri = descri;
	}
	/**
	 * @return the uploadDate
	 */
	@Temporal(TemporalType.DATE)
	public Date getUploadDate() {
		return uploadDate;
	}
	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}