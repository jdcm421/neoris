package com.test.neoris.security;

import com.test.neoris.security.config.AuthFilter;
import com.test.neoris.service.impl.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private AuthFilter authFilter; 
  
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoService();
    }
    
    private static final String[] AUTHORIZADO = {
        // -- Swagger UI v2
        "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
        "/configuration/security", "/swagger-ui.html", "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**", "/swagger-ui/**","/api/user/registro"
    };
    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http.cors().and().csrf().disable() 
                .authorizeHttpRequests() 
                .requestMatchers(AUTHORIZADO).permitAll() 
                .and() 
                .authorizeHttpRequests().anyRequest().authenticated() 
                .and() 
                .sessionManagement() 
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
                .and() 
                .authenticationProvider(authenticationProvider()) 
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) 
                .build(); 
    } 
  
    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
  
    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
}
