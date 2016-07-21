package io.crowdcode.speedbay.auction.repository.datajpa;


import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.config.DataJpaRepositoryConfiguration;
import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuctionRepositoryDataJpaSortingTest {

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
                new Sort.Order(Sort.Direction.ASC, "title"),
                new Sort.Order(Sort.Direction.DESC, "bids.amount"));

        List<Auction> auctions = repository.findAll(sort);
        auctions.forEach(System.out::println);
    }

    private void persistTestData() {
        repository.save(AuctionFixture.createTestAuction("A", 3));
        repository.save(AuctionFixture.createTestAuction("B", 2));
        repository.save(AuctionFixture.createTestAuction("C", 1));
    }


}