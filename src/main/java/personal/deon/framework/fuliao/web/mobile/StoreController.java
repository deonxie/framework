package personal.deon.framework.fuliao.web.mobile;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.entity.FuliaoUser;
import personal.deon.framework.fuliao.entity.StoreProduct;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.StoreProductService;

@Controller
@RequestMapping("/mobile/store")
public class StoreController extends GenericController {
	@Autowired
	FuliaoProductService ser;
	@Autowired
	FuliaoUserService userSer;
	@Autowired
	StoreProductService storeProductSer;
	
	@RequestMapping("product")
	@ResponseBody
	public String[] storeProduct(@RequestParam("pid")String id){
		ShiroUser user = shiroUser();
		if(user ==null)
			return new String[]{"false","请先登录平台"};
		int count = ser.findIsSelfProduct(id,user.id);
		if(count>0)
			return new String[]{"false","不能收藏自己的产品"};
		count = storeProductSer.findIsStoreed(user.id, id);
		if(count >0)
			return new String[]{"false","已收藏过，不能重复收藏"};
		StoreProduct sp = new StoreProduct();
		sp.setCreateTime(new Date());
		sp.setOwnUser(new FuliaoUser(user.id));
		sp.setProduct(new FuliaoProduct(id));
		storeProductSer.save(sp);
		return new String[]{"true","收藏成功"};
	}
    
    @RequestMapping(value ="list/product")
    public String listProduct(PageRequest pageRequest, Model model,ServletRequest request) {
    	ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		pageRequest.setOrderBy("createTime");
		pageRequest.setOrderDir("desc");
		Map<String, Object> param = Servlets.getParametersStartingWith(request, SEARCH_PERFIX);
		param.put("EQ_ownUser.id", user.id);
        Page<StoreProduct> products = storeProductSer.search(param, pageRequest);
        model.addAttribute(PAGE, products);
        model.addAttribute(PAGE_REQUEST, pageRequest);
        return "mobile/storeProductList";
    }
    
    @RequestMapping("remove/product")
    public String removeStoreProduct(@RequestParam("id")String id){
    	ShiroUser user = shiroUser();
		if(user ==null)
			return "mobile/login";
		storeProductSer.removeStoreProduct(id);
		return REDIRECT+"/mobile/store/list/product";
    }
  
}
