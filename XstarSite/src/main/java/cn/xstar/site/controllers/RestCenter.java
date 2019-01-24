package cn.xstar.site.controllers;

import cn.xstar.site.Const;
import cn.xstar.site.model.Article;
import cn.xstar.site.model.Data;
import cn.xstar.site.model.User;
import cn.xstar.site.services.AdminService;
import cn.xstar.site.util.CipherUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 接口中心
 */
@Controller
@RequestMapping("/api/v1/")
public class RestCenter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AdminService adminService;

    @PostMapping("/saveArticle")
    @ResponseBody
    public Data<JSONObject> saveArticle(Article article) {
        Data<JSONObject> data = new Data<>();
        article.setCreateTime(new Date());
        adminService.save(article);
        data.setCode(Const.SUCCESS_CODE);
        data.setMsg("文章保存成功！");
        return data;
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Data<JSONObject> register(User user) {
        logger.info(user.getUsername() + "/" + user.getPasswd());
        Data<JSONObject> result = new Data<>();
        if (StringUtils.isEmpty(user.getUsername())) {
            result.setCode(Const.ERROR_CODE);
            result.setMsg("用户名不可为空！");
            return result;
        }
        if (StringUtils.isEmpty(user.getPasswd())) {
            result.setCode(Const.ERROR_CODE);
            result.setMsg("密码不可为空！");
            return result;
        }
        User u = adminService.findUserByName(user.getUsername());
        if (u != null) {
            result.setCode(Const.USER_EXSIT_CODE);
            result.setMsg(Const.defaultMsg(Const.USER_EXSIT_CODE));
        } else {
            user.setPasswd(CipherUtil.MD5(user.getPasswd()));
            adminService.save(user);
            result.setCode(Const.SUCCESS_CODE);
            result.setMsg("账号注册成功！");
        }
        return result;
    }

}
