package com.nextory.techtest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nextory.techtest.models.Author;
import com.nextory.techtest.services.AuthorService;

@RestController
public class AuthorController {
    
    @Autowired
    AuthorService authorService;

    @GetMapping("/api/author")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return (ResponseEntity.notFound().build());
        } else {
            return (ResponseEntity.ok(authors));
        }
    }

    @GetMapping("/api/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer _id) {
        Author author = authorService.getAuthorById(_id);
        if (author == null) {
            return (ResponseEntity.notFound().build());
        } else {
            return (ResponseEntity.ok(author));
        }
    }
}
