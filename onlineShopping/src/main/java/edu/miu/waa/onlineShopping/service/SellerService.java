package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Seller;

public interface SellerService {
    Seller getSellerByID(Long sellerId);
}
