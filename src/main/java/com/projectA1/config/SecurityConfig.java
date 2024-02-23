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
	//사용자 : USER , 사업주 : OWNER
	public SecurityFilterChain fileChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request
                  .requestMatchers("/user/*").hasRole("USER")
                  .anyRequest().permitAll()
        )
        .authorizeHttpRequests(request -> request
                .requestMatchers("/owner/*").hasRole("OWNER")
                .anyRequest().permitAll()
        )
        .formLogin(login -> login
      		  .loginPage("/login")
      		  .loginProcessingUrl("/loginPro")
      		  .defaultSuccessUrl("/")    
        ); //"login"으로 페이지 이동 => loginPro로 처리 => 성공시 "/"로 이동
		return http.build();
	}

}
