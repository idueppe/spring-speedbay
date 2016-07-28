package io.crowdcode.speedbay.auction.controller;


import io.crowdcode.speedbay.auction.dto.AuctionCreateDto;
import io.crowdcode.speedbay.auction.service.AuctionCompositeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Controller
@RequestMapping("/")
public class AuctionFormController {

    private static final Logger log = LoggerFactory.getLogger(AuctionFormController.class);

    @Autowired
    private AuctionCompositeService auctionCompositeService;

    @ModelAttribute("auctionForm")
    public AuctionCreateDto createAuctionForm() {
        AuctionCreateDto form = new AuctionCreateDto();
        return form;
    }

    @RequestMapping(value = "auctions/new", method = RequestMethod.GET)
    public String showNewAuctionForm(@ModelAttribute AuctionCreateDto auction) {
        return "/auctionForm";
    }

    @RequestMapping(value = "auctions/new", method = RequestMethod.POST)
    public String checkNewAuction(@ModelAttribute @Valid AuctionCreateDto auctionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Received invalid form" + auctionForm);

            return "/auctionForm";
        } else {
            log.info("Received valid form " + auctionForm);

            auctionCompositeService.placeAuction(auctionForm.getTitle(), auctionForm.getDescription(), auctionForm.getMinAmount());

            return "redirect:/auctions";
        }
    }
}
