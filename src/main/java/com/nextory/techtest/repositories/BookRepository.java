package com.nextory.techtest.repositories;

import com.nextory.techtest.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nextory.techtest.models.Book;

import java.util.List;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Page<Book> findAll(Pageable pageable);

    @Query(value="select b from Author a " +
            "inner join Book b " +
            "on b.author.id = a.id " +
            "where a.id = :authorid " +
            "Order by b.boostScore DESC " +
            "LIMIT 6")
    List<Book> getAllBookSuggestions(@Param("authorid") Long authorid);


    @Query(value = "select c from Book b " +
            "inner join comments c " +
            "on b.id = c.book.id " +
            "where c.book.id = :bookId")
    Page<Comment> getAllCommentsByPageByBookId(@Param("bookId") Integer _id, Pageable pageable);

}
