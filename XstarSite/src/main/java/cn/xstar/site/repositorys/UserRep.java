package cn.xstar.site.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.xstar.site.model.User;

@Repository
public interface UserRep extends JpaRepository<User, Integer>{

}
