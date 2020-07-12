package edu.miu.waa.onlineShopping.service;

import java.util.Set;

import edu.miu.waa.onlineShopping.domain.Product;

public interface ProductService {
	
	public Product save(Product product);
	
	public void deleteById(Long product_id);
	
	public Product findById(Long product_id);
	
	public Set<Product> findProductsByCategoryId(Long category_id);
	
	public Set<Product> findProductsBySellerId(Long seller_id);

}
