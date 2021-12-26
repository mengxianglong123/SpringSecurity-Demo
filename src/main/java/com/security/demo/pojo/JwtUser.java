package com.security.demo.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class JwtUser implements UserDetails {   //实现UserDeails接口
    //用户名
    private String username;
    //密码
    private String password;
    // 权限（角色）列表
    Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String stuId, String password, List<GrantedAuthority> grantedAuthorities) {
        this.username = stuId;
        this.password = password;
        this.authorities = grantedAuthorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}