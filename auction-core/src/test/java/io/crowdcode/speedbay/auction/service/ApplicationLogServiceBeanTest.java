package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.ApplicationLogSimpleConfiguration;
import io.crowdcode.speedbay.auction.config.JdbcTransactionConfiguration;
import io.crowdcode.speedbay.auction.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationLogSimpleConfiguration.class, JdbcTransactionConfiguration.class, })
public class ApplicationLogServiceBeanTest {

    @Autowired
    private ApplicationLogService appLogService;

    @Test
    @Sql(statements = "DELETE FROM application_log")
    public void testApplicationLogging() throws Exception {
        appLogService.log("JUNIT TEST %s", "LOG");
        List<Message> messages = appLogService
                .lastLogs(Duration.of(5, ChronoUnit.SECONDS));

        messages.forEach(System.out::println);

        assertThat(messages, contains(hasProperty("message", is("JUNIT TEST LOG"))));
    }
}