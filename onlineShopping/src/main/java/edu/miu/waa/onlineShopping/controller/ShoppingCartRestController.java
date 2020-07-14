package edu.miu.waa.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.CartItem;
import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.exceptionHandler.ProductNotFoundException;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.ProductService;
import edu.miu.waa.onlineShopping.service.ShoppingCartService;

@Controller
@RequestMapping(value = "rest/shoppingCart")
public class ShoppingCartRestController {

	@Autowired
	BuyerService buyerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
    private ShoppingCartService shoppingCartService;
	
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable Long productId, @RequestParam("buyerId") Long buyerId) {
		
		Buyer buyer = buyerService.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();

        Product product = productService.getProductById(productId);
        System.out.println("product: " + product);
        
        if (product == null) {
            throw new IllegalArgumentException(new ProductNotFoundException(productId));
        }
        shoppingCart.addCartItem(new CartItem(product));
        shoppingCartService.update(shoppingCart);
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable Long productId, @RequestParam("buyerId") Long buyerId) {
    	
    	Buyer buyer = buyerService.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();

        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException(new ProductNotFoundException(productId));
        }
        shoppingCart.removeCartItem(new CartItem(product));
        shoppingCartService.update(shoppingCart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
    public void handleServerErrors(Exception ex) {
    }
	
}
