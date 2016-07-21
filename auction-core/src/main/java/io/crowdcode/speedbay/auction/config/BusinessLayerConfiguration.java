package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BusinessLayerConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public AuctionRepository auctionInMemoryRepository() {
        return new AuctionRepositoryInMemoryBean();
    }

    @Bean
    public AuctionService auctionServiceBean() {
        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionInMemoryRepository());
        return auctionService;
    }


    @Bean
    public AuctionService auctionServiceBean2() {
        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionInMemoryRepository());
        return auctionService;
    }

}
