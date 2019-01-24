package cn.xstar.site.controllers;

import cn.xstar.site.Const;
import cn.xstar.site.model.Article;
import cn.xstar.site.model.User;
import cn.xstar.site.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            String footerText = "个人网站 Power By Springboot";
            vars.put("footerText", footerText);
            viewResolver.setStaticVariables(vars);
        }
    }


    @RequestMapping("/")
    public String index(Model model) {
        String title = "我的个人网站";
        model.addAttribute("title", title);
        model.addAttribute("nav", indexService.getIndexNavigator());
        model.addAttribute("blogs", indexService.getIndexBlogs());
        return "index";
    }

    @GetMapping(value = "/blog/{articleId}")
    public String blog(Model model, HttpSession session, @PathVariable("articleId") int articleId) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        Article blog = indexService.getBlogById(articleId);
        if (blog != null) {
            model.addAttribute("blog", blog);
            model.addAttribute("user", u);
            return "/blog";
        } else {
            return "404";
        }
    }
}
