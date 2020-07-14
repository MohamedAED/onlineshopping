package edu.miu.waa.onlineShopping.config;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.*;
import edu.miu.waa.onlineShopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import javax.servlet.ServletContext;

@Component
public class OnApplicationStartUp {

	@Autowired
	private AdminService adminService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	ServletContext servletContext;

	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) throws Exception {
		createAdminUser();

		createFolder();

		CreateInitDataBaseInfo();

		createBuyer("buyer1",UserStatus.APPROVED);
		createBuyer("buyer2",UserStatus.PENDING);
		createBuyer("buyer3",UserStatus.APPROVED);

//		createSeller("seller",UserStatus.APPROVED);
/*		createSeller("seller3",UserStatus.APPROVED);
		createSeller("seller2",UserStatus.PENDING);*/

	}

	private void createFolder() {
		File file = new File(servletContext.getRealPath("/") + "/products");
		file.mkdir();

		File fileInv = new File(servletContext.getRealPath("/") + "/invoices");
		fileInv.mkdir();

	}

	private void createAdminUser() throws ParseException, IOException {
		Admin user = new Admin();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("admin");
		user.setPassword("admin");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.ADMIN);
		adminService.saveUser(user);
	}




	private void createBuyer(String username, UserStatus userStatus) throws ParseException, IOException {
		Buyer user = new Buyer();
		user.setEmail("Mohamedsaleh1984@hotmail.com");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setRole(Role.BUYER);
		user.setShoppingCart(new ShoppingCart());
		user.setFirstName(username);
		user.setLastName(username);
		user.setUsername(username);
		user.setPassword(username);
		user.setApproved(userStatus);
		user.setRole(Role.BUYER);
		user.setShoppingCart(new ShoppingCart());

		CardPayment cardPayment = new CardPayment();
		cardPayment.setCardNumber("1234567812345678");
		cardPayment.setExpiryDate("09/24");
		cardPayment.setCvv("123");
		cardPayment.setNameOnCard(username);
		user.setCardPayment(cardPayment);
		ShippingAddress address = new ShippingAddress();
		address.setCountry("usa");
		address.setState("iowa");
		address.setCity("fairfield");
		address.setStreet("1000 north");
		address.setZipCode("52557");
		user.setShippingAddress(address);

		BillingAddress billingAddress = new BillingAddress();
		billingAddress.setCountry("usa");
		billingAddress.setState("iowa");
		billingAddress.setCity("fairfield");
		billingAddress.setStreet("1000 north");
		billingAddress.setZipCode("52557");
		user.setBillingAddress(billingAddress);
		buyerService.saveUser(user);

	}

	private void CreateInitDataBaseInfo() throws ParseException {
		/******************** Product Category ************************/
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName("Clothing");
		productCategoryService.save(productCategory);

		productCategory = new ProductCategory();
		productCategory.setName("Books");
		productCategoryService.save(productCategory);

		productCategory = new ProductCategory();
		productCategory.setName("Movies,Music");
		productCategoryService.save(productCategory);

		productCategory = new ProductCategory();
		productCategory.setName("Computers");
		productCategoryService.save(productCategory);

		productCategory = new ProductCategory();
		productCategory.setName("Smart Home");
		productCategoryService.save(productCategory);
		/******************** Seller ************************/
		Seller user = new Seller();
		user.setFirstName("Mohmed");
		user.setLastName("Zezo");
		user.setEmail("seller@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("seller");
		user.setPassword("seller");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.SELLER);
		user.setProducts(new HashSet<>());
		/***********************Add Associate Products **********************/
		Product product = new Product();
		product.setDescription("Shoes desc");
		product.setName("shirt");
		product.setPrice(new BigDecimal(100));
		product.setSeller(user);
		product.setStockQuantity((long) 15);
		product.setProductCategory(productCategoryService.getProductCategoryByName("Clothing"));
		product.setProductNumber(1L);

		Product product2 = new Product();
		product2.setDescription("Dell Laptop 142L");
		product2.setName("Dell Laptop 142L");
		product2.setPrice(new BigDecimal(800));
		product2.setStockQuantity((long) 7);
		product2.setSeller(user);
		product2.setProductCategory(productCategoryService.getProductCategoryByName("Computers"));
		product2.setProductNumber(2L);

		Product product3 = new Product();
		product3.setDescription("Men Shirt");
		product3.setName("Men Shirt");
		product3.setPrice(new BigDecimal(15));
		product3.setStockQuantity((long) 10);
		product3.setSeller(user);
		product3.setProductCategory(productCategoryService.getProductCategoryByName("Clothing"));
		product3.setProductNumber(3L);

		Product product4 = new Product();
		product4.setDescription("C++ for Beginners");
		product4.setName("C++");
		product4.setPrice(new BigDecimal(100));
		product4.setStockQuantity((long) 17);
		product4.setSeller(user);
		product4.setProductCategory(productCategoryService.getProductCategoryByName("Books"));
		product4.setProductNumber(4L);


		user.getProducts().add(product);
		user.getProducts().add(product2);
		user.getProducts().add(product3);
		user.getProducts().add(product4);

		sellerService.saveUser(user);
		/**************************************************************************************************/
		/*********************************** Seller 2 *****************************************************/
		Seller sellerAccount = new Seller();
		sellerAccount.setFirstName("Mohmed");
		sellerAccount.setLastName("Zezo");
		sellerAccount.setEmail("seller@miu.edu");
		sellerAccount.setPhoneNumber("6418192921");
		sellerAccount.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		sellerAccount.setUsername("seller");
		sellerAccount.setPassword("seller");
		sellerAccount.setApproved(UserStatus.APPROVED);
		sellerAccount.setRole(Role.SELLER);
		sellerAccount.setProducts(new HashSet<>());
		/***********************Add Associate Products **********************/
		Product amrCD = new Product();
		amrCD.setDescription("Amr Diab CD");
		amrCD.setName("Amr Diab CD");
		amrCD.setPrice(new BigDecimal(100));
		amrCD.setSeller(sellerAccount);
		amrCD.setStockQuantity((long) 15);
		amrCD.setProductCategory(productCategoryService.getProductCategoryByName("Movies,Music"));
		amrCD.setProductNumber(5L);

		Product titanic = new Product();
		titanic.setDescription("Dell Laptop 142L");
		titanic.setName("Dell Laptop 142L");
		titanic.setPrice(new BigDecimal(800));
		titanic.setStockQuantity((long) 7);
		titanic.setSeller(sellerAccount);
		titanic.setProductCategory(productCategoryService.getProductCategoryByName("Movies,Music"));
		titanic.setProductNumber(6L);


		sellerAccount.getProducts().add(amrCD);
		sellerAccount.getProducts().add(titanic);


		sellerService.saveUser(sellerAccount);

	}
}
