package cn.xstar.site.controllers;

import cn.xstar.site.model.Ueditor;
import cn.xstar.site.model.UeditorConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 百度ueditor富媒体编辑器 配置及上传设置
 */
@Controller
public class UeditorController {

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

}
