package edu.miu.waa.onlineShopping.service;

import edu.miu.waa.onlineShopping.domain.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart create(ShoppingCart shoppingCart);

	ShoppingCart read(Long shoppingCartId);

	void update(ShoppingCart shoppingCart);

	void delete(Long shoppingCartId);

}
