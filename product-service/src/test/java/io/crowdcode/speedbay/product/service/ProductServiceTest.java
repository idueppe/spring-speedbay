package io.crowdcode.speedbay.product.service;

import io.crowdcode.speedbay.product.ProductServiceApplication;
import io.crowdcode.speedbay.product.model.Product;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductServiceApplication.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    // IntelliJ doesn't recognise default datasource from spring boot
    @Autowired(required=false)
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Test
    public void testCreateProduct() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Long productId = productService.createProduct(uuid, "string", "description");
        Long foundProductId = jdbcTemplate.queryForObject(
                "SELECT id FROM Product WHERE uuid = :uuid",
                new MapSqlParameterSource().addValue("uuid", uuid),
                Long.class);

        assertThat(productId, is(equalTo(foundProductId)));
    }

    @Test
    public void testFindProduct() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Long productId = productService.createProduct(uuid, "string", "description");
        Product found = productService.findProduct(uuid);
        assertThat(found.getUuid(), is(equalTo(uuid)));

    }
}