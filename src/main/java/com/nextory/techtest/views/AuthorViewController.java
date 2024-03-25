package com.nextory.techtest.views;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nextory.techtest.models.Author;
import com.nextory.techtest.services.AuthorService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorViewController {
    
    @Autowired
    AuthorService authorService;

    @GetMapping("/author")
    public String getAuthorPage(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(25);
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);

        Page<Author> authors = authorService.getAllAuthorsbyPage(pageable);
        model.addAttribute("authors", authors);

        int totalPages = authors.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return ("authors/index");
    }

    @GetMapping("/author/{id}")
    public String getAuthorPageDetail(@PathVariable("id") Integer _id, Model model) {
        Author author = authorService.getAuthorById(_id);
        model.addAttribute("author", author);

        return ("authors/detail");
    }
}
