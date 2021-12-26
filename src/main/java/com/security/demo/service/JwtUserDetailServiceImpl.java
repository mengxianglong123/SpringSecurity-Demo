package com.security.demo.service;

import com.security.demo.pojo.JwtUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //暂时写成固定的
        if(!s.equals("admin")) return null;//用户不是admin，报错
        System.out.println("查询"+s);
        return new JwtUser("admin","$2a$10$WtN/BQbwY8dI0me.JsLxP.yyGePyTMg3bi3GZeRogowB4ZuoL1zrK", AuthorityUtils.commaSeparatedStringToAuthorityList("user,ROLE_admin"));
    }
}
