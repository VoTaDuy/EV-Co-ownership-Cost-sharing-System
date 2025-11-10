package com.example.demo.Routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {

    @Value("${history.service.url}")
    private String historyServiceUrl; // ví dụ http://localhost:8083

    @Value("${admin.service.url}")
    private String adminServiceUrl;

    @Value("${booking.service.url}")
    private String bookingServiceUrl;

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
    public RouterFunction<ServerResponse> historyService() {
        RouterFunction<ServerResponse> getRoute = route(RequestPredicates.GET("/api/reports/**"), request -> {
            String pathAfter = request.path().replaceFirst("/api/reports/", "");
            String url = historyServiceUrl + "/api/reports/" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });

        RouterFunction<ServerResponse> postRoute = route(RequestPredicates.POST("/api/reports/**"), request -> {
            String pathAfter = request.path().replaceFirst("/api/reports/", "");
            String url = historyServiceUrl + "/api/reports/" + pathAfter;
            return HandlerFunctions.http(url).handle(request);
        });

        return getRoute.and(postRoute); // ghép nhiều route
    }




    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.ok()
                        .body("Fallback response")); // fallback đơn giản
    }
}
