package io.crowdcode.speedbay.product.fixture;

import io.crowdcode.speedbay.product.model.Product;

import java.util.UUID;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class ProductFixture {

    public static Product defaultProduct(String uuid) {
        return new Product().withUuid(uuid).withId(1L).withTitle("title").withDescription("description");
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

}
