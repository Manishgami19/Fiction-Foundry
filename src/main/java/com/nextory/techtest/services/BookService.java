package com.nextory.techtest.services;

import java.util.ArrayList;
import java.util.List;

import com.nextory.techtest.models.Comment;
import com.nextory.techtest.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return (books);
    }

    public Page<Book> getAllBooksbyPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Integer bookId) {
        return (bookRepository.findById(bookId).get());
    }

    public void saveComment(Comment newComment) {
        commentRepository.save(newComment);
    }

    public List<Book> getAllBookSuggestions(Long authorid) {
        return bookRepository.getAllBookSuggestions(authorid);
    }

    public Page<Comment> getAllCommentsByPageByBookId(Integer _id, Pageable pageable) {
        return bookRepository.getAllCommentsByPageByBookId(_id, pageable);
    }
}
