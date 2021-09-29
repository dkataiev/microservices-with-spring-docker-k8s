package lab.dkataiev.ms.gateway.config;

import org.apache.http.Header;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/k8s/accounts/**")
                        .filters(f -> f.rewritePath("/k8s/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://accounts"))
                .route(p -> p.path("/k8s/loans/**")
                        .filters(f -> f.rewritePath("/k8s/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://loans"))
                .route(p -> p.path("/k8s/cards/**")
                        .filters(f -> f.rewritePath("/k8s/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://cards"))
                .build();
    }

}
