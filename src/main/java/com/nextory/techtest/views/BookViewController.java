package com.nextory.techtest.views;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.nextory.techtest.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextory.techtest.models.Book;
import com.nextory.techtest.services.BookService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookViewController {

    @Autowired 
    BookService bookService;

    @GetMapping("/book")
    public String getBookPage(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(25);
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);

        Page<Book> books = bookService.getAllBooksbyPage(pageable);
        model.addAttribute("books", books);

        int totalPages = books.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return ("books/index");
    }
    @GetMapping("/book/{id}")
    public String getBookDetailPage(@PathVariable("id") Integer _id, Model model) {
        Book book = bookService.getBookById(_id);
        model.addAttribute("book", book);

        List<Comment> commentSortedByRating = book.getComments();
        Collections.sort(commentSortedByRating, (o1, o2) -> (o2.getRating().compareTo(o1.getRating())));
        model.addAttribute("commentSortedByRating", commentSortedByRating);

        List<Book> suggestions = bookService
                .getAllBookSuggestions(book.getAuthor().getId())
                .stream()
                .filter(e -> e.getCover() != book.getCover())
                .collect(Collectors.toList());
        model.addAttribute("suggestions", suggestions);

        return ("books/detail");
    }


}
