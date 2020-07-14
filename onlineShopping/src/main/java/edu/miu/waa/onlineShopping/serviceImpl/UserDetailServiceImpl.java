package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.User;
import edu.miu.waa.onlineShopping.repository.AdminRepository;
import edu.miu.waa.onlineShopping.repository.BuyerRepository;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	System.out.println("Find by username: "+username);
    	Admin currentAdmin = adminRepository.findByUsername(username);
		Buyer currentBuyer = buyerRepository.findByUsername(username);
		Seller currentSeller = sellerRepository.findByUsername(username);
    	System.out.println("found it");
		UserDetails user;
    	if(currentAdmin != null){
			user = new org.springframework.security.core.userdetails.User(username, currentAdmin.getPassword()
					,AuthorityUtils.createAuthorityList(currentAdmin.getRole().toString()));
			System.out.println(user.getUsername() + " " + "  " + user.getPassword());
			System.out.println("match it admin");
		}
    	else if(currentBuyer != null){
			user = new org.springframework.security.core.userdetails.User(username, currentBuyer.getPassword()
					,AuthorityUtils.createAuthorityList(currentBuyer.getRole().toString()));
			System.out.println(user.getUsername() + " " + "  " + user.getPassword());
			System.out.println("match it admin");
		}
        else{
			user = new org.springframework.security.core.userdetails.User(username, currentSeller.getPassword()
					,AuthorityUtils.createAuthorityList(currentSeller.getRole().toString()));
			System.out.println(user.getUsername() + " " + "  " + user.getPassword());
			System.out.println("match it admin");
		}
        return user;
    } 
}