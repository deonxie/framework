package personal.deon.framework.fuliao.web.mobile;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.FuliaoPermission;
import personal.deon.framework.fuliao.entity.FuliaoProduct;
import personal.deon.framework.fuliao.entity.ProductType;
import personal.deon.framework.fuliao.service.FuliaoProductService;
import personal.deon.framework.fuliao.service.FuliaoShopService;
import personal.deon.framework.fuliao.service.ProductTypeService;
import personal.deon.framework.fuliao.util.ImageUtil;
import personal.deon.framework.weixin.util.WeixinFileUtil;

@Controller
@RequestMapping("/mobile/product/option")
public class UpdateFuliaoProductController extends GenericController {
	Logger logger = LoggerFactory.getLogger(UpdateFuliaoProductController.class);
	@Autowired
	FuliaoProductService ser;
	@Autowired
	ProductTypeService typeSer;
	@Autowired
	FuliaoShopService shopSer;
	
	@RequestMapping("update")
	public String update(@ModelAttribute("initproduct")FuliaoProduct product,Model model){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.SHOP_ISSHOP.value))
			return "mobile/noright";
		
		model.addAttribute("types", typeSer.getAll());
		model.addAttribute(ENTITY, product);
		return "mobile/productForm";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("initproduct")FuliaoProduct product,
			@RequestParam("typeid")String typeid,HttpServletRequest request,
			RedirectAttributes model){
		if(shiroUser()==null)
			return REDIRECT+"/mobile/login";
		if(!hasPermission(FuliaoPermission.SHOP_ISSHOP.value))
			return "mobile/noright";
		if(StringUtils.isNotBlank(product.getName())){
			if(StringUtils.isBlank(product.getId())){
				product.setShop(shopSer.findUserShop(shiroUser().id));
				product.setCreateTime(new Date());
			}else
				product.setUpdateTime(new Date());
			product.setType(new ProductType(typeid));
			dealImgs(request,product);
			ser.save(product);
			model.addFlashAttribute("errorMsg","添加成功");
		}else
			model.addFlashAttribute("errorMsg","产品名字未填，不能保存产品");
		return REDIRECT+"/mobile/product/option/update";
	}
	
	@ModelAttribute("initproduct")
	public FuliaoProduct initDbProduct(@RequestParam(value="id",defaultValue="")String id){
		if(StringUtils.isBlank(id))
			return new FuliaoProduct();
		return ser.get(id);
	}
	/**
	 * 处理产品图片，
	 * 1若页面传回1表示图片沿用以前的图片，
	 * 2若为其他非空值表示使用微信新上传的图片（mediaid）
	 * 3若为空值表示删除当前图片
	 * @param request
	 * @param product
	 */
	private final void dealImgs(HttpServletRequest request,FuliaoProduct product){
		String imgs0 = request.getParameter("imgs0");
		String imgs1 = request.getParameter("imgs1");
		String imgs2 = request.getParameter("imgs2");
		String[] dbimgs = product.getImgs();
		
		String root = request.getServletContext().getRealPath("static");
		String saveDir = "/upload/product/";
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(imgs0)){
			if("1".equals(imgs0) && dbimgs.length>0){
				sb.append(dbimgs[0]).append(FuliaoProduct.split);
			}else{
				imgs0 = WeixinFileUtil.downloadFile(imgs0, root+saveDir);
				if(StringUtils.isNotBlank(imgs0))
					sb.append(saveDir).append(imgs0).append(FuliaoProduct.split);
			}
		}if(StringUtils.isNotBlank(imgs1)){
			if("1".equals(imgs1) && dbimgs.length>1){
				sb.append(dbimgs[1]).append(FuliaoProduct.split);
			}else{
				imgs1 = WeixinFileUtil.downloadFile(imgs1, root+saveDir);
				if(StringUtils.isNotBlank(imgs1))
					sb.append(saveDir).append(imgs1).append(FuliaoProduct.split);
			}
		}if(StringUtils.isNotBlank(imgs2)){
			if("1".equals(imgs2) && dbimgs.length>2){
				sb.append(dbimgs[2]).append(FuliaoProduct.split);
			}else{
				imgs2 = WeixinFileUtil.downloadFile(imgs2, root+saveDir);
				if(StringUtils.isNotBlank(imgs2))
					sb.append(saveDir).append(imgs2).append(FuliaoProduct.split);
			}
		}
		if(sb.length()>0){
			product.setImgNames(sb.substring(0,sb.lastIndexOf(FuliaoProduct.split)));
			product.setStatus(FuliaoProduct.status_enable);
			String cover = ImageUtil.scale(root+product.getImgs()[0], 200, 150, 'S', null, false);
			if(StringUtils.isNotBlank(cover))
				product.setCoverimg(saveDir+cover);
		}else{
			product.setImgNames(null);
			product.setStatus(FuliaoProduct.status_disable);
		}
	}
}
