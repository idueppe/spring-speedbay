package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.repository.ApplicationLogRepository;
import io.crowdcode.speedbay.auction.repository.jdbc.ApplicationLogRepositoryBean;
import io.crowdcode.speedbay.auction.service.ApplicationLogAdvice;
import io.crowdcode.speedbay.auction.service.ApplicationLogServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
@Import({DatabasePopulateConfiguration.class})
@EnableAspectJAutoProxy
public class ApplicationLogAdviceConfiguration {

    @Bean
    public ApplicationLogAdvice appLogAdvice() {
        return new ApplicationLogAdvice();
    }

    @Bean
    public ApplicationLogRepository appLogRepositoryBean() {
        return new ApplicationLogRepositoryBean();
    }

    @Bean
    public ApplicationLogServiceBean appLogServiceBean() {
        return new ApplicationLogServiceBean();
    }

}
