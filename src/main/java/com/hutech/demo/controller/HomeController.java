package com.hutech.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "The best event ever!");
        return "home/home";
    }
    @GetMapping("/contact")
    public String contact() {
        return "home/contact";
    }
}