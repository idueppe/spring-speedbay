package io.crowdcode.speedbay.auction.remote;

import io.crowdcode.speedbay.auction.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

import static io.crowdcode.speedbay.common.AnsiColor.blue;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Component
public class ProductRestClientBean implements ProductRestClient {

    private static final Logger log = LoggerFactory.getLogger(ProductRestClientBean.class);

    // TODO
    private String productServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String createProduct(String title, String description) {
        String ownerUuid = UUID.randomUUID().toString().substring(0,5);
        ProductDto productDto = new ProductDto()
                .withTitle(title)
                .withUuid(ownerUuid)
                .withDescription(description);

        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, jsonContentTypeHeaders());

        ResponseEntity<String> response = restTemplate.exchange(productServiceUrl, HttpMethod.POST, entity, String.class, Void.class);
        log.info(blue("Created Auction at {}"), response.getHeaders().getFirst("location"));
        return ownerUuid;
    }

    @Override
    public ProductDto findProduct(String productUuid) {
        String url = productServiceUrl + "/{productUuid}";

        ResponseEntity<ProductDto> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        defaultHttpEntity(),
                        ProductDto.class,
                        productUuid);

        return response.getBody();
    }

    private HttpEntity<Object> defaultHttpEntity() {
        return new HttpEntity<>(jsonContentTypeHeaders());
    }

    private HttpHeaders jsonContentTypeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        return headers;
    }

}
