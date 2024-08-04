package ru.gb.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.gb.demo.model.Roles;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(configure->configure
                        .requestMatchers("/issue/**").hasAuthority(Roles.ADMIN.toString())
                        .requestMatchers("/book/books").permitAll()
                        .requestMatchers("reader/readers").authenticated()
                        .anyRequest().hasAuthority(Roles.ADMIN.toString())
                ).formLogin(Customizer.withDefaults())
                .build();
    }
}
