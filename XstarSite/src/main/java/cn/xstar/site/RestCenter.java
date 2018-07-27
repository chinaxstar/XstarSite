package cn.xstar.site;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.xstar.site.model.NavigatorInfo;
import cn.xstar.site.model.Ueditor;
import cn.xstar.site.model.UeditorConfig;

@Controller
public class RestCenter {
	private final NavigatorInfo[] nav = { new NavigatorInfo("/", "网站首页"), new NavigatorInfo("/", "关于我"),
			new NavigatorInfo("/", "博客日志"), new NavigatorInfo("/", "分享"), new NavigatorInfo("/", "留言"), };

	@RequestMapping("/")
	public String index(Model model) {
		String title = "我的个人网站";
		String footerText = "个人网站 Power By Springboot";

		model.addAttribute("title", title);
		model.addAttribute("footerText", footerText);
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
		String config=UeditorConfig.getConfig();
		System.out.println(config);
		return config;
	}

	@RequestMapping("/imgUpload")
	@ResponseBody
	public Ueditor imgUpload(MultipartFile upfile) {
		Ueditor ueditor = new Ueditor();
		return ueditor;
	}

}
