package io.crowdcode.speedbay.auction.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class DefaultInMemoryProfileCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(DefaultInMemoryProfileCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getEnvironment() != null) {
            // FIXME should be configurable
            final String[] activeProfiles = context.getEnvironment().getActiveProfiles();
            for (String profile : activeProfiles) {
                if ("datajpa".equals(profile) || "jpa".equals(profile)) {
                    return false;
                }
            }
        }
        return true;
    }
}
