package com.example.spsdemowmx.service;


import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdate(User user) {
        log.debug("UserService.saveOrUpdate, user : {}", user);
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
}
