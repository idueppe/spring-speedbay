package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BusinessLogicConfiguration {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private InMemoryStore<Auction> inMemoryStore;

    @Bean(name = {"auctionService", "auctionServiceBean"})
    public AuctionService auctionServiceBean(@Qualifier("auctionRepository") AuctionRepository auctionRepositoryXyz) {
        AuctionRepositoryInMemoryBean bean = context.getBean(AuctionRepositoryInMemoryBean.class);
        System.out.println(bean);

        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionRepositoryXyz);
        return auctionService;
    }

    @Bean
//    public AuctionRepository auctionRepository(InMemoryStore<Auction> inMemoryStore) {
    public AuctionRepository auctionRepository() {
        AuctionRepositoryInMemoryBean repository = new AuctionRepositoryInMemoryBean();
        repository.setStore(inMemoryStore);
        return repository;
    }

    @Bean()
    public InMemoryStore<Auction> inMemoryStore() {

        return new InMemoryStore<>();
    }

}
