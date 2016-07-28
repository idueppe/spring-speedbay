package io.crowdcode.speedbay.auction;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.DataJpaRepositoryConfiguration;
import io.crowdcode.speedbay.auction.config.RestClientConfiguration;
import io.crowdcode.speedbay.auction.config.RestTemplateConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@SpringBootApplication
@ComponentScan(
        basePackages = {"io.crowdcode.speedbay.auction"},
        excludeFilters =
                {
                        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "io\\.crowdcode\\.speedbay\\.auction\\.config.*")
                }
)
@Import({
        DataJpaRepositoryConfiguration.class,
        BusinessLogicAnnotationConfiguration.class,
        RestClientConfiguration.class,
        RestTemplateConfiguration.class
})
public class AuctionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }
}
