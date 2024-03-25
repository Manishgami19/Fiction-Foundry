package com.nextory.techtest.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexViewController {

    @GetMapping("/")
    public String getIndex() {
        return ("redirect:/book");
    }
}
