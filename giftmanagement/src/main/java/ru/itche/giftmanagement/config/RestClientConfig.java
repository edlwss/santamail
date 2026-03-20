package ru.itche.giftmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final LetterServiceProperties letterServiceProperties;

    @Bean
    public RestClient letterRestClient() {
        return RestClient.builder()
                .baseUrl(letterServiceProperties.getUrl())
                .build();
    }
}

