package personal.deon.framework.core.web;

import cn.gd.thinkjoy.modules.web.Servlets;
import cn.gd.thinkjoy.modules.web.pojo.PageRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import personal.deon.framework.core.entity.CoreUser;
import personal.deon.framework.core.service.CoreRoleServcie;
import personal.deon.framework.core.service.CoreUserService;

import javax.servlet.ServletRequest;

import java.util.List;

/**
 * 用户注册的Controller.
 *
 */
@Controller
@RequestMapping(value = "/core/user")
public class CoreUserController extends GenericController {

    @Autowired
    private CoreUserService userService;
    @Autowired
    private CoreRoleServcie roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(value = "")
    public String list(PageRequest pageRequest, Model model,
                       ServletRequest request) {
        Page<CoreUser> users = userService.search(Servlets.getParametersStartingWith(request, SEARCH_PERFIX), pageRequest);
        model.addAttribute(PAGE, users);
        model.addAttribute(PAGE_REQUEST, pageRequest);
        return "core/user/userList";
    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateForm(@RequestParam(value ="id", defaultValue ="")String id, Model model) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("allRoles", roleService.getAll());
        return "core/user/userForm";
    }

    @RequiresPermissions("user:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") CoreUser user,
    		@RequestParam(value = "roleIds") List<String> checkedRoleList,
                         RedirectAttributes redirectAttributes) {
        String message = userService.registerUser(user, checkedRoleList);
        addMessage(redirectAttributes, message);
        return "redirect:/core/user";
    }

    @RequiresPermissions("user:edit")
    @RequestMapping(value = "delete")
    public String delete(@RequestParam("id")String id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        addMessage(redirectAttributes, "删除用户成功");
        return "redirect:/core/user";
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute("user")
    public CoreUser getUser(@RequestParam(value ="id", defaultValue ="")String id, Model model) {
        if (StringUtils.isNotBlank(id)) {
           return userService.get(id);
        }
        return new CoreUser();
    }

    @ResponseBody
    @RequiresPermissions("user:edit")
    @RequestMapping(value = "validate")
    public String checkLoginName(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            CoreUser user = userService.findUserByAccount(loginName);
            return user == null ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
        }
        return Boolean.FALSE.toString();
    }
}
