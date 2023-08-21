package com.kolaykafe.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * TODO: BU ŞEKİLDE YEDİ GİBİ İNCELEMEYE DEVAM !!!
     *       ADMIN BACKEND İÇİN DE YAPILACAK
     * */

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange((exchanges) ->
                        exchanges
                                .pathMatchers("/api/command/menu**","/api/command/order**")
                                    .hasRole("ADMIN")
                                .pathMatchers("/api/query/menu**",
                                        "/api/query/order**",
                                        "/api/query/user**",
                                        "/api/command/user**")
                                    .permitAll()
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
