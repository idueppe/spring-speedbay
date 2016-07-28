package io.crowdcode.speedbay.auction.remote;

import io.crowdcode.speedbay.auction.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Service
public class ImageRestClientBean implements ImageRestClient {

    private static final Logger log = LoggerFactory.getLogger(ImageRestClient.class);

    @Value("${image.service.url}")
    private String imageReserviceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public URI findThumbnail(String ownerUuid) {
        String url = imageReserviceUrl + "/{ownerUuid}";

        ResponseEntity<String[]> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        defaultHttpEntity(),
                        String[].class,
                        ownerUuid);

        String[] images = response.getBody();

        if (images != null && images.length > 0) {
            try {
                return new URL(imageReserviceUrl + "images/" + images[0]).toURI();
            } catch (URISyntaxException | MalformedURLException e) {
                throw new SystemException("Configuration is wrong.", e);
            }
        } else {
            return null;
        }
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
