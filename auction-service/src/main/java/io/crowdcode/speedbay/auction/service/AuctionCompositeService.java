package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.dto.AuctionInfoDto;
import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface AuctionCompositeService {

    Long placeAuction(String title, String description, BigDecimal minAmount);

    AuctionInfoDto findAuction(Long auctionId);

    List<AuctionInfoDto> findRunningAuctions();

    List<AuctionInfoDto> findExpiredAuctions();

    void bidOnAuction(final Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException;

}
