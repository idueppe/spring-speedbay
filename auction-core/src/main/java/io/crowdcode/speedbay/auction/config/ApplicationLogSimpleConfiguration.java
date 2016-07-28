package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.repository.ApplicationLogRepository;
import io.crowdcode.speedbay.auction.repository.jdbc.ApplicationLogRepositoryBean;
import io.crowdcode.speedbay.auction.service.ApplicationLogService;
import io.crowdcode.speedbay.auction.service.ApplicationLogServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import({BusinessLogicAnnotationConfiguration.class, DatabasePopulateConfiguration.class})
public class ApplicationLogSimpleConfiguration {

    @Bean
    public ApplicationLogRepository applicationLogRepository() {
        return new ApplicationLogRepositoryBean();
    }

    @Bean
    public ApplicationLogService applicationLogService() {
        return new ApplicationLogServiceBean();
    }
}
