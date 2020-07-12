package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.*;
import java.util.*;

public interface BuyerService {
    List<Buyer> getAllBuyer();
    Set<Seller> getFollowing(Long buyerId);
    boolean IsFollowing(Long buyerId, Long sellerId);
    void followSeller(Long buyerId, Long sellerId);
    void unfollowSeller(Long buyerId, Long sellerId);
    Buyer findUserByUsername(String username);
    List<Buyer> findUnapprovedBuyers();
    void approveBuyer(Long id);
    Buyer saveUser(Buyer buyer);
    Buyer findUserById(Long id);
}