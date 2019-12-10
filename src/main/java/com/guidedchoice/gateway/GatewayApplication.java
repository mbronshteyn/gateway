package com.guidedchoice.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class GatewayApplication {

    @Bean
    public RouteLocator myRoutes( RouteLocatorBuilder builder ) throws Exception {

		return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri(httpUri))
				.route(p -> p
						.path("/sentence-service/**")
                        .filters( f-> f
								.rewritePath(
                        		"/sentence-service/(?<segment>.*)", "/$\\{segment}")
                                .retry( 3 )
						)
						.uri( "lb://SENTENCE:8082" )
				)
                .build();
    }

//
//    @Autowired
//    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator;

//	@RequestMapping("/fallback")
//	public Mono<String> fallback() {
//		return Mono.just("fallback\n");
//	}


	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


}




@Configuration
@EnableDiscoveryClient
class GatewayDiscoveryConfiguration {

	@Autowired
	DiscoveryLocatorProperties discoveryLocatorProperties;


//	@Bean
//	public DiscoveryClientRouteDefinitionLocator
//	discoveryClientRouteLocator(DiscoveryClient discoveryClient) {
//		return new DiscoveryClientRouteDefinitionLocator(discoveryClient, discoveryLocatorProperties );
//	}
}

