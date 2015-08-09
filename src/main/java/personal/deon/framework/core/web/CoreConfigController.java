package personal.deon.framework.core.web;


import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import personal.deon.framework.core.entity.CoreConfig;
import personal.deon.framework.core.service.CoreConfigService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping(value = "/core/config")
public class CoreConfigController extends GenericController {
    @Autowired
    CoreConfigService conSer;

    @RequiresPermissions("config:view")
    @RequestMapping(value = "")
    public String list(PageRequest pageRequest, Model model,
                       ServletRequest request) {
        Page<CoreConfig> configs = conSer.search(
                Servlets.getParametersStartingWith(request, SEARCH_PERFIX),pageRequest);

        model.addAttribute(PAGE, configs);
        model.addAttribute(PAGE_REQUEST, pageRequest);
        model.addAttribute("constKeyNum", CoreConfigService.getConstKey().size());
        return "core/coreConfigList";
    }

    @RequiresPermissions("config:view")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateForm(@RequestParam(value="id",defaultValue ="")String id, Model model) {
    	CoreConfig conf = conSer.get(id);
        model.addAttribute(ENTITY, conf);
        model.addAttribute("constKey", CoreConfigService.getConstKey());
        return "core/coreConfigForm";
    }

    @RequiresPermissions("config:edit")
    @RequestMapping(value="save",method = RequestMethod.POST)
    public String update(CoreConfig config,Model model) {
        conSer.save(config);
        return REDIRECT+"/core/config";
    }

    @ModelAttribute
    public CoreConfig getRole(@RequestParam(value="id", defaultValue = "") String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return new CoreConfig();
        }
        return conSer.get(id);
    }
}
