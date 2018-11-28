package cn.xstar.site.repositorys;

import cn.xstar.site.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRep extends JpaRepository<Article, Integer> {
}
