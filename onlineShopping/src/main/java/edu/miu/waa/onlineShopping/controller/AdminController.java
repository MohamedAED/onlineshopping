package edu.miu.waa.onlineShopping.controller;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.domain.enums.Role;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    SellerService sellerService;
    @Autowired
    BuyerService buyerService;

    @RequestMapping("/approveUsers")
    public String approveUsers(Model model) {
        model.addAttribute("unapprovedUsers",getUnapprovedUsers());
        return "approveUsers";
    }
    @RequestMapping("/approve")
    public String approveUsers(@RequestParam("userId") String userId, @RequestParam("role") String role, Model model) {
        Long id = Long.parseLong(userId);
        if(role.equals(Role.SELLER.toString()))
            sellerService.approveSeller(id);
        else {
            buyerService.approveBuyer(id);
        }
        model.addAttribute("unapprovedUsers",getUnapprovedUsers());
        return "approveUsers";
    }
    public List<User> getUnapprovedUsers(){
        List<User> unapprovedUsers = new ArrayList<>();
        unapprovedUsers.addAll(sellerService.findUnapprovedSellers());
        unapprovedUsers.addAll(buyerService.findUnapprovedBuyers());
        return unapprovedUsers;
    }
}
