package personal.deon.framework.weixin.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public enum MimeType{
//	rm, rmvb, wmv, avi, mpg, mpeg, mp4
	RM("rm","audio/x-pn-realaudio"),
	RMVB("rmvb","audio/x-pn-realaudio"),
	WMV("wmv","audio/x-ms-wmv"),
	AVI("avi","video/x-msvideo"),
	MPG("mpg","video/mpeg"),
	MPEG("mpeg","video/mpeg"),
	MP4("mp4","video/mp4"),
//	bmp, png, jpeg, jpg, gif
	BMP("bmp","application/x-MS-bmp"),
	PNG("png","image/png"),
	JPEG("jpeg","image/jpeg"),
	JPG("jpg","image/jpeg"),
	GIF("gif","image/gif"),
//	mp3、wma、wav、amr
	MP3("mp3","audio/x-mpeg"),
	WMA("wma","audio/x-ms-wma"),
	WAV("wav","audio/x-wav"),
	AWB("awb","audio/amr-wb");
	
	String name,type;
	static Map<String, MimeType> types = Maps.newHashMap();
	static{
		for(MimeType mt : MimeType.values()){
			types.put(mt.getName(), mt);
		}
	}
	
	MimeType(String name,String type){
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/***
	 * 查询文件的mime type值
	 * @param fileName文件的名称
	 * @return
	 */
	public static String fileMimeType(String fileName){
		fileName = StringUtils.defaultString(fileName, "");
		fileName = fileName.substring(fileName.lastIndexOf(".")+1);
		if(types.containsKey(fileName)){
			return types.get(fileName).type;
		}
		return null;
	}
	
	/**其他方式获取文件的mime type值
	 * 1：import javax.activation.MimetypesFileTypeMap;
	 * 	  new MimetypesFileTypeMap().getContentType(f);
	 * 2：import java.net.URLConnection;
	 *    URLConnection.guessContentTypeFromStream(is);
	 */
}
