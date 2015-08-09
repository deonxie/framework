package personal.deon.framework.weixin.util;

import personal.deon.framework.weixin.service.WeixinConfigService;

public class WeixinFileUtil {
	
	/***
	 * 下载微信多媒体文件到指定目录下
	 * @param mediaId：多媒体id值
	 * @param saveDir：保存的目录
	 * @return 返回新建的文件名称
	 */
	public static String downloadFile(String mediaId,String saveDir){
		return HttpNetUtil.downloadFile("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+
				WeixinConfigService.accessToken()+"&media_id="+mediaId, saveDir);
	}
	
}
