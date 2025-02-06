package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/details")
    public String about() {
        return "details"; // Corresponds to details.html
    }


}
