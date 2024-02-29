package com.projectA1.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
                .requestMatchers("/", "/main", "/join/**", "/login/**", "/centerManage/**", "/diary/**",
                      "/login/loginPage", "/user/join", "/owner/join", "/fragments/*")
                .permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/img/**").permitAll().requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/owner/**").hasRole("OWNER")
	            .anyRequest().authenticated())
	        .formLogin(login -> login
	            .loginPage("/login/loginPage")
	            .loginProcessingUrl("/loginPro")
	            .defaultSuccessUrl("/", true)
	            .permitAll())
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll());

	    return http.build();
	}


	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
