package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.miu.waa.onlineShopping.service.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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
            Product product = productService.getProductById(productId);
            Buyer buyer =(Buyer) buyerService.findUserById(buyerId);
            model.addAttribute("following",buyerService.IsFollowing(buyer.getUserId(),product.getSeller().getUserId()));
            model.addAttribute("buyerId",buyer.getUserId());
            model.addAttribute("UserInfo",buyer);
        }

        Product product = productService.getProductById(productId);
        this.product = product;
        Set<Review> reviews = product.getReviews();
        model.addAttribute("product",product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("UserRole",userRole);
        model.addAttribute("buyerId",buyerId);

        return "productinfo";
    }

    @GetMapping("/productView")
    public String getProductByIdView(@RequestParam("id") Long productId, Model model, HttpSession session) {

        Product product = productService.getProductById(productId);
        this.product = product;
        Set<Review> reviews = product.getReviews();
        model.addAttribute("product",product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("UserRole","");
        model.addAttribute("buyerId","");

        return "productinfo";
    }

    @GetMapping("/showByCategory")
    public String showByCategory(@RequestParam("categoryID") Long productCategoryID, Model model, Principal principal) {
        Buyer buyer2= buyerService.findUserByUsername(principal.getName());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("UserInfo",buyer2);
        model.addAttribute("buyerId",buyer2.getUserId());
        model.addAttribute("UserRole",buyer2.getRole().toString().toLowerCase());

        model.addAttribute("products",productService.getAllProductsPerCategory(productCategoryID));
        return "forward:/";
    }

    @RequestMapping("/showByCategoryLoggedIn")
    public String showByCategoryLoggedIn(Principal principal,@RequestParam("categoryID") Long productCategoryID, Model model) {
        if (principal != null){
            Buyer buyer= buyerService.findUserByUsername(principal.getName());
            model.addAttribute("UserInfo",buyer);
            model.addAttribute("buyerId",buyer.getUserId());
            model.addAttribute("UserRole",buyer.getRole().toString().toLowerCase());
        }
        model.addAttribute("products", productService.getAllProductsPerCategory(productCategoryID));
        return "home";
    }


    @GetMapping("/followSeller")
    public String followSeller(@RequestParam("sellerId") Long sellerID,@RequestParam("buyerid") Long buyerid, Model model) {
        model.addAttribute("product",product );
        model.addAttribute("following",buyerService.IsFollowing(buyerid,sellerID));
        //Add Seller To Buyer Following List
        buyerService.followSeller(buyerid,sellerID);
        return "forward:/products/product?id=" + product.getId()+"&buyerid="+buyerid;
    }

    @GetMapping("/unfollowSeller")
    public String unfollowSeller(@RequestParam("sellerId") Long sellerID,@RequestParam("buyerid") Long buyerid, Model model) {
        model.addAttribute("product",product );
        model.addAttribute("following",buyerService.IsFollowing(buyerid,sellerID));
        //Remove Seller From Following List
        buyerService.unfollowSeller(buyerid,sellerID);
        return "forward:/products/product?id=" + product.getId()+"&buyerid="+buyerid;
    }
}
