package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.Seller;

public interface SellerService {

	public Seller save(Seller seller);

	public void deleteById(Long seller_id);

	public Seller findById(Long seller_id);

}
