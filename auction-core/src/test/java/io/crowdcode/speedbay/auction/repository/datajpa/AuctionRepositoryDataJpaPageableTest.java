package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.DataJpaRepositoryConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicAnnotationConfiguration.class, DataJpaRepositoryConfiguration.class})
@Transactional
@ActiveProfiles("datajpa")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionRepositoryDataJpaPageableTest {

    private static final Logger logger = LoggerFactory
            .getLogger(AuctionRepositoryDataJpaTest.class);

    @Autowired
    private AuctionRepositoryDataJpa repository;

    @Test
    public void testFindAllSorting() throws Exception {
        persistTestData();

        Sort sort = new Sort(Sort.Direction.DESC, "title");

        List<Auction> auctions = repository.findAll(sort);
        auctions.forEach(System.out::println);
    }

    @Test
    public void testFindAllMultipleSorting() throws Exception {
        persistTestData();

        Sort sort = new Sort(
                new Sort.Order(Sort.Direction.DESC, "minAmount"),
                new Sort.Order(Sort.Direction.ASC, "title"));

        PageRequest pageRequest = new PageRequest(0, 10, sort);


        Page<Auction> page = repository.findAll(pageRequest);

        int pageCount = 1;
        while (page.hasNext()) {
            printPage(page, pageCount++);
            page = repository.findAll(page.nextPageable());
        }
        printPage(page, pageCount++);
    }

    private void printPage(Page<Auction> page, int pageCount) {
        System.out.printf("----- PAGE %d -----\n", pageCount);
        page.forEach(a -> System.out.printf("Title: %s, Bid: %f\n", a
                .getTitle(), a.getHighestBid().getAmount().floatValue()));
    }

    private void persistTestData() {
        for (int i = 1; i <= 100; i++) {
            repository.save(AuctionFixture.createTestAuction("A" + i, i));
        }
    }

}