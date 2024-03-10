package com.example.security.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DudController {

    @RequestMapping(method = RequestMethod.GET, value = "/dud")
    public String info() {
        return "If I'm not back in five minutes, just wait longer.";
    }
}
