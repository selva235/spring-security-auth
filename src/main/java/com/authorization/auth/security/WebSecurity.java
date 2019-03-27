package com.authorization.auth.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private AuthenticationEntryPoint unauthorizedEntryPoint;
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationEntryPoint unauthorizedEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	 http
         .csrf().disable() 
         .exceptionHandling()
         .authenticationEntryPoint(unauthorizedEntryPoint)
         .and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
             .authorizeRequests()
                 .antMatchers(HttpMethod.POST, "/saveUser").permitAll() 
                 .antMatchers(HttpMethod.GET, "/Userlist").authenticated()
         .and()
		       .addFilter(new JWTAuthenticationFilter(authenticationManager()))
		       .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    	
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}