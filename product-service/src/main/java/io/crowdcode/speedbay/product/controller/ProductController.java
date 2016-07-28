package io.crowdcode.speedbay.product.controller;

import io.crowdcode.speedbay.product.exception.ProductNotFoundException;
import io.crowdcode.speedbay.product.model.Product;
import io.crowdcode.speedbay.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RestController
@RequestMapping(path="/api/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(path="/{productUuid}", method = RequestMethod.GET)
    public Product get(@PathVariable("productUuid") String productUuid) throws ProductNotFoundException {
        return productService.findProduct(productUuid);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Product> post(@RequestBody @Valid Product product, UriComponentsBuilder uriComponentsBuilder, BindingResult bindingResult) throws ProductNotFoundException {
        log.info(red("hat fehler? {}"),bindingResult.hasErrors());
        if (!bindingResult.hasErrors()) {
            productService.createProduct(product.getUuid(), product.getTitle(), product.getDescription());

            URI location = uriComponentsBuilder
                    .path("/api/products/{uuid}")
                    .buildAndExpand(product.getUuid())
                    .toUri();
            return ResponseEntity.created(location).body(null);

        }
        return ResponseEntity.badRequest().body(product);
    }


}
