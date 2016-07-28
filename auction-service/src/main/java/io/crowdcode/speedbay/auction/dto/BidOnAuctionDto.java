package io.crowdcode.speedbay.auction.dto;

import java.math.BigDecimal;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class BidOnAuctionDto {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BidOnAuctionDto withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

}



