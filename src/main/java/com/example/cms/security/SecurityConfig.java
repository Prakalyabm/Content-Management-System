package com.example.cms.security;

import java.beans.Customizer;

import org.springdoc.core.properties.SwaggerUiConfigProperties.Csrf;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
    @Bean
    
     AuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
	 provider.setPasswordEncoder(passwordEncoder());
	 provider.setUserDetailsService(customUserDetailService);
	 return provider;
}
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 return http.csrf(Csrf->Csrf.disable())
    			 .authorizeHttpRequests(auth->auth.requestMatchers("/users/register")
    			 .permitAll().anyRequest().authenticated())
    			 .formlogin(Customizer.withDefault))
                 .Build();

    }

}
