package org.backend.config;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) -> ReactiveSecurityContextHolder.getContext()
                        .map(SecurityContext::getAuthentication)
                        .cast(JwtAuthenticationToken.class)
                        .map(jwt -> {
                            System.out.println("✅ Usando WebClient con filtro JWT: " + jwt.getToken().getTokenValue());

                            return ClientRequest.from(request)
                                    .headers(headers -> headers.setBearerAuth(jwt.getToken().getTokenValue()))
                                    .build();
                        })
                        .flatMap(next::exchange))
                .build();
    }


}

 */

