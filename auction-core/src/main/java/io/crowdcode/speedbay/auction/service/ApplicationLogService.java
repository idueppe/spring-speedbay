package io.crowdcode.speedbay.auction.service;


import io.crowdcode.speedbay.auction.model.Message;

import java.time.Duration;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface ApplicationLogService {

    void log(String message, Object... args);

    List<Message> lastLogs(Duration duration);

}
