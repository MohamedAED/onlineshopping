package edu.miu.waa.onlineShopping.controller;


import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductService productService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    SellerService sellerService;
    @Autowired
    UserService userService;

    @ModelAttribute("cats")
    public List<ProductCategory> getAllCategories() {
        return productCategoryService.getAllProductCategory();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getHomePage(Principal principal, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @RequestMapping(value = {"/SignedIn"}, method = RequestMethod.GET)
    public String getHomePage(Principal principal,@RequestParam("UserId") String UserId, @RequestParam("UserRole") String UserRole, Model model) {

        Buyer buyer2= buyerService.findUserByUsername(principal.getName());
        model.addAttribute("products", productService.getAllProducts());
        String userRole = "buyer";
        Buyer buyer;
        buyer = buyerService.findUserById(Long.parseLong(UserId));
        model.addAttribute("UserInfo",buyer2==null?buyer:buyer2);
        model.addAttribute("buyerId",UserId);
        model.addAttribute("UserRole",userRole);

        return "home";
    }

    @RequestMapping(value = {"/contShopping"}, method = RequestMethod.GET)
    public String getHomePageFromShopping(Principal principal, Model model) {
        Buyer buyer2= buyerService.findUserByUsername(principal.getName());
        model.addAttribute("products", productService.getAllProducts());

        model.addAttribute("UserInfo",buyer2);
        model.addAttribute("buyerId",buyer2.getUserId());
        model.addAttribute("UserRole",buyer2.getRole().toString().toLowerCase());

        return "home";
    }

    @GetMapping("/showByCategory")
    public String showByCategory(@RequestParam("categoryID") Long productCategoryID, Model model) {
        String userRole = (String) model.getAttribute("UserRole");
        if (userRole == "buyer") {
            Buyer buyer = (Buyer) model.getAttribute("UserInfo");
            model.addAttribute("UserInfo", buyer);
        } else if (userRole == "seller") {
            Seller seller = (Seller) model.getAttribute("UserInfo");
            model.addAttribute("UserInfo", seller);
        } else if (userRole == "admin") {
            Admin admin = (Admin) model.getAttribute("UserInfo");
            model.addAttribute("UserInfo", admin);
        }


        model.addAttribute("products", productService.getAllProductsPerCategory(productCategoryID));
        model.addAttribute("UserRole", userRole);

        return "home";
    }


}
