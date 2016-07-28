package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.dto.AuctionInfoDto;
import io.crowdcode.speedbay.auction.dto.ProductDto;
import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.remote.ImageRestClient;
import io.crowdcode.speedbay.auction.remote.ProductRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static io.crowdcode.speedbay.common.AnsiColor.yellow;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Service
public class AuctionCompositeServiceBean implements AuctionCompositeService {

    private final static Logger log = LoggerFactory.getLogger(AuctionServiceBean.class);

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ProductRestClient productService;

    @Autowired
    private ImageRestClient imageService;

    @Override
    public Long placeAuction(String title, String description, BigDecimal minAmount) {
        String productUuid = productService.createProduct(title, description);
        return auctionService.placeAuction(title, description, minAmount, productUuid);
    }

    @Override
    public AuctionInfoDto findAuction(Long auctionId) {
        return toAuctionDto(auctionService.findAuction(auctionId));
    }

    @Override
    public List<AuctionInfoDto> findRunningAuctions() {
        return auctionService
                .findRunningAuctions()
                .parallelStream()
                .map(this::toAuctionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuctionInfoDto> findExpiredAuctions() {
        return auctionService
                .findExpiredAuctions()
                .parallelStream()
                .map(this::toAuctionDto)
                .collect(Collectors.toList());
    }

    @Override
    public void bidOnAuction(Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {
        auctionService.bidOnAuction(auctionId, amount);
    }

    private AuctionInfoDto toAuctionDto(Auction auction) {
        URI thumbnail = imageService.findThumbnail(auction.getProductUuid());
        ProductDto product = productService.findProduct(auction.getProductUuid());
        return convertToAuctionInfoDto(auction, thumbnail, product);
    }

    private AuctionInfoDto convertToAuctionInfoDto(Auction auction, URI thumbnail, ProductDto product) {
        AuctionInfoDto dto = new AuctionInfoDto()
                .withId(auction.getId())
                .withThumbnail(thumbnail)
                .withTitle(product.getTitle())
                .withHighestBidder(auction.getHighestBid().getEmail())
                .withHighestBidAmount(auction.getHighestBid().getAmount());
        log.info(yellow("Converted to {}"), dto);
        return dto;
    }
}
