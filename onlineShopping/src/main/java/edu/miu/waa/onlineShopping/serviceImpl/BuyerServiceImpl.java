package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.repository.BuyerRepository;
import edu.miu.waa.onlineShopping.repository.ProductCategoryRepository;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    BuyerRepository buyerRepository;

    @Override
    public List<Buyer> getAllBuyer() {
        Iterable<Buyer> iterable = buyerRepository.findAll();
        List<Buyer> buyerList = new ArrayList<>();
       for (Buyer p : iterable) {
            buyerList.add(p);
        }
        return buyerList;
    }

    /*

    * */
    public Set<Seller> getFollowing(Long buyerId)
    {
        Iterable<Buyer> iterable = buyerRepository.findAll();
        Set<Seller> buyerList = new HashSet<>();
        for (Buyer buyer : iterable) {
           if(buyer.getUserId() == buyerId)
           {
               buyerList = buyer.getFollowingSellers();
               break;
           }
        }
        return buyerList;
    }

    /*
    *
    * */
    public boolean IsFollowing(Long buyerId,Long sellerId)
    {
        return getFollowing(buyerId).contains(sellerId);
    }
}
