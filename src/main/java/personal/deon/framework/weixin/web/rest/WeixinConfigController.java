package personal.deon.framework.weixin.web.rest;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.weixin.service.WeixinConfigService;
import personal.deon.framework.weixin.util.SHA1;

@Controller
@RequestMapping("/mobile/weixin/config")
public class WeixinConfigController {
	@Autowired
	WeixinConfigService confSer;
	static String noncestr = "teFonericFacto";
	
	@PostConstruct
	public void init(){
		confSer.init();
	}
	/**
	 * 获取网页调用微信js的权限
	 * @return
	 */
	@RequestMapping("jsApiToken")
	@ResponseBody
	public String[] jsApiToken(@RequestParam("url")String url){
		long timestamp = new Date().getTime()/1000;
		String jsapi = WeixinConfigService.jsApiToken();
    	StringBuffer sb = new StringBuffer("jsapi_ticket=");
    	sb.append(jsapi).append("&noncestr=").append(noncestr);
    	sb.append("&timestamp=").append(timestamp).append("&url=").append(url);

		return new String[]{WeixinConfigService.getAppid(), noncestr,timestamp+"",SHA1.sha1(sb.toString())};
	}
	
}
