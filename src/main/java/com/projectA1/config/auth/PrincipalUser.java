package com.projectA1.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projectA1.model.Owner;
import com.projectA1.model.User;

import lombok.Getter;

@Getter
public class PrincipalUser implements UserDetails {
	//범용 user
    private Object user;

    public PrincipalUser(Object user) {
        this.user = user;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof User) {
            User currentUser = (User) user;
            for (String userRole : currentUser.getRole()) {
                authorities.add(new SimpleGrantedAuthority(userRole));
            }
        } else if (user instanceof Owner) {
            Owner currentOwner = (Owner) user;
            for (String ownerRole : currentOwner.getRole()) {
                authorities.add(new SimpleGrantedAuthority(ownerRole));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if (user instanceof User) {
            return ((User) user).getPassword();
        } else if (user instanceof Owner) {
            return ((Owner) user).getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (user instanceof User) {
            return ((User) user).getName();
        } else if (user instanceof Owner) {
            return ((Owner) user).getName();
        }
        return null;
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
