package edu.miu.waa.onlineShopping.service;

import java.util.Optional;
import java.util.Set;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;

public interface PlaceOrderService {

	PlaceOrder create(PlaceOrder placeOrder);

	Optional<PlaceOrder> read(Long placeOrderId);

	void update(PlaceOrder placeOrder);

	void delete(Long placeOrderId);
	
	Set<PlaceOrder> placeOrders(ShoppingCart shoppingCart, Buyer buyer, String paymentType);
	
}
