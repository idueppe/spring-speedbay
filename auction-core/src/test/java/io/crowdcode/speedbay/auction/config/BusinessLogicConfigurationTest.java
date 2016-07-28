package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.yellow;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class BusinessLogicConfigurationTest {

    public static final Logger log = LoggerFactory.getLogger(BusinessLogicConfiguration.class);
    private AnnotationConfigApplicationContext context;


    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void testModelConfiguration() throws Exception {
        logBeanNames(context);
    }

    @Test
    public void testApplicationContextWithIntegration() throws Exception {
        AuctionService service = context.getBean("auctionService", AuctionService.class);

        Auction auction = AuctionFixture.buildDefaultAuction();

        Long auctionId = service.placeAuction(auction.getTitle(), auction.getDescription(), auction.getMinAmount());
        assertThat(auctionId, is(notNullValue()));

        service.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        Auction found = service.findAuction(auctionId);
        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));
    }

    private void logBeanNames(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            log.info(green("> " + name));
            log.info(yellow(Arrays.toString(context.getAliases(name))));
        }
    }
}