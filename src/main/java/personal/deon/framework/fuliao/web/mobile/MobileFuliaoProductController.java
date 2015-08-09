package personal.deon.framework.fuliao.web.mobile;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.dto.PageUtilDto;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.service.FuliaoUserService;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.FuliaoShopService;
import personal.deon.framework.fuliao.service.ProductTypeService;

@Controller
@RequestMapping("/mobile/product")
public class MobileFuliaoProductController extends GenericController {
	@Autowired
	FuliaoProductService ser;
	@Autowired
	ProductTypeService typeSer;
	@Autowired
	FuliaoUserService userSer;
	@Autowired
	FuliaoShopService shopSer;
	
	
	@RequestMapping("")
	public String list(PageUtilDto page,HttpServletRequest request,Model model){
		page.parseRequest(request);
		page.setContent(ser.fuliaoproductIndexPage(page));
		model.addAttribute(PAGE, page);
		model.addAttribute("types", typeSer.getAll());
		return "mobile/fuliaoproductPage";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam("id")String id,HttpServletRequest request,Model model){
		FuliaoProduct product = ser.get(id);
		model.addAttribute(ENTITY, product);
		model.addAttribute("electProduct", ser.findLikeTarget(product));
		return "mobile/productDetail";
	}
	
	@RequestMapping("mylist")
	public String productList(PageUtilDto page,HttpServletRequest request,Model model){
		if(shiroUser()==null){
			return REDIRECT+"/mobile/login";
		}
		page.parseRequest(request);
		page.setContent(ser.selfProductPage(page,shiroUser().id));
		model.addAttribute(PAGE, page);
		model.addAttribute("types", typeSer.getAll());
		return "mobile/productList";
	}
	
	@RequestMapping("updatesutats")
	public String updatesutats(@RequestParam("id")String id,@RequestParam("status")int status,
			RedirectAttributes model){
		if(shiroUser()==null){
			return REDIRECT+"/mobile/login";
		}
		FuliaoProduct product =ser.get(id);
		if(product!=null && StringUtils.isNotBlank(product.getCoverimg())){
			if(status == FuliaoProduct.status_disable)
				product.setStatus(FuliaoProduct.status_disable);
			else
				product.setStatus(FuliaoProduct.status_enable);
			ser.save(product);
		}else
			model.addFlashAttribute("error", "产品没有上传图片不能发布");
		return REDIRECT+"/mobile/product/mylist";
	}
}
