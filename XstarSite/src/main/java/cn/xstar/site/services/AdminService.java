package cn.xstar.site.services;

import cn.xstar.site.model.Article;
import cn.xstar.site.model.User;
import cn.xstar.site.repositorys.BlogRep;
import cn.xstar.site.repositorys.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 管理员服务
 */
@Service
public class AdminService {

    @Autowired
    private BlogRep blogRep;
    @Autowired
    private UserRep userRep;


    /**
     * 获取用户博客
     *
     * @return
     */
    public List<Article> getUserBlogs(int userId) {
        User user = new User();
        user.setId(userId);
        return blogRep.findArticlesByAuthor(user);
    }


    public Article save(Article article) {
        article.setLastModifyTime(new Date());
        article.setLastModifyAuthor(article.getAuthor());
        return blogRep.saveAndFlush(article);//获取自增
    }

    public User save(User user) {
        return userRep.saveAndFlush(user);
    }


    public User getUser(int userId) {
        Optional<User> user = userRep.findById(userId);
        return user.orElse(null);
    }

    public List<User> findUsersLikeName(String name) {
        String like = "%" + name + "%";
        return userRep.findUsersByUsernameLikeOrNickNameLike(like, like);
    }

    public User findUserByName(String name) {
        return userRep.findUserByUsername(name);
    }

}
