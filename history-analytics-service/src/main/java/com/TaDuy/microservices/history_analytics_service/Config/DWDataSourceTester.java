package com.TaDuy.microservices.history_analytics_service.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DWDataSourceTester {
    @Autowired
    @Qualifier("dwDataSource")
    private DataSource dwDataSource;

    @PostConstruct
    public void test() throws SQLException {
        System.out.println("DW connection = " + dwDataSource.getConnection());
    }
}