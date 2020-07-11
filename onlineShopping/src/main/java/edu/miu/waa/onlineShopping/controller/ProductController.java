package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import edu.miu.waa.onlineShopping.service.*;
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public String getProductDetails(@PathVariable("productId") Long productId, Model model){
        Product product =productService.getProductById(productId);
        model.addAttribute("product",product);

        return "productInfo";
    }
}
