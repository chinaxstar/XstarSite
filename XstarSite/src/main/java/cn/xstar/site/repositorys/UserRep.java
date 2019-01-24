package cn.xstar.site.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.xstar.site.model.User;

import java.util.List;

@Repository
public interface UserRep extends JpaRepository<User, Integer> {

    List<User> findUsersByUsernameLikeOrNickNameLike(String userName, String nickName);

    User findUserByUsername(String name);
}
