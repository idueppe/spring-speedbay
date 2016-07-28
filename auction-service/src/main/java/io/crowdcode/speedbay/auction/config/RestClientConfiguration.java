package io.crowdcode.speedbay.auction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Arrays;
import java.util.Locale;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(Locale.ENGLISH);
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("speedbay-language");
        return cookieLocaleResolver;
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {

        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(-1);

        return new MessageSource() {
            private final Logger log = LoggerFactory.getLogger(MessageSource.class);

            @Override
            public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
                log.info(green("MESSAGE KEY: {} Locale:{}"), code, locale);
                return messageSource.getMessage(code, args, locale);
            }

            @Override
            public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
                log.info(green("MESSAGE KEY: {} Locale:{}"), code, locale);
                return messageSource.getMessage(code, args, defaultMessage, locale);
            }

            @Override
            public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
                log.info(green("MESSAGE KEY: [{}] Locale: {}"), Arrays.toString(resolvable.getCodes()), locale);
                return messageSource.getMessage(resolvable, locale);
            }
        };
    }
}
