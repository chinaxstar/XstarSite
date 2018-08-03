package cn.xstar.site;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import cn.xstar.site.model.NavigatorInfo;
import cn.xstar.site.model.Ueditor;
import cn.xstar.site.model.UeditorConfig;
import cn.xstar.site.model.User;
import cn.xstar.site.repositorys.UserRep;

@Controller
public class RestCenter {
	private final NavigatorInfo[] nav = { new NavigatorInfo("/", "网站首页"), new NavigatorInfo("/", "关于我"),
			new NavigatorInfo("/", "博客日志"), new NavigatorInfo("/", "分享"), new NavigatorInfo("/", "留言"), };

	@Autowired
	private UserRep userRep;
	
	@Resource
	private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
		System.out.println("configureThymeleafStaticVars 配置thymeleaf静态变量"+viewResolver);
	    if(viewResolver != null) {
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

	@RequestMapping("/editor")
	public String editorArticle(Model model) {
		return "articleEditor";
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

	@RequestMapping("/admin")
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

	@PostMapping("/login")
	public String dologin(Model model, String username, String passwd, HttpSession session) {
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);
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
}
