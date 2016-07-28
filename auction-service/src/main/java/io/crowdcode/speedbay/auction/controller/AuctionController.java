package io.crowdcode.speedbay.auction.controller;


import io.crowdcode.speedbay.auction.dto.AuctionInfoDto;
import io.crowdcode.speedbay.auction.service.AuctionCompositeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static io.crowdcode.speedbay.common.AnsiColor.yellow;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Controller
@RequestMapping("/auctions")
public class AuctionController {

    private static final Logger log = LoggerFactory.getLogger(AuctionController.class);

    @Autowired
    private AuctionCompositeService auctionCompositeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView auctions(ModelAndView modelAndView) {
        List<AuctionInfoDto> auctions = auctionCompositeService.findRunningAuctions();
        auctions.forEach(a -> log.info(yellow("Auctions: {} "), a));
        log.info(blue("Found {} Auctions"), auctions.size());

        modelAndView.addObject("auctions", auctions);
        modelAndView.setViewName("/auctions");

        return modelAndView;
    }

    @RequestMapping(value = "/{auctionId}", method = RequestMethod.GET)
    public ModelAndView showAuction(ModelAndView modelAndView, @PathVariable("auctionId") Long auctionId, RedirectAttributes redirectAttributes) {
        AuctionInfoDto auction = auctionCompositeService.findAuction(auctionId);

        if (auction != null) {
            modelAndView.addObject("auction", auction);
            modelAndView.setViewName("/auctionDetail");
        } else {
            redirectAttributes.addFlashAttribute("message", "Auction with Id " + auctionId + "not found");
            modelAndView.setViewName("redirect:/auctions");
        }
        return modelAndView;
    }

}
