package com.example.spsdemowmx.repository;

import com.example.spsdemowmx.domian.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
    User findOneByUsernameAndPassword(String username , String password);

    /*
     * 添加分页
     * */
    @Query("select u from User u where 1=1 and (?1 is null or u.username like concat('%',?1,'%'))")
    Page<User> queryByKeyword(@Param("keyword") String Keyword, Pageable pageable);

    /**
     * 添加修改密码
     * 在@Query之前必须用@Modifying做修饰，通知SpringData这是一个修改的操作（不支持Insert）
     */
    @Modifying // 通知SpringData完成Update | Delete操作
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :userId")
    void updatePassword(@Param("userId") Long id, @Param("newPassword") String password);

}

