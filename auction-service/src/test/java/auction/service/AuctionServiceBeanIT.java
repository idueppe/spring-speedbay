package auction.service;

import io.crowdcode.speedbay.auction.AuctionServiceApplication;
import io.crowdcode.speedbay.auction.service.AuctionCompositeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AuctionServiceApplication.class)
public class AuctionServiceBeanIT {

    @Autowired
    private AuctionCompositeService auctionService;

    @Test
    @Repeat(5)
    public void testPlaceAuction() throws Exception {
        Long auctionId = auctionService.placeAuction("title", "description", BigDecimal.ONE);
        auctionService.findRunningAuctions();
        auctionService.bidOnAuction(auctionId, BigDecimal.TEN);
    }


}