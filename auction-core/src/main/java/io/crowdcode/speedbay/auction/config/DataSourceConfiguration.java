package io.crowdcode.speedbay.auction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@PropertySource(name = "databaseProperties", value = {"classpath:${stage:local}-database.properties"})
public class DataSourceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${jdbcDriverClassName}")
    private String jdbcDriverClassName;
    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbcUsername}")
    private String jdbcUsername;
    @Value("${jdbcPassword}")
    private String jdbcPassword;

    @Bean
    // Attention: it must be static to be initialized very early in the process
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        log.info("Connecting to {}", jdbcUrl);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

}
