package com.example.openapispringclient;

import com.example.demo.client.api.BookApi;
import com.example.demo.client.api.MemberApi;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

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
    HttpServiceProxyFactory httpServiceProxyFactory(WebClient bookWebClient) {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(bookWebClient))
                .blockTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    BookApi bookApi(HttpServiceProxyFactory httpServiceProxyFactory) {
        return httpServiceProxyFactory.createClient(BookApi.class);
    }

    @Bean
    public MemberApi memberApi(HttpServiceProxyFactory httpServiceProxyFactory) {
//        var apiPackage = "com.example.demo.client.api";
        return httpServiceProxyFactory.createClient(MemberApi.class);
    }



}
