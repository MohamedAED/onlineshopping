package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repositry.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SellerService(SellerRepository sellerRepository,
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