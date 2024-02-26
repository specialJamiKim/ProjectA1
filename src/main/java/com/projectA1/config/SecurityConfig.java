package com.projectA1.config;

import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projectA1.model.User;

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
                		//페이지에 대한 권한 허용, 순서대로 적용되는 부분이라 위에가 우선순위 높음
                        .requestMatchers("/login/loginPage").permitAll() // 로그인 페이지만 허용
                        .requestMatchers("/login/indilogin", "/").permitAll() // 로그인 페이지만 허용
                        .requestMatchers("/join/*").permitAll() // 회원가입 페이지는 모든 사용자에게 허용
                        .requestMatchers("/user/join", "/owner/join").permitAll() // 회원가입 페이지는 모든 사용자에게 허용
                        .requestMatchers("/user/*").hasRole("USER") // 유저만 접근 가능
                        .requestMatchers("/owner/*").hasRole("OWNER") // 오너만 접근 가능
                        .anyRequest().authenticated() // 나머지 요청은 인증된 사용자만 접근 가능
                )
                
                .formLogin(login -> login
                        .loginPage("/login/loginPage") // 로그인 페이지 URL 설정
                        .loginProcessingUrl("/loginPro") // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트할 경로 설정
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // 로그아웃 후 리다이렉트할 경로 설정
                        .permitAll());
        return http.build();
    }

}

