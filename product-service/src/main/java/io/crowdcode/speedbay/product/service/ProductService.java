package io.crowdcode.speedbay.product.service;

import io.crowdcode.speedbay.product.exception.ProductNotFoundException;
import io.crowdcode.speedbay.product.model.Product;
import io.crowdcode.speedbay.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Long createProduct(final String uuid, final String title, final String description) {
        Product product = new Product()
                .withUuid(uuid)
                .withTitle(title)
                .withDescription(description);
        return productRepository.save(product).getId();
    }

    public Product findProduct(final String uuid) throws ProductNotFoundException {
        return productRepository.findByUuid(uuid).orElseThrow(
                () -> new ProductNotFoundException(uuid));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
