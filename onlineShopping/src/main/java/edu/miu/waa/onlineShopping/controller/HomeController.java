package edu.miu.waa.onlineShopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    @RequestMapping("/")
    public String goHome() {
        return "home";
    }
}
