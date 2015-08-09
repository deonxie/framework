package personal.deon.framework.weixin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import personal.deon.framework.core.web.GenericController;
import personal.deon.framework.weixin.service.WeixinMaterialService;

@Controller
@RequestMapping("/weixin/material")
public class WeixinMaterialController extends GenericController{
	
	@Autowired
	WeixinMaterialService ser;
}
