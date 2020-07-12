package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repository.ProductRepository;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public SellerServiceImpl(SellerRepository sellerRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sellerRepository = sellerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Seller findUserByUsername(String username) {
        return sellerRepository.findByUsername(username);
    }

    public List<Seller> findUnapprovedSellers(){
        return sellerRepository.findAllUnApprovedUsers();
    }
    public void approveSeller(Long id){
        Seller oldUser = sellerRepository.findUserById(id);
        if(oldUser == null){
            try {
                throw new SQLException("error while approving the user");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        oldUser.setApproved(UserStatus.APPROVED);
        sellerRepository.save(oldUser);
    }

    public Seller saveUser(Seller seller) {
        seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
        return sellerRepository.save(seller);
    }
}
