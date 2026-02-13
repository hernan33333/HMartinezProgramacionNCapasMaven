/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alien 7
 */
@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource(){
        
        DriverManagerDataSource dataSoure = new DriverManagerDataSource();
        
        dataSoure.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        dataSoure.setUsername("HMartinezProgramacionNCapas");
        dataSoure.setPassword("password1");
        
        return dataSoure;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
    
        return new JdbcTemplate(dataSource);
    
    }
    
}
