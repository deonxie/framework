package personal.deon.framework.fuliao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.fuliao.service.FuliaoProductService;

@Controller
@RequestMapping("/fuliao/product")
public class FuliaoProductController extends GenericController {
	@Autowired
	FuliaoProductService ser;
}
