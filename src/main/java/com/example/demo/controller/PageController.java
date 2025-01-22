package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // Corresponds to index.html
    }

    @GetMapping("/details")
    public String about() {
        return "details"; // Corresponds to details.html
    }


}
