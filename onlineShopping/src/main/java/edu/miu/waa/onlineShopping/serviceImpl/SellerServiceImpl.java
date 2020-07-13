package edu.miu.waa.onlineShopping.serviceImpl;

import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.service.SellerService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	SellerRepository sellerRepository;

	@Override
	public void deleteById(Long seller_id) {
		sellerRepository.deleteById(seller_id);
	}

	@Override
	public Seller findById(Long seller_id) {
		return sellerRepository.findById(seller_id).get();
	}


	@Override
	public Seller findUserByUsername(String username) {
		return sellerRepository.findByUsername(username);
	}

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

	@Override
	public List<Seller> findUnapprovedSellers(){
		return sellerRepository.findAllUnApprovedUsers();
	}

	@Override

	public Seller saveUser(Seller seller) {
		seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
		return sellerRepository.save(seller);
	}

	@Override
	public void approveSeller(Long id, String statusId){
		Seller oldUser = sellerRepository.findUserById(id);
		if(oldUser == null){
			try {
				throw new SQLException("error while approving the user");
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if(Integer.parseInt(statusId)==2)
			oldUser.setApproved(UserStatus.APPROVED);
		else
			oldUser.setApproved(UserStatus.REJECTED);
		sellerRepository.save(oldUser);
	}
}
