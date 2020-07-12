package edu.miu.waa.onlineShopping.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.repository.SellerRepository;
import edu.miu.waa.onlineShopping.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	SellerRepository sellerRepository;

	@Override
	public Seller save(Seller seller) {
		return sellerRepository.save(seller);
	}

	@Override
	public void deleteById(Long seller_id) {
		sellerRepository.deleteById(seller_id);
	}

	@Override
	public Seller findById(Long seller_id) {
		return sellerRepository.findById(seller_id).get();
	}

}
