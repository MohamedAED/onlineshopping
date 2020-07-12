package edu.miu.waa.onlineShopping.serviceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.CartItem;
import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.repository.PlaceOrderRepository;
import edu.miu.waa.onlineShopping.service.PlaceOrderService;
import edu.miu.waa.onlineShopping.service.ShoppingCartService;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

	private Map<Seller, Set<CartItem>> sellerProducts = new HashMap<Seller, Set<CartItem>>();
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private PlaceOrderRepository placeOrderRepository;
	
	@Override
	public PlaceOrder create(PlaceOrder placeOrder) {
		return placeOrderRepository.save(placeOrder);
	}

	@Override
	public Optional<PlaceOrder> read(Long placeOrderId) {
		return placeOrderRepository.findById(placeOrderId);
	}

	@Override
	public void update(PlaceOrder placeOrder) {
		placeOrderRepository.save(placeOrder);
	}

	@Override
	public void delete(Long placeOrderId) {
		placeOrderRepository.deleteById(placeOrderId);
	}

	@Override
	public PlaceOrder placeOrders(ShoppingCart shoppingCart) {
		
		Set<CartItem> cartItems;
		PlaceOrder placeOrder;
		
		for(CartItem item : shoppingCart.getItems().values()) {
			if(sellerProducts.containsKey(item.getProduct().getSeller())) {
				cartItems = sellerProducts.get(item.getProduct().getSeller());
			}
			else {
				cartItems = new HashSet<CartItem>();
			}
			cartItems.add(item);
			sellerProducts.put(item.getProduct().getSeller(), cartItems);
		}
		
		for(Set<CartItem> sellerCartItems : sellerProducts.values()) {
			placeOrder = new PlaceOrder(shoppingCart.getTotalPrice(), sellerCartItems);
			create(placeOrder);
		}
		
		shoppingCart.setItems(null);
		shoppingCart.setTotalPrice(new BigDecimal(0.00));
		shoppingCartService.update(shoppingCart);
		
		return null;
	}

}
