package com.example.demo.Routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {

    @Value("${history.service.url}")
    private String historyServiceUrl;

    @Value("${admin.service.url}")
    private String adminServiceUrl;

    @Value("${booking.service.url}")
    private String bookingServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> adminContractsTemplate() {
        return RouterFunctions.route(RequestPredicates.path("/admin/**"), request -> {
            String pathAfter = request.path().replaceFirst("/admin", "");
            String url = adminServiceUrl + "/admin" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });
    }

    @Bean
    public RouterFunction<ServerResponse> bookingService(){
        return RouterFunctions.route(RequestPredicates.path("/booking/**"),request -> {
            String pathAfter = request.path().replaceFirst("/booking", "");
            String url = bookingServiceUrl + "/booking" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });
    }



    @Bean
    public RouterFunction<ServerResponse> historyService(){
        return RouterFunctions.route(RequestPredicates.path("/past/**"),request -> {
            String pathAfter = request.path().replaceFirst("/past", "");
            String url = historyServiceUrl + "/past" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });
    }
    @Bean
    public RouterFunction<ServerResponse> userService(){
        return RouterFunctions.route(RequestPredicates.path("/user/**"),request -> {
            String pathAfter = request.path().replaceFirst("/user", "");
            String url = userServiceUrl + "/user" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });
    }



    @Bean
    public RouterFunction<ServerResponse> paymentService(){
        return RouterFunctions.route(RequestPredicates.path("/payment/**"),request -> {
            String pathAfter = request.path().replaceFirst("/payment", "");
            String url = paymentServiceUrl + "/payment" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.ok()
                        .body("Fallback response")); // fallback đơn giản
    }
}
