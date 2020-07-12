package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.miu.waa.onlineShopping.service.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("products")
public class ProductController {
    private String strCurrentUserRole = "seller";
    private Long currentUserID = 1l;
    private Product product;

    @Autowired
    BuyerService buyerService;

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

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        this.product = product;
        Set<Review> reviews = product.getReviews();
        model.addAttribute("product",product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("currentUserID",currentUserID);
        model.addAttribute("following",buyerService.IsFollowing(currentUserID,product.getSeller().getUserId()));
        return "productInfo";
    }
    @GetMapping("/showByCategory")
    public String showByCategory(@RequestParam("categoryID") Long productCategoryID, Model model) {
        model.addAttribute("products",productService.getAllProductsPerCategory(productCategoryID));
        return "home";
    }

    @GetMapping("/followSeller")
    public String followSeller(@RequestParam("sellerId") Long sellerID, Model model) {
        model.addAttribute("product",product );
        //Add Seller To Buyer Following List
        buyerService.followSeller(currentUserID,sellerID);
        return "redirect:/products/product?id=" + product.getId();
    }

    @GetMapping("/unfollowSeller")
    public String unfollowSeller(@RequestParam("sellerId") Long sellerID, Model model) {
        model.addAttribute("product",product );
        //Remove Seller From Following List
        buyerService.unfollowSeller(currentUserID,product.getSeller().getUserId());
        return "redirect:/products/product?id=" + product.getId();
    }
}
