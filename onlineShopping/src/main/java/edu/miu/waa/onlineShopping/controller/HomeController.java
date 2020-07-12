package edu.miu.waa.onlineShopping.controller;


import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.service.ProductCategoryService;
import edu.miu.waa.onlineShopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private String strCurrentUserRole = "seller";
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductService productService;

    @ModelAttribute("userRole")
    public String getUserRole() {
        return strCurrentUserRole;
    }

    @ModelAttribute("cats")
    public List<ProductCategory> getAllCategories() {
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/")
    public String greet(Model model) {
        model.addAttribute("products",productService.getAllProducts());
        return "home";
    }

    @GetMapping("/showByCategory")
    public String showByCategory(@RequestParam("categoryID") Long productCategoryID, Model model) {
        model.addAttribute("products",productService.getAllProductsPerCategory(productCategoryID));
        return "home";
    }


}
