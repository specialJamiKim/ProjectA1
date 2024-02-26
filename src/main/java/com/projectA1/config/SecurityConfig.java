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
            .requestMatchers("/","loginPage","login").permitAll() // 내부 요청 허용
           // .requestMatchers("/user/*").permitAll() // 모든 사용자가 접근할 수 있도록 설정
           
            .anyRequest().authenticated()  // anyRequest()를 마지막에 배치
            )
            
            .formLogin(login -> login
               // .loginPage("/loginPage")
                .loginProcessingUrl("/loginPro")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/loginPage")
                    .permitAll()
            );
        return http.build();
    }
    
}

