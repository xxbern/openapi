package com.example.openapispringclient;

import com.example.demo.client.BookApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    @Value("${client.openapi-app.url}")
    String url;


    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean
    BookApi postClient(WebClient bookWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(bookWebClient))
                        .build();
        return httpServiceProxyFactory.createClient(BookApi.class);
    }



}
