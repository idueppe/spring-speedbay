package io.crowdcode.speedbay.auction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import({JpaPersistenceContextConfiguration.class})
@EnableJpaRepositories(value = "io.crowdcode.speedbay.auction.repository.datajpa")
public class DataJpaRepositoryConfiguration {

}
