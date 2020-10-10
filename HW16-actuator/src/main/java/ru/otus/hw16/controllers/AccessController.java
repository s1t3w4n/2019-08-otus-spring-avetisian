package ru.otus.hw16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {

    @GetMapping("/denied")
    public String accessDenied() {
        return "denied";
    }
}
