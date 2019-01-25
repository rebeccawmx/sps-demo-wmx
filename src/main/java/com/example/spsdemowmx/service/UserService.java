package com.example.spsdemowmx.service;


import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService {


    // Spring seuriy用户密码加密
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 创建用户或更新用户
    public User saveOrUpdate(User user) {
        log.debug("UserService.saveOrUpdate, user : {}", user);
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.info("UserService.findAll");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findOne(Long id) {
        log.info("UserService.findOne, id : {}", id);
        return userRepository.findById(id);

    }

    public void delete(Long id) {
        log.debug("UserService.delete, id : {}", id);
        userRepository.deleteById(id);
    }

    /**
    * 添加分页
    */
    public Page<User> queryUsers(String keyword, Pageable pageable) {
        System.out.println("UserService.queryUsers");
        return userRepository.queryByKeyword(keyword, pageable);
    }

    /**
     * 查找用户名密码
     */
    public  User loginUsers(String username , String password){
        return userRepository.findOneByUsernameAndPassword(username,password);
    }
}
