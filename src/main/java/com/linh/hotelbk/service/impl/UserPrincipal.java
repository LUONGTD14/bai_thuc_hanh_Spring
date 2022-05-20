package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private UserEntity user;

    public UserPrincipal(UserEntity user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        this.user.getRoles().forEach(
                r -> {
                    String role = r.getRoleName();
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);
                    roles.add(grantedAuthority);
                }
        );
        return roles;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getFullName();
    }

    public String getEmail(){
        return this.user.getEmail();
    }

    public UserEntity getUser(){
        return this.user;
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
        return this.user.getIsActive();
    }
}
