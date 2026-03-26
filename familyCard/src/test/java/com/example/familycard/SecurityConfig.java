package com.example.familycard;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



// @Configuration: tells Spring to use this class to configure Spring and Spring Boot itself.
// Any Beans specified in this class will now be available to Spring's Auto Configuration engine.
@Configuration
public class SecurityConfig {

    // avec Bean, on utilise le SecurityFilterChain de spring security
    // sans Bean, c'est un object normal (object ordinaire)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/cashcards/**")
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .build();

    }

    // avec Bean, on utilise le PasswordEncoder de spring security
    // sans Bean, c'est un object normal (object ordinaire)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails sarah = users
                .username("sarah1")
                .password(passwordEncoder.encode("abc123"))
                .roles() // No roles for now
                .build();
        return new InMemoryUserDetailsManager(sarah);
    }
}