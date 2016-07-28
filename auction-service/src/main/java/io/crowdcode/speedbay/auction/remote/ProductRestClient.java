package io.crowdcode.speedbay.auction.remote;

import io.crowdcode.speedbay.auction.dto.ProductDto;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface ProductRestClient {

    String createProduct(String title, String description);

    ProductDto findProduct(String productUuid);
}
