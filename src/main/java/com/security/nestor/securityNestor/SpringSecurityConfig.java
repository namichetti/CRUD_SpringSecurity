package com.security.nestor.securityNestor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.nestor.securityNestor.auth.handler.LoginSuccesHandler;
import com.security.nestor.securityNestor.service.JpaUserDetailService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JpaUserDetailService userDetailsService;
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/","/css/**","/js/**").permitAll().anyRequest().authenticated()
		.and().formLogin().successHandler(successHandler).loginPage("/login").permitAll()
		.and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/error_403");
	
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	
}
