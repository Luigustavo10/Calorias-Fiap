package br.com.fiap.calorias.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Spring Security Configuration
@EnableWebSecurity // Enable Spring Security
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken; // Token verification filter
    @Bean // Bean to configure authentication manager
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Use stateless session management
                .authorizeHttpRequests(authorize -> authorize// Authorize HTTP requests
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Allow POST requests to /auth/login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Allow POST requests to /auth/register
                        .requestMatchers(HttpMethod.POST, "/alimentos") // Allow POST requests to /alimentos
                        .hasRole("ADMIN")// Require ADMIN role
                        .requestMatchers(HttpMethod.DELETE, "/api/alimentos/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/alimentos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/alimentos").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() // Require authentication for any other request
                )
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration // Authentication configuration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
