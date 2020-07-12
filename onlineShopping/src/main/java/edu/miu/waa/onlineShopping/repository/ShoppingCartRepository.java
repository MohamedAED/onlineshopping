package edu.miu.waa.onlineShopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.miu.waa.onlineShopping.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
