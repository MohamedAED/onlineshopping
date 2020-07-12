package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.ProductCategory;

import java.util.List;

public interface BuyerService {
    List<Buyer> getAllBuyer();
    List<Buyer> getAllBuyerForProduct(Long productId);
}
