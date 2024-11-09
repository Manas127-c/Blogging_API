package com.blogging.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogging.spring.security.CustomUserDetailsService;
import com.blogging.spring.security.JwtAuthenticationEntryPoint;
import com.blogging.spring.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	/*
	 steps:-
	 1.first create a SecurityConfig class
	 2.In this class create SecurityFilterChain @bean object 
	 3.And in bean declaration method pass HttpsSecurity object as a parameter
	 4.Then access all the request authentication using HttpSecurity object in this bean method using HttpSecurity object parameter
	 5.Then after create a CustomUserDetailService class and implements UserDetailService
	 6.And then @override this interface's method loadUserByUserName(pass username as parameter)
	 7.IN this override method get User data by username using UserRepository interface features.
	 8.And return that user values.
	 9.Create another PasswordEncoder bean in SecurityConfig for BCrypt the password
	 10.Then create DaoAuthenticationProvider(DAP) Bean in this bean create a DAP object and 
	  	set userdetails into .setUserDetailService() and set password using passwordencoder Bean method into 
	  	.setPasswordEncoder() And then return the object;
	 
	  */ 
	
	private static final String[] URL= {"/api/v1/auth/**","/api/v1/auth/register","/v3/api-docs/**","/v2/api-docs/**",
			"/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**"};

	@Autowired
	private CustomUserDetailsService cService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(customizer->customizer.disable());
//		http.authorizeHttpRequests(request->request.anyRequest().authenticated());
////		http.formLogin(Customizer.withDefaults());
//		http.httpBasic(Customizer.withDefaults());
//		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(URL).permitAll()
			.requestMatchers("/api/v1/auth/register").permitAll()
			.requestMatchers(HttpMethod.GET).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(this.jEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}

	// Password Encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Custom User details config
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.cService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
