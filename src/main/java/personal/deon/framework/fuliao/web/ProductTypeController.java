package personal.deon.framework.fuliao.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.entity.ProductType;
import personal.deon.framework.fuliao.service.ProductTypeService;

@Controller
@RequestMapping("/fuliao/producttype")
public class ProductTypeController extends GenericController {
	@Autowired
	ProductTypeService ser;

	@RequestMapping("")
	public String list(PageRequest pageRequest,HttpServletRequest request,Model model){
		Page<ProductType> page = ser.search(
				Servlets.getParametersStartingWith(request, SEARCH_PERFIX), pageRequest);
		model.addAttribute(PAGE, page);
		model.addAttribute(PAGE_REQUEST, pageRequest);
		return "fuliao/productTypeList";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(ProductType type){
		type.setUpdateTime(new Date());
		ser.save(type);
		return REDIRECT+"/fuliao/producttype";
	}
}
