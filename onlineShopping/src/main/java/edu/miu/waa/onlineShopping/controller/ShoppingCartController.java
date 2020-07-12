package edu.miu.waa.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.service.BuyerService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private BuyerService buyerService;
	
	@RequestMapping
	public String getCart(@RequestParam("buyerId") Long buyerId, Model model) {
		
		Buyer buyer = buyerService.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();
			
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("cartItems", shoppingCart.getItems());
		model.addAttribute("buyerId", buyerId);
		return "ShoppingCart";
	}
	
}
