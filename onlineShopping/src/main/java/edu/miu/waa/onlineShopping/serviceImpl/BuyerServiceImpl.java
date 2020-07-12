package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repository.BuyerRepository;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public BuyerServiceImpl(BuyerRepository buyerRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.buyerRepository = buyerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Buyer findUserById(Long id) {
        return buyerRepository.findById(id).get();
    }

    public Buyer findUserByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }

    public List<Buyer> findUnapprovedBuyers(){
        return buyerRepository.findAllUnApprovedUsers();
    }
    public void approveBuyer(Long id){
        Buyer oldUser = buyerRepository.findById(id).get();
        if(oldUser == null){
            try {
                throw new SQLException("error while approving the user");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        oldUser.setApproved(UserStatus.APPROVED);
        buyerRepository.save(oldUser);
    }

    public Buyer saveUser(Buyer buyer) {
        buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
        return buyerRepository.save(buyer);
    }
    @Override
    public List<Buyer> getAllBuyer() {
        Iterable<Buyer> iterable = buyerRepository.findAll();
        List<Buyer> buyerList = new ArrayList<>();
        for (Buyer p : iterable) {
            buyerList.add(p);
        }
        return buyerList;
    }

    public Set<Seller> getFollowing(Long buyerId) {
        Iterable<Buyer> iterable = buyerRepository.findAll();
        Set<Seller> sellerSet = new HashSet<>();
        for (Buyer buyer : iterable) {
            if (buyer.getUserId() == buyerId) {
                sellerSet = buyer.getFollowingSellers();
                break;
            }
        }
        return sellerSet;
    }

    /*
     *
     * */
    public boolean IsFollowing(Long buyerId, Long sellerId) {
        Set<Seller> sellerSet = getFollowing(buyerId);
        for(Seller seller : sellerSet){
            if(seller.getUserId() == sellerId)
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public void followSeller(Long buyerId, Long sellerId) {
        Buyer buyer = buyerRepository.findById(buyerId).get();
        Seller seller = sellerRepository.findById(sellerId).get();
        buyer.getFollowingSellers().add(seller);
        buyerRepository.save(buyer);
    }

    @Override
    public void unfollowSeller(Long buyerId, Long sellerId) {
        Buyer buyer = buyerRepository.findById(buyerId).get();
        Seller seller = sellerRepository.findById(sellerId).get();
        buyer.getFollowingSellers().remove(seller);
        buyerRepository.save(buyer);
    }


}
