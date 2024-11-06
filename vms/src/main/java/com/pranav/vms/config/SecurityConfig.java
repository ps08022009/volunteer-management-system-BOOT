package com.pranav.vms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()  
                .anyRequest().authenticated()  
            .and()
            .formLogin()
                .loginPage("/login") 
                .permitAll()  
                .defaultSuccessUrl("/home", true)  
            .and()
            .logout()
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/login?logout")  
                .permitAll();  

        return http.build();
    }
}
