package io.crowdcode.speedbay.auction.service;


import io.crowdcode.speedbay.auction.exception.BadWordException;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface BadWordValidator {

    void checkBadWords(String title) throws BadWordException;

    boolean isInvalid(String title);
}
