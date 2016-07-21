package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Repository
public interface AuctionRepositoryDefaultDataJpa extends JpaRepository<Auction, Long> {
}
