package edu.miu.waa.onlineShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.waa.onlineShopping.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
