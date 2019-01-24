package com.example.spsdemowmx.config;

import com.example.spsdemowmx.security.DomainUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author wumengxuan
 * @Date 2019/1/23 11:05
 */

@Component
@EnableWebSecurity
// 开启权限设置
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()                        // 认证所有的URL，以下按照顺序进行匹配
                    .antMatchers("/v2/api-docs", "/configuration/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
                    .antMatchers("/assets/**").permitAll()
                    .antMatchers("/login", "/logout").permitAll()
                    .antMatchers("/actuator", "/actuator/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/activate").permitAll()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/account/reset-password/init").permitAll()
                    .antMatchers("/api/account/reset-password/finish").permitAll()
                     //.antMatchers("/admin/**").hasRole("ADMIN")
                     //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .antMatchers("/api/**").authenticated()
                    .anyRequest().authenticated()           // 确保任何请求都要求用户进行身份验证
                    .and()                                  // 继续配置
                .formLogin()                                // 默认的基于表单form的验证
                     //.loginPage("/login")                         // 指定登录界面
                    .permitAll()                            // 允许所有用户访问登陆界面
                    .and()
                .logout().permitAll()                       // 允许用户登出
                    .and()
                .headers().frameOptions().disable() // allow iFrame for H2 console
                    .and()
                .httpBasic() // 允许用户使用HTTP基本身份进行验证
                    .and()
                .csrf().disable(); // disable CSRF, TODO: enable
    }

    //注入 userDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
