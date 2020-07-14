package edu.miu.waa.onlineShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.miu.waa.onlineShopping.domain.PlaceOrder;
import edu.miu.waa.onlineShopping.domain.Product;
import edu.miu.waa.onlineShopping.domain.Seller;
import edu.miu.waa.onlineShopping.service.OrderService;
import edu.miu.waa.onlineShopping.service.ProductService;
import edu.miu.waa.onlineShopping.service.SellerService;
import edu.miu.waa.onlineShopping.service.StorageService;

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

	/*
	 * save seller find seller
	 */
	
//	@ModelAttribute("orderStatus")
//	public void addOrderStatus(Model model) {
////		List<String> orderStatus = Stream.of(OrderStatus.values())
////                .map(OrderStatus::name)
////                .collect(Collectors.toList());
//		
//		List<OrderStatus> orderStatus = Arrays.asList(OrderStatus.values());
//		model.addAttribute("orderStatus", orderStatus);
//	}

	@GetMapping(value = "/seller/find")
	public String findSeller(@RequestParam(value = "seller_id") Long seller_id,
			@ModelAttribute("product") Product product, Model model) {

		Seller seller = sellerService.findById(seller_id);

		model.addAttribute("seller", seller);

		model.addAttribute("product", new Product());
		return "sellerPage";

//		return seller;
	}

	@PostMapping(value = "/seller/addProduct")
	public String addProduct(@ModelAttribute("product") Product product,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {
		
		
		MultipartFile employeeImage = product.getImage();
		
		Seller seller = sellerService.findById(seller_id);

		product.setSeller(seller);
		seller.getProducts().add(product);

		seller = sellerService.save(seller);
		

		if (employeeImage != null && !employeeImage.isEmpty()) {
			try {
				storageService.saveImage(employeeImage, product.getProductNumber());
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		model.addAttribute("seller", seller);
		return "sellerPage";

//		return seller;
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

		sellerService.save(seller);

		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());
		return "SellerPage";

//		return seller;
	}

	@GetMapping(value = "/seller/deleteProduct")
	public String deleteProduct(@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "seller_id") Long seller_id, Model model) {

		Seller seller = sellerService.findById(seller_id);
		seller.getProducts().removeIf(p -> p.getId() == product_id);

		sellerService.save(seller);

		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());
		return "SellerPage";

//		return seller;

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

		sellerService.save(seller);

		model.addAttribute("seller", seller);
		model.addAttribute("product", new Product());
		return "SellerPage";
	}

}
