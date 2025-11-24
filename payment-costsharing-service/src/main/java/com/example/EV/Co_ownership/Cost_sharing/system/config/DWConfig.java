package com.example.EV.Co_ownership.Cost_sharing.system.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.EV.Co_ownership.payment-costsharing-service.system.RepositoryData",
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
                .packages("com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse")
                .persistenceUnit("dw")
                .build();
    }


    @Bean
    public PlatformTransactionManager dwTransactionManager(
            @Qualifier("dwEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}