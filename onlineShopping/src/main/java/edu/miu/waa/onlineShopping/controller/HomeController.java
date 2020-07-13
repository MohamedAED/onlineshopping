package edu.miu.waa.onlineShopping.controller;


import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.ProductCategoryService;
import edu.miu.waa.onlineShopping.service.ProductService;
import edu.miu.waa.onlineShopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute("cats")
    public List<ProductCategory> getAllCategories() {
        return productCategoryService.getAllProductCategory();
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getHomePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @RequestMapping(value = {"/SignedIn"}, method = RequestMethod.GET)
    public String getHomePage(@RequestParam("UserId") String UserId, @RequestParam("UserRole") String UserRole, Model model) {
        System.out.println("UserId =>" + UserId + ", UserRole => " + UserRole);
        model.addAttribute("products", productService.getAllProducts());
        String userRole = UserRole;
        // User user =(User) model.getAttribute("UserInfo");
        // String userRole =(String) model.getAttribute("UserRole");
        if (UserRole == null || UserId.equals(""))
            userRole = "";
        else {
          if(userRole.equals("buyer"))
            {
                Buyer buyer = buyerService.findUserById(Long.parseLong(UserId));
                model.addAttribute("UserInfo",buyer);
            }
            else if(userRole == "seller")
            {
                Seller seller = sellerService.findById(Long.parseLong(UserId));
                model.addAttribute("UserInfo",seller);
            }
            else if(userRole == "admin")
            {
            }
        }
        model.addAttribute("buyerId",UserId);
        model.addAttribute("UserRole",userRole);

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
