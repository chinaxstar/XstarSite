package cn.xstar.site.repositorys;

import cn.xstar.site.model.Article;
import cn.xstar.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRep extends JpaRepository<Article, Integer> {

    List<Article> findArticlesByAuthor(User user);
}

