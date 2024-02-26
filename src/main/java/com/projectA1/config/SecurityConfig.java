package com.projectA1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain fileChain(HttpSecurity http) throws Exception {  	
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> request
            .requestMatchers("/user/*").hasRole("USER") //유저만 접근가능
            .requestMatchers("/owner/*").hasRole("OWNER") // 오너만 접근가능
            .requestMatchers("/","/loginPage", "/loginPro").permitAll()
            .anyRequest().authenticated()  // anyRequest()를 마지막에 배치
            )
            
            .formLogin(login -> login
                .loginPage("/loginPage")
                .loginProcessingUrl("/loginPro")
                .defaultSuccessUrl("/", true)
                .permitAll()
            );
        
        return http.build();
    }
    
}

