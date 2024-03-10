package com.example.security.spring.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/precious")
    @PreAuthorize("hasAuthority('Precious')")
    public String precious(){
        return "Hello Gollum!";
    }

    @GetMapping("/bonjour")
    @PreAuthorize("hasAuthority('French')")
    public String bonjour(){
        return "Bonjour!";
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('Read', 'Write')")
    public String greet(){
        return "Hello";
    }

    @GetMapping("/tryme")
    @PreAuthorize("@authorizationEvaluator.isAuthorized()")
    public String tryMe(){
        return "\"It only takes one bullet\" - Rango";
    }
}
