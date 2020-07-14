package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Seller;

import java.util.List;

public interface SellerService {
    Seller findUserByUsername(String username);
    Seller getSellerByID(Long sellerId);
    List<Seller> findAll();
    Seller saveUser(Seller seller);
    void approveSeller(Long id, String statusId);
    void deleteById(Long seller_id);
    Seller findById(Long seller_id);
}