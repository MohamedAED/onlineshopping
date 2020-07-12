package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.repositry.SellerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private SellerRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(SellerRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findUnapprovedUsers(){
        return userRepository.findAllUnApprovedUsers();
    }
    public void approveUser(Long id){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null){
            try {
                throw new SQLException("error while approving the user");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        oldUser.setApproved(true);
        userRepository.save(oldUser);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}