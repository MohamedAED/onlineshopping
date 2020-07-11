package edu.miu.waa.onlineShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String greet(Model model) {

        //model.addAttribute("firstName", "Josh");
        model.addAttribute("userRole","buyer");
        //model.addAttribute("userRole","seller");


        return "home";
    }
}
