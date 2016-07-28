package auction.remote;

import io.crowdcode.speedbay.auction.AuctionServiceApplication;
import io.crowdcode.speedbay.auction.dto.ProductDto;
import io.crowdcode.speedbay.auction.remote.ImageRestClient;
import io.crowdcode.speedbay.auction.remote.ProductRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AuctionServiceApplication.class)
public class ProductRestClientBeanIT {

    private static final Logger log = LoggerFactory.getLogger(ProductRestClientBeanIT.class);

    @Autowired
    private ProductRestClient productRestClient;

    @Autowired
    private ImageRestClient imageRestClient;

    @Test
    public void testCreateAndFindProduct() throws Exception {
        String productUuid = productRestClient.createProduct("title", "description");
        ProductDto product = productRestClient.findProduct(productUuid);
        assertThat(product.getTitle(), is("title"));
    }

    @Test
    public void testFindImages() throws Exception {
        String productUuid = productRestClient.createProduct("title", "description");
        URI thumbnail = imageRestClient.findThumbnail(productUuid);
        assertNull(thumbnail);
    }

    @Test
    public void testFindSpecificThumbnail() throws Exception {
        URI thumbnail = imageRestClient.findThumbnail("f3479");
        log.info(blue("URL: {}"), thumbnail.toString());
        assertNotNull(thumbnail);
    }


}