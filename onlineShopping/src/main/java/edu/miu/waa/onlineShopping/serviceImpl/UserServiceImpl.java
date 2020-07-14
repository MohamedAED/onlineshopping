package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.service.AdminService;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.SellerService;
import edu.miu.waa.onlineShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AdminService adminService;
    @Autowired
    SellerService sellerService;
    @Autowired
    BuyerService buyerService;

    public User getUserByName(String username){
        User user;
        if (sellerService.findUserByUsername(username) != null) {
            user = sellerService.findUserByUsername(username);
        } else if (adminService.findUserByUsername(username) != null) {
            user = adminService.findUserByUsername(username);
        } else {
            user = buyerService.findUserByUsername(username);
        }
        return user;
    }

        public List<User> getUnapprovedUsers(){
        List<User> unapprovedUsers = new ArrayList<>();
        unapprovedUsers.addAll(sellerService.findAll());
        unapprovedUsers.addAll(buyerService.getAllBuyer());
        return unapprovedUsers;
    }
}
