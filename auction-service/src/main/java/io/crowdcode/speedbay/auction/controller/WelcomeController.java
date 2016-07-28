package io.crowdcode.speedbay.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String showWelcome() {
        return "/welcome";
    }

}
