package com.test.neoris.service.impl;

import com.test.neoris.entity.Usuario;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserInfoDetails implements UserDetails {
    private String name; 
    private String password; 
    private List<GrantedAuthority> authorities; 
  
    public UserInfoDetails(Usuario user) { 
        name = user.getNombre(); 
        password = user.getPassword(); 
        authorities = Arrays.stream(user.getEmail().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    } 
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities; 
    } 
  
    @Override
    public String getPassword() { 
        return password; 
    } 
  
    @Override
    public String getUsername() { 
        return name; 
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
