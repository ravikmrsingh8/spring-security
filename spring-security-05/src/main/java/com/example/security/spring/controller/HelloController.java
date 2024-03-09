package com.example.security.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/precious")
    public String secret(){
        return "Oh, My precious!";
    }

    @GetMapping("/hello")
    public String greet() {
        return "Hello!";
    }
}
