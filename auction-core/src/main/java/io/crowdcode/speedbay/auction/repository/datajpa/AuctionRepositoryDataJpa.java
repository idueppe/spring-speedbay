package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */


@Repository
@Profile({"datajpa"})
public interface AuctionRepositoryDataJpa extends AuctionRepositoryDefaultDataJpa, AuctionRepository {

    @Query("SELECT a FROM Auction a WHERE a.id = :id")
    Optional<Auction> find(@Param("id") Long auctionId);

    List<Auction> findAll();

    Auction save(Auction auction);

    List<Auction> allAuctionWithABidFrom(String email);
}
