package io.crowdcode.speedbay.auction.dto;

import java.math.BigDecimal;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionCreateDto {

    private String title;
    private String description;
    private BigDecimal minAmount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public AuctionCreateDto withTitle(final String title) {
        this.title = title;
        return this;
    }

    public AuctionCreateDto withDescription(final String description) {
        this.description = description;
        return this;
    }

    public AuctionCreateDto withMinAmount(final BigDecimal minAmount) {
        this.minAmount = minAmount;
        return this;
    }


}
