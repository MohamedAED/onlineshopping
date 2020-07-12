package edu.miu.waa.onlineShopping.service;

import java.util.Optional;

import edu.miu.waa.onlineShopping.domain.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart create(ShoppingCart shoppingCart);

	Optional<ShoppingCart> read(Long shoppingCartId);

	void update(ShoppingCart shoppingCart);

	void delete(Long shoppingCartId);

}
