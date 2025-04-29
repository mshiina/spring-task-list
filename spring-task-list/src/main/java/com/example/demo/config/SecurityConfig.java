package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserRepository userRepository;

	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/users/new", "/users/add", "/login", "/register", "/css/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/tasks", true)
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout")
						.permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
		return email -> {
			var user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));

			return org.springframework.security.core.userdetails.User
					.withUsername(user.getEmail())
					.password(user.getPassword())
					.roles("USER")// ユーザーには「USER」ロールを付与
					.build();
		};
	}
}
