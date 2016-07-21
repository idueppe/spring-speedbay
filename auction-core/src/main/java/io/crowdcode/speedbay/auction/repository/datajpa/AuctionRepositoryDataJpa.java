package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Repository
@Profile({"datajpa"})
public interface AuctionRepositoryDataJpa extends JpaRepository<Auction, Long>, AuctionRepository, AuctionCustomRepository {

    Auction save(Auction auction);

    @Query("SELECT a FROM Auction a WHERE a.id = :auctionId")
    Optional<Auction> find(@Param("auctionId") Long auctionId);
}
