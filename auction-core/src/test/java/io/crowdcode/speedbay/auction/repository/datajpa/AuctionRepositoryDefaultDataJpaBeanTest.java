package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.config.DataJpaRepositoryConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataJpaRepositoryConfiguration.class)
@Transactional

public class AuctionRepositoryDefaultDataJpaBeanTest {

    @Autowired
    private AuctionRepositoryDefaultDataJpa auctionRepository;

    @Test
    public void testCreateAAuction() throws Exception {
        Auction auction = auctionRepository.save(AuctionFixture.buildDefaultAuction());
        assertNotNull(auction.getId());
        assertNotNull(auction.getTitle());
        assertNotNull(auction.getDescription());
    }
}