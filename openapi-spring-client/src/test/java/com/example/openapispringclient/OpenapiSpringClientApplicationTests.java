package com.example.openapispringclient;

import com.example.demo.client.api.BookApi;
import com.example.demo.client.api.MemberApi;
import com.example.demo.client.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = {OpenapiSpringClientApplicationTests.class})
@Import(ClientConfiguration.class)
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@Slf4j
class OpenapiSpringClientApplicationTests {

    @Autowired
    BookApi bookApi;

    @Autowired
    MemberApi memberApi;


    @Test
    @Order(1)
    void allBook() {
        var allBook = bookApi.allBook();
        log.info(allBook.toString());
    }

    @Test
    @Order(2)
    void getBookNotFound() {
        try {
            var book = bookApi.getBook(3L);
            log.info(book.toString());
        } catch (WebClientResponseException responseException) {
            log.info(responseException.toString());
            var responseBodyAsString = responseException.getResponseBodyAsString(Charset.defaultCharset());
            log.info(responseBodyAsString);
        }
    }

    @Test
    @Order(3)
    void bookSave() {
        var newBook = new Book().author("xx").created(LocalDateTime.now()).bornDate(LocalDate.now()).id(3L).name("newBook");
        var book = bookApi.saveBook(newBook);
        log.info(book.toString());
    }

    @Test
    @Order(4)
    void getBook() {
        var book = bookApi.getBook(3L);
        log.info(book.toString());
    }


    @Test
    @Order(5)
    void getMember() {
        try {
            var member = memberApi.getMember(1L);
        } catch (WebClientResponseException responseException) {
            log.info(responseException.toString());
            var responseBodyAsString = responseException.getResponseBodyAsString(Charset.defaultCharset());
            log.info(responseBodyAsString);
        }
    }



}
