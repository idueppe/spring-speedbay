package io.crowdcode.speedbay.product.exception;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class ProductNotFoundExceptionTest {

    @Test
    public void testNotFoundMessage() throws Exception {
        assertThat(new ProductNotFoundException("UUIDABC").getMessage(), is("Product with UUID UUIDABC not found"));

    }
}