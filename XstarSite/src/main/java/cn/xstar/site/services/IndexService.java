package cn.xstar.site.services;

import cn.xstar.site.model.Article;
import cn.xstar.site.model.NavigatorInfo;
import cn.xstar.site.model.SysSetting;
import cn.xstar.site.model.User;
import cn.xstar.site.repositorys.BlogRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    @Autowired
    private BlogRep blogRep;

    @Autowired
    SysSetting setting;


    /**
     * 主页博客列表
     *
     * @return
     */
    public List<Article> getIndexBlogs() {
        return getUserBlogs(setting.adminUser);
    }

    public List<Article> getUserBlogs(int id) {
        User user = new User();
        user.setId(id);
        return blogRep.findArticlesByAuthor(user);
    }

    public Article getBlogById(int blog) {
        return blogRep.getOne(blog);
    }

    public NavigatorInfo[] getIndexNavigator() {
        final NavigatorInfo[] nav = {
                new NavigatorInfo("/", "网站首页")
                , new NavigatorInfo("/", "关于我")
                , new NavigatorInfo("/", "分享")
                , new NavigatorInfo("/", "留言"),};
        return nav;
    }

}
