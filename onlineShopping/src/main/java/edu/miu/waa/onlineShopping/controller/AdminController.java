package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.Role;
import edu.miu.waa.onlineShopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    SellerService sellerService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("/approveUsers")
    public String approveUsers(Model model) {
        model.addAttribute("unapprovedUsers",getUnapprovedUsers());
        return "approveUsers";
    }
    @RequestMapping("/approveUserById")
    public String approveUserById(@RequestParam("userId") String userId, @RequestParam("role") String role, @RequestParam("status") String statusId, Model model) {
        Long id = Long.parseLong(userId);
        if(role.equals(Role.SELLER.toString()))
            sellerService.approveSeller(id, statusId);
        else {
            buyerService.approveBuyer(id, statusId);
        }
        model.addAttribute("unapprovedUsers",getUnapprovedUsers());
        return "approveUsers";
    }
    @RequestMapping("/approveReviewById")
    public String approveReviewById(@RequestParam("reviewId") String reviewId, @RequestParam("status") String statusId,Model model) {
        Long id = Long.parseLong(reviewId);
        reviewService.approveReview(id, statusId);
        model.addAttribute("unapprovedReviews",reviewService.findUnapprovedReviews());
        return "approveReviews";
    }
    @RequestMapping("/approveReviews")
    public String approveReviews(Model model) {
        model.addAttribute("unapprovedReviews",reviewService.findUnapprovedReviews());
        return "approveReviews";
    }
    public List<User> getUnapprovedUsers(){
        List<User> unapprovedUsers = new ArrayList<>();
        unapprovedUsers.addAll(sellerService.findAll());
        unapprovedUsers.addAll(buyerService.getAllBuyer());
        return unapprovedUsers;
    }
}
