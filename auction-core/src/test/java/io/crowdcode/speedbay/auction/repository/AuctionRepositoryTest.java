package io.crowdcode.speedbay.auction.repository;

import io.crowdcode.speedbay.auction.dto.AuctionInfoDto;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionRepositoryTest {

    private AbstractApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("exercise-6.xml");
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    public void testApplicationContextWithIntegration() throws Exception {
        AuctionRepository repository = applicationContext.getBean("auctionRepository", AuctionRepository.class);
        assertNotNull(repository);

        AuctionService service = applicationContext.getBean("auctionService", AuctionService.class);

        assertNotNull(service);

        Auction auction = AuctionFixture.buildAuction();

        Long auctionId = service.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());

        assertThat(auctionId, is(notNullValue()));

        service.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        AuctionInfoDto auctionDto = service.findAuction(auctionId);
        assertThat(auctionDto.getHighestBidAmount().doubleValue(), is(11.0));
    }

}