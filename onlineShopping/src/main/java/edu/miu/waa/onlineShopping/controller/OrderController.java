package edu.miu.waa.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.miu.waa.onlineShopping.domain.BillingAddress;
import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.CardPayment;
import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.ShippingAddress;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.PlaceOrderService;
import edu.miu.waa.onlineShopping.service.ShoppingCartService;

@Controller
public class OrderController {

	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private PlaceOrderService placeOrderService;
	
	@Autowired
    private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/order")
	public String getCart(@RequestParam("buyerId") Long buyerId, Model model) {
		
		Buyer buyer = buyerService.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();
		ShippingAddress shippingAddress = buyer.getShippingAddress();
		BillingAddress billingAddress = buyer.getBillingAddress();
		CardPayment cardPayment = buyer.getCardPayment();
		
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("cartItems", shoppingCart.getItems());
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cardPayment", cardPayment);
		
		return "PlaceOrderForm";
	}
	
	@RequestMapping("/placeOrder")
	public String placeOrder(@RequestParam("buyerId") Long buyerId, Model model) {
		
		Buyer buyer = buyerService.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();
		
		PlaceOrder placeOrder = placeOrderService.placeOrders(shoppingCart);
		
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("buyerName", buyer.getFirstName() + " " + buyer.getLastName());
		model.addAttribute("placeOrder", placeOrder);
		model.addAttribute("orderItems", placeOrder.getItems());
		
		return "OrderConfirmation";
	}
	
}
