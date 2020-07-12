package edu.miu.waa.onlineShopping.repository;

import org.springframework.data.repository.CrudRepository;

import edu.miu.waa.onlineShopping.domain.PlaceOrder;


public interface OrderRepository  extends CrudRepository<PlaceOrder, Long>{

}
