package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.service.AdminService;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.SellerService;
import edu.miu.waa.onlineShopping.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    AdminService adminService;
    SellerService sellerService;
    BuyerService buyerService;

    public User getUserByName(String username){
        User user;
        user = sellerService.findUserByUsername(username);
        if(user == null)
        {
            user = adminService.findUserByUsername(username);
        }
        if(user == null)
            user = buyerService.findUserByUsername(username);

        return user;
    }
}
