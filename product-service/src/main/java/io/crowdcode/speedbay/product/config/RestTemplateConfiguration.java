package io.crowdcode.speedbay.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter jsonConverter) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(jsonConverter));
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

}
