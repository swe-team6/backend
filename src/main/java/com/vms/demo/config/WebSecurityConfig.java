package com.vms.demo.config;

import com.vms.demo.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().anonymous());
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		return http.build();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(
//						requests ->
//								requests
//										.requestMatchers(HttpMethod.GET, "/users")
//										.hasRole("USER")
//										.requestMatchers(HttpMethod.GET, "/admins")
//										.hasRole("ADMIN")
//										.anyRequest()
//										.authenticated())
//				.formLogin(Customizer.withDefaults());
//		return http.build();
//	}
	// @Bean
	// public UserDetailsService userDetailsService() {
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("password")
	// .roles("USER")
	// .build();

	// return new InMemoryUserDetailsManager(user);
	// }
}