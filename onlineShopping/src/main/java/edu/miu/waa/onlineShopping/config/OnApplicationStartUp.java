package edu.miu.waa.onlineShopping.config;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.ReviewStatus;
import edu.miu.waa.onlineShopping.domain.enums.Role;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class OnApplicationStartUp {

	@Autowired
	private AdminService adminService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SellerService sellerService;

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
		createSeller("seller",UserStatus.APPROVED);
		createBuyer("buyer1",UserStatus.APPROVED);
		createSeller("seller2",UserStatus.PENDING);
		createBuyer("buyer2",UserStatus.PENDING);
		createSeller("seller3",UserStatus.APPROVED);
		createBuyer("buyer3",UserStatus.APPROVED);
		createProductCategories();
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

	public void createProductCategories(){
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
	}

	private void createSeller(String username, UserStatus userStatus) throws ParseException, IOException {
		Seller user = new Seller();
		user.setFirstName(username);
		user.setLastName(username);
		user.setEmail("seller@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername(username);
		user.setPassword(username);
		user.setApproved(userStatus);
		user.setRole(Role.SELLER);
		sellerService.saveUser(user);

		Product product = new Product();
		product.setDescription("Shoes desc");
		product.setName("shirt");
		product.setPrice(new BigDecimal(100));
		product.setSeller(user);
		product.setStockQuantity((long) 15);
		product.setProductCategory(productCategoryService.getProductCategoryByName("Clothing"));
		productService.save(product);

		Product product2 = new Product();
		product2.setDescription("WAA desc2");
		product2.setName("WAA");
		product2.setPrice(new BigDecimal(100));
		product2.setStockQuantity((long) 17);
		product2.setSeller(user);
		product2.setProductCategory(productCategoryService.getProductCategoryByName("Books"));
		productService.save(product2);
	}

	private void createBuyer(String username, UserStatus userStatus) throws ParseException, IOException {
		Buyer user = new Buyer();
		user.setFirstName(username);
		user.setLastName(username);
		user.setEmail("buyer4@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
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

		Review review = new Review();
		review.setDescription("good");
		review.setBuyer(user);
		review.setReviewStatus(ReviewStatus.PENDING);
		reviewService.save(review);
	}
}
