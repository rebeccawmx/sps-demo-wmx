package com.example.spsdemowmx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * userService → securityConfiguration → userDetailService → userService
 * 为避免出现依赖循环，把 PasswordEncoder 单独定义
 * @Description TODO
 * @Author wumengxuan
 * @Date 2019/1/24 15:50
 */

@Component
public class DefaultPasswordEncoder {
    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
