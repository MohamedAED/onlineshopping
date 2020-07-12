package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.ProductCategory;
import edu.miu.waa.onlineShopping.domain.Seller;

import java.util.List;
import java.util.Set;

public interface BuyerService {
    List<Buyer> getAllBuyer();
    Set<Seller> getFollowing(Long buyerId);
    boolean IsFollowing(Long buyerId,Long sellerId);
}
