package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.repository.ProductRepository;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;


    @Override
    public Seller getSellerByID(Long sellerId) {
        Iterable<Seller> iterable = sellerRepository.findAll();
        Seller sellerObj = new Seller() ;
        for (Seller seller : iterable) {
           if(seller.getUserId() == sellerId)
           {
               sellerObj = seller;
                break;
           }
        }
        return sellerObj;
    }
}
