package io.crowdcode.speedbay.auction.repository.datajpa;

import io.crowdcode.speedbay.auction.model.Auction;

import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface AuctionCustomRepository {

    List<Auction> allAuctionWithABidFrom(String email);

}
