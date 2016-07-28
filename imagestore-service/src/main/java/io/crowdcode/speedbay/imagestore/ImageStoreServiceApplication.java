package io.crowdcode.speedbay.imagestore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
public class ImageStoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageStoreServiceApplication.class, args);
    }

    @Value("${imagestore.image.dir}")
    private String imageDir;
    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(imageDir).mkdirs();
        };
    }
}
