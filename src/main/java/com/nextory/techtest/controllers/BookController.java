package com.nextory.techtest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.services.BookService;

@RestController
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping("/api/book")
    private ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return (ResponseEntity.notFound().build());
        } else {
            return (ResponseEntity.ok(books));
        }
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer _id) {
        Book book = bookService.getBookById(_id);
        if (book == null) {
            return (ResponseEntity.notFound().build());
        } else {
            return (ResponseEntity.ok(book));
        }
    }
    
}
