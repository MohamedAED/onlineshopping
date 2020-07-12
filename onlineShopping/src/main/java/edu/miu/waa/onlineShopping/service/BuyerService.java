package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repositry.BuyerRepository;
import edu.miu.waa.onlineShopping.repositry.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BuyerService(BuyerRepository buyerRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.buyerRepository = buyerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Buyer findUserByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }

    public List<Buyer> findUnapprovedBuyers(){
        return buyerRepository.findAllUnApprovedUsers();
    }
    public void approveBuyer(Long id){
        Buyer oldUser = buyerRepository.findUserById(id);
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
}
