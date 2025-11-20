package com.TaDuy.microservices.history_analytics_service.Config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
@Configuration
@EnableJpaRepositories(
        basePackages = "com.TaDuy.microservices.history_analytics_service.Repository",
        entityManagerFactoryRef = "dwEntityManagerFactory",
        transactionManagerRef = "dwTransactionManager"
)
public class DWConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dw")
    public HikariDataSource dwDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dwDataSource())
                .packages("com.TaDuy.microservices.history_analytics_service.Entity")
                .persistenceUnit("dw")
                .build();
    }


    @Bean
    public PlatformTransactionManager dwTransactionManager(
            @Qualifier("dwEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}



