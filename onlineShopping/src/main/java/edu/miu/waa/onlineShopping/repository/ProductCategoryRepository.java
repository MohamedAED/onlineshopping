package edu.miu.waa.onlineShopping.repository;

import edu.miu.waa.onlineShopping.domain.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ProductCategoryRepository extends CrudRepository<ProductCategory, Long>{

}
