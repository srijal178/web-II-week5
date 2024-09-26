package com._th.database.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection for simplicity (not recommended for production)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/signup", "/login", "/css/**", "/js/**", "/images/**").permitAll()  // Allow access to signup, login, and static resources
                .requestMatchers("/hotel/**", "/user/**").hasRole("ADMIN")  // Restrict admin pages to ADMIN role
                .requestMatchers("/rating/**").hasRole("USER")  // Restrict rating pages to USER role
                .anyRequest().authenticated()  // All other requests require authentication
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")  // Custom login page
                .defaultSuccessUrl("/home", true)  // After successful login, redirect to home
                .failureUrl("/login?error=true")  // On failure, redirect to login page with error
                .permitAll()  // Allow access to login for everyone
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // Define custom logout URL
                .logoutSuccessUrl("/login?logout=true")  // On logout success, redirect to login page
                .permitAll()  // Allow logout for everyone
            )
            .build();
    }
}
