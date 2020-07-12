package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query(value = "SELECT p FROM Product p WHERE p.seller.userId = :seller_id")
	public Set<Product> findBySellerId(Long seller_id);
	
	@Query(value = "SELECT p FROM Product p WHERE p.productCategory.id = :category_id")
	public Set<Product> findByCategoryId(Long category_id);


}
