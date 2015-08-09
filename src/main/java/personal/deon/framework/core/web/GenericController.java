package personal.deon.framework.core.web;

import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;

/**
 * 通用控制器层主要用来简化一些日常开发
 * Created by chenling on 14-7-30.
 */
public class GenericController {
	public final static String SEARCH_PERFIX = "search_";
	public final static String PAGE = "page";
	public final static String PAGE_REQUEST = "pageRequest";
	public final static String ENTITY = "entity";
	public final static String REDIRECT = "redirect:";
	
	
//	public final static String PARAMS_FIELD = "params_value";
//	public final static String ERROR_Msg = "errorMsg";
//	public final static String ORDERBY_PERFIX = "orderby_";
    /**
     * 获取每个Controller类注解中的Mapper地址
     * 页面通过${symbol_dollar}{baseMapper}引用相关地址
     *
     * @return
     */
    @ModelAttribute("baseMapper")
    public String generateMapper() {
        String baseMapper = null;
        RequestMapping annotations = getClass().getAnnotation(RequestMapping.class);
        if (null != annotations) {
            String[] values = annotations.value();
            if (null != values && values.length > 0) {
                baseMapper = values[0];
            }
        }
        return baseMapper;
    }

    /**
     * 添加Model消息
     * @param messages 消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     * @param messages 消息
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }
    
    /**获取登录用户*/
    public ShiroUser shiroUser(){
    	Object obj = SecurityUtils.getSubject().getPrincipal();
    	if(obj == null)
    		return null;
    	return (ShiroUser)obj;
    }
    
    public boolean hasPermission(String permission){
    	return SecurityUtils.getSubject().isPermitted(permission);
    }
}
