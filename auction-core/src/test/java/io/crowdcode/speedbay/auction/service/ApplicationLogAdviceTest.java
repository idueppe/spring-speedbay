package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.ApplicationLogAdviceConfiguration;
import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.JdbcTransactionConfiguration;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ApplicationLogAdviceConfiguration.class,
        JdbcTransactionConfiguration.class,
        BusinessLogicAnnotationConfiguration.class
})
public class ApplicationLogAdviceTest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ApplicationLogService appLogService;

    @Test
    @Sql(statements = "DELETE FROM application_log")
    public void testAppLogConfiguration() throws Exception {
        Auction auction = new Auction()
                .withTitle("MacBook Pro")
                .withDescription("MacBook Pro 15\" Retina (Late 2012)")
                .withMinAmount(BigDecimal.TEN);
        auctionService.placeAuction(auction.getTitle(), auction.getDescription(), auction.getMinAmount());

        List<Message> messages = appLogService.lastLogs(Duration.of(10, ChronoUnit.SECONDS));
        messages.forEach(System.out::println);

        assertThat(messages.isEmpty(), is(false));
    }
}