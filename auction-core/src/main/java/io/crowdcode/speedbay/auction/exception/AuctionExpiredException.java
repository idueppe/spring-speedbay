package io.crowdcode.speedbay.auction.exception;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionExpiredException extends ApplicationException {

    public AuctionExpiredException(Long auctionId) {
        super("Auction" + auctionId + " already expired ");
    }

}
