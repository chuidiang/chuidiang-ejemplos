package com.chuidiang.example.bootprimefaces.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class WebController {
    @GetMapping("/pepe")
    public String greeting(Model model) {
        return "index.xhtml";
    }
}

