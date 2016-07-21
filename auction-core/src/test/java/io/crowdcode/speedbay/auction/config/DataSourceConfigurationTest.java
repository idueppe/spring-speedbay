package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DataSourceConfiguration.class)
public class DataSourceConfigurationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testForExistingDatasource() throws Exception {
        assertNotNull(dataSource);
    }
}