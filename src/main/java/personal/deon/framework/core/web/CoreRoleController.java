package personal.deon.framework.core.web;


import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;
import personal.deon.framework.core.entity.CoreRole;
import personal.deon.framework.core.entity.Permission;
import personal.deon.framework.core.service.CoreRoleServcie;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/core/role")
public class CoreRoleController extends GenericController {
    @Autowired
    private CoreRoleServcie roleServcie;

    @RequiresPermissions("role:view")
    @RequestMapping(value = "")
    public String list(PageRequest pageRequest, Model model,
                       ServletRequest request) {

        if (StringUtils.isBlank(pageRequest.getOrderBy())) {
            pageRequest.setOrderBy("id");
            pageRequest.setOrderDir("desc");
        }

        Page<CoreRole> roles = roleServcie.search(
                Servlets.getParametersStartingWith(request, SEARCH_PERFIX),pageRequest);
        pageRequest.setPrePage(roles.hasPreviousPage());
        pageRequest.setNextPage(roles.hasNextPage());

        model.addAttribute(PAGE, roles);
        model.addAttribute(PAGE_REQUEST, pageRequest);
        return "core/role/roleList";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateForm(@RequestParam(value ="id", defaultValue ="")String id, Model model) {
        CoreRole role = roleServcie.get(id);
        model.addAttribute("role", role);
        model.addAttribute("permissionListByType", Permission.getListMap());
        return "core/role/roleForm";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@Valid @ModelAttribute("role") CoreRole role,
                         RedirectAttributes redirectAttributes) {
        String message = roleServcie.saveRole(role);
        addMessage(redirectAttributes, message);
        return REDIRECT+"/core/role";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@RequestParam(value ="id")String id,
                         RedirectAttributes redirectAttributes) {
        roleServcie.delete(id);
        addMessage(redirectAttributes, "删除角色成功");
        return REDIRECT+"/core/role";
    }

    @ModelAttribute
    public void getRole(@RequestParam(value ="id",defaultValue ="")String id, Model model) {
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("role", roleServcie.get(id));
        }
    }

    @ResponseBody
    @RequiresPermissions("role:edit")
    @RequestMapping(value = "validate")
    public String checkLoginName(String name) {
        if (StringUtils.isNotBlank(name)) {
            CoreRole role = roleServcie.findRoleByRoleName(name);
            return role == null ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
        }
        return Boolean.FALSE.toString();
    }
}
