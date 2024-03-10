package com.example.security.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/precious")
    public String secret(){
        return "Oh, My precious!";
    }

    @GetMapping("/hello")
    public String greet() {
        return "Hello!";
    }

    @GetMapping("/bonjour")
    public String bonjour(){
        return "Bonjour!";
    }
}
