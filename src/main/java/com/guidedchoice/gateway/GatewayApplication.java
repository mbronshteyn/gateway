package com.guidedchoice.gateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class GatewayApplication {

    @Bean
<<<<<<< Updated upstream
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) throws Exception {

    String httpUri = uriConfiguration.getHttpbin();
=======
    public RouteLocator myRoutes( RouteLocatorBuilder builder ) throws Exception {
>>>>>>> Stashed changes

		return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri(httpUri))
				.route(p -> p
<<<<<<< Updated upstream
						.path("/sentence-client/**")
            .filters( f -> f.rewritePath(
                        		"/sentence-client/(?<segment>.*)", "/$\\{segment}")
                             .retry( 4 )
						)
						.uri( "lb://SENTENCE" ))
            .build();
=======
						.path("/sentence-service/**")
                        .filters( f-> f
								.rewritePath(
                        		"/sentence-service/(?<segment>.*)", "/$\\{segment}")
                                .retry( 3 )
						)
						.uri( "lb://SENTENCE:8082" )
				)
//                .route(p -> p
//                        .host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config
//                                        .setName("mycmd")
//                                        .setFallbackUri("forward:/fallback")))
//                        .uri(httpUri))
                .build();
>>>>>>> Stashed changes
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

