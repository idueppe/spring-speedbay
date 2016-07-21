package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaPersistenceContextConfiguration.class)
public class JpaPersistenceContextConfigurationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void entityManager() throws Exception {
        assertNotNull(em);
    }
}