package io.crowdcode.speedbay.auction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import(DataSourceConfiguration.class)
public class DatabasePopulateConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabasePopulateConfiguration.class);


    @Value("${initScript}")
    private String initScript;

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        log.info(green(" Populate Database with {} "), initScript);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(new ClassPathResource(initScript));
        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            log.error(red("Exception Populating Database"), e);
        }
        return populator;
    }

}
