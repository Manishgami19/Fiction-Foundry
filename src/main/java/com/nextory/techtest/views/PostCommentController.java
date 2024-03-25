package com.nextory.techtest.views;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.models.Comment;
import com.nextory.techtest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class PostCommentController {
    @Autowired
    BookService bookService;

    @GetMapping("/book/{id}/comment")
    public String getBookDetailPage(@PathVariable("id") Integer _id, Model model) {
        Book book = bookService.getBookById(_id);

        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());

        return ("books/comment");
    }

    @PostMapping("/book/{id}/comment")
    public String postComment(
            @ModelAttribute Comment comment,
            @PathVariable("id") Integer id,
            Model model) {

        Comment newComment = new Comment();
        Book bookDetails = bookService.getBookById(id);

        if(!(comment.getPseudonyme().isEmpty() || comment.getContent().isEmpty() || comment.getRating().isEmpty())) {
            newComment.setPseudonyme(comment.getPseudonyme());
            newComment.setContent(comment.getContent());
            newComment.setRating(comment.getRating());
            newComment.setBook(bookDetails);
            newComment.setUpdatedAt(LocalDateTime.now());
            newComment.setUpdatedAt(LocalDateTime.now());

            bookService.saveComment(newComment);
        }
        List<Comment> commentSortedByRating = bookDetails.getComments();
        Collections.sort(commentSortedByRating, (o1, o2) -> (o2.getRating().compareTo(o1.getRating())));

        List<Book> suggestions = bookService.getAllBookSuggestions(bookDetails.getAuthor().getId());
        model.addAttribute("book", bookDetails);
        model.addAttribute("commentSortedByRating", commentSortedByRating);
        model.addAttribute("suggestions", suggestions);

        return ("books/detail");
    }
}
