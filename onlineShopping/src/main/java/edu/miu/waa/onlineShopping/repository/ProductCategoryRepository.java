package edu.miu.waa.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.waa.onlineShopping.domain.ProductCategory;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
