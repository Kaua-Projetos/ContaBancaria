//package com.example;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public <HttpSecurity> SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/usuario/**").permitAll() // Libera o acesso ao endpoint /usuario
//                        .anyRequest().authenticated() // Bloqueia qualquer outro endpoint
//                )
//                .httpBasic(httpBasic -> httpBasic.disable()); // Opcional: desativa autenticação HTTP básica
//        return http.build();
//    }
//}
