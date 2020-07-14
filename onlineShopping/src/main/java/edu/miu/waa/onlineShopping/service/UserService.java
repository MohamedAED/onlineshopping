package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.User;

import java.util.List;

public interface UserService {
    User getUserByName(String username);
    List<User> getUnapprovedUsers();

}
