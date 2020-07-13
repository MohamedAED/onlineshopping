package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.miu.waa.onlineShopping.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("products")
public class ProductController {
    private Product product;

    @Autowired
    BuyerService buyerService;

    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductService productService;

    @ModelAttribute("cats")
    public List<ProductCategory> getAllCategories() {
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") Long productId,
                                 @RequestParam("buyerid") Long buyerId,
                                 Model model, HttpSession session) {

        String userRole = (String) session.getAttribute("UserRole");

        if(userRole == null)
        {
            userRole = "";
        }
        else
        {
            Buyer buyer =(Buyer) model.getAttribute("UserInfo");
            model.addAttribute("following",buyerService.IsFollowing(buyer.getUserId(),product.getSeller().getUserId()));
            model.addAttribute("buyerId",buyer.getUserId());
        }

        Product product = productService.getProductById(productId);
        this.product = product;
        Set<Review> reviews = product.getReviews();
        model.addAttribute("product",product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("UserRole",userRole);

        return "productinfo";
    }


    @GetMapping("/showByCategory")
    public String showByCategory(@RequestParam("categoryID") Long productCategoryID, Model model) {
        model.addAttribute("products",productService.getAllProductsPerCategory(productCategoryID));
        return "home";
    }

    @GetMapping("/followSeller")
    public String followSeller(@RequestParam("sellerId") Long sellerID, Model model) {
        User user =(User) model.getAttribute("UserInfo");
        model.addAttribute("product",product );
        //Add Seller To Buyer Following List
        buyerService.followSeller(user.getUserId(),sellerID);
        return "redirect:/products/product?id=" + product.getId();
    }

    @GetMapping("/unfollowSeller")
    public String unfollowSeller(@RequestParam("sellerId") Long sellerID, Model model) {
        User user =(User) model.getAttribute("UserInfo");
        model.addAttribute("product",product );
        //Remove Seller From Following List
        buyerService.unfollowSeller(user.getUserId(),product.getSeller().getUserId());
        return "redirect:/products/product?id=" + product.getId();
    }
}
