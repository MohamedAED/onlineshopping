package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.miu.waa.onlineShopping.service.*;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product",product );
        return "productInfo";
    }
/*
    @GetMapping("/follow/{sellerId}")
    public String followSeller(@PathVariable("sellerId") Long sellerId, Model model){
        Product product = productService.getProductById(productId);
        model.addAttribute("product",product);

        return "productInfo";
    }
    @GetMapping("/unfollow/{sellerId}")
    public String unfollowSeller(@PathVariable("sellerId") Long sellerId, Model model){
        Product product =productService.getProductById(productId);
        model.addAttribute("product",product);

        return "productInfo";
    }
    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, Model model){
        Product product =productService.getProductById(productId);
        model.addAttribute("product",product);

        return "productInfo";
    }*/
}
