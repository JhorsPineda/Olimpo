package com.senacsf.olimpo.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Configura el repositorio del token CSRF
//                )
//
//                .csrf().disable() // Desactiva CSRF
//
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/auth/login").permitAll() // Permite el acceso al endpoint de login sin autenticación
//                        .requestMatchers("/api/csrf-token").permitAll() // Permite el acceso al endpoint de csrf-token sin autenticación
//                        .requestMatchers("/api/persons/**").permitAll() // Permite el acceso al endpoint de Person all sin autenticación
//                        .anyRequest().authenticated() // Requiere autenticación para otras solicitudes
//                )
//                .cors(cors -> cors
//                        .configurationSource(corsConfigurationSource()) // Configura CORS
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Configura el encoder de contraseñas
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Configura orígenes permitidos
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Configura métodos permitidos
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Configura encabezados permitidos
//        configuration.setAllowCredentials(true); // Permite credenciales (cookies, cabeceras de autenticación)
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita CSRF para pruebas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/persons/**").permitAll() // Permite el acceso a todos los endpoints bajo /api/persons/
                        .anyRequest().authenticated() // Requiere autenticación para otras solicitudes
                )
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()) // Configura CORS
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configura el encoder de contraseñas
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Configura orígenes permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Configura métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Configura encabezados permitidos
        configuration.setAllowCredentials(true); // Permite credenciales (cookies, cabeceras de autenticación)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
