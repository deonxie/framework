package personal.deon.framework.fuliao.web.mobile;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.AskBuyInfo;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.entity.ProductType;
import personal.deon.framework.fuliao.service.AskBuyInfoService;
import personal.deon.framework.fuliao.service.AskBuyMappingProductService;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.ProductTypeService;
import personal.deon.framework.fuliao.util.ImageUtil;
import personal.deon.framework.weixin.util.WeixinFileUtil;

@Controller
@RequestMapping("/mobile/askbuyinfo")
public class UpdateAskBuyInfoController extends GenericController {
	@Autowired
	AskBuyInfoService ser;
	@Autowired
	ProductTypeService typeSer;
	@Autowired
	FuliaoUserService userSer;
	@Autowired
	AskBuyMappingProductService mappSer;
	@Autowired
	FuliaoProductService productSer;
	
	@RequestMapping("add")
	public String update(Model model){
		model.addAttribute("types", typeSer.getAll());
		return "mobile/askbuyForm";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(AskBuyInfo ask,@RequestParam("typeid")String typeid,
			HttpServletRequest request,Model model){
		
		if(StringUtils.isNotBlank(ask.getImgName())){
			String root = request.getServletContext().getRealPath("static");
			String saveDir = "/upload/askbuy/";
			ask.setImgName(saveDir+WeixinFileUtil.downloadFile(ask.getImgName(), root+saveDir));
			String cover = ImageUtil.scale(root+ask.getImgName(), 200, 150, 'S', null, false);
			if(StringUtils.isNotBlank(cover))
				ask.setCoverimg(saveDir+cover);
		}
		ShiroUser user = shiroUser();
		if(user!=null){
			FuliaoUser fu = userSer.get(user.id);
			ask.setTelNum(fu.getAccount());
			ask.setOwnUser(fu);
		}
		ask.setProductType(new ProductType(typeid));
		ask.setCreateTime(new Date());
		ser.save(ask);
		return REDIRECT+"/mobile/usercenter";
	}
	
	@RequestMapping(value="buyProduct",method=RequestMethod.POST)
	@ResponseBody
	public String[] buyProduct(@RequestParam("pid")String pid,
			@RequestParam("count")int count,Model model){
		ShiroUser user = shiroUser();
		if(user==null)
			return new String[]{Boolean.FALSE.toString(),"未登录"};
		FuliaoProduct product = productSer.get(pid);
		if(product==null || user.id.equals(product.getShop().getShopkeeper().getId()))
			return new String[]{Boolean.FALSE.toString(),"不能求购自己的产品"};
		AskBuyInfo ask = new AskBuyInfo();
		ask.setCreateTime(new Date());
		ask.setRequestNum(count);
		ask.setRequirement("产品名称："+product.getName()+"<br>产品材质："+product.getTexture());
		ask.setImgName(product.getCoverimg());
		ask.setCoverimg(product.getCoverimg());
		ask.setUnits(product.getUnits());
		ask.setOwnUser(userSer.get(user.id));
		ask.setTelNum(StringUtils.defaultString(ask.getOwnUser().getTelPhone(),user.loginName));
		ser.save(ask);
		return new String[]{Boolean.TRUE.toString()};
	}
	
	@RequestMapping("list")
	public String askbuyList(PageUtilDto page,HttpServletRequest request,Model model){
		if(shiroUser()==null){
			return REDIRECT+"/mobile/login";
		}
		page.parseRequest(request);
		if(!page.getField().containsKey("option"))
			page.addField("option", 1);
		page.setContent(ser.selfAskbuyinfoList(page,shiroUser().id));
		model.addAttribute(PAGE, page);
		return "mobile/askbuyList";
	}
	
	@RequestMapping("macthInfo")
	public String detail(@RequestParam("id")String id,Model model){
		model.addAttribute("mapps", mappSer.findByAskId(id));
		model.addAttribute(ENTITY, ser.get(id));
		return "mobile/askMatchInfo";
	}
	
	@RequestMapping("againMatching")
	public String againMatching(@RequestParam("id")String id,
			@RequestParam(value="remark",defaultValue="")String remark){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		AskBuyInfo ask = ser.get(id);
		if(ask !=null){
			remark = StringUtils.defaultString(ask.getRemark())+"<br>"+
					shiroUser().name+":确认处理结果。"+StringUtils.defaultString(remark);
			ask.setRemark(remark);
			ask.setStatus(AskBuyInfo.status_usersure);
			ser.save(ask);
		}
		return REDIRECT+"/mobile/askbuyinfo/list";
	}
}
