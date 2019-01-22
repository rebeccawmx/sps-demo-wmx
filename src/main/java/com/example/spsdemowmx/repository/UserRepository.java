package com.example.spsdemowmx.repository;

import com.example.spsdemowmx.domian.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);

    /*
     * 添加分页
     * */
    @Query("select u from User u where 1=1 and (?1 is null or u.username like concat('%',?1,'%'))")
    Page<User> queryByKeyword(@Param("keyword") String Keyword, Pageable pageable);
}

