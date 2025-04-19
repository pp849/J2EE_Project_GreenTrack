package com.luv2code.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        // REST API route protections
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")

                        // Admin routes (Thymeleaf web pages)
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Allow all other requests
                        .anyRequest().permitAll()
        );

        // Use form-based login (custom login page)
        http.formLogin(form -> form
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
        );

        // Logout support
        http.logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
        );

        // Disable CSRF for APIs or testing (optional)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
