package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.time.TimeMachine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class Auction extends AbstractEntity<Auction, Long> {

    private String owner;

    private LocalDateTime beginDate;

    private LocalDateTime expireDate;

    private BigDecimal minAmount;

    private String title;

    private String description;

    private List<Bid> bids = new ArrayList<>();

    public Bid getHighestBid() {
        return bids
                .stream()
                .max((b1, b2) -> b1.getAmount().compareTo(b2.getAmount()))
                .orElse(new Bid().withAmount(BigDecimal.ZERO).withEmail("-"));
    }


    public boolean isExpired() {
        return expireDate.isBefore(TimeMachine.now());
    }

    public boolean isRunning() {
        return (beginDate.isBefore(TimeMachine.now())
                || beginDate.isEqual(TimeMachine.now()))
                && expireDate.isAfter(TimeMachine.now());
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

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

    public Auction withOwner(final String owner) {
        this.owner = owner;
        return this;
    }

    public Auction withBeginDate(final LocalDateTime beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public Auction withExpireDate(final LocalDateTime expireDate) {
        this.expireDate = expireDate;
        return this;
    }


    public Auction withBids(final List<Bid> bids) {
        this.bids = bids;
        return this;
    }

    public Auction withMinAmount(final BigDecimal minAmount) {
        this.minAmount = minAmount;
        return this;
    }

    public Auction withTitle(final String title) {
        this.title = title;
        return this;
    }

    public Auction withDescription(final String description) {
        this.description = description;
        return this;
    }


}
