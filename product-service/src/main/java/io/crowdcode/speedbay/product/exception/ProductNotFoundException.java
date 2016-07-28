package io.crowdcode.speedbay.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product not found!")
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(final String uuid) {
        super(String.format("Product with UUID %s not found", uuid));
    }
}
