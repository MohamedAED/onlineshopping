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
    @Autowired
    UserService userService;

    @ModelAttribute("unapprovedUsers")
    public List<User> UnapprovedUsers(){
        return userService.getUnapprovedUsers();
    }

    @RequestMapping("/approveUsers")
    public String approveUsers(Model model) {
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
}
