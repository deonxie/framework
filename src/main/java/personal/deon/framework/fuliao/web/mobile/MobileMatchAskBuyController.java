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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.AskBuyInfo;
import personal.deon.framework.fuliao.entity.AskBuyMappingProduct;
import personal.deon.framework.fuliao.entity.FuliaoPermission;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.service.AskBuyInfoService;
import personal.deon.framework.fuliao.service.AskBuyMappingProductService;

@Controller
@RequestMapping("/mobile/matchaskbuy")
public class MobileMatchAskBuyController extends GenericController {
	@Autowired
	AskBuyInfoService ser;
	@Autowired
	AskBuyMappingProductService mappSer;
	
	
	@RequestMapping("list")
	public String askbuyList(PageUtilDto page,HttpServletRequest request,
			Model model){
		if(shiroUser()==null){
			return REDIRECT+"/mobile/login";
		}
		page.parseRequest(request);
		page.setContent(ser.macthAskbuyList(page));
		model.addAttribute(PAGE, page);
		return "mobile/matchAskbuyList";
	}
	
	


/***==================以下是客服为求购信息匹配产品的方法组====================***/	
	@RequestMapping("matching")
	public String matching(PageUtilDto page,@RequestParam("id")String id,
			HttpServletRequest request,Model model){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.ASK_MATCH.value))
			return "mobile/noright";
		AskBuyInfo ask = ser.get(id);
		if(ask!=null && ask.getStatus() != AskBuyInfo.status_usersure){
			page.parseRequest(request);
			page.setContent(ser.matchingProduct(page));
			model.addAttribute(PAGE, page);
		}
		model.addAttribute("mapps", mappSer.findByAskId(id));
		model.addAttribute(ENTITY, ask);
		return "mobile/askMatchProduct";
	}
	
	@RequestMapping(value="saveMatching",method=RequestMethod.POST)
	public String saveMatchProduct(@RequestParam("aid")String aid,
			@RequestParam("pid")String pid,AskBuyMappingProduct mapp,RedirectAttributes model){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.ASK_MATCH.value))
			return "mobile/noright";
		
		StringBuffer error = new StringBuffer();
		if(mapp.getBookNum()<1)
			error.append("预定数量小于1，");
		if(mapp.getPrice()<0 && mapp.getAmount()<0)
			error.append("单价、总价两者至少填其一。");
		if(StringUtils.isNotBlank(error)){
			model.addFlashAttribute("message", error);
			return REDIRECT+"/mobile/matchaskbuy/matching?id="+aid;
		}
		int count = mappSer.findByAskIdAndProductId(aid,pid);
		if(count<1){
			mapp.setAskbuy(new AskBuyInfo(aid));
			mapp.setProduct(new FuliaoProduct(pid));
			mapp.setCreateTime(new Date());
			if(mapp.getAmount()<=0)
				mapp.setAmount(mapp.getPrice()*mapp.getBookNum());
			mappSer.save(mapp);
		}else
			error.append("该产品已经加入");
		model.addFlashAttribute("message", error);
		return REDIRECT+"/mobile/matchaskbuy/matching?id="+aid;
	}
	
	@RequestMapping("deleteMatching")
	public String deleteMatching(@RequestParam("id")String id,
			@RequestParam("aid")String aid){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.ASK_MATCH.value))
			return "mobile/noright";
		
		mappSer.delete(id);
		return REDIRECT+"/mobile/matchaskbuy/matching?id="+aid;
	}
	
	@RequestMapping("finishMatching")
	public String finishMatching(@RequestParam("aid")String aid,
			@RequestParam(value="handsel",defaultValue="0")float handsel,
			@RequestParam(value="remark",defaultValue="")String remark){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.ASK_MATCH.value))
			return "mobile/noright";
		AskBuyInfo ask = ser.get(aid);
		if(ask!=null){
			int count = mappSer.findByAskIdForCount(aid);
			ask.setStatus(AskBuyInfo.status_finish);
			ask.setResult(count>0?AskBuyInfo.result_find:AskBuyInfo.result_notfind);
			ask.setHandsel(count>0?handsel:0);
			remark = StringUtils.defaultString(ask.getRemark())+"<br>"+
					shiroUser().name+":已处理本次求购。"+StringUtils.defaultString(remark);
			ask.setRemark(remark);
			ser.save(ask);
		}
		return REDIRECT+"/mobile/matchaskbuy/list";
	}
}
