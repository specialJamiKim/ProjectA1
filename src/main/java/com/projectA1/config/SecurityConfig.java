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
    
//	@Bean
//	public SecurityFilterChain fileChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(request -> request.requestMatchers("/user/*").hasRole("USER") // 유저만 접근 가능
//						.requestMatchers("/owner/*").hasRole("OWNER") // 오너만 접근 가능
//						.requestMatchers("/", "/loginPage", "/loginPro").permitAll() // 다른 경로에 대한 모든 사용자 접근 허용
//						.anyRequest().authenticated() // 나머지 요청은 인증된 사용자만 접근 가능
//				)
//
//				.formLogin(login -> login.loginPage("/user/loginPage") // 사용자 로그인 페이지 경로 설정
//						.loginProcessingUrl("/user/indilogin") // 사용자 로그인 처리 URL 설정
//						.defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트할 경로 설정
//						.permitAll())
//
//				.formLogin(login -> login.loginPage("/owner/loginPage") // 소유자 로그인 페이지 경로 설정
//						.loginProcessingUrl("/owner/ownerlogin") // 소유자 로그인 처리 URL 설정
//						.defaultSuccessUrl("/", true) // 로그인 성공 후 리다이렉트할 경로 설정
//						.permitAll())
//				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/loginPage").permitAll());
//		return http.build();
//	}

    @Bean
    public SecurityFilterChain fileChain(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.authorizeRequests().anyRequest().permitAll();
      return http.build();
    }
    
}

