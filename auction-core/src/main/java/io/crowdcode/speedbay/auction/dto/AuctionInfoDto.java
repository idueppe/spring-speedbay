package io.crowdcode.speedbay.auction.dto;

import java.math.BigDecimal;
import java.net.URI;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionInfoDto {

    private Long id;
    private String title;
    private URI thumbnail;
    private BigDecimal highestBidAmount;
    private String highestBidder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URI getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(URI thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BigDecimal getHighestBidAmount() {
        return highestBidAmount;
    }

    public void setHighestBidAmount(BigDecimal highestBidAmount) {
        this.highestBidAmount = highestBidAmount;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
    }

    public AuctionInfoDto withId(final Long id) {
        this.id = id;
        return this;
    }

    public AuctionInfoDto withTitle(final String title) {
        this.title = title;
        return this;
    }

    public AuctionInfoDto withThumbnail(final URI thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }


    public AuctionInfoDto withHighestBidAmount(final BigDecimal highestBidAmount) {
        this.highestBidAmount = highestBidAmount;
        return this;
    }

    public AuctionInfoDto withHighestBidder(final String highestBidder) {
        this.highestBidder = highestBidder;
        return this;
    }

    @Override
    public String toString() {
        return "AuctionInfoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail=" + thumbnail +
                ", highestBidAmount=" + highestBidAmount +
                ", highestBidder='" + highestBidder + '\'' +
                '}';
    }
}
