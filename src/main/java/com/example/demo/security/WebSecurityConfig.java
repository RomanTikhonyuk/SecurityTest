package com.example.demo.security;

import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").hasAnyAuthority("USER","ADMIN")
//                        .requestMatchers("/new").hasAnyAuthority("ADMIN", "USER")
//                        .requestMatchers("/edit/**").hasAnyAuthority("ADMIN", "USER")
//                        .requestMatchers("/delete/**", "/admin/users").hasAuthority("ADMIN")
//                        .requestMatchers("/welcome/**","/registration").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login.permitAll())
//                .logout(logout -> logout.permitAll())
//                .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
//        ;


        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(eh -> eh.accessDeniedPage("/403"));
        return http.build();
    }
}