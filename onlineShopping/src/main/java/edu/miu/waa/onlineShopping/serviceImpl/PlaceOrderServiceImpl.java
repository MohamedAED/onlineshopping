package edu.miu.waa.onlineShopping.serviceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.CartItem;
import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.repository.PlaceOrderRepository;
import edu.miu.waa.onlineShopping.service.AdminService;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.EmailSenderService;
import edu.miu.waa.onlineShopping.service.PlaceOrderService;
import edu.miu.waa.onlineShopping.service.SellerService;
import edu.miu.waa.onlineShopping.service.ShoppingCartService;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

	private Map<Seller, Set<CartItem>> sellerProducts = new HashMap<Seller, Set<CartItem>>();
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	PlaceOrderRepository placeOrderRepository;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	AdminService adminService;
	
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
	public Set<PlaceOrder> placeOrders(ShoppingCart shoppingCart, Buyer buyer, String paymentType) {
		
		Set<PlaceOrder> placedOrders = new HashSet<PlaceOrder>();
		Set<CartItem> cartItems;
		PlaceOrder placeOrder;
		BigDecimal cartItemsTotalPrice;
		Seller seller = new Seller();
		String emailMessage = "Thank you for purchasing with SOKS (Group 7 - WAA Project), This is a confirmation mail for your order/s ";
		
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
			cartItemsTotalPrice = new BigDecimal(0);
			for(CartItem cartItem : sellerCartItems) {
				seller = cartItem.getProduct().getSeller();
				cartItemsTotalPrice = cartItemsTotalPrice.add(cartItem.evaluateTotalPrice());
			}
			int randomValue = (int) (1000 + (Math.random() * 9999));
			String orderNumber = "ORDER # -111-" + randomValue;
			placeOrder = new PlaceOrder(orderNumber, cartItemsTotalPrice, sellerCartItems, seller, buyer.getShippingAddress(), buyer.getBillingAddress());
			emailMessage = emailMessage + orderNumber + ", ";
			create(placeOrder);
			placedOrders.add(placeOrder);
			seller.getOrders().add(placeOrder);
			sellerService.save(seller);
		}
		
		int requiredPoints = shoppingCart.getTotalPrice().divideToIntegralValue(new BigDecimal(2)).intValue();
		buyer.getOrders().addAll(placedOrders);
		if(paymentType.equals("creditCard")) {
			buyer.gainPoints(shoppingCart.getTotalPrice());
		}
		else {
			buyer.setPoints(buyer.getPoints() - requiredPoints);
		}
			
		buyerService.saveUser(buyer);
		
		shoppingCart.setItems(null);
		shoppingCart.setTotalPrice(new BigDecimal(0.00));
		shoppingCartService.update(shoppingCart);
		
		//sendConfirmationMail(buyer, emailMessage) ;
		
		return placedOrders;
	}
	
	private void sendConfirmationMail(Buyer buyer, String emailMessage) {

		// sending confirmation email
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(buyer.getEmail());
		mailMessage.setSubject(adminService.getConfirmationSubject());
		mailMessage.setText(emailMessage);

		emailSenderService.sendEmail(mailMessage);

	}

}
