package com.projectA1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projectA1.model.User;

import lombok.Getter;
@Getter
public class PrincipalUser implements UserDetails {
    private User user;

    public PrincipalUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String userRole : user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(userRole));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        // 사용자의 암호를 반환합니다.
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // 사용자의 이메일을 반환합니다.
        return user.getEmail();
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
