package com.example.openapiapp.api;

import com.example.demo.api.BookApi;
import com.example.demo.api.model.AllBooksResponse;
import com.example.demo.api.model.Book;
import com.example.demo.api.model.Empty;
import com.example.demo.api.model.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookApiImpl implements BookApi {

    Map<Long, Book> bookMap = new HashMap<>(
            Map.of(1L, new Book().author("xx").created(LocalDateTime.now()).bornDate(LocalDate.now()).id(1L).name("Megalovania"),
            2L, new Book().author("xxs").created(LocalDateTime.now()).bornDate(LocalDate.of(2002, 1, 1)).id(2L).name("Peoples"))
    );

    @Override
    public ResponseEntity<AllBooksResponse> allBook() {
        var response = new AllBooksResponse()
                .data(new ArrayList<>(bookMap.values()))
                .page(new Page().count(1024L));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Empty> saveBook(Book book) {
        bookMap.put(book.getId(), book);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Book> getBook(Long id) {
        var book = bookMap.get(id);
        if (book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "书不存在");

        return ResponseEntity.ok(book);
    }



}
