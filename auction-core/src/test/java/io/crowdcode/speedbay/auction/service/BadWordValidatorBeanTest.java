package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.BusinessLogicAnnotationConfiguration;
import io.crowdcode.speedbay.auction.exception.BadWordException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessLogicAnnotationConfiguration.class)
public class BadWordValidatorBeanTest {

    @Autowired
    private BadWordValidator validator;

    @Test(expected = BadWordException.class)
    public void testBadWordCheck() throws Exception {
        validator.checkBadWords("PERL");
    }

    @Test
    public void testIsValid() throws Exception {
        assertTrue(validator.isInvalid("PERL"));
    }

}