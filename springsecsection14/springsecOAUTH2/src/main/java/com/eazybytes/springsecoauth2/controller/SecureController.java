package com.eazybytes.springsecoauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

    @GetMapping
    public String securePage() {
        return "secure.html";
    }
}
