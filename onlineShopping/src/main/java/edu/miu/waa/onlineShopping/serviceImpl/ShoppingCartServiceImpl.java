package edu.miu.waa.onlineShopping.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.repository.ShoppingCartRepository;
import edu.miu.waa.onlineShopping.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart create(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public Optional<ShoppingCart> read(Long shoppingCartId) {
		return shoppingCartRepository.findById(shoppingCartId);
	}

	@Override
	public void update(ShoppingCart shoppingCart) {
		shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public void delete(Long shoppingCartId) {
		shoppingCartRepository.deleteById(shoppingCartId);
	}

}
