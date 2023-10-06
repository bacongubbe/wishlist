package dev.bacongubbe.wishlistapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler handler;

    @Autowired
    public SecurityConfig(OAuth2LoginSuccessHandler handler){
        this.handler = handler;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/").permitAll();
                auth.requestMatchers("/api/**").authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
            .oauth2Login(oauth -> oauth.successHandler(handler))
            .build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatchers(matchers -> matchers
                .requestMatchers(
                    antMatcher("/swagger-ui.html"),
                    antMatcher("/swagger-ui/**"),
                    antMatcher("/v3/api-docs/**"),
                    antMatcher("/proxy/**")
                )
            )
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .build();
    }

}
