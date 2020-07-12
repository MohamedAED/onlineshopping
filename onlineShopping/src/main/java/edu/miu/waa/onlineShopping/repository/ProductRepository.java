package edu.miu.waa.onlineShopping.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.miu.waa.onlineShopping.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query(value = "SELECT p FROM Product p WHERE p.seller.userId = :seller_id")
	public Set<Product> findBySellerId(Long seller_id);
	
	@Query(value = "SELECT p FROM Product p WHERE p.productCategory.id = :category_id")
	public Set<Product> findByCategoryId(Long category_id);

}
