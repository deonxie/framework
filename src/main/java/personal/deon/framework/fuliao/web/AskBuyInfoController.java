package personal.deon.framework.fuliao.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.AskBuyInfo;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.service.AskBuyInfoService;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.util.ImageUtil;

@Controller
@RequestMapping("/fuliao/askbuyinfo")
public class AskBuyInfoController extends GenericController {
	@Autowired
	AskBuyInfoService ser;
	@Autowired
	FuliaoProductService pser;
	
	@RequestMapping("imgs")
	@ResponseBody
	public boolean createimg(HttpServletRequest request){
		String root = request.getServletContext().getRealPath("static");
		String saveDir = "/upload/product/";
		List<FuliaoProduct> list = pser.getAll();
		for(FuliaoProduct p:list){
			if(StringUtils.isNotBlank(p.getImgNames())&& StringUtils.isBlank(p.getCoverimg())){
				String cover = ImageUtil.scale(root+p.getImgs()[0], 200, 150, 'S', null, false);
				if(StringUtils.isNotBlank(cover))
					p.setCoverimg(saveDir+cover);
			}
		}
		pser.save(list);
		saveDir = "/upload/askbuy/";
		List<AskBuyInfo> aslist = ser.getAll();
		for(AskBuyInfo a:aslist){
			if(StringUtils.isNotBlank(a.getImgName()) &&StringUtils.isBlank(a.getCoverimg())){
				String cover = ImageUtil.scale(root+a.getImgName(), 200, 150, 'S', null, false);
				if(StringUtils.isNotBlank(cover))
					a.setCoverimg(saveDir+cover);
			}
		}
		ser.save(aslist);
		return true;
	} 
}
