package cn.xstar.site;

import cn.xstar.site.model.*;
import cn.xstar.site.repositorys.BlogRep;
import cn.xstar.site.repositorys.UserRep;
import cn.xstar.site.util.CipherUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class RestCenter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final NavigatorInfo[] nav = {
            new NavigatorInfo("/", "网站首页")
            , new NavigatorInfo("/", "关于我")
            , new NavigatorInfo("/blogs", "博客日志")
            , new NavigatorInfo("/", "分享")
            , new NavigatorInfo("/", "留言"),};

    @Autowired
    private UserRep userRep;
    @Autowired
    private BlogRep blogRep;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        System.out.println("configureThymeleafStaticVars 配置thymeleaf静态变量" + viewResolver);
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
        model.addAttribute("nav", nav);
        return "index";
    }

    @GetMapping("/editor")
    public String editorArticle(Model model, HttpSession session) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        if (u == null) return "/admin/login";
        model.addAttribute("user", u);
        return "/admin/articleEditor";
    }

    @RequestMapping("/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        String config = UeditorConfig.getConfig();
        return config;
    }

    @RequestMapping("/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        return ueditor;
    }

    @GetMapping("/admin")
    public String login(Model model, HttpSession session) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        if (u == null)
            return "/admin/login";
        else {
            model.addAttribute("user", u);
            model.addAttribute("title", "管理中心");
            return "/admin/manager";
        }

    }


    @GetMapping(value = "/blogs")
    public String blogs(Model model, HttpSession session) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        if (u == null) {
            u = new User();
            u.setId(1);
        }
        Article article = new Article();
        article.setAuthor(u);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnoreNullValues();
        List<Article> blogs = blogRep.findAll(Example.of(article, matcher));
        model.addAttribute("blogs", blogs);
        return "/admin/user_blogs";
    }


    @GetMapping(value = "/blog/{articleId}")
    public String blog(Model model, HttpSession session, @PathVariable("articleId") int articleId) {
        User u = (User) session.getAttribute(Const.SESSION_USER);
        Optional<Article> op = blogRep.findById(articleId);
        if (op.isPresent()) {
            Article blog = op.get();
            model.addAttribute("blog", blog);
            model.addAttribute("user", u);
            return "/admin/blog";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/register")
    public String registNew(Model model) {
        return "/admin/register";
    }


    @PostMapping("/login")
    public String dologin(Model model, String username, String passwd, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPasswd(CipherUtil.MD5(passwd));
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnoreNullValues();
        Example<User> s = Example.of(user, matcher);
        Optional<User> op = userRep.findOne(s);
        if (op.isPresent()) {
            User u = op.get();
            model.addAttribute("user", u);
            model.addAttribute("title", "编辑文章");
            session.setAttribute(Const.SESSION_USER, u);
            return "/admin/manager";
        } else {
            String msg = "用户名或密码错误！";
            model.addAttribute("msg", msg);
            return "/admin/login";
        }
    }

    /**
     * 检测用户名是否重复
     *
     * @param username
     * @return
     */
    @PostMapping("/checkRegister")
    public Data<JSONObject> register(String username) {
        Data<JSONObject> result = new Data<>();
        if (StringUtils.isEmpty(username)) {
            result.setCode(Const.ERROR_CODE);
            result.setMsg("用户名不可为空！");
            return result;
        }
        User user = new User();
        user.setUsername(username);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnoreNullValues();
        List<User> users = userRep.findAll(Example.of(user, matcher));
        if (users.size() > 0) {
            result.setCode(Const.USER_EXSIT_CODE);
            result.setMsg(Const.defaultMsg(Const.USER_EXSIT_CODE));
        } else {
            result.setCode(Const.SUCCESS_CODE);
            result.setMsg("用户名可用！");
        }
        return result;
    }

    @PostMapping("/register")
    @ResponseBody
    public Data<JSONObject> register(String username, String passwd) {
        Data<JSONObject> result = new Data<>();
        if (StringUtils.isEmpty(username)) {
            result.setCode(Const.ERROR_CODE);
            result.setMsg("用户名不可为空！");
            return result;
        }
        if (StringUtils.isEmpty(passwd)) {
            result.setCode(Const.ERROR_CODE);
            result.setMsg("密码不可为空！");
            return result;
        }
        User user = new User();
        user.setUsername(username);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnoreNullValues();
        List<User> users = userRep.findAll(Example.of(user, matcher));
        if (users.size() > 0) {
            result.setCode(Const.USER_EXSIT_CODE);
            result.setMsg(Const.defaultMsg(Const.USER_EXSIT_CODE));
        } else {
            user.setPasswd(CipherUtil.MD5(passwd));
            userRep.save(user);
            result.setCode(Const.SUCCESS_CODE);
            result.setMsg("账号注册成功！");
        }
        return result;
    }

    @PostMapping("/saveArticle")
    @ResponseBody
    public Data<JSONObject> saveArticle(Article article) {
        Data<JSONObject> data = new Data<>();
        article.setLastModifyTime(System.currentTimeMillis());
        article.setLastModifyAuthor(article.getAuthor().getId());
        blogRep.saveAndFlush(article);//获取自增？
        data.setCode(Const.SUCCESS_CODE);
        data.setMsg("文章保存成功！");
        return data;
    }

}
