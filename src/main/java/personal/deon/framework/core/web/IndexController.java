package personal.deon.framework.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆成功后，映射到的主页
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String list() {
        // 首页展示可以进行一些个性化的定制
        return "/index";
    }

    @RequestMapping("/menuList/{firstLevel}")
    public String menuList(@PathVariable("firstLevel") String firstLevel) {
        return "/menu/" + firstLevel;
    }
}
