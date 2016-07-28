package io.crowdcode.speedbay.auction.controller;

import io.crowdcode.speedbay.auction.dto.AuctionCreateDto;
import io.crowdcode.speedbay.auction.dto.AuctionInfoDto;
import io.crowdcode.speedbay.auction.dto.BidOnAuctionDto;
import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.service.AuctionCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RestController
@RequestMapping(path="/api/auctions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuctionRestController {

    @Autowired
    private AuctionCompositeService auctionCompositeService;


    @RequestMapping(method = RequestMethod.GET)
    public List<AuctionInfoDto> getAuctions() {
        return auctionCompositeService.findRunningAuctions();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> placeAuction(@RequestBody AuctionCreateDto auctionCreateDto, UriComponentsBuilder uriComponentsBuilder) {
        Long auctionId = auctionCompositeService.placeAuction(
                auctionCreateDto.getTitle(),
                auctionCreateDto.getDescription(),
                auctionCreateDto.getMinAmount());
        URI uri = uriComponentsBuilder.path("/{auctionId}").buildAndExpand(auctionId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(path="/{auctionId}", method = RequestMethod.GET)
    public AuctionInfoDto getAuction(@PathVariable("auctionId") Long auctionId) {
        return auctionCompositeService.findAuction(auctionId);
    }

    @RequestMapping(path="/{auctionId}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<Void> bidOnAuction(@PathParam("auctionId") Long auctionId, @RequestBody() BidOnAuctionDto bidOnAuction) throws BidTooLowException, AuctionExpiredException, AuctionNotFoundException {
        auctionCompositeService.bidOnAuction(auctionId, bidOnAuction.getAmount());
        return ResponseEntity.accepted().build();
    }

}
