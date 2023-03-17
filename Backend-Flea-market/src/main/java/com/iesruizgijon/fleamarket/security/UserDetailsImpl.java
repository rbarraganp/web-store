package com.iesruizgijon.fleamarket.security;

import com.iesruizgijon.fleamarket.model.User;
import com.iesruizgijon.fleamarket.model.emun.RoleUser;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {


    @Serial
    private static final long serialVersionUID = -4833175351345627550L;
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPasswordUser();
    }

    @Override
    public String getUsername() {
        return user.getEmailUser();
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

    public String getName(){
        return user.getNickUser();
    }
    public Integer getIdUser(){ return user.getIdUser();}

    public RoleUser getRoleUser(){
        return user.getRoleUser();
    }

}
