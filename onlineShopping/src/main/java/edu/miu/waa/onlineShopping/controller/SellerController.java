package edu.miu.waa.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.service.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class SellerController {

	@Autowired
	ProductService productService;

	@Autowired
	SellerService sellerService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	StorageService storageService;

	@GetMapping(value = "/seller/find")
	public String findSeller(@ModelAttribute("product") Product product, Model model, Principal principal) {
		Seller seller = sellerService.findUserByUsername(principal.getName());
		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());
		model.addAttribute("successMessage", "");
		return "sellerPage";
	}

	@PostMapping(value = "/seller/addProduct")
	public String addProduct(Principal principal,@Valid Product product, BindingResult bindingResult, Model model) {
		Seller seller1 = sellerService.findUserByUsername(principal.getName());
		if(!bindingResult.hasErrors()){
			MultipartFile employeeImage = product.getImage();
			product.setSeller(seller1);
			seller1.getProducts().add(product);
			seller1 = sellerService.saveUser(seller1);
			model.addAttribute("successMessage", "product saved successfully");
			if (employeeImage != null && !employeeImage.isEmpty()) {
				try {
					storageService.saveImage(employeeImage, product.getProductNumber());

				} catch (Exception e) {
					throw new RuntimeException("Product Image saving failed", e);
				}
			}
		}
		model.addAttribute("product", new Product());
		model.addAttribute("seller", seller1);
		return "sellerPage";
	}

	@GetMapping(value = "/seller/updateProductPage")
	public String updateProductPage(@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {

		Product product = productService.findById(product_id);
		
		model.addAttribute("product", product);
		model.addAttribute("seller_id", seller_id);
		
		return "EditProductPage";
	}

	@PostMapping(value = "/seller/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product,
			@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {

		Seller seller = sellerService.findById(seller_id);
		Product old_product = seller.getProducts().stream().filter(p -> p.getId() == product_id).findFirst().get();

		old_product.setName(product.getName());
		old_product.setDescription(product.getDescription());
		old_product.setPrice(product.getPrice());
		old_product.setStockQuantity(product.getStockQuantity());

		sellerService.saveUser(seller);
		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());

		return "SellerPage";
	}

	@GetMapping(value = "/seller/deleteProduct")
	public String deleteProduct(@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {

		Seller seller = sellerService.findById(seller_id);
		seller.getProducts().removeIf(p -> p.getId() == product_id);

		sellerService.saveUser(seller);
		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());

		return "SellerPage";
	}
	
	
	@GetMapping(value = "/seller/reviewOrder")
	public String reviewOrder(@RequestParam(value = "order_id") Long order_id,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {

		PlaceOrder order = orderService.findById(order_id);
		
		model.addAttribute("order", order);
		model.addAttribute("order_id", order.getId());
		model.addAttribute("seller_id", seller_id);
		
		return "ReviewSellerOrders";
	}

	@PostMapping("/seller/updateOrderStatus")
	public String updateOrderStatus(
			@ModelAttribute("order") PlaceOrder order, 
			@RequestParam(value = "order_id") Long order_id,
			@RequestParam(value = "seller_id") Long seller_id,
			Model model) {
		
		Seller seller = sellerService.findById(seller_id);
		PlaceOrder old_order = seller.getOrders().stream().filter(p -> p.getId() == order_id).findFirst().get();

		old_order.setStatus(order.getStatus());

		sellerService.saveUser(seller);

		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());
		return "SellerPage";
	}

}
