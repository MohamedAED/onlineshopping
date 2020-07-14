package edu.miu.waa.onlineShopping.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.miu.waa.onlineShopping.domain.Buyer;
import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.PreOrderInfo;
import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Review;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.PlaceOrderService;
import edu.miu.waa.onlineShopping.service.ProductService;
import edu.miu.waa.onlineShopping.service.ReportManagerService;
import net.sf.jasperreports.engine.JRException;

@Controller
public class OrderController {

	@Autowired
	BuyerService buyerervice;
	
	@Autowired
	private PlaceOrderService placeOrderService;
	
	@Autowired
	ReportManagerService reportManagerService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/order")
	public String getOrderInfo(PreOrderInfo preOrderInfo, @RequestParam("buyerId") Long buyerId, Model model, RedirectAttributes redirectAttributes) {
		
		Buyer buyer = buyerervice.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();
		
		String paymentType = "creditCard";
		preOrderInfo = new PreOrderInfo(buyer.getShippingAddress(), buyer.getBillingAddress(), buyer.getCardPayment(), buyer.getShoppingCart(), buyer, paymentType);
		redirectAttributes.addFlashAttribute("preOrderInfo", preOrderInfo);
		redirectAttributes.addFlashAttribute("buyerId", buyerId);
		
		Integer allowPointsPayment = 0;
		int requiredPoints = shoppingCart.getTotalPrice().divideToIntegralValue(new BigDecimal(2)).intValue();
		if(requiredPoints <= buyer.getPoints()) {
			allowPointsPayment = 1;
		}
		
		//model.addAttribute("paymentType", paymentType);
		redirectAttributes.addFlashAttribute("requiredPoints", requiredPoints);
		redirectAttributes.addFlashAttribute("allowPointsPayment", allowPointsPayment);
		redirectAttributes.addFlashAttribute("buyerId", buyerId);
		
		return "redirect:/displayOrder";
	}
	
	@RequestMapping(value = "/displayOrder")
	public String getOrderPage() {
		return "PlaceOrderForm";
	}
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public String placeOrder(@ModelAttribute("preOrderInfo") PreOrderInfo preOrderInfo, @RequestParam("buyerId") Long buyerId, Model model) {
		
		Buyer buyer = buyerervice.findUserById(buyerId);
		ShoppingCart shoppingCart = buyer.getShoppingCart();

		Set<PlaceOrder> placedOrders = placeOrderService.placeOrders(shoppingCart, buyer, preOrderInfo.getPaymentType());

		model.addAttribute("buyer", buyer);
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("buyerName", buyer.getName());
		model.addAttribute("placedOrders", placedOrders);
		
		return "BuyerOrders";
	}
	
	@RequestMapping("/buyerOrders")
	public String getBuyerOrder(@RequestParam("buyerId") Long buyerId, Model model) {
		
		Buyer buyer = buyerervice.findUserById(buyerId);
		Set<PlaceOrder> placedOrders = buyer.getOrders();

		model.addAttribute("buyer", buyer);
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("buyerName", buyer.getName());
		model.addAttribute("placedOrders", placedOrders);
		
		return "BuyerOrders";
	}
	
	@RequestMapping(value = "/order/generateInvoice/{orderId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String downloadInvoice(@PathVariable Long orderId, @RequestParam("buyerId") Long buyerId, Model model) throws FileNotFoundException, JRException {

		String path = reportManagerService.generatePdfInvoice(orderId);
		model.addAttribute("error", true);
		model.addAttribute("errorMessage", "Invoice Succfully Downloaded to  " + path);
		return "forward:/buyerOrders?buyerId=" + buyerId;
	}
	
	@RequestMapping(value = "/order/writeReview/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String writeProductReview(@PathVariable Long productId, @RequestParam("buyerId") Long buyerId,  
			@RequestParam("productReview") String productReview, Model model) throws FileNotFoundException, JRException {
		
		Buyer buyer = buyerervice.findUserById(buyerId);
		Review review = new Review(buyer, productReview);
		Product product = productService.findById(productId);
		product.getReviews().add(review);
		productService.save(product);
		
		return "forward:/buyerOrders?buyerId=" + buyerId;
	}
	
}
