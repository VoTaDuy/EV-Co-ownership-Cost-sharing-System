package com.example.EV.Co_ownership.Cost_sharing.system.config;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData",
        entityManagerFactoryRef = "dwEntityManagerFactory",
        transactionManagerRef = "dwTransactionManager"
)
public class DWConfig {

    @Bean(name = "dwDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dw")
    public DataSource dwDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dwEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dwEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dwDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse")
                .persistenceUnit("dw")
                .build();
    }

    @Bean(name = "dwTransactionManager")
    public PlatformTransactionManager dwTransactionManager(
            @Qualifier("dwEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
