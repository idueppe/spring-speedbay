package io.crowdcode.speedbay.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.crowdcode.speedbay.product.ProductServiceApplication;
import io.crowdcode.speedbay.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class ProductControllerIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(ProductControllerIntegrationTest.class);

    @Value("${local.server.port}")
    private int port;

    @Value("${server.context-path:/}")
    private String contextPath;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRunningTomcat() throws Exception {
        log.info(green("Test Server is running at http://localhost:{}{}"),port,contextPath);

        System.out.println(green("TEST IS RUNNING"));
        System.out.println(red("TEST IS STOPPED"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        RestTemplate restTemplate = createRestTemplate();

        Product product = new Product()
                .withUuid(UUID.randomUUID().toString())
                .withTitle("INTEGRATION TEST");

        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:"+port+contextPath+"/api/products",
                product,
                Void.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        String location = response.getHeaders().getFirst("location");

        Product found = restTemplate.getForObject(location, Product.class);

        System.out.println(blue(found.toString()));

        assertThat(found.getUuid(), is(product.getUuid()));

    }

    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonConverter =
                new MappingJackson2HttpMessageConverter();

        jsonConverter.setObjectMapper(objectMapper);
        restTemplate.setMessageConverters(Arrays.asList(jsonConverter));

        return restTemplate;
    }































}
