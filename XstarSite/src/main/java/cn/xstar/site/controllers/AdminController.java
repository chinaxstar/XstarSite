package cn.xstar.site.controllers;

import cn.xstar.site.Const;
import cn.xstar.site.model.Article;
import cn.xstar.site.model.User;
import cn.xstar.site.repositorys.BlogRep;
import cn.xstar.site.services.AdminService;
import cn.xstar.site.services.IndexService;
import cn.xstar.site.util.CipherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AdminService adminService;
    @Autowired
    IndexService indexService;

    @GetMapping("/articleEditor")
    public String editorArticle(Model model, HttpSession session) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        Article article = (Article) session.getAttribute(Const.SESSION_ARTICLE);
        if (u == null) return "/admin/login";
        model.addAttribute("user", u);
        model.addAttribute("article", article);
        return "admin/articleEditor";
    }


    @RequestMapping(value = "/{userId}/blogs", method = RequestMethod.GET)
    public String blogs(Model model, @PathVariable("userId") int userId) {
        List<Article> blogs = adminService.getUserBlogs(userId);
        model.addAttribute("blogs", blogs);
        return "/admin/user_blogs";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registNew(Model model) {
        return "/admin/register";
    }


    @GetMapping()
    public String login(Model model, HttpSession session) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        List<Article> blogs = indexService.getIndexBlogs();
        if (u == null)
            return "/admin/login";
        else {
            model.addAttribute("user", u);
            model.addAttribute("blogs", blogs);
            model.addAttribute("title", "管理中心");
            return "/admin/manager";
        }

    }


    @PostMapping("/login")
    public String dologin(Model model, User user, HttpSession session) {
        User u = adminService.findUserByName(user.getUsername());
        String msg = null;
        if (u != null && u.getPasswd().equalsIgnoreCase(CipherUtil.MD5(user.getPasswd()))) {
            model.addAttribute("user", u);
            model.addAttribute("title", "编辑文章");
            List<Article> blogList = adminService.getUserBlogs(u.getId());
            model.addAttribute("blogList", blogList);
            session.setAttribute(Const.SESSION_USER, u);
            return "/admin/manager";
        } else if (u != null) {
            msg = "用户名不存在！";
        } else {
            msg = "密码错误！";
        }
        model.addAttribute("msg", msg);
        return "/admin/login";
    }

}
