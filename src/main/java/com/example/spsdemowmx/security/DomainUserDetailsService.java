package com.example.spsdemowmx.security;

import com.example.spsdemowmx.domian.User;
import com.example.spsdemowmx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author wumengxuan
 * @Date 2019/1/23 16:09
 */

@Slf4j
@Component("userDetailService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ( username == null || "".equals(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        User user = userRepository.findOneByUsername(username);
        if ( user == null ){
            throw new UsernameNotFoundException("该用户不存在！");
        }

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(),
                grantedAuthorities);
    }
}