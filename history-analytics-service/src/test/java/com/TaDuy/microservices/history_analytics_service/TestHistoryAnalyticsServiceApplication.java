package com.TaDuy.microservices.history_analytics_service;

import org.springframework.boot.SpringApplication;

public class TestHistoryAnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(HistoryAnalyticsServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
