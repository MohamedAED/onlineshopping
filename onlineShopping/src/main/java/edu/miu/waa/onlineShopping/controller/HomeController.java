package edu.miu.waa.onlineShopping.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    @GetMapping("/")
    public String greet(Model model) {
        model.addAttribute("firstName", "Josh");
        return "home";
    }
}
