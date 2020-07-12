package edu.miu.waa.onlineShopping.service;


import edu.miu.waa.onlineShopping.domain.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerByID(Long sellerId);
    Seller findUserByUsername(String username);
    List<Seller> findUnapprovedSellers();
    Seller saveUser(Seller seller) ;
    void approveSeller(Long id);

}

