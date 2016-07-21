package io.crowdcode.speedbay.auction.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Aspect
public class ApplicationLogAdvice {

    private static final Logger log = LoggerFactory.getLogger(ApplicationLogAdvice.class);

    @Autowired
    @Lazy
    private ApplicationLogService appLogService;

    @Around("execution(* io.crowdcode.speedbay.auction.service.AuctionServiceBean.*(..))")
    public Object basicLogging(ProceedingJoinPoint pjp) throws Throwable {
        appLogService.log(pjp.getSignature().getName());
        log.info(green("starting method {}"), pjp.toString());
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }


}
